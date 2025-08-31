package app.domain.ports;

import app.domain.model.ClinicalHistory;
import java.util.List;

public interface ClinicalHistoryPort {
    ClinicalHistory findByDate(ClinicalHistory clinicalHistory) throws Exception;
    List<ClinicalHistory> findAll() throws Exception;
    void save(ClinicalHistory clinicalHistory) throws Exception;
    void update(ClinicalHistory clinicalHistory) throws Exception;
    void delete(ClinicalHistory clinicalHistory) throws Exception;
}
