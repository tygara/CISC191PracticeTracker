public class SongExercise extends Exercise {

  private final String songTitle;
  private final String artist;

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

  public String getSongTitle() {
    return songTitle;
  }

  public String getArtist() {
    return artist;
  }

  @Override
  public String getCategory() {
    return "Song";
  }
}

