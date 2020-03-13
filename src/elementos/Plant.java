package elementos;

public abstract class Plant extends GameObject{
	
	protected int coste;
	
	public Plant(int coste, int frecuencia, int vida, int x, int y, int danyo, String nombre, String mote){
		super(x,y,vida,frecuencia,danyo,nombre,mote);
		this.coste = coste;
	}
	
	public Plant(String nombre, String mote, String ayuda, int coste){
		super(nombre, mote, ayuda);
		this.coste = coste;
	}
	
	public int getCoste() {
		return this.coste;
	}

	
	public abstract void update();
	

	
	/*public Plant parse(String args){    //////////////////////////////////////
		return null;
	}*/
	
	public abstract Plant getPlanta(int x, int y);

	public String resumen() {
		// TODO Auto-generated method stub
		return null;
	}


		
}
