/*
 * This class creates an object that is a collection
 * of all the users that exist using Capriottify during a single
 * run of the program.
 */



import java.util.ArrayList;
import java.util.List;

public class UserCollection {
	private List<User> userCollection;
	
	public UserCollection() {
		// Creates a list to hold all the users
		// that exist in this run of the program.
		userCollection = new ArrayList<User>();
	}
	
	public boolean userExists(String username) {
		// This method iterates through the users in a session 
		// and if one exists matching the passed in username returns true.
		// Otherwise this returns false.
		for (User users : userCollection) {
			if (users.getName().equals(username)) {
			return true;
			}
		}
		return false;
	}
	
	public User login(String username, String password) {
		// This method takes in String username and password and checks
		// to see if they match the username and password of an User object inside
		// the collection. If there is a match, that user object is returned, null otherwise.
		for (User users : userCollection) {
			if (users.getName().equals(username) && users.attemptLogin(password) == true) {
				return users;
			}
		}
		return null;
	}
	
	public void addUser(User add) {
		// Adds a new User object to the collection.
		userCollection.add(add);
	}
	
	public String toString() {
		// This method returns a String showing each user, their name and the number
		// of playlists associated with that user.
		String contents = "{ ";
		for (User users : userCollection) {
			contents += users.getName() + ":" + users.getName() + ", " + users.getPlaylistNumber() + " playlists, ";
		}
		
		return contents + "}";
		}
	}
