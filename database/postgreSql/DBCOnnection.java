package postgre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCOnnection {

	static String DRIVER = "org.postgresql.Driver";
	static String URL = "jdbc:postgresql://localhost:5432/tbTest";
	static String USER = "postgres";
	static String PWD = "tiger";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
//		 insert(conn);
		select(conn);
	}

	public static void select(Connection conn) throws SQLException {
		String sql = "SELECT * FROM public.\"TB_USER\"";
		Statement statement = null;
		ResultSet rs =  null;
		try{
			statement = conn.createStatement();
		    rs = statement.executeQuery(sql);
		    while(rs.next()){
		    	int id = rs.getInt("USER_ID");
		    	String name = rs.getString("USER_NAME");
		    	System.out.println("id:"+id+"\tname:"+name);
		    }
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null){
				rs.close();
			}
			if(statement!=null){
				statement.close();
			}
			conn.close();
		}
		
	}

	public static void insert(Connection conn) throws SQLException {
		String sql = "INSERT INTO public.\"TB_USER\"(\"USER_NAME\")VALUES ('eva');";// (USER_ID,USER_NAME,USER_DESC)
		Statement statement = null;
		try {
			statement = conn.createStatement();
			int r = statement.executeUpdate(sql);
			System.out.println("r:" + r);
		} finally {
			statement.close();
			conn.close();
		}
	}

	private static Connection getConn() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(URL, USER, PWD);
		return conn;
	}
}
