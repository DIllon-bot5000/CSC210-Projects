/*
 * This class acts a a playlist object inside the Capriottify program
 * after being created by a logged in user. Each playlist is created
 * with a name and either an empty list of songs or a list already chosen by
 * the user. Users can add songs, remove songs, 
 * "play" songs and shuffle the order of the songs in the playlist.
 */



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Playlist {
	
	private String playlistName;
	private List <Song> playList;
	
	public Playlist(String name) {
		// Creates the playlist with the passed in name
		// and an empty list of songs.
		playlistName = name;
		playList = new ArrayList<Song>();
		
	}
	
	public Playlist(String name, List<Song> contents) {
		// Creates the playlist with the passed in name 
		// and preselected songs contains in another playlist.
		playlistName = name;
		playList = contents;
	}
	
	public String getName() {
		// Returns the name of the playlist.
		return playlistName;
	}
	public void addSong(Song song) {
		// Adds the passed in song object to the playlist.
		playList.add(song);
	}
	
	public void play() {
		// Loops through the list of songs and plays each by
		// incrementing the play count in the Song object.
		for (Song song : playList) {
			song.play();
		}
	}
	
	public void shuffle() {
		// Shuffles the order of the the songs in the playlist
		// for the next play.
		Collections.shuffle(playList);
	}
	
	public void removeSong(Song song) {
		// Removes a passed in song object from the playlist.
		if (playList.contains(song)) {
			int index = playList.indexOf(song);
			playList.remove(index);
		}
	}
}
