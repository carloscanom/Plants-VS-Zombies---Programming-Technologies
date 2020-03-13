package elementos;

public class CommonZombie extends Zombie{
	
	protected static final int danyo = 0;
	protected static final int vida = 5;
	protected static final int frecuencia = 2;
	protected static final String nombre = "Común";
	protected static final String mote = "Z";
	protected static final String ayuda = "[Z]ombie común: Speed: 2, Harm: 1, Life: 5";
	
	public CommonZombie(int x, int y){
		super(x, y, vida, frecuencia, nombre,mote);
	}
	
	public CommonZombie(){
		super(nombre,mote,ayuda);
	}

	@Override
	public Zombie getZombie(int x, int y) {
		return new CommonZombie(x,y);
	}

}
