package elementos;

public class Peashooter extends Plant{
	
	protected static final int coste = 50;
	protected static final int danyo = 1;
	protected static int vida = 3;
	protected static final int frecuencia = 1;
	protected static final String nombre = "Peashooter";
	protected static final String mote = "P";
	protected static final String ayuda = "[P]eashooter: Cost: 50 suncoins  Harm: 1";
	public Peashooter(int x, int y) {
		super(coste, frecuencia, vida, x, y, danyo, nombre,mote);
	}
	
	public Peashooter(){
		super(nombre,mote,ayuda,coste);
	}

	@Override
	public void update() {
		ciclos++;
		if (ciclos % frecuencia == 0){
			this.getJuego().ataqueNormalDerecha(this.getX(), this.getY(), danyo);
		}
		
	}

	@Override
	public Plant getPlanta(int x, int y) {
		return new Peashooter(x, y);
		
	}
	
}
