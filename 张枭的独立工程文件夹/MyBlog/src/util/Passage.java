package util;
/**
 * ���µ�ʵ����
 * ���������������������ݡ�������������
 * ��ҳ
 * 
 * �ر�涨��	��������ΪΪ����1  ������Ϊ����2   ���Ϸ���Ϊ����3
 * 
 */
public class Passage {
	private String passagename;
	private String content;
	private int attribute;
	private int id;
	
	public static final int PAGE_SIZE=2;//Ϊ��ҳ����׼��
	
	public Passage(){
		
	}
	public Passage(String passagename,String describe,String content,int attribute){
		
		this.passagename=passagename;
		this.content=content;
		this.attribute=attribute;
	}
	
	
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassagename() {
		return passagename;
	}
	public void setPassagename(String passagename) {
		this.passagename = passagename;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAttribute() {
		return attribute;
	}
	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}
	
	
}
