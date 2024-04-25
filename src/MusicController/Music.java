package MusicController;

public class Music {
	
	private String path; 
	private String title;	
	
	
	
	// 2. method
	
	
	public Music(String path, String title) {
		super();
		this.path = path;
	
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	
	public String getTitle() {
		return title;
	}
}
