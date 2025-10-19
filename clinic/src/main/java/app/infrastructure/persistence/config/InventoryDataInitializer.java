package app.infrastructure.persistence.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import app.infrastructure.persistence.entities.DiagnosticAidEntity;
import app.infrastructure.persistence.entities.MedicineEntity;
import app.infrastructure.persistence.entities.ProcedureEntity;
import app.infrastructure.persistence.repository.DiagnosticAidRepository;
import app.infrastructure.persistence.repository.MedicineRepository;
import app.infrastructure.persistence.repository.ProcedureRepository;

/**
 * Inicializa el inventario con registros base para medicamentos,
 * procedimientos y ayudas diagnosticas.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class InventoryDataInitializer implements CommandLineRunner {

    private final MedicineRepository medicineRepository;
    private final ProcedureRepository procedureRepository;
    private final DiagnosticAidRepository diagnosticAidRepository;

    public InventoryDataInitializer(MedicineRepository medicineRepository,
                                    ProcedureRepository procedureRepository,
                                    DiagnosticAidRepository diagnosticAidRepository) {
        this.medicineRepository = medicineRepository;
        this.procedureRepository = procedureRepository;
        this.diagnosticAidRepository = diagnosticAidRepository;
    }

    @Override
    public void run(String... args) {
        seedMedicines();
        seedProcedures();
        seedDiagnosticAids();
    }

    private void seedMedicines() {
        List<MedicineEntity> defaults = List.of(
            medicine("MED-001", "Acetaminofen 500mg", 3500.0),
            medicine("MED-002", "Ibuprofeno 400mg", 4200.0),
            medicine("MED-003", "Amoxicillina 500mg", 7800.0),
            medicine("MED-004", "Omeprazol 20mg", 8600.0),
            medicine("MED-005", "Loratadina 10mg", 4100.0),
            medicine("MED-006", "Formina 850mg", 5900.0),
            medicine("MED-007", "Losartan 50mg", 6400.0),
            medicine("MED-008", "Atorvastatina 20mg", 9800.0),
            medicine("MED-009", "Inhalador Salbutamol", 15200.0),
            medicine("MED-010", "Insulina NPH", 23800.0)
        );
        defaults.forEach(medicine -> {
            if (!medicineRepository.existsById(medicine.getId())) {
                medicineRepository.save(medicine);
            }
        });
    }

    private void seedProcedures() {
        List<ProcedureEntity> defaults = List.of(
            procedure("PROC-001", "Consulta general", 25000.0),
            procedure("PROC-002", "Curacion de heridas", 18000.0),
            procedure("PROC-003", "Vacunacion", 22000.0),
            procedure("PROC-004", "Terapia respiratoria", 30000.0),
            procedure("PROC-005", "Control prenatal", 45000.0),
            procedure("PROC-006", "Electrocardiograma", 52000.0),
            procedure("PROC-007", "Fisioterapia muscular", 40000.0),
            procedure("PROC-008", "Extraccion de puntos", 16000.0),
            procedure("PROC-009", "Suturas menores", 28000.0),
            procedure("PROC-010", "Consulta dermatologica", 36000.0)
        );
        defaults.forEach(procedure -> {
            if (!procedureRepository.existsById(procedure.getId())) {
                procedureRepository.save(procedure);
            }
        });
    }

    private void seedDiagnosticAids() {
        List<DiagnosticAidEntity> defaults = List.of(
            aid("DIA-001", "Hemograma completo", 48000.0),
            aid("DIA-002", "Perfil lipidico", 52000.0),
            aid("DIA-003", "Prueba de glucosa", 29000.0),
            aid("DIA-004", "Prueba de embarazo", 18000.0),
            aid("DIA-005", "Rayos X toracico", 78000.0),
            aid("DIA-006", "Ecografia obstetrica", 95000.0),
            aid("DIA-007", "Pruebas hepaticas", 61000.0),
            aid("DIA-008", "Perfil tiroideo", 69000.0),
            aid("DIA-009", "Uroanalisis", 24000.0),
            aid("DIA-010", "Antigeno COVID-19", 42000.0)
        );
        defaults.forEach(aid -> {
            if (!diagnosticAidRepository.existsById(aid.getId())) {
                diagnosticAidRepository.save(aid);
            }
        });
    }

    private MedicineEntity medicine(String id, String name, Double cost) {
        MedicineEntity entity = new MedicineEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setCost(cost);
        return entity;
    }

    private ProcedureEntity procedure(String id, String name, Double cost) {
        ProcedureEntity entity = new ProcedureEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setCost(cost);
        return entity;
    }

    private DiagnosticAidEntity aid(String id, String name, Double cost) {
        DiagnosticAidEntity entity = new DiagnosticAidEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setCost(cost);
        return entity;
    }
}