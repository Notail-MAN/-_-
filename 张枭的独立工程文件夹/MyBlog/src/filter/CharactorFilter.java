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

/**
 * ×Ö·û¼¯ÉèÖÃÆ÷
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/*" })
public class CharactorFilter implements Filter {
	private String  encoding=null;

    public CharactorFilter() {
    	
    }

	
	public void destroy() {
		encoding=null;
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if(encoding!=null){
			request.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset="+encoding);
		}
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		encoding="utf-8";
	}

}
