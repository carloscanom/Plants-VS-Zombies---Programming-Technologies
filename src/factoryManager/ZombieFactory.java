package factoryManager;
import java.util.Random;

import elementos.BucketHeadZombie;
import elementos.CommonZombie;
import elementos.SportyZombie;
import elementos.Zombie;

public class ZombieFactory {
	
	private static Zombie [] zombiesDisponibles = {
			new CommonZombie(),
			new BucketHeadZombie(),
			new SportyZombie()
		};
	
	public static Zombie getZombie(String nombreZombie) {
		boolean ok = false;
		int i = 0;
		while(i < zombiesDisponibles.length && !ok){
			if(zombiesDisponibles[i].getNombre().equalsIgnoreCase(nombreZombie)  || zombiesDisponibles[i].getMote().equalsIgnoreCase(nombreZombie)){
				ok = true;
				return zombiesDisponibles[i];
			}
			
			i++;
		}
		return null;
	}
	
	public static Zombie aVerCualCojoBro(){
		Random hola;
		hola = new Random();
		return zombiesDisponibles[hola.nextInt(zombiesDisponibles.length)];
	}
	
	public static String infoAvailableZombies(){
        String aux="";
        for(Zombie z: zombiesDisponibles)
            aux = aux + z.info()+"\n";
        
        return aux;
    }

}
