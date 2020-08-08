package co.com.s4n.deliveries.services;

import java.util.HashMap;
import java.util.Map;

public class ServiceSpec {
	
	private String SERVICE_NAME;
	
	private Map<String, String> serviceMetadata = new HashMap<String, String>();
	
	private Object serviceRequest = new Object();

	public String getSERVICE_NAME() {
		return SERVICE_NAME;
	}

	public void setSERVICE_NAME(String sERVICE_NAME) {
		SERVICE_NAME = sERVICE_NAME;
	}

	public Map<String, String> getServiceMetadata() {
		return serviceMetadata;
	}

	public void setServiceMetadata(Map<String, String> serviceMetadata) {
		this.serviceMetadata = serviceMetadata;
	}

	public Object getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(Object serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
}
