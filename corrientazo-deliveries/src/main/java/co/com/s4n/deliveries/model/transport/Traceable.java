package co.com.s4n.deliveries.model.transport;

import co.com.s4n.deliveries.services.monitoring.trace.TraceService;

public interface Traceable {

	public final TraceService traceService = new TraceService();
	
	void trace(boolean detail);
	
}
