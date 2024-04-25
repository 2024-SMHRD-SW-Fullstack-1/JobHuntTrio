package dto;

public class MemberDTO {
	
	private String id;
	private String pw;
	private String name;
	private String gender;
	private int age;
	
	
	public MemberDTO(String id, String pw, String name, String gender, int age) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	
	public MemberDTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
		
	}


	public MemberDTO(String id2) {
		this.id = id;
		
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
	
}
