package elementos;

public class Cherrybomb extends Plant{

	protected static final int coste = 50;
	protected static final int danyo = 10;
	protected static int vida = 2;
	protected static final int frecuencia = 2;
	protected static final String nombre = "Petacereza";
	protected static final String mote = "C";
	protected static final String ayuda = "Peta[c]ereza: Cost: 50 suncoins  Harm: 10";

	public Cherrybomb(int x, int y) {
		super(coste, frecuencia, vida, x, y, danyo, nombre,mote);
	}
	
	public Cherrybomb(){
		super(nombre,mote,ayuda,coste);
	}

	@Override
	public void update() {
		ciclos++;
		if (ciclos % frecuencia == 0){
			this.getJuego().ataqueCasillasAlrededor(this.getX(), this.getY(), danyo);
		}
		
	}

	@Override
	public Plant getPlanta(int x, int y) {
		return new Cherrybomb(x, y);
	}

}
