package kz.iskst.dao;

/**
 * @author Nikichus
 *
 */
public class DaoException extends Exception {

	@Override
	public String getMessage() {
		// TODO јвтоматически созданна€ заглушка метода
		return super.getMessage();
	}

	@Override
	public String toString() {
		// TODO јвтоматически созданна€ заглушка метода
		return super.toString();
	}

	public DaoException() {
		super();
		// TODO јвтоматически созданна€ заглушка конструктора
	}

	public DaoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO јвтоматически созданна€ заглушка конструктора
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
		// TODO јвтоматически созданна€ заглушка конструктора
	}

	
	/**
	 * @param sql
	 * take the <code>sql</code> string in parameter of constructor 
	 * and build message
	 */
	public DaoException(String sql) {
		super("DaoException - sql execute error: " + sql);
		
	}
	
	public DaoException(Throwable cause) {
		super(cause);
		// TODO јвтоматически созданна€ заглушка конструктора
	}
	
}
