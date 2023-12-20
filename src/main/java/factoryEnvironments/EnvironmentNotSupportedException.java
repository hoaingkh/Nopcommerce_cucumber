package factoryEnvironments;

public class EnvironmentNotSupportedException extends IllegalStateException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EnvironmentNotSupportedException(String envName) {
		// TODO Auto-generated constructor stub
		super(String.format("Environment %s is not supported", envName));
	}

}
