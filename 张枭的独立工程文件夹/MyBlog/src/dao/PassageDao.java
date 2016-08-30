package dao;
/**
 * 有关passage的dao文件，针对全文章查询做了修改
 */
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
			System.out.println("定位：passagedao/getconnection");
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println("定位：passagedao/getconnection");
			e.printStackTrace();
		}
		return conn;
		
	}

	
	//分页结果查询集    page为查询的页数，attribute为本项目的特定参数
	public ArrayList<Passage> find(int page,int attribute){
		ArrayList<Passage> list=new ArrayList<Passage>();
		Connection conn=getConnection();
		String sql="select * from tb_passage where attribute=? order by id limit ?,?";
		if(attribute==0)
			sql="select * from tb_passage order by id limit ?,?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			if(attribute!=0){
				ps.setInt(1, attribute);
				ps.setInt(2, (page-1)*Passage.PAGE_SIZE);//偏移量
				ps.setInt(3, Passage.PAGE_SIZE);		//最大结果条数
				//为sql语句赋值
			}else{
				ps.setInt(1, (page-1)*Passage.PAGE_SIZE);//偏移量
				ps.setInt(2, Passage.PAGE_SIZE);		//最大结果条数				
			}
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()){
				Passage passage=new Passage();
				passage.setContent(resultSet.getString("content"));
				passage.setPassagename(resultSet.getString("passagename"));
				passage.setDate(resultSet.getString("date"));
				passage.setId(resultSet.getInt("id"));
				list.add(passage);//将属性信息传入list中
			}
			resultSet.close();
			ps.close();
			conn.close();//关闭资源
			
		} catch (SQLException e) {
			System.out.println("定位：passagedao/find");
			e.printStackTrace();
		
		}
		
 		return list;
	}

	
	//查询总记录数 attribute为0时为全查询
	public int findCount(int attribute){
		int count=0;
		Connection conn=getConnection();
		String sql=" select count(*) from tb_passage where attribute=?";
		if(attribute==0){
			sql=" select count(*) from tb_passage";
		}
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			if(attribute!=0)
				ps.setInt(1, attribute);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);			//以int的形式返回指定列的值
			}
			System.out.println("count的值:"+count);
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("定位：passagedao/paassfindcout");
			e.printStackTrace();
		
		}
		return count;
	}

	public void deletepassage(int passageid){
		Connection conn=getConnection();
		String sql="delete from tb_passage where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, passageid);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("定位：passagedao/deletpassage");
			e.printStackTrace();
		}
	}

}
