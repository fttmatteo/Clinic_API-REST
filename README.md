## [English version](#English)
## [Descargar colección postman](/clinic/postman/full collection.json)

---
## ESPAÑOL
# Clínic – API REST (Spring Boot)

Clinic es una API REST para la gestión operativa de una clínica, implementada con Spring Boot 3 (Java 17) bajo Arquitectura Hexagonal. Expone casos de uso para pacientes, empleados, citas, historia clínica (incluye signos vitales), órdenes médicas y su ejecución, procedimientos, medicamentos, ayudas diagnósticas e invoices.

La seguridad se maneja con JWT (JJWT, HS256) y autorización por roles (DOCTOR, NURSE, PERSONAL_ADMINISTRATIVE, HUMAN_RESOURCES, INFORMATION_SUPPORT). La persistencia usa JPA/Hibernate con MySQL (se incluye H2 para entornos locales de prueba). El diseño desacopla el dominio de la infraestructura mediante puertos y adaptadores (adapter/in para REST/validación y adapter/out para seguridad/JPA), lo que facilita pruebas y evolución.

---

## 🚀 Stack

* **Lenguaje:** Java 17
* **Framework:** Spring Boot 3.5.x
* **Build:** Maven (wrapper incluido: `mvnw` / `mvnw.cmd`)
* **Persistencia:** Spring Data JPA (MySQL 8 por defecto)
* **Validación:** Spring Validation
* **Seguridad:** JWT (jjwt 0.11.5), Spring Security (BCrypt)
* **Utilidades:** Lombok

---

## 🛠️ Dependencias clave (`pom.xml`)
- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-data-jpa`
- `org.springframework.boot:spring-boot-starter-validation`
- `org.springframework.boot:spring-boot-starter-security`
- `org.springframework.security:spring-security-crypto`
- `io.jsonwebtoken:jjwt-api:0.11.5`, `jjwt-impl:0.11.5`, `jjwt-jackson:0.11.5`
- `org.projectlombok:lombok` (opcional, anotaciones)
- `com.mysql:mysql-connector-j` (runtime)
- `com.h2database:h2` (runtime opcional)
- `org.springframework.boot:spring-boot-starter-test` (test)

---

## 🗂 Estructura
```
clinic/
 ├─ src/main/java/app
 │   ├─ adapter/
 │   │   ├─ in/
 │   │   │   ├─ builder/
 │   │   │   ├─ rest/
 │   │   │   │   ├─ controllers/
 │   │   │   │   └─ request/
 │   │   │   └─ validators/
 │   │   └─ out/
 │   │       ├─ persistence/
 │   │       └─ security/   
 │   ├─ application/
 │   │   ├─ exceptions/
 │   │   └─ usecase/          
 │   ├─ domain/            
 │   │   ├─ model/ 
 │   │   │   ├─ auth/
 │   │   │   └─ enums/
 │   │   ├─ ports/ 
 │   │   └─ services/ 
 │   └─ infrastructure/
 │       ├─ persistence/
 │       │   ├─ entities 
 │       │   ├─ mapper   
 │       │   └─ repository
 │       └─ security/  
 └─ src/main/resources
     ├─ application.properties
     └─ data.sql
```

---

## 🗄️ Base de datos

### Motor y conexión

El proyecto viene configurado para **MySQL 8**. Ajusta `src/main/resources/application.properties` si tu entorno difiere:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinic?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Bogota
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
```

> **Esquema**: crea previamente la base de datos `clinic` en MySQL. Las tablas se crean/actualizan con `ddl-auto=update`.

---

### Semilla de datos

`src/main/resources/data.sql` carga valores por defecto para **medicines**, **procedures** y **diagnostic_aids** si aún no existen.

---

## ▶️ Arranque
```bash
# Desde la carpeta clinic/
mvn spring-boot:run
# o construir el .jar
mvn clean package
java -jar target/clinic-0.0.1-SNAPSHOT.jar
```

---

## 👤 Usuario administrador inicial

Al crear la BD por primera vez, inserta un **administrador** de RRHH directamente en la tabla `employees`:

```sql
INSERT INTO employees (address,birth_date,document,email,full_name,password,phone,role,user_name)
VALUES ('direccion','1999-01-01',1000000001,'correo@dominio.com','nombre','A!123456789',3000000001,'HUMAN_RESOURCES','ADMIN');
```

> **Contraseña y hashing:** el sistema usa **BCrypt**. Si insertas la contraseña en texto plano como arriba, en el **primer login** se detecta y se guarda automáticamente codificada.

---

## 🔐 Autenticación y autorización

> El servicio escucha en **[http://localhost:8080](http://localhost:8080)** (valor por defecto de Spring Boot si no se define `server.port`).

  **POST** `/auth/login` — Iniciar sesión  
  **Body (JSON):**
  ```json
  { "username": "ADMIN", "password": "A!123456789" }
  ```
  **Respuesta (JSON):**
  ```json
  { "token": "eyJhbGciOi..." }
  ```

* Usa el token en `Authorization: Bearer <JWT>` para llamar a los endpoints.

> El **JWT** se firma con una **clave generada en memoria** (`JwtAdapter` usa `Keys.secretKeyFor(HS256)`), por lo que **los tokens se invalidan en cada reinicio**. El token expira en ~30 minutos.

---

## Roles y rutas

  - `/auth/**` → público.
  - `/employees/**` → `HUMAN_RESOURCES`.
  - `/administrative/**` → `PERSONAL_ADMINISTRATIVE`.
  - `/doctor/**` → `DOCTOR`.
  - `/nurse/**` → `NURSE`.
  - `/support/**` → `INFORMATION_SUPPORT`.

---

## 📚 Endpoints

### Gestión de personal (RR. HH.) — `/employees` (rol: HUMAN_RESOURCES)
- **POST** `/employees/doctor` — Crear médico (body: `EmployeeRequest`)
- **POST** `/employees/nurse` — Crear enfermero (body: `EmployeeRequest`)
- **POST** `/employees/administrative` — Crear administrativo (body: `EmployeeRequest`)
- **POST** `/employees/information-support` — Crear personal de apoyo a la información (body: `EmployeeRequest`)
- **DELETE** `/employees/{document}` — Eliminar empleado por documento

**`EmployeeRequest`:**
```json
{ 
  "fullName": "Nombre Apellido",
  "document": 1234567890,
  "birthDate": "01/01/1990",
  "address": "Calle 123",
  "phone": 3001234567,
  "email": "usuario@correo.com",
  "userName": "usuario2025",
  "password": "A!123456789"
}
```

### Administración — `/administrative` (rol: PERSONAL_ADMINISTRATIVE)
- **POST** `/administrative/patients` — Crear paciente (body: `PatientRequest`)
- **POST** `/administrative/appointments` — Crear cita (body: `AppointmentRequest`)
- **GET** `/administrative/appointments/doctor/{doctorDocument}` — Listar citas por doctor
- **GET** `/administrative/appointments/patient/{patientDocument}` — Listar citas por paciente
- **DELETE** `/administrative/appointments/{appointmentId}` — Cancelar cita
- **POST** `/administrative/invoices` — Crear factura (body: `InvoiceRequest`)
- **GET** `/administrative/invoices/patient/{patientDocument}` — Listar facturas por paciente
- **GET** `/administrative/orders/{patientDocument}` — Buscar órdenes por paciente

**`PatientRequest`:**
```json
{
  "fullName": "Juan Pérez",
  "document": 100200300,
  "birthDate": "01/01/1990",
  "gender": "M",
  "address": "Calle 45 #10-20",
  "phone": 3001234567,
  "email": "juan@correo.com",
  "contactFirstName": "Primer Nombre Contacto",
  "contactLastName": "Segundo Nombre Contacto",
  "contactRelation": "Relación Contacto",
  "contactPhone": 3001234567,
  "companyName": "Nombre Compañia De Seguros",
  "policyNumber": 4567,
  "policyStatus": "si",
  "policyExpiry": "21/10/2026"
}
```

**`AppointmentRequest`:**
```json
{
  "patientDocument": 100200300,
  "doctorDocument": 900100200,
  "dateTime": "03/11/2025 09:00"
}
```

**`InvoiceRequest`:**
```json
{
  "patientId": 100200300,
  "doctorDocument": 900100200,
  "orderId": 123456
}
```

### Médico — `/doctor` (rol: DOCTOR)
- **POST** `/doctor/orders` — Crear orden médica (body: `MedicalOrderRequest`)
- **GET** `/doctor/orders/{patientDocument}` — Consultar órdenes del paciente
- **POST** `/doctor/records` — Crear registro clínico (body: `MedicalRecordRequest`)

**`MedicalOrderRequest`:**
```json
{
  "doctorDocument": 900100200,
  "patientId": 100200300,
  "items":[
    { "type": "MEDICINE", "referenceId": "MED-001" },
    { "type": "PROCEDURE", "referenceId": "PROC-001" }
  ]
}
```

**`MedicalRecordRequest`:**
```json
{
  "doctorDocument": 900100200,
  "patientId": 100200300,
  "orderId": 1,
  "motive": "Dolor de cabeza",
  "symptoms": "Cefalea, fiebre",
  "diagnosis": "Migraña"
}
```

### Enfermería — `/nurse` (rol: NURSE)
- **POST** `/nurse/vital-signs` — Registrar signos vitales (body: `VitalSignsRequest`)
- **POST** `/nurse/orders/{orderId}/items/{itemNumber}/execute` — Ejecutar ítem de una orden

**`VitalSignsRequest`:**
```json
{
  "nurseDocument": 700300400,
  "patientId": 100200300,
  "bloodPressure": "120/80",
  "temperature": 36.5,
  "pulse": 75,
  "oxygenLevel": 98
}
```

**`OrderExecutionRequest`:**
```json
{
  "nurseDocument": 700300400,
  "amount": "2.5",
  "notes": "Aplicación sin novedades"
}
```

### Apoyo a la información — `/support` (rol: INFORMATION_SUPPORT)
- **GET** `/support/medicines` — Listar medicamentos
- **POST** `/support/medicines` — Crear medicamento (body: `MedicineRequest`)
- **GET** `/support/procedures` — Listar procedimientos
- **POST** `/support/procedures` — Crear procedimiento (body: `ProcedureRequest`)
- **GET** `/support/diagnostic-aids` — Listar ayudas diagnósticas
- **POST** `/support/diagnostic-aids` — Crear ayuda diagnóstica (body: `DiagnosticAidRequest`)

**`MedicineRequest`:**
```json
{
  "id": "MED-005",
  "name": "Acetaminofén 500 mg",
  "cost": "1200.00",
  "dose": "1 tableta cada 8h",
  "treatmentDuration": "5 dias"
}
```

**`ProcedureRequest`:**
```json
{
  "id": "PROC-001",
  "name": "Radiografía de tórax",
  "cost": "25000.00",
  "quantity": "1",
  "frequency": "ÚNICA",
  "requiresSpecialist": "false"
}
```

**`DiagnosticAidRequest`:**
```json
{
  "id": "DA-001",
  "name": "Hemograma completo",
  "cost": "18000.00",
  "quantity": "1",
  "requiresSpecialist": "false"
}
```

---

## 🧠 Reglas de negocio (resumen)

**Roles y alcance funcional:**

* **HUMAN_RESOURCES:** crear/eliminar usuarios de la app; administrar datos de empleados.
* **PERSONAL_ADMINISTRATIVE:** registrar pacientes, programar citas, gestionar facturación y seguros.
* **INFORMATION_SUPPORT:** mantener inventarios (medicamentos, procedimientos, ayudas), integridad de datos.
* **NURSE:** registrar signos vitales, administrar medicamentos/procedimientos y sus ejecuciones.
* **DOCTOR:** acceso total al paciente, historia clínica, generar diagnósticos, órdenes (medicamento, procedimiento, ayuda diagnóstica).

**Reglas destacadas:**

* **Órdenes**: número de orden único; relación **orden–ítem** única; múltiples ítems por orden (medicamentos/procedimientos/ayudas) numerados desde 1.
* **Exclusividad de ayudas diagnósticas**: si se solicita una ayuda diagnóstica **no** se recetan medicamentos/procedimientos en esa misma atención; tras resultado, se genera un nuevo registro con diagnóstico y posibles recetas.
* **Facturación**: si póliza activa → copago $50.000 y resto a aseguradora; tope anual de copagos $1’000.000 por paciente; sin póliza o inactiva → paga el total.

---

## 📄 Licencia
MIT

---

**Última actualización:** 2025-11-23 18:00

---

## ENGLISH
# Clinic – REST API (Spring Boot)

Clinic is a REST API for the operational management of a clinic, implemented with Spring Boot 3 (Java 17) using a Hexagonal Architecture. It exposes use cases for patients, employees, appointments, medical records (including vital signs), medical orders and their execution, procedures, medications, diagnostic aids, and invoices.

Security is handled with JWT (JJWT, HS256) and role-based authorization (DOCTOR, NURSE, ADMINISTRATIVE_STAFF, HUMAN_RESOURCES, INFORMATION_SUPPORT). Persistence uses JPA/Hibernate with MySQL (H2 is included for local test environments). The design decouples the domain from the infrastructure using ports and adapters (adapter/in for REST/validation and adapter/out for security/JPA), which facilitates testing and evolution.

---

## 🚀 Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3.5.x
* **Build:** Maven (wrapper included: `mvnw` / `mvnw.cmd`)
* **Persistence:** Spring Data JPA (MySQL 8 by default)
* **Validation:** Spring Validation
* **Security:** JWT (jjwt 0.11.5), Spring Security (BCrypt)
* **Utilities:** Lombok

---

## 🛠️ Key dependencies (`pom.xml`)
- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-data-jpa`
- `org.springframework.boot:spring-boot-starter-validation`
- `org.springframework.boot:spring-boot-starter-security`
- `org.springframework.security:spring-security-crypto`
- `io.jsonwebtoken:jjwt-api:0.11.5`, `jjwt-impl:0.11.5`, `jjwt-jackson:0.11.5`
- `org.projectlombok:lombok` (optional, annotations)
- `com.mysql:mysql-connector-j` (runtime)
- `com.h2database:h2` (optional runtime)
- `org.springframework.boot:spring-boot-starter-test` (test)

---

## 🗂 Structure
```
clinic/ 
 ├─ src/main/java/app 
 │   ├─ adapter/ 
 │   │   ├─ in/ 
 │   │   │   ├─ builder/ 
 │   │   │   ├─ rest/ 
 │   │   │   │   ├─ controllers/ 
 │   │   │   │   └─ request/ 
 │   │   │   └─ validators/ 
 │   │   └─ out/ 
 │   │       ├─ persistence/ 
 │   │       └─ security/ 
 │   ├─ application/ 
 │   │   ├─ exceptions/ 
 │   │   └─ usecase/ 
 │   ├─ domain/ 
 │   │   ├─ model/ 
 │   │   │   ├─ auth/ 
 │   │   │   └─ enums/ 
 │   │   ├─ ports/ 
 │   │   └─ services/ 
 │   └─ infrastructure/ 
 │       ├─ persistence/ 
 │       │   ├─ entities 
 │       │   ├─ mapper 
 │       │   └─ repository 
 │       └─ security/ 
 └─ src/main/resources 
     ├─ application.properties
     └─ data.sql
```

---

## 🗄️ Database

### Engine and Connection

The project is configured for **MySQL 8**. Adjust `src/main/resources/application.properties` if your environment differs:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinic?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Bogota
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
```

> **Schema**: previously create the Database `clinic` in MySQL. Tables are created/updated with `ddl-auto=update`.

---

### Data Seed

`src/main/resources/data.sql` loads default values ​​for **medicines**, **procedures**, and **diagnostic_aids** if they don't already exist.

---

## ▶️ Booting
```bash
# From the clinic/ folder
mvn spring-boot:run
# or build the .jar package
mvn clean package
java -jar target/clinic-0.0.1-SNAPSHOT.jar
```

---

## 👤 Initial Administrator User

When creating the database for the first time, insert an HR administrator directly into the `employees` table:

```sql
INSERT INTO employees (address,birth_date,document,email,full_name,password,phone,role,user_name)
VALUES ('address','1999-01-01',1000000001,'email@domain.com','name','A!123456789',3000000001,'HUMAN_RESOURCES','ADMIN');
```

> **Password and hashing:** The system uses **BCrypt**. If you enter the password in plain text as above, it will be detected and automatically saved encrypted on the **first login**.

---

## 🔐 Authentication and authorization

> The service listens on **[http://localhost:8080](http://localhost:8080)** (Spring Boot default if `server.port` is not defined).

  **POST** `/auth/login` — Log in
  **Body (JSON):**
  ```json
  { "username": "ADMIN", "password": "A!123456789" }
  ```
  **Response (JSON):**
  ```json
  { "token": "eyJhbGciOi..." }
  ```

* Use the token in `Authorization: Bearer <JWT>` to call the endpoints.

> The **JWT** is signed with a **key generated in memory** (`JwtAdapter` uses `Keys.secretKeyFor(HS256)`), so **tokens are invalidated on each reboot**. The token expires in ~30 minutes.

---

## Roles and routes

  - `/auth/**` → public.
  - `/employees/**` → `HUMAN_RESOURCES`. 
  - `/administrative/**` → `PERSONAL_ADMINISTRATIVE`. 
  - `/doctor/**` → `DOCTOR`. 
  - `/nurse/**` → `NURSE`. 
  - `/support/**` → `INFORMATION_SUPPORT`.

---

## 📚 Endpoints

### Personnel Management (HR) — `/employees` (role: HUMAN_RESOURCES)
- **POST** `/employees/doctor` — Create doctor (body: `EmployeeRequest`)
- **POST** `/employees/nurse` — Create nurse (body: `EmployeeRequest`)
- **POST** `/employees/administrative` — Create administrative staff (body: `EmployeeRequest`)
- **POST** `/employees/information-support` — Create information support staff (body: `EmployeeRequest`)
- **DELETE** `/employees/{document}` — Delete employee by document

**`EmployeeRequest`:**
```json
{
  "fullName": "First Name Last Name",
  "document": 1234567890, 
  "birthDate": "01/01/1990", 
  "address": "123rd Street", 
  "phone": 3001234567, 
  "email": "user@mail.com", 
  "userName": "user2025", 
  "password": "A!123456789"
}
```

### Administration — `/administrative` (role: PERSONAL_ADMINISTRATIVE)
- **POST** `/administrative/patients` — Create patient (body: `PatientRequest`)
- **POST** `/administrative/appointments` — Create appointment (body: `AppointmentRequest`)
- **GET** `/administrative/appointments/doctor/{doctorDocument}` — List appointments by doctor
- **GET** `/administrative/appointments/patient/{patientDocument}` — List appointments by patient
- **DELETE** `/administrative/appointments/{appointmentId}` — Cancel appointment
- **POST** `/administrative/invoices` — Create invoice (body: `InvoiceRequest`)
- **GET** `/administrative/invoices/patient/{patientDocument}` — List invoices by patient
- **GET** `/administrative/orders/{patientDocument}` — Find orders by patient

**`PatientRequest`:**
```json
{ 
  "fullName": "Juan Pérez", 
  "document": 100200300, 
  "birthDate": "01/01/1990", 
  "gender": "M", 
  "address": "Calle 45 #10-20", 
  "phone": 3001234567,
  "email": "juan@correo.com",
  "contactFirstName": "First Name",
  "contactLastName": "Second Name",
  "contactRelation": "Contact Relationship",
  "contactPhone": 3001234567,
  "companyName": "Insurance Company Name",
  "policyNumber": 4567,
  "policyStatus": "yes",
  "policyExpiry": "21/10/2026"
}
```

**`AppointmentRequest`:**
```json
{
  "patientDocument": 100200300,
  "doctorDocument": 900100200,
  "dateTime": "03/11/2025 09:00"
}
```

**`InvoiceRequest`:**
```json
{
  "patientId": 100200300,
  "doctorDocument": 900100200,
  "orderId": 123456
}
```

### Doctor — `/doctor` (role: DOCTOR)
- **POST** `/doctor/orders` — Create medical order (body: `MedicalOrderRequest`)
- **GET** `/doctor/orders/{patientDocument}` — Check patient orders
- **POST** `/doctor/records` — Create medical record (body: `MedicalRecordRequest`)

**`MedicalOrderRequest`:**
```json
{
  "doctorDocument": 900100200,
  "patientId": 100200300,
  "items":[
    { "type": "MEDICINE", "referenceId": "MED-001" },
    { "type": "PROCEDURE", "referenceId": "PROC-001" }
  ]
}
```

**`MedicalRecordRequest`:**
```json
{
  "doctorDocument": 900100200,
  "patientId": 100200300,
  "orderId": 1,
  "motive": "Headache",
  "symptoms": "Headache, fever",
  "diagnosis": "Migraine"
}
```

### Nursing — `/nurse` (role: NURSE)
- **POST** `/nurse/vital-signs` — Record vital signs (body: `VitalSignsRequest`)
- **POST** `/nurse/orders/{orderId}/items/{itemNumber}/execute` — Execute item from a order

**`VitalSignsRequest`:**
```json
{
  "nurseDocument": 700300400,
  "patientId": 100200300,
  "bloodPressure": "120/80",
  "temperature": 36.5,
  "pulse": 75,
  "oxygenLevel": 98
}
```

**`OrderExecutionRequest`:**
```json
{
  "nurseDocument": 700300400,
  "amount": "2.5",
  "notes": "Application no updates"
}
```

### Information Support — `/support` (role: INFORMATION_SUPPORT)
- **GET** `/support/medicines` — List medications
- **POST** `/support/medicines` — Create medication (body: `MedicineRequest`)
- **GET** `/support/procedures` — List procedures
- **POST** `/support/procedures` — Create procedure (body: `ProcedureRequest`)
- **GET** `/support/diagnostic-aids` — List diagnostic aids
- **POST** `/support/diagnostic-aids` — Create diagnostic aid (body: `DiagnosticAidRequest`)

**`MedicineRequest`:**
```json
{
  "id": "MED-005",
  "name": "Acetaminophen 500 mg",
  "cost": "1200.00",
  "dose": "1 tablet every 8 hours",
  "treatmentDuration": "5 days"
}
```

**`ProcedureRequest`:**
```json
{
  "id": "PROC-001",
  "name": "Chest X-ray",
  "cost": "25000.00",
  "quantity": "1",
  "frequency": "UNIQUE",
  "requiresSpecialist": "false"
}
```

**`DiagnosticAidRequest`:**
```json
{
  "id": "DA-001",
  "name": "Complete Blood Count",
  "cost": "18000.00",
  "quantity": 1,
  "requiresSpecialist": "false"
}
```

---

## 🧠 Business Rules (Summary)

**Roles and Functional Scope:**

* **HUMAN_RESOURCES:** Create/delete app users; manage employee data.
* **ADMINISTRATIVE_STAFF:** Register patients, schedule appointments, manage billing and insurance.
* **INFORMATION_SUPPORT:** Maintain inventories (medications, procedures, aids), data integrity.
* **NURSE:** record vital signs, administer medications/procedures and their execution.
* **DOCTOR:** Full access to the patient, medical record, generate diagnoses, orders (medication, procedure, diagnostic aid).

**Key Rules:**

* **Orders**: Unique order number; unique **order-item** relationship; multiple items per order (medications/procedures/aids) numbered from 1.
* **Diagnostic Aid Exclusivity**: If a diagnostic aid is requested, **no** medications/procedures are prescribed during that same visit; after the results are obtained, a new record is generated with the diagnosis and any prescriptions.
* **Billing**: If policy is active → co-payment $50,000 and the remainder to the insurer; annual co-payment limit $1,000,000 per patient; without a policy or with an inactive policy → pays the full amount.

---

## 📄 MIT License

---

**Last updated:** 2025-11-23 18:00

