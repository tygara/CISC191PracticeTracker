public class ArpeggioExercise extends Exercise {

  private final String chordSymbol;  
  private final int targetTempoBpm;

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

  public String getChordSymbol() {
    return chordSymbol;
  }

  public int getTargetTempoBpm() {
    return targetTempoBpm;
  }

  @Override
  public String getCategory() {
    return "Arpeggio";
  }
}
