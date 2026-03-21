package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MuttersDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public List<Mutter> findAll() {
		List<Mutter> mutterList = new ArrayList<Mutter>();
		
		// JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// select文
			String sql = "select * from Mutters order by id desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECT文を実行し、結果表（ResultSet）を取得
	    ResultSet rs = pStmt.executeQuery();
	    
	    // select文の結果をArrayListに格納
	    while (rs.next()) {
        int id = rs.getInt("ID");
        String userName = rs.getString("NAME");
        String text = rs.getString("TEXT");
        Mutter mutter = new Mutter(id, userName, text);
        mutterList.add(mutter);
	    }
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}
	
	// データ書き込み用のメソッド
	public boolean create(Mutter mutter) {
		// JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			String sql = "insert into mutters(name, text) values(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getText());
			
			// INSERT文を実行（resultには追加された行数が代入される）
			int result = pStmt.executeUpdate();
			// 解説
			// INSERTの場合: 1行追加に成功すれば 1、失敗すれば（何らかの理由で追加されなければ） 0 が返ります。
			// UPDATEやDELETEの場合: 条件に合うデータが5件あれば 5 が返り、1件もなければ 0 が返ります。
			// もし戻り値が単なる boolean だったら、「成功したけど、実は1件も更新されていなかった（0件更新）」という事態に気づけなくなってしまいます。
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
