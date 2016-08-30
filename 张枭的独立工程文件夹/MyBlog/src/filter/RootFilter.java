package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 检查session，确定用户是否为root
 */

@WebFilter( dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/root/#" })
public class RootFilter implements Filter {
	//不过滤的URL
    public RootFilter() {

    }


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//类型强转，检查session中的“root”属性是否为true，为空则跳转到error.jsp页面
		System.out.println("dofilter");
	HttpServletRequest request2=(HttpServletRequest) request;
	HttpServletResponse response2=(HttpServletResponse) response;
	HttpSession session=request2.getSession();
	
			
		
		//检查是否为root用户
		if(session.getAttribute("root")=="true"){
			chain.doFilter(request, response);
		}else{
			//response2.setCharacterEncoding("utf-8");有了字符集过滤器这段注释
			PrintWriter out=response2.getWriter();
			out.println("<h3>您好管理员，您还没有登录</h3><br>");
			out.println("<a href='/MyBlog/index.html'>返回登录</a>");
			//response2.sendRedirect("error.jsp");//请求重定向（前后不使用同一个request）
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
