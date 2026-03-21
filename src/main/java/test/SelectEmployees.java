package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectEmployees {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e){
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベース接続
		try(Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")){
			
			// SELECT文を準備
			String sql = "select id, name, age from EMPLOYEES";
			// SQLインジェクション対策
			PreparedStatement pSmt = conn.prepareStatement(sql);
			
			// select文を実行し、結果表を取得
			ResultSet rs = pSmt.executeQuery();
			
			// 結果表に格納されたレコードの内容を表示
			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				
				// 取得したデータをコンソールに出力
				System.out.println("ID：" + id);
				System.out.println("名前：" + name);
				System.out.println("年齢：" + age);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
