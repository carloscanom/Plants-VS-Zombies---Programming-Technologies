package elementos;

public class WallNut extends Plant{

	protected static final int coste = 50;
	protected static final int danyo = 0;
	protected static int vida = 10;
	protected static final int frecuencia = 1;
	protected static final String nombre = "Nuez";
	protected static final String mote = "N";
	protected static final String ayuda = "[N]uez: Cost: 50 suncoins  Harm: 0";

	public WallNut(int x, int y) {
		super(coste, frecuencia, vida, x, y, danyo, nombre,mote);
	}
	
	public WallNut(){
		super(nombre,mote,ayuda,coste);
	}

	@Override
	public void update() {
		ciclos++;
	}

	@Override
	public Plant getPlanta(int x, int y) {
		return new WallNut(x, y);
	}

}
