package co.com.s4n.deliveries.model.transport;

import co.com.s4n.deliveries.services.monitoring.TraceService;

public interface Traceable {

	public static final TraceService traceService = new TraceService();
	
	void trace();
	
}
