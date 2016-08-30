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
 * ���session��ȷ���û��Ƿ�Ϊroot
 */

@WebFilter( dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/root/#" })
public class RootFilter implements Filter {
	//�����˵�URL
    public RootFilter() {

    }


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//����ǿת�����session�еġ�root�������Ƿ�Ϊtrue��Ϊ������ת��error.jspҳ��
		System.out.println("dofilter");
	HttpServletRequest request2=(HttpServletRequest) request;
	HttpServletResponse response2=(HttpServletResponse) response;
	HttpSession session=request2.getSession();
	
			
		
		//����Ƿ�Ϊroot�û�
		if(session.getAttribute("root")=="true"){
			chain.doFilter(request, response);
		}else{
			//response2.setCharacterEncoding("utf-8");�����ַ������������ע��
			PrintWriter out=response2.getWriter();
			out.println("<h3>���ù���Ա������û�е�¼</h3><br>");
			out.println("<a href='/MyBlog/index.html'>���ص�¼</a>");
			//response2.sendRedirect("error.jsp");//�����ض���ǰ��ʹ��ͬһ��request��
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
