/**
 * Represents an arpeggio exercise
 *
 * IS-A: ArpeggioExercise is an Exercise.
 * HAS-A: Arpeggio has a chord symbol and target tempo
 */
public class ArpeggioExercise extends Exercise {

  private final String chordSymbol;  
  private final int targetTempoBpm;

/**
 * Creates a new ArpeggioExercise
 *
 * @param name the name of the exercise 
 * @param targetMinutesPerDay daily practice goals in minutes
 * @param chordSymbol chord symbol associated with the arpeggio 
 * @param targetTempoBpm target tempo in beats per minute
 * @throws IllegalArgumentException if chordSymbol is null/blank or if targetTempo is not positve chord symbol associated with the arpeggio 
 */
  public ArpeggioExercise(String name,
                          int targetMinutesPerDay,
                          String chordSymbol,
                          int targetTempoBpm) {
    super(name, targetMinutesPerDay);

    if(chordSymbol == null || chordSymbol.isBlank()) {
      throw new IllegalArgumentException("chordSymbol must not be blank");
    }
    
    if(targetTempoBpm <= 0) {
      throw new IllegalArgumentException("targetTempoBpm must be > 0");
    }

    this.chordSymbol = chordSymbol;
    this.targetTempoBpm = targetTempoBpm;
  }

/** 
 *  Returns the chordSymbol linked to this exercise 
 *
 *  @return the chord symbol
 */
  public String getChordSymbol() {
    return chordSymbol;
  }


/**
 *  Returns target tempo for this exercise.
 *  @return tempo in beats per minute
 */
  public int getTargetTempoBpm() {
    return targetTempoBpm;
  }

/**
 *  Returns exercise category
 *  @return Arpeggio (name of this exercise)
 */
  @Override
  public String getCategory() {
    return "Arpeggio";
  }
}
