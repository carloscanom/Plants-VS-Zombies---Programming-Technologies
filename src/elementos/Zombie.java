package elementos;

public abstract class Zombie extends GameObject{
	
	protected static final int danyo = 1;


	public Zombie(int x, int y, int vida, int frecuencia, String nombre, String mote) {
		super(x, y, vida, frecuencia, danyo, nombre,mote);
	}
	
	public Zombie(String nombre, String mote, String ayuda){
		super(nombre,mote,ayuda);
	}
	
	public void update(){
		ciclos++;
		if(ciclos % frecuencia == 0){
			if(juego.celdaVacia(this.x, this.y - 1)){
				juego.zombieSeMueve(this.x, this.y, this);
			}
			else{
				juego.zombieAtaca(this.x, this.y, danyo);
			}
		}
		
	}

	public abstract Zombie getZombie(int x, int y);
	
	
	
	
	
	
}
