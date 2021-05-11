/*
 * This class creates a unique object for each song read in from the 
 * list of songs taken in the command line argument in PA4 Main.
 */
public class Song implements Comparable {

	private String songName;
	private String artist;
	private int playCount;
	
	public Song(String title, String artist) {
		// Creates a new Song object with a given song name, 
		// song artist and defaults the amount of times played to 0.
		songName = title;
		this.artist = artist;
		playCount = 0;
	}
	
	public String getTitle() {
		// Returns the title of the song.
		return songName;
	}
	
	public String getArtist() {
		// Returns the artist of the song.
		return artist;
	}
	
	public int getTimesPlayed() {
		// Returns the amount of times a song is played.
		return playCount;
	}
	
	public void playCount() {
		// Increments the amount of times a song is played by 1.
		playCount += 1;
	}
	@Override
	// This method is used to compare song objects when being
	// added to the TreeSet in the Library class so the songs are in
	// in alphabetical order.
	public int compareTo(Object o) {
		Song song = (Song) o;
		return this.songName.compareTo(song.getTitle());
	}
	
	public void play() {
	// This method "plays" the song by printing out a representation 
	// of the song and increments the play count.
		System.out.println(toString());
		playCount();
	}
	
	public String toString() {
		// Prints out a representation of the song, artist and number
		// of times played.
		return (songName + " by " + artist + ", " + playCount + "play(s)");
	}
}
