package mx.com.proyect.puntoventa.web.resultsQuerys;

import mx.com.proyect.puntoventa.web.model.DeliveryStatusDTO;

public class ResultQueryDelivery extends ResultQuerySaleNote {
	private DeliveryStatusDTO deliveryStatusDTO;

	public DeliveryStatusDTO getDeliveryStatusDTO() {
		return deliveryStatusDTO;
	}

	public void setDeliveryStatusDTO(DeliveryStatusDTO deliveryStatusDTO) {
		this.deliveryStatusDTO = deliveryStatusDTO;
	}
}
