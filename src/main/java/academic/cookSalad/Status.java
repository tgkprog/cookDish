package academic.cookSalad;

public class Status {
	private Ingredients leftOverIngrediants;//Ingredients passed in minus what was used. Or same what was passed in case too few ingredients

	private   boolean success;//false if could not make else true

	public Ingredients getLeftOverIngrediants() {
		return leftOverIngrediants;
	}

	public void setLeftOverIngrediants(Ingredients leftOverIngrediants) {
		this.leftOverIngrediants = leftOverIngrediants;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
