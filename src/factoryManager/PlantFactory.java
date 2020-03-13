package factoryManager;
import elementos.Cherrybomb;
import elementos.Peashooter;
import elementos.Plant;
import elementos.Sunflower;
import elementos.WallNut;

public abstract class PlantFactory {
	
	private static Plant [] plantasDisponibles = {
		new Sunflower(),
		new Peashooter(),
		new Cherrybomb(),
		new WallNut()
	};

	public static Plant getPlanta(String nombrePlanta) {
		boolean ok = false;
		int i = 0;
		while(i < plantasDisponibles.length && !ok){
			if(plantasDisponibles[i].getNombre().equalsIgnoreCase(nombrePlanta)  || plantasDisponibles[i].getMote().equalsIgnoreCase(nombrePlanta)){
				ok = true;
				return plantasDisponibles[i];
			}
			
			else i++;
		}
		return null;
	}

	public static String infoAvailablePlants(){
        String aux="";
        for(Plant p: plantasDisponibles)
            aux = aux + p.info()+"\n";
        
        return aux;
    }
	
}
