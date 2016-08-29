package dao;

import java.sql.*;
import java.util.ArrayList;
import util.*;;
public class PassageDao {
	
	
	//连接数据库，该函数得到了connection对象
	public Connection getConnection(){
		Connection conn=null;
		try{
			String URL="jdbc:mysql://127.0.0.1:3306/myjdb";
			String USER="root";
			String PASSWORD="zx123456";
			Class.forName("com.mysql.jdbc.Driver");
			conn= DriverManager.getConnection(URL, USER, PASSWORD);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}

	public ArrayList<Passage> find(int page,int attribute){
		ArrayList<Passage> list=new ArrayList<Passage>();
		Connection conn=getConnection();
		String sql="select * from tb_passage where attribute=? order by id dese limit?,?";
 		return list;
	}



}
