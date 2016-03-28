package tomcat.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年2月2日 上午11:40:53
 * 类说明
 */
public class DBConnection {
    private static final String url = "jdbc:derby:E:/derbydb;create=false;user=root;password=tiger";//;user=test;password=test
//    private static final String driver = "";
//    private static final String name = "";
//    private static final String pwd = "";
    
    public static Connection getConnection() throws SQLException {   
        Connection connection = DriverManager   
                .getConnection(url);   
        connection.setAutoCommit(false);   
        return connection;   
    }  
    
    public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
	}
}
