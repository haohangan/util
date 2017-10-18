package download.jar;

import java.sql.SQLException;

import org.skife.jdbi.v2.DBI;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBUtils {
	private static final String URL = "jdbc:h2:tcp://localhost/~/test;DB_CLOSE_DELAY=-1";
	private static final String USER = "sa";
	private static final String PWD = "tiger";
	private static final String DIRVER = "org.h2.Driver";

	protected static HikariDataSource ds;
	protected static DBI dbi;

	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(URL);
		config.setUsername(USER);
		config.setPassword(PWD);
		config.setDriverClassName(DIRVER);
		ds = new HikariDataSource(config);
		dbi = new DBI(ds);
	}

	public static DBI getDBI() {
		return dbi;
	}

	// public static Connection getConn() throws SQLException {
	// return ds.getConnection();
	// }

	public static void main(String[] args) throws SQLException {
	}
}
