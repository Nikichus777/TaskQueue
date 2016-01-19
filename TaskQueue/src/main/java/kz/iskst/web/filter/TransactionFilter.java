package kz.iskst.web.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import kz.iskst.dao.ConnectionFactoryFactory.FactoryType;
import kz.iskst.dao.TransactionManagerImpl;

import org.apache.log4j.Logger;

public class TransactionFilter implements Filter {
	private Logger logger = Logger.getLogger(TransactionFilter.class);
	private boolean isActive = false;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if (filterConfig.getInitParameter("active").equals("true")) isActive = true;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (isActive == true) {
			StringBuilder errors = new StringBuilder("");
			TransactionManagerImpl.setConnection(FactoryType.C3P0);
			Connection connection = TransactionManagerImpl.getConnection();
			logger.debug("start Transaction");

			chain.doFilter(request, response);
			
			request.setAttribute("transactionErrors", errors);
			try {
				if (connection != null)
				connection.commit();

			} catch (SQLException se) {
				try {
					if (connection != null)
					connection.rollback();
				} catch (SQLException e) {
					logger.warn("CONNECTION DIDN'T ROLLBACK!!");
					errors.append("Невозможно отменить операцию при ошибке\n");
					e.printStackTrace();
				}
				if (se.getSQLState() != null){
					if (se.getSQLState().equals("23000")){
						logger.warn("value IS NOT UNIQUE");
						errors.append("Значение логина или email не уникально!\n");
						se.printStackTrace();
					}
				}
			}

			catch (Exception e) {
				try {
					if (connection != null)
					connection.rollback();
				} catch (SQLException e1) {
					logger.error("UNKNOWN ERROR ON ROLLBACK CONNECTION");
					errors.append("Произошла неизвестная ошибка при обработке запроса к базе данных");
					e1.printStackTrace();
				}
				logger.warn("CONNECTION  DID ROLLBACK!!");
				e.printStackTrace();

				//throw new RuntimeException();
			} finally {
				try {
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					logger.warn("Can't to close Connection\n");
					errors.append("Невозможно закрыть соединение с БД");
					e.printStackTrace();
				}
				TransactionManagerImpl.close();

			}
		}
	}

	@Override
	public void destroy() {
			
	}


}
