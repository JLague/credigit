package terminal.gpio.buzzer;

/**
 * 
 * @author Bank-era Corp.
 * 
 * Enum permettant d'énumerer les sons possibles pour le buzzer
 *
 */
public enum BuzzerSounds {
	
	CONFIRME(new int[] {532, 698}, new int[] {2, 1}, 250), REFUSE(new int[] {305}, new int[] {3}, 500);
	
	/**
	 * Les notes composant le son
	 */
	private int[] notes;
	
	/**
	 * Le temp pour chaque note
	 */
	private int[] temps;
	
	/**
	 * La durée d'une note (en ms)
	 */
	private int duration;
	
	private BuzzerSounds(int[] notes, int[] temps, int duration)
	{
		if(notes.length == temps.length) {
			this.notes = notes;
			this.temps = temps;
			this.duration = duration;
		}
		else
			throw new IllegalArgumentException("The notes and times differ in length.");
		
	}
	
	public int[] getNotes() {
		return this.notes;
	}
	
	public int[] getTemps() {
		return this.temps;
	}
	
	public int getDuration() {
		return this.duration;
	}
}
