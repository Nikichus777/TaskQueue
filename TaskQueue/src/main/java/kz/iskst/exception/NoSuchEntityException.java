package kz.iskst.exception;

public class NoSuchEntityException  extends Exception{

    public NoSuchEntityException() {
	super();
	
	// TODO Auto-generated constructor stub
    }

    public NoSuchEntityException(String message, Throwable cause,
	    boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
	// TODO Auto-generated constructor stub
    }

    public NoSuchEntityException(String message, Throwable cause) {
	super(message, cause);
	// TODO Auto-generated constructor stub
    }

    public NoSuchEntityException(String message) {
	super(message + " ResultSet is empty");
	// TODO Auto-generated constructor stub
    }

    public NoSuchEntityException(Throwable cause) {
	super(cause);
	// TODO Auto-generated constructor stub
    }
    
}
