package dto;

public class StatDTO {
	private String id;
	private String nickname;
	private int intellect;
	private int cs;
	private int algorithm;
	private int health;
	private int license;
	private int day;
	
	public StatDTO() {
		
	}
	
	public StatDTO(String id, String nickname,int intellect, int cs, int algorithm, int health, int license, int day) {
		this.id = id;
		this.nickname = nickname;
		this.intellect = intellect;
		this.cs = cs;
		this.algorithm = algorithm;
		this.health = health;
		this.license = license;
		this.day = day;
	}
	
	

	public StatDTO(String id, String nickname) {
		this.id = id;
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public int getIntellect() {
		return intellect;
	}

	public void setIntellect(int intellect) {
		this.intellect = intellect;
	}

	public int getCs() {
		return cs;
	}

	public void setCs(int cs) {
		this.cs = cs;
	}

	public int getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(int algorithm) {
		this.algorithm = algorithm;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getLicense() {
		return license;
	}

	public void setLicense(int license) {
		this.license = license;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	
	
}