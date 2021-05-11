/*
 * This class represents a user logged into the Capriottify app.
 * They have a username, password and a collection ofplaylists tied to their account
 * as well as the total number.
 */



import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String name;
	private String password;
	private List<Playlist> myPlayLists;
	private int totalPlaylists;
	
	public User(String name, String password) {
		// Creates a new user object with the passed in
		// username, password and creates a new empty
		// list for potential created playlists.
		this.name = name;
		this.password = password;
		myPlayLists = new ArrayList<Playlist>();		
	}
	
	public String getName() {
		// This returns the user's name in a String.
		return name;
	}
		
	
	public boolean attemptLogin(String password) {
		// Checks if the passed in password matches the 
		// one with the given user. Returns true or false.
		if (password.equals(this.password)) {
			return true;
		}
		return false;
	}
	
	public int getPlaylistNumber() {
		// Returns the total amount of playlists tied to a user account.
		return totalPlaylists;
	}
	
	public void increasePlaylistCount() {
		// Increments the amount of playlists tied to a user
		// after the creation of a new playlist.
		totalPlaylists += 1;
	}
	
	public void addPlaylist(Playlist newPlaylist) {
		// Adds a new playlist to the collection of playlists and increases
		// the amount of playlists tied to a user.
		myPlayLists.add(newPlaylist);
		increasePlaylistCount();
	}
	
	public List<Playlist> getPlaylists() {
		// Returns the list of playlists tied to a user.
		return myPlayLists;
	}
	
	public void selectPlaylist(String name) {
		// If a playlist with the name matching the
		// parameter passed in exists in the collection
		// of playlists then that playlist is played.
		for (Playlist playlist : myPlayLists) {
			if (playlist.getName().equals(name)) {
				playlist.play();
			}
		}
	}
	
	public String toString() {
		// Returns a string stating the users name and the 
		// number of playlists tied to that user.
		return name + ", " + totalPlaylists + " playlists.";
	}
}
