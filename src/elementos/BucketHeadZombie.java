package elementos;

public class BucketHeadZombie extends Zombie{
	
	protected static final int vida = 8;
	protected static final int frecuencia = 4;
	protected static final String nombre = "Caracubo";
	protected static final String mote = "W";
	protected static final String ayuda = "[Z]ombie caracubo: Speed: 4, Harm: 1, Life: 8";
	
	public BucketHeadZombie(int x, int y){
		super(x, y, vida, frecuencia, nombre, mote);
	}
	
	public BucketHeadZombie(){
		super(nombre,mote,ayuda);
	}

	@Override
	public Zombie getZombie(int x, int y) {
		return new BucketHeadZombie(x,y);
	}


}
