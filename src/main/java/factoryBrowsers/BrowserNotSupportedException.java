package factoryBrowsers;

public class BrowserNotSupportedException extends IllegalStateException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BrowserNotSupportedException(String browserName) {
		// TODO Auto-generated constructor stub
		super(String.format("Browser %s is not supported", browserName));
	}

}
