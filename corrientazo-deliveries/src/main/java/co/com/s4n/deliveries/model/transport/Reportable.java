package co.com.s4n.deliveries.model.transport;

import co.com.s4n.deliveries.services.monitoring.report.DeliveryReportingService;

public interface Reportable {

	DeliveryReportingService deliveryReportingService = new DeliveryReportingService();
	
	void reportDeliveries(Vehicle vehicle);
	
}
