/*
* This class is the library object holding all the songs 
* read in from the command line argument in PA4 main. This object
* handles adding and removing songs in the library.
*/


import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Library {
	
	private Set<Song> songList;
	
	public Library() {
	//creates a set to hold the songs 
		songList = new TreeSet<Song>();
		
	}
	public Song getSong(String title) {
	// This method loops through the set to see if there is a song object
	// with the same title as the passed in string and if so returns it otherwise null.
		for (Song song : songList) {
			if (song.getTitle().equals(title)) {
				return song;
			}
		}
		return null;		
	}
	
	public List<Song> getAllSongs(){
	// casts the set of songs to a List and returns that.
		return (List<Song>) songList;
	}
	
	public void addSong(Song song) {
	// This method checks if a previous instance of a song object exists and removes
	// it before adding the new song. If one doesn't exist the new song is added.
		if(songList.contains(song)) {
			songList.remove(song);
		}
		songList.add(song);
	}
	
	public void removeSong(Song song) {
	// Removes the passed in song object from the set.
		songList.remove(song);
	}
	
	public String toString() {
	// This method returns a String representation of the song library
	// using the String method in the Song class in the form of
	// [song] by [song artist], [number of] play(s).
		String library = "";
		for (Song song : songList) {
			library += song.toString() + "\n";
		}
		return library;
	}
}

