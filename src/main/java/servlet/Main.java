package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;

@WebServlet("/Main")
public class Main extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // こちらはアプリケーションスコープの実装
  	// つぶやきリストをアプリケーションスコープから取得
//    ServletContext application = this.getServletContext();
//    List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
//    // 取得できなかった場合は、つぶやきリストを新規作成して
//    // アプリケーションスコープに保存
//    if (mutterList == null) {
//      mutterList = new ArrayList<>();
//      // ここで、アプリケーションスコープの設定が行われる。第一引数がその名前の命名
//      application.setAttribute("mutterList", mutterList);
//    }
  	GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
  	// DBから最新のつぶやきリストを取得するロジックを呼び出す
  	List<Mutter> mutterList = getMutterListLogic.execute();
  	// 取得したリストをリクエストスコープに保存する
  	// main.jspでそれをfor eachで表示するため、
  	request.setAttribute("mutterList", mutterList);
    
    // ログインしているか確認するため
    // セッションスコープからユーザー情報を取得
    // HttpSession session = request.getSession();
    // User loginUser = (User) session.getAttribute("loginUser");
  	
  	HttpSession session = request.getSession();
  	String userId = (String) session.getAttribute("userId");
    
    if (userId == null) { // ログインしていない場合
      // リダイレクト
      response.sendRedirect("index.jsp");
      return;
    } else { // ログイン済みの場合
      // フォワード
      RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
      dispatcher.forward(request, response);
    }
  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		// request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		
		// 入力チェック
		// パラメータに値があるなら、、つまりPost時に適切な値であるなら、
		if(text != null && text.length() != 0) {
			// アプリケーションスコープに保存されたつぶやきリストを取得
//			ServletContext application = this.getServletContext();
//			List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
			
			// セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");
			
			// つぶやきを作成してつぶやきリスト（DB）に追加
			Mutter mutter = new Mutter(userId, text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter);
			
			// アプリケーションスコープにつぶやきリストを保存
//			application.setAttribute("mutterList", mutterList);
		} else {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute(""
					+ "", "つぶやきが入力されていません");
		}
			
		// メイン画面にフォワード
		// ※フォワード実行だと、doGetが実行されず結果的につぶやきの取得が実行されない。
		// リダイレクトを実行することでdoGetメソッドが実行され、結果的につぶやき一覧取得ができる。
		// RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
    // dispatcher.forward(request, response);
		
		response.sendRedirect("Main");
	}
}