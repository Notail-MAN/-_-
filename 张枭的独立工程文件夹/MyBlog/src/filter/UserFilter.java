package filter;

import java.io.IOException;
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
 * ���session��ȷ���û��Ƿ��¼
 */
//ɸѡ����Ϊȫɸѡ
@WebFilter( dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/*" })
public class UserFilter implements Filter {
	//�����˵�URL
	private final String noLoginPaths="error.jsp;Login_Test;index.html";
	
    public UserFilter() {

    }


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//����ǿת�����session�еġ�username�������Ƿ�Ϊ�գ�Ϊ������ת��error.jspҳ��
	HttpServletRequest request2=(HttpServletRequest) request;
	HttpServletResponse response2=(HttpServletResponse) response;
	HttpSession session=request2.getSession();
	
		if(noLoginPaths!=null){
			//�ָ�����
			String[] strArray=noLoginPaths.split(";");
			for(int i=0;i<strArray.length;i++){
				//��֤�ַ�����İ�ȫ��
				if(strArray[i]==null||"".equals(strArray[i])) continue;
				
				if(request2.getRequestURI().indexOf(strArray[i])!=-1 	){
					chain.doFilter(request, response);
					return;
				}
			}
		}
	
		if(session.getAttribute("username")!=null){
			chain.doFilter(request, response);
		}else{
			response2.sendRedirect("error.jsp");//�����ض���ǰ��ʹ��ͬһ��request��
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
