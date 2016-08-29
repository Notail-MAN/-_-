package util;
/**
 * 文章的实体类
 * 包含了文章名、文章内容、文章类别的属性
 * 分页
 * 
 * 特别规定：	碎言碎语为为类型1  慢生活为类型2   资料分享为类型3
 * 
 */
public class Passage {
	private String passagename;
	private String content;
	private int attribute;
	private int id;
	
	public static final int PAGE_SIZE=2;//为分页功能准备
	
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
