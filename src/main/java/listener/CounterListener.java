package listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class CounterListener implements ServletContextListener {
  public void contextInitialized(ServletContextEvent sce) {
    // 1. 管理オブジェクトを取得
  	ServletContext context = sce.getServletContext();
  	// 2. 初期値を生成
    Integer count = 0;
    // 3. アプリケーションスコープに "count" という名前で値を保存（設定）する
    context.setAttribute("count", count);
  }
	public void contextDestroyed(ServletContextEvent sce) {
	}
}