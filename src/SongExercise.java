/**
 * Represents a song-based exercise
 * 
 * IS-A: SongExercise is an Exercise.
 * HAS-A: SongExercise has a song title and an artist.
 */
public class SongExercise extends Exercise {

  private final String songTitle;
  private final String artist;

  /**
   * Creates a new SongExercise.
   *
   * @param name the exercise name
   * @param targetMinutesPerDay daily practice goal in minutes
   * @param songTitle the title of the song being practiced
   * @param artist the artist or composer of the song
   * @throws IllegalArgumentException if songTitle or artist are blank
   */
  public SongExercise(String name,
                      int targetMinutesPerDay,
                      String songTitle,
                      String artist) {
    super(name, targetMinutesPerDay);

    if (songTitle == null || songTitle.isBlank()) {
      throw new IllegalArgumentException("songTitle must not be blank");
    }
    if (artist == null || artist.isBlank()) {
      throw new IllegalArgumentException("artist must not be blank");
    }

    this.songTitle = songTitle;
    this.artist = artist;
  }



  /**
   * Returns the title of the song practiced.
   *
   * @return the song title
   */
  public String getSongTitle() {
    return songTitle;
  }


  
  /**
   * Returns the artist or composer of the song.
   *
   * @return the artist name
   */
  public String getArtist() {
    return artist;
  }



  /**
   * Returns the exercise category.
   *
   * @return "Song"
   */
  @Override
  public String getCategory() {
    return "Song";
  }
}

