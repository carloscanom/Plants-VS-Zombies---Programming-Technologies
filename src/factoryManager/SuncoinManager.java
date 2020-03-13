package factoryManager;

public class SuncoinManager {
	
private int suncoins;
	
	public SuncoinManager(){
		suncoins = 50;
	}

	public int getSuncoins() {
		return suncoins;
	}

	/*public boolean pagarSuncoins(int suncoins) {
		if (this.suncoins >= suncoins){
		this.suncoins -= suncoins;
		return true;
		}
		
		else return false;
	}*/

	public void setSuncoins(int suncoins) {
		this.suncoins = suncoins;
	}

}
