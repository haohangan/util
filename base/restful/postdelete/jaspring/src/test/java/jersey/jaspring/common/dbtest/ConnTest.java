package jersey.jaspring.common.dbtest;

import java.sql.SQLException;

import org.junit.Test;

import jersey.jaspring.common.dbutils.ConnectionUtils;

public class ConnTest {
   
	@Test
	public void testConn() throws SQLException {
		System.out.println(ConnectionUtils.getDataSource().getConnection());
	}
}
