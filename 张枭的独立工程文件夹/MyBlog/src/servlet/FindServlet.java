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

	//针对root管理页面进行了修正，分页设计时没有考虑到降低与servlet的耦合性！ 这是一个错误的设计，维护成本太高！ 请下次一定注意
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currPage=1;
		int attribute=1;
		int rootflag=0;						//验证是否为管理页面的请求
		if(request.getHeader("Referer").indexOf("manage")!=-1)
			rootflag=1;
		if(request.getParameter("rootflag")!=null)
			rootflag=1;

		System.out.println(request.getHeader("Referer"));
		
		if(request.getParameter("page")!=null){
			currPage=Integer.parseInt(request.getParameter("page"));
			attribute=Integer.parseInt(request.getParameter("attribute"));
		}
		PassageDao dao=new PassageDao();
		ArrayList<Passage> list=dao.find(currPage,attribute);
		request.setAttribute("list", list);				//当前分页集
		int pages;										//总页数
		int count=dao.findCount(attribute);
		if(count%Passage.PAGE_SIZE==0){
			pages=count/Passage.PAGE_SIZE;
		}else{
			pages=count/Passage.PAGE_SIZE+1; 
		}
		
		StringBuffer sb=new StringBuffer();
		
		if(rootflag!=1){					//组装分页超链接
		for(int i=1;i<=pages;i++){
				if(i==currPage){
					sb.append("【"+i+"】");
				}else{
					sb.append("<a href='FindServlet?attribute="+attribute+"&page="+i+"'>"+i+"</a>");
				}
				sb.append(" ");
			}
		}else{
			for(int i=1;i<=pages;i++){
				if(i==currPage){
					sb.append("【"+i+"】");
				}else{
					sb.append("<a href='FindServlet?rootflag=1&attribute="+attribute+"&page="+i+"'>"+i+"</a>");
				}
				sb.append(" ");
				}
					
		}
		request.setAttribute("bar", sb.toString());
		
		if(rootflag==1)
			{
				System.out.println("跳转到管理页");
				request.getRequestDispatcher("root/passage_manage.jsp").forward(request, response);
			}
		else 
			{
				request.getRequestDispatcher("passage_list.jsp").forward(request, response);
			}
	
	}
}


