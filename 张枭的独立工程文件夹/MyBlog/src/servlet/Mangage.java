package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.PassageDao;


/**
 * Servlet implementation class Mangage
 */
@WebServlet("/Mangage")
public class Mangage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Mangage() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String passageid=request.getParameter("passageid");
			System.out.println("zhelishiservlet");
			if(passageid!=null)
			{
				deletpassage(Integer.parseInt( passageid));
			}
			
			request.getRequestDispatcher("FindServlet?rootflag=1&page=1&attribute=0").forward(request,response);//·µ»Ø
	}
	
	void deletpassage(int passageid){
		PassageDao dao=new PassageDao();
		dao.deletepassage(passageid);
	}

}
