package kz.iskst.dao;

/**
 * @author Nikichus
 *
 */
public class DaoException extends Exception {

	@Override
	public String getMessage() {
		// TODO ������������� ��������� �������� ������
		return super.getMessage();
	}

	@Override
	public String toString() {
		// TODO ������������� ��������� �������� ������
		return super.toString();
	}

	public DaoException() {
		super();
		// TODO ������������� ��������� �������� ������������
	}

	public DaoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO ������������� ��������� �������� ������������
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
		// TODO ������������� ��������� �������� ������������
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
		// TODO ������������� ��������� �������� ������������
	}
	
}
