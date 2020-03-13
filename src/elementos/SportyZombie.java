package elementos;

public class SportyZombie extends Zombie{
	
	protected static final int vida = 2;
	protected static final int frecuencia = 1;
	protected static final String nombre = "Deportista";
	protected static final String mote = "X";
	protected static final String ayuda = "[Z]ombie deportista: Speed: 1, Harm: 1, Life: 2";
	
	public SportyZombie(int x, int y){
		super(x, y, vida, frecuencia, nombre,mote);
	}
	
	public SportyZombie(){
		super(nombre,mote,ayuda);
	}

	@Override
	public Zombie getZombie(int x, int y) {
		return new SportyZombie(x,y);
	}


}
