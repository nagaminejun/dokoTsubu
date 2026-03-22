package model;

import dao.AccountsDAO;

public class LoginLogic {
	
	// これは13章までのメソッド。
//	public boolean execute(User user) {
//		if (user.getPass().equals("1234")) {	return true;	}
//		return false;
//	}
	
	// これは14章以降でログイン機能、新規登録機能用
	public boolean execute(Login login) {
    // 1. DAOをインスタンス化
    AccountsDAO dao = new AccountsDAO();
    
    // 2. DBへ問い合わせ（見つかればAccountインスタンス、なければnullが返る）
    Account account = dao.findByLogin(login);
    
    // 3. accountがnullでなければ「ログイン成功(true)」を返す
    // 知らなかったのでメモ
    // account != nullの結果（true or false）をreturnするという短くできる記述方法
    return account != null;
  }
}
