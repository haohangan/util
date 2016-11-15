package jersey.jaspring.common.dbutils;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class ConnectionUtils {

	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/test?rewriteBatchedStatements=true";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "tiger";
	private static HikariDataSource hds;

	static {
		hds = new HikariDataSource();
		hds.setMaximumPoolSize(20);
		hds.setDriverClassName(DRIVER);
		hds.setJdbcUrl(URL);
		hds.addDataSourceProperty("user", USER_NAME);
		hds.addDataSourceProperty("password", PASSWORD);
		hds.setAutoCommit(false);
	}

	public static DataSource getDataSource() {
		return hds;
	}
	
}
