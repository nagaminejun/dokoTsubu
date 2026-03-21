package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FieldCounterServlet
 */
@WebServlet("/FieldCounterServlet")
public class FieldCounterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Integer count; // 訪問回数のカウント
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public FieldCounterServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ
		super.init(config);
		// 訪問回数を初期化
		count = 0;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		count++;
		// HTMLを出力
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\" />");
    out.println("<title>訪問回数を表示</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<p>訪問回数：" + count + "</p>");
    out.println("<a href=\"CounterServlet\">更新</a>");
    out.println("</body>");
    out.println("</html>");
	}

	public void destroy() {
		System.out.println("destroy()が実行されました");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
