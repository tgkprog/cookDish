package academic.cookSalad;

public class Ingredients implements Cloneable {

	private int carrotQty;
	private int iceBergLettuceQty;

	private int chickenQty;

	private int beansQty;

	public int getCarrots() {
		return carrotQty;
	}

	public void setCarrots(int carrotQty) {
		this.carrotQty = carrotQty;
	}

	public int getIceBergLettuce() {
		return iceBergLettuceQty;
	}

	public void setIceBergLettuce(int iceBergLettuceQty) {
		this.iceBergLettuceQty = iceBergLettuceQty;
	}

	public int getChickens() {
		return chickenQty;
	}

	public void setChickens(int chickenQty) {
		this.chickenQty = chickenQty;
	}

	public int getBeans() {
		return beansQty;
	}

	public void setBeans(int beansQty) {
		this.beansQty = beansQty;
	}

	public Object clone() throws CloneNotSupportedException{		
		return super.clone();
	}
	
	public boolean equalValues(Ingredients other){
		return this.beansQty == other.beansQty &&
				this.carrotQty == other.carrotQty && this.chickenQty == other.chickenQty
				&& this.iceBergLettuceQty == other.iceBergLettuceQty;
	}
}
