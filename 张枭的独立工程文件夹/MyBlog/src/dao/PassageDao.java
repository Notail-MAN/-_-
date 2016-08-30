package dao;
/**
 * �й�passage��dao�ļ������ȫ���²�ѯ�����޸�
 */
import java.sql.*;
import java.util.ArrayList;
import util.*;;
public class PassageDao {
	
	
	//�������ݿ⣬�ú����õ���connection����
	public Connection getConnection(){
		Connection conn=null;
		try{
			String URL="jdbc:mysql://127.0.0.1:3306/db_myblog";
			String USER="root";
			String PASSWORD="zx123456";
			Class.forName("com.mysql.jdbc.Driver");
			conn= DriverManager.getConnection(URL, USER, PASSWORD);
		}catch (ClassNotFoundException e) {
			System.out.println("��λ��passagedao/getconnection");
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println("��λ��passagedao/getconnection");
			e.printStackTrace();
		}
		return conn;
		
	}

	
	//��ҳ�����ѯ��    pageΪ��ѯ��ҳ����attributeΪ����Ŀ���ض�����
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
				ps.setInt(2, (page-1)*Passage.PAGE_SIZE);//ƫ����
				ps.setInt(3, Passage.PAGE_SIZE);		//���������
				//Ϊsql��丳ֵ
			}else{
				ps.setInt(1, (page-1)*Passage.PAGE_SIZE);//ƫ����
				ps.setInt(2, Passage.PAGE_SIZE);		//���������				
			}
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()){
				Passage passage=new Passage();
				passage.setContent(resultSet.getString("content"));
				passage.setPassagename(resultSet.getString("passagename"));
				passage.setDate(resultSet.getString("date"));
				passage.setId(resultSet.getInt("id"));
				list.add(passage);//��������Ϣ����list��
			}
			resultSet.close();
			ps.close();
			conn.close();//�ر���Դ
			
		} catch (SQLException e) {
			System.out.println("��λ��passagedao/find");
			e.printStackTrace();
		
		}
		
 		return list;
	}

	
	//��ѯ�ܼ�¼�� attributeΪ0ʱΪȫ��ѯ
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
				count=rs.getInt(1);			//��int����ʽ����ָ���е�ֵ
			}
			System.out.println("count��ֵ:"+count);
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("��λ��passagedao/paassfindcout");
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
			System.out.println("��λ��passagedao/deletpassage");
			e.printStackTrace();
		}
	}

}
