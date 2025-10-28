# Clinic Clean Architecture (Hexagonal) — Proyecto

> **Stack:** Spring Boot · Java 17 · JPA/Hibernate · MySQL 8 (H2 opcional) · JWT · Arquitectura Hexagonal

## 🚀 Resumen
Aplicación clínica con arquitectura hexagonal. Incluye gestión de pacientes, empleados, inventario (medicinas, procedimientos, ayudas diagnósticas), órdenes médicas, registros clínicos y facturación con políticas de copago.

## 🗂 Estructura
```
clinic/
 ├─ src/main/java/app
 │   ├─ adapter/
 │   │   ├─ in/   
 │   │   └─ out/   
 │   ├─ application/ 
 │   ├─ domain/       
 │   └─ infrastructure/
 │       ├─ entities 
 │       ├─ mapper   
 │       └─ repository
 └─ src/main/resources
     └─ application.properties
```

## 🧰 Prerrequisitos
- **Java 17+**
- **MySQL 8**
- **Maven** o wrapper `./mvnw`

**Configuración (`application.properties`):**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinic?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Bogota
spring.datasource.username=clinic_user
spring.datasource.password=strong_pass
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## 🔐 Seguridad (JWT + Roles)
- Login en `/auth/**` (público). Rutas protegidas por rol: `DOCTOR`, `NURSE`, `HUMAN_RESOURCES`, `PERSONAL_ADMINISTRATIVE`, `INFORMATION_SUPPORT`.
- Config en `SecurityConfig` y filtro `JwtAuthenticationFilter`.

## ▶️ Arranque
```bash
cd clinic
./mvnw spring-boot:run
```

**Primer inicio:** si no existen tablas, puedes usar temporalmente:
```properties
spring.jpa.hibernate.ddl-auto=create
```
Luego vuelve a `update` para no perder datos.
---

## 📦 Endpoints principales (ejemplos)

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

### Crear orden médica — `POST /doctor/orders`
**Request:**
```json
{
  "doctorDocument": "1234567890",
  "patientId": "1001234567",
  "items": [
    {
      "itemNumber": "1",
      "type": "MEDICINE",
      "referenceId": "MED-00045",
      "dose": "500mg",
      "treatmentDuration": "5d",
      "cost": "IGNORAR_EN_SERVIDOR",
      "requiresSpecialist": "no",
      "specialistTypeId": ""
    }
  ]
}
```

**Reglas clave:**
- **No mezclar** ayudas diagnósticas con procedimientos/medicamentos en la misma orden.
- 
- 
- 

### Crear factura — `POST /administrative/invoices`
- Copago **$50.000** si la póliza está activa y **acumulado anual < $1’000.000**. Si lo supera, **copago = 0** el resto del año.
- Si es medicina, **requiere** una orden válida del paciente.

---

## 🧱 Arquitectura (Hexagonal)
- **Domain**: entidades y servicios de negocio (`CreateMedicalOrder`, `CreateInvoice`, etc.). Sin dependencias de frameworks.
- **Application**: orquesta casos de uso y puertos.
- **Adapters**: `in` (REST, validación) y `out` (JPA, seguridad).
- **Infrastructure**: entidades JPA, repositorios y mapeadores.

Beneficios: bajo acoplamiento, testabilidad, reemplazo de infraestructura sin tocar el dominio.

---

## 📄 Licencia
MIT

---

**Última actualización:** 2025-10-22 00:38
