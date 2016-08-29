package dao;

import java.sql.*;
import java.util.ArrayList;
import util.*;;
public class PassageDao {
	
	
	//连接数据库，该函数得到了connection对象
	public Connection getConnection(){
		Connection conn=null;
		try{
			String URL="jdbc:mysql://127.0.0.1:3306/db_myblog";
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

	
	//分页结果查询集    page为查询的页数，attribute为本项目的特定参数
	public ArrayList<Passage> find(int page,int attribute){
		ArrayList<Passage> list=new ArrayList<Passage>();
		Connection conn=getConnection();
		String sql="select * from tb_passage where attribute=? order by id limit ?,?";
		
		try {
			
			PreparedStatement ps=conn.prepareStatement(sql);
			System.out.println("异常定位2");
			ps.setInt(1, 1);
			ps.setInt(2, (page-1)*Passage.PAGE_SIZE);
			ps.setInt(3, Passage.PAGE_SIZE);
			System.out.println("打印page："+page);
			ResultSet resultSet=ps.executeQuery();
			System.out.println("page1查询");
			while(resultSet.next()){
				Passage passage=new Passage();
				passage.setContent(resultSet.getString("content"));
				passage.setPassagename(resultSet.getString("passagename"));
				passage.setDate(resultSet.getString("date"));
				passage.setId(resultSet.getInt("id"));
				list.add(passage);
			}
			resultSet.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("passagedao");
		}
		
 		return list;
	}

	
	//查询总记录数
	public int findCount(){
		int count=0;
		Connection conn=getConnection();
		String sql="select count(*) from tb_passage";
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				System.out.println("异常定位3");
				count=rs.getInt(1);
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("paasscout");
			e.printStackTrace();
		
		}
		return count;
	}

}
