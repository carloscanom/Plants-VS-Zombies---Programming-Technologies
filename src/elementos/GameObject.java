package elementos;
import resto.Game;

public abstract class GameObject {
	protected int x;
	protected int frecuencia;
	protected int y;
	protected int vida;
	protected int ciclos;
	protected int danyo;
	protected String nombre;
	protected Game juego;
	protected String mote;
	protected String ayuda;
	
	public GameObject(int x, int y, int vida, int frecuencia, int danyo, String nombre, String mote){
		this.x = x;
		this.y = y;
		this.vida = vida;
		this.ciclos = 0;
		this.nombre = nombre;
		this.frecuencia = frecuencia;
		this.danyo = danyo;
		this.mote = mote;
	}
	
	public GameObject(String nombre, String mote, String ayuda){
		this.nombre = nombre;
		this.mote = mote;
		this.ayuda = ayuda;
	}
	
	
	public int getVida() {
		return this.vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}


	public int getCiclos() {
		return this.ciclos;
	}

	public void setCiclos(int ciclos) {
		this.ciclos = ciclos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public abstract void update();
	
	public String getAyuda(){
		return this.ayuda;
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}

	public Game getJuego() {
		return juego;
	}
	
	public boolean quitarVida(int danyo){
		this.vida -= danyo;
		return this.vida <= 0;
	}
	
	public String info(){
        return ""+mote+"[l:"+vida+",x:"+x+",y:"+y+",t:"+(frecuencia - ciclos%frecuencia)+"]";
    }

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getMote() {
		return mote;
	}
	
	public String infoRelease(){
        return ""+mote+"[" +this.getVida()+"]";
    }
	
	public void setGame(Game juego){
		this.juego=juego;
	}

	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}
	
	
	
	
	
}

