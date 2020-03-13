package resto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import elementos.ListaPlantas;
import elementos.ListaZombies;
import elementos.Plant;
import elementos.Zombie;
import excepciones.FileContentsException;
import factoryManager.PlantFactory;
import factoryManager.SuncoinManager;
import factoryManager.ZombieFactory;
import factoryManager.ZombieManager;

public class Game {
	
	private Tablero tablero;
	private SuncoinManager dinerito;
	private int filas;
	private int columnas;
	private ZombieManager zombies;
	private Level nivel;
	private int ciclos;
	private long semilla;
	private Random aleatorio;
	
	public Game(Level nivel, long semilla, int x, int y){
		this.nivel = nivel;
		this.semilla = semilla;
		this.filas = x;
		this.columnas = y;
		aleatorio = new Random(semilla);
		tablero = new Tablero(x,y,this);
		zombies = new ZombieManager(nivel);
		dinerito = new SuncoinManager();
	}
	
	public Game(Level nivel, int x, int y){
		this.nivel = nivel;
		this.semilla = -1;
		this.filas = x;
		this.columnas = y;
		aleatorio = new Random();
		tablero = new Tablero(x,y,this);
		zombies = new ZombieManager(nivel);
		dinerito = new SuncoinManager();
	}

	public boolean celdaVacia(int x, int y) {
		return tablero.casillaVacia(x, y);
	}

	public void zombieAtaca(int x, int y, int danyo) {
		tablero.ZombieAtaca(x, y, danyo);
	}

	public void addPlanta(Plant planta, int x, int y) {
		planta.setX(x);
		planta.setY(y);
		Plant p = planta.getPlanta(x, y);
		tablero.addPlant(p);
	}
	
	public void addPlanta(Plant planta){
		Plant p = planta.getPlanta(planta.getX(), planta.getY());
		tablero.addPlant(p);
	}
	

	public boolean hayUnZombie(int x, int y) {
		return tablero.hayUnZombie(x, y);
		
	}

	public void ataqueNormalDerecha(int x, int y, int fuerza){
		tablero.ataqueNormalDerecha(x, y, fuerza);
	}
	
	public void ataqueCasillasAlrededor(int x, int y, int fuerza){
		tablero.ataqueCasillasAlrededor(x, y, fuerza);
	}

	public boolean suficientesSuncoins(int coste) {
		
		if (dinerito.getSuncoins() >= coste){
			dinerito.setSuncoins(dinerito.getSuncoins() - coste);
			return true;
			}
			
			else return false;
		
	}

	public void reset() {
		ciclos = 0;
		if(this.semilla == -1)
			aleatorio = new Random();
		else 
			aleatorio = new Random(this.semilla);
		
		tablero = new Tablero(this.filas,this.columnas,this);
		zombies = new ZombieManager(nivel);
		dinerito = new SuncoinManager();
		System.out.println(this.getCabecera() + this.getTablero());
	}
	
	public void update(){
		
		tablero.update();
		ciclos++;
		
		if(zombies.isZombieAdded(aleatorio)){
			int ale = aleatorio.nextInt(this.filas);
			while(!tablero.casillaVacia(ale, columnas - 1)){
				ale = aleatorio.nextInt(this.filas);
			}
			Zombie z = ZombieFactory.aVerCualCojoBro();
			z.setX(ale);
			z.setY(columnas - 1);
			Zombie aux = z.getZombie(ale, columnas - 1);
			tablero.addZombie(aux);
			
		}
		
		//tablero.update();
	//	ciclos++;
	}

	public void anyadeSoles(int suncoins) {
		dinerito.setSuncoins(dinerito.getSuncoins()+suncoins);
		
	}
	
	public boolean terminado() {
		return (zombies.getZombiesLeftToAppear() == 0 && tablero.zombiesLeftToAppear() == 0) || tablero.gananZombies();										
		
	}
	
	public boolean gananZombies(){
		return tablero.gananZombies();
	}
	public String getTablero() {

        return tablero.toString();

    }

    public String getCabecera() {
        return "Number of cycles: " + ciclos
                + "\nSuncoins: " + dinerito.getSuncoins()
                + "\nRemaining zombies: " + zombies.getZombiesLeftToAppear()
                + "\nLevel: " + this.nivel
                + "\nSeed: " + this.semilla + "\n\n";

    }
    
    public String getInfoEnListas(){
    	return tablero.getInfoListas();
    }

	public void zombieSeMueve(int x, int y, Zombie zombie) {
		tablero.zombieSeMueve(x,y,zombie);
		
	}

	public void load(BufferedReader in) throws FileContentsException {
		try {
			
			String [] palabras;
			
			palabras = MyStringUtils.loadLine(in, "cycle", false);
			int ciclitos = Integer.parseInt(palabras[0]) - 1;
			if(this.ciclos < 0) throw new FileContentsException("Numero de ciclo incorrecto");
			
			palabras = MyStringUtils.loadLine(in, "sunCoins", false);
			int dineros = Integer.parseInt(palabras[0]);
			if(dineros < 0) throw new FileContentsException("Numero de suncoins incorrecto");
			/*else{
				dinerito = new SuncoinManager();
				this.dinerito.setSuncoins(dineros);
			}*/

			palabras = MyStringUtils.loadLine(in, "level", false);
			Level nivelito = Level.parse(palabras[0]);
			if(nivelito == null) throw new FileContentsException("Nivel incorrecto");
			//this.nivel = nivelito;
			
			palabras = MyStringUtils.loadLine(in, "remZombies", false);
			int remZombies = Integer.parseInt(palabras[0]);
			if(remZombies < 0) throw new FileContentsException("Remaining zombies incorrecto");
			/*this.zombies = new ZombieManager(this.nivel);
			this.zombies.setZombiesLeftToAppear(remZombies);*/
			
			
			//PLANTLIST
			palabras = MyStringUtils.loadLine(in, "plantList", true);
			ListaPlantas plantlistita = new ListaPlantas();
			
			for(int i = 0; i < palabras.length; i++){
				String [] palabritas;
				palabritas = palabras[i].split(":");
				if (palabritas.length != 5) throw new FileContentsException("Número de argumentos inválido en la lista de plantas");
				String mote = palabritas[0];
				int vidaRestante = Integer.parseInt(palabritas[1]);
				if(vidaRestante <= 0) throw new FileContentsException("Vida muy escasita");
				int x = Integer.parseInt(palabritas[2]);
				int y = Integer.parseInt(palabritas[3]);
				if((x < 0 && x >= 4) && (y < 0 && y >= 7)) throw new FileContentsException("x o y posiciones invalidas");
				int ciclosRes = Integer.parseInt(palabritas[4]);
				
				Plant planta = PlantFactory.getPlanta(mote);
				planta.setX(x);
				planta.setY(y);
				Plant p = planta.getPlanta(planta.getX(), planta.getY());
				p.setVida(vidaRestante);
				p.setCiclos(planta.getCiclos() - ciclosRes);
				
				plantlistita.anyadir(p);
				
			}
			
			//ZOMBIELIST
			palabras = MyStringUtils.loadLine(in, "zombieList", true);
			ListaZombies zombielistita = new ListaZombies();
			
			for(int i = 0; i < palabras.length; i++){
				String [] palabritas;
				palabritas = palabras[i].split(":");
				if (palabritas.length != 5) throw new FileContentsException("Número de argumentos inválido en la lista de zombies");
				String mote = palabritas[0];
				int vidaRestante = Integer.parseInt(palabritas[1]);
				if(vidaRestante <= 0) throw new FileContentsException("Vida muy escasita");
				int x = Integer.parseInt(palabritas[2]);
				int y = Integer.parseInt(palabritas[3]);
				if((x < 0 && x >= 4) && (y < 0 && y >= 7)) throw new FileContentsException("x o y posiciones invalidas");
				int ciclosRes = Integer.parseInt(palabritas[4]);
				
				Zombie zombie = ZombieFactory.getZombie(mote);
				zombie.setX(x);
				zombie.setY(y);
				Zombie z = zombie.getZombie(zombie.getX(), zombie.getY());
				z.setVida(vidaRestante);
				z.setCiclos(z.getCiclos() - ciclosRes);
				
				
				zombielistita.anyadir(z);
				
			}
			
			
			
			this.ciclos = ciclitos;
			
			dinerito = new SuncoinManager();
			this.dinerito.setSuncoins(dineros);
			
			this.nivel = nivelito;
			
			this.zombies = new ZombieManager(this.nivel);
			this.zombies.setZombiesLeftToAppear(remZombies);
			
			
			for(int i = 0; i < plantlistita.getNum(); i++){
				//addPlanta(plantlistita.getPlantAtIndex(i));
				tablero.addPlant(plantlistita.getPlantAtIndex(i));

			}
			
			for(int i = 0; i < zombielistita.getNum(); i++){
				//Zombie aux = zombielistita.getZombieInPos(i).getZombie(zombielistita.getZombieInPos(i).getX(), zombielistita.getZombieInPos(i).getY());
				tablero.addZombie(zombielistita.getZombieInPos(i));
			}
			
			
		} catch (IOException e) {
			throw new FileContentsException("Error en el fichero");
		} catch (FileContentsException e) {
			throw new FileContentsException(e.getMessage());
		} catch (NumberFormatException e){
			throw new FileContentsException("Se esperaba un entero y no se ha encontrado");
		}
	}

	public void store(BufferedWriter escritor) throws FileContentsException{
		try {
			escritor.write("cycle: " + this.ciclos);
			escritor.newLine();
			escritor.write("sunCoins: " + this.dinerito.getSuncoins());
			escritor.newLine();
			escritor.write("level: " + this.nivel);
			escritor.newLine();
			escritor.write("remZombies: " + this.zombies.getZombiesLeftToAppear());
			escritor.newLine();
			
			
			int i, j;
			
			
			//LISTAPLANTAS
			escritor.write("plantList: ");
			ListaPlantas aux = tablero.getListaPlantas();
			
			if(aux.getNum() > 0){
			for(i = 0; i < aux.getNum() - 1; i++){
				escritor.write(aux.getPlantAtIndex(i).getMote() + ":");
				escritor.write(aux.getPlantAtIndex(i).getVida() + ":");
				escritor.write(aux.getPlantAtIndex(i).getX() + ":" + aux.getPlantAtIndex(i).getY() + ":");
				//escritor.write(aux.getPlantAtIndex(i).getFrecuencia() - aux.getPlantAtIndex(i).getCiclos());
				int leftCycles = aux.getPlantAtIndex(i).getFrecuencia() - (aux.getPlantAtIndex(i).getCiclos() % aux.getPlantAtIndex(i).getFrecuencia());
				escritor.write(leftCycles + "");
				escritor.write(", ");
			}
			
			escritor.write(aux.getPlantAtIndex(i).getMote() + ":");
			escritor.write(aux.getPlantAtIndex(i).getVida() + ":");
			escritor.write(aux.getPlantAtIndex(i).getX() + ":" + aux.getPlantAtIndex(i).getY() + ":");
			//escritor.write(aux.getPlantAtIndex(i).getFrecuencia() - aux.getPlantAtIndex(i).getCiclos());
			int leftCycles = aux.getPlantAtIndex(i).getFrecuencia() - (aux.getPlantAtIndex(i).getCiclos() % aux.getPlantAtIndex(i).getFrecuencia());
			escritor.write(leftCycles + "");
			}
			
			escritor.newLine();
			
			
			//LISTAZOMBIES
			escritor.write("zombieList: ");
			ListaZombies zaux = tablero.getListaZombies();
			
			if(zaux.getNum() > 0){
			for(j = 0; j < zaux.getNum() - 1; j++){
				escritor.write(zaux.getZombieInPos(j).getMote() + ":");
				escritor.write(zaux.getZombieInPos(j).getVida() + ":");
				escritor.write(zaux.getZombieInPos(j).getX() + ":" + zaux.getZombieInPos(j).getY() + ":");
				int leftCycles = zaux.getZombieInPos(j).getFrecuencia() - (zaux.getZombieInPos(j).getCiclos() % zaux.getZombieInPos(j).getFrecuencia());
				escritor.write(leftCycles + "");
				escritor.write(", ");
			}
			
			escritor.write(zaux.getZombieInPos(j).getMote() + ":");
			escritor.write(zaux.getZombieInPos(j).getVida() + ":");
			escritor.write(zaux.getZombieInPos(j).getX() + ":" + zaux.getZombieInPos(j).getY() + ":");
			//escritor.write(zaux.getZombieInPos(j).getFrecuencia() - zaux.getZombieInPos(j).getCiclos());
			int leftCycles = zaux.getZombieInPos(j).getFrecuencia() - (zaux.getZombieInPos(j).getCiclos() % zaux.getZombieInPos(j).getFrecuencia());
			escritor.write(leftCycles + "");
			}
			
			
			
		} catch (IOException e) {
			throw new FileContentsException("Error al escribir en el fichero");
		}
		
		
	}
	
	

    
    

}
