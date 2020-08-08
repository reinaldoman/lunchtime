package co.com.s4n.deliveries.services;

public class ServiceGateway {

	//TODO implement gateway logic here
	
	private ServiceSpec serviceSpecification = new ServiceSpec();
	private ServiceRequest serviceRequest = new ServiceRequest();
	
	public ServiceResponse callService() {
		ServiceLocator serviceLocator = new ServiceLocator();
		ServiceResponse serviceResponse = serviceLocator.findService(serviceSpecification, serviceRequest);
		return null;
	}
}
