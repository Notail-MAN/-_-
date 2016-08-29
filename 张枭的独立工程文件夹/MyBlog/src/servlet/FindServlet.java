package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PassageDao;
import util.Passage;

@WebServlet("/FindServlet")
public class FindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   
    public FindServlet() {
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currPage=1;
		int attribute=1;
		if(request.getParameter("page")!=null){
			currPage=Integer.parseInt(request.getParameter("page"));
			attribute=Integer.parseInt(request.getParameter("attribute"));
		}
		PassageDao dao=new PassageDao();
		ArrayList<Passage> list=dao.find(currPage,attribute);
		request.setAttribute("list", list);
		int pages;
		int count=dao.findCount();
		if(count%Passage.PAGE_SIZE==0){
			pages=count/Passage.PAGE_SIZE;
		}else{
			pages=count/Passage.PAGE_SIZE+1; 
		}
		
		StringBuffer sb=new StringBuffer();
		for(int i=1;i<pages;i++){
			if(i==currPage){
				sb.append("°æ"+i+"°ø");
			}else{
				sb.append("<a href='FindServlet?attribute=1&page="+i+"'>"+i+"</a>");
			}
			sb.append(" ");
		}
		request.setAttribute("bar", sb.toString());
		System.out.println("404≤È’“");
		request.getRequestDispatcher("passage_list.jsp").forward(request, response);
		System.out.println("kk");
	}

}
