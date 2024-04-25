package MusicController;

import java.util.ArrayList;

import javazoom.jl.player.MP3Player;

public class MusicController {
	private MP3Player mp3 = new MP3Player();
	private ArrayList<Music> musicList = new ArrayList<Music>();
	private int i=0;
	
	public MusicController() {
		String musicPath = ".\\player\\";
		musicList.add(new Music(musicPath+"main.mp3"));
		
	}
	
	public Music play() {
		if(mp3.isPlaying()) {
			mp3.stop();
		}
		mp3.play(musicList.get(i).getPath());
		return musicList.get(i);
	}
	
	public boolean stop() {
		boolean result = false;
		if(mp3.isPlaying()) {
			mp3.stop();
			result = true;
		}
		return result;
	}
}
