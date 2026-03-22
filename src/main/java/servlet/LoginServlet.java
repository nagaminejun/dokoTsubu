package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Login;
import model.LoginLogic;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	// ここからは１４章、DBと連携するログインチェック
  	request.setCharacterEncoding("UTF-8");
  	String userId = request.getParameter("userId");
  	String pass = request.getParameter("pass");
  	
  	// ログイン処理実行
  	Login login = new Login(userId, pass);
  	LoginLogic bo = new LoginLogic();
  	boolean result = bo.execute(login);
  	
  	// ログイン処理の成否によって処理を分岐
  	if (result) {
  		// セッションスコープにユーザーIDを保存
  		HttpSession session = request.getSession();
  		session.setAttribute("userId", userId);
  		RequestDispatcher dispatcher = request.getRequestDispatcher("/loginOK.jsp");
      dispatcher.forward(request, response);
  	} else {
  		response.sendRedirect("LoginServlet?error=true");
  		return;
  	}
  	// こちらは１３章までの処理
  	// リクエストパラメータの取得
    // request.setCharacterEncoding("UTF-8");
//    String name = request.getParameter("name");
//    String pass = request.getParameter("pass");
//    // Userインスタンス（ユーザー情報）の生成
//    User user = new User(name, pass);
//    // ログイン処理
//    LoginLogic loginLogic = new LoginLogic();
//    boolean isLogin = loginLogic.execute(user);
//
//    // ログイン成功時の処理
//    if (isLogin) {
//      // ユーザー情報をセッションスコープに保存
//      HttpSession session = request.getSession();
//      session.setAttribute("loginUser", user);
//    }
//    // ログイン結果画面にフォワード
//    RequestDispatcher dispatcher = request.getRequestDispatcher("/loginResult.jsp");
//    dispatcher.forward(request, response);
  }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		// super.doGet(req, resp);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
    dispatcher.forward(request, response);
	}
}