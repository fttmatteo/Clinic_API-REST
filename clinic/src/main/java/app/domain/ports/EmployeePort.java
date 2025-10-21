package app.domain.ports;

import app.domain.model.Employee;

/**
 * Puerto de persistencia para operaciones relacionadas con los empleados de la
 * clínica. Define las operaciones básicas que deben implementar los
 * adaptadores de infraestructura para almacenar y recuperar empleados.
 */
public interface EmployeePort {
    Employee findByDocument(Employee employee) throws Exception;
    Employee findByUserName(Employee employee) throws Exception;
    void save(Employee employee) throws Exception;
}