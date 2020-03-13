package resto;

public enum Level {
	
	EASY,
	HARD,
	INSANE;
	
	
	public static Level parse(String inputString) {
		for (Level level : Level. values() )
		if (level . name().equalsIgnoreCase(inputString)) return level;
		return null;
		}


}
