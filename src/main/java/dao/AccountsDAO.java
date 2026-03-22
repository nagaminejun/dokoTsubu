package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountsDAO {
//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public Account findByLogin(Login login) {
		Account account = null;
		
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// select文
			String sql = "select * from ACCOUNTS where user_id = ? and pass = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login.getUserId());
			pStmt.setString(2, login.getPass());
			
			// SELECT文を実行し、結果表（ResultSet）を取得
	    ResultSet rs = pStmt.executeQuery();
	    
	    if(rs.next()) {
	    	String userId = rs.getString("USER_ID");
	      String pass = rs.getString("PASS");
	      String mail = rs.getString("MAIL");
	      String name = rs.getString("NAME");
	      int age = rs.getInt( "AGE");
	      account = new Account(userId, pass, mail, name, age);
	    }
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}
}
