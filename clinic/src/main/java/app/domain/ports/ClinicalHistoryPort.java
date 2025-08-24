package app.domain.ports;

import app.domain.model.ClinicalHistory;
import java.util.List;

public interface ClinicalHistoryPort {
    ClinicalHistory findByDate(ClinicalHistory history) throws Exception;
    List<ClinicalHistory> findAll() throws Exception;
    void save(ClinicalHistory history) throws Exception;
    void update(ClinicalHistory history) throws Exception;
    void delete(ClinicalHistory history) throws Exception;
}
