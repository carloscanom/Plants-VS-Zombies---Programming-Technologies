package elementos;

public class Sunflower extends Plant {
	
	protected static final int coste = 20;
	protected static final int danyo = 0;
	protected static int vida = 1;
	protected static int frecuencia = 2;
	protected static final String nombre = "Sunflower";
	protected static final String mote = "S";
	protected static final String ayuda = "[S]unflower: Cost: 50 suncoins  Harm: 0";

	public Sunflower(int x, int y) {
		super(coste, frecuencia, vida, x, y, danyo, nombre,mote);
		
	}
	
	public Sunflower(){
		super(nombre,mote,ayuda,coste);
	}
	
	
	@Override
	public void update() {
		ciclos++;
		if(ciclos%frecuencia == 0){
			this.getJuego().anyadeSoles(10);
		}
		
	}
	
	@Override
	public Plant getPlanta(int x, int y) {
		return new Sunflower(x, y);
	}

}
