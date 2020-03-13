package factoryManager;
import java.util.Random;

import resto.Level;

public class ZombieManager {
	
	private int zombiesLeftToAppear;
	private float frecuencia;
	public ZombieManager(Level nivel){ 
		if(nivel == Level.EASY){
			zombiesLeftToAppear = 3;
			frecuencia = (float) 0.1;
		}
		else if(nivel == Level.HARD){
			zombiesLeftToAppear = 5;
			frecuencia = (float) 0.2;
		}
		else {
			zombiesLeftToAppear = 10;
			frecuencia = (float) 0.3;
		}
	}
	
	public boolean isZombieAdded(Random rnd){
		int valor = rnd.nextInt(10); //Devuelve un entero entre 0 y 9
		float aux = frecuencia * 10;
		if (valor < (int)aux && zombiesLeftToAppear > 0){
			zombiesLeftToAppear--;
			return true;
		}
		else return false;
	}

	public int getZombiesLeftToAppear() {
		return zombiesLeftToAppear;
	}

	public void setZombiesLeftToAppear(int zombiesLeftToAppear) {
		this.zombiesLeftToAppear = zombiesLeftToAppear;
	}
		
}
