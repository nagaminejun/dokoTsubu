package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebFilter("/*")
public class SetEncodingFilter extends HttpFilter implements Filter {

	//@Override
	// 教材はpublicだが、HttpFilterを継承している場合は protected で良い。
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO 自動生成されたメソッド・スタブ
		//super.doFilter(request, response, chain);
		
		// 文字エンコーディングを設定
		request.setCharacterEncoding("UTF-8");
		// 次のフィルタまたはサーブレットへ実行を渡す
		chain.doFilter(request, response);
	}
	
}
