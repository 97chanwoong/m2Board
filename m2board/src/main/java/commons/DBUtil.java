package commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	   public Connection getConnection() throws Exception {
	      //1. 드라이버 연결 
	      Class.forName("org.mariadb.jdbc.Driver");
	      System.out.println("드라이버 로딩 성공");
	      String url = "jdbc:mariadb://3.38.124.179:3306/m2";
	      String dbuser = "root";
	      String dbpw = "1234";
	      Connection conn = DriverManager.getConnection(url, dbuser, dbpw);
	      
	      return conn;
	   }
	}
