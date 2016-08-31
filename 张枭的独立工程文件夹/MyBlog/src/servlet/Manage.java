package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.PassageDao;
import util.Passage;


/**
 * Servlet implementation class Manage
 */
@WebServlet("/Manage")
public class Manage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Manage() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		System.out.println("there is Manage");
		String passageid=request.getParameter("passageid");
			if(passageid!=null)//删除功能启动
			{
				deletpassage(Integer.parseInt( passageid));
				request.getRequestDispatcher("FindServlet?rootflag=1&page=1&attribute=0").forward(request,response);//返回
			}
			if(request.getParameter("newpassage")!=null)
			{
				Passage p=new Passage();
				p.setPassagename(request.getParameter("passagename"));
				p.setContent(request.getParameter("content"));
				p.setAttribute(Integer.parseInt(request.getParameter("attribute")));
				Date date=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
				String today=sdf.format(date);
				p.setDate(today);
				
				System.out.println(p.getPassagename());
				System.out.println( p.getContent() );
				System.out.println(p.getDate());
				System.out.println(p.getAttribute());
				
				PassageDao dao=new PassageDao();
				dao.newpassage(p);
				request.getRequestDispatcher("FindServlet?rootflag=1&page=1&attribute=0") .forward(request,response);
			}
			
			
	}
	
	void deletpassage(int passageid){
		PassageDao dao=new PassageDao();
		dao.deletepassage(passageid);
	}

}
