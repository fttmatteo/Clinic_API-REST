package app.domain.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.model.ClinicalOrder;
import app.domain.model.OrderItem;
import app.domain.model.enums.OrderItemType;
import app.domain.ports.ClinicalOrderPort;

@Service
public class CreateClinicalOrder {
	
	@Autowired
	private ClinicalOrderPort clinicalOrderPort;

	public ClinicalOrder execute(ClinicalOrder order, List<OrderItem> items) throws Exception {
		if (order == null) throw new Exception("La orden es obligatoria");
		if (order.getNumberOrder() == null) throw new Exception("El número de orden es obligatorio");
		if (order.getPatientDocument() == null) throw new Exception("La cédula del paciente es obligatoria");
		if (order.getDoctorDocument() == null) throw new Exception("La cédula del médico es obligatoria");
		if (items == null || items.isEmpty()) throw new Exception("La orden debe tener al menos un ítem");

		boolean hasDiag = false, hasMedOrProc = false;
		Set<Long> itemIds = new HashSet<>();
		for (OrderItem i : items) {
			if (i.getOrderItemId() == null) throw new Exception("Cada ítem debe tener número de ítem");
			if (!itemIds.add(i.getOrderItemId())) throw new Exception("El número de ítem se repite en la orden");
			if (i.getType() == OrderItemType.DIAGNOSTIC_AID) hasDiag = true;
			if (i.getType() == OrderItemType.MEDICINE || i.getType() == OrderItemType.PROCEDURE) hasMedOrProc = true;
		}
		if (hasDiag && hasMedOrProc) {
			throw new Exception("No se pueden mezclar ayudas diagnósticas con medicamentos/procedimientos en la misma orden");
		}
		return clinicalOrderPort.save(order);
	}
}
