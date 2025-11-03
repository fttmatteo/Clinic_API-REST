# Clínica IPS – Backend (Spring Boot)

Aplicación para la gestión de información de una clínica (pacientes, personal, órdenes médicas, ayudas diagnósticas, facturación, etc.), desarrollada con Java 17 y Spring Boot siguiendo Arquitectura Hexagonal (Ports & Adapters).

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

> **Esquema**: crea previamente la base de datos `clinic` en MySQL. Las tablas se crean/actualizan con `ddl-auto=update`.

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

### Semilla de datos

`src/main/resources/data.sql` carga valores por defecto para **medicines**, **procedures** y **diagnostic_aids** si aún no existen.

---

## ▶️ Arranque
```bash
cd clinic
./mvnw spring-boot:run
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

* **Login**: `POST /auth/login` con cuerpo:

  ```json
  { "username": "ADMIN", "password": "A!123456789" }
  ```

  Respuesta: `{ "token": "<JWT>" }`
* Usa el token en `Authorization: Bearer <JWT>` para llamar a los endpoints.

> **JWT**: el token expira en ~30 minutos. Tras reiniciar la app, los tokens emitidos antes dejan de ser válidos.

---

## 📦 Endpoints de ejemplos

### Crear paciente — `POST /administrative/patients`
```json
{
  "fullName": "Ana Pérez",
  "document": "1001234567",
  "birthDate": "12/05/1990",
  "gender": "FEMALE",
  "address": "Cra 1 #2-3",
  "phone": "3001234567",
  "email": "ana@correo.com",
  "contactFirstName": "Luis",
  "contactLastName": "Pérez",
  "contactRelation": "Hermano",
  "contactPhone": "3007654321",
  "companyName": "Seguros Salud",
  "policyNumber": "POL-001",
  "policyStatus": "si",
  "policyExpiry": "31/12/2025"
}
```
- Persiste en `patients` y `insurance` (1—1).

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

**Última actualización:** 2025-11-03 01:29
