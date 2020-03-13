package resto;
import elementos.GameObject;
import elementos.ListaPlantas;
import elementos.ListaZombies;
import elementos.Plant;
import elementos.Zombie;

public class Tablero {
	
	private GameObject[][] tablero;
	private ListaPlantas listaPlantas;
	private ListaZombies listaZombies;
	private int filas;
	private int columnas;
	private Game juego;
	
	

	public Tablero(int filas, int columnas, Game juego) {
		tablero = new GameObject[filas][columnas];
		listaPlantas = new ListaPlantas();
		listaZombies = new ListaZombies();
		this.filas = 4;
		this.columnas = 8;
		this.juego = juego;
	}
	
	public void eliminarDeTablero(int x, int y){
		if(!casillaVacia(x, y)){
			if(hayUnZombie(x, y))
				listaZombies.eliminar((Zombie) tablero[x][y]);
			
			else listaPlantas.eliminar((Plant) tablero[x][y]);
			
			tablero[x][y] = null;
		}
		
	}
	
	public boolean casillaVacia(int x, int y){
		if(tablero[x][y] == null){
			return true;
		}
		return false;
	}
	
	public boolean hayUnZombie(int x, int y){
		boolean encontrado = false;
		int i = 0;
		if(x >= 0 && x <= 3 && y >= 0 && y <= 7){
			while (i < listaZombies.getNum() && !encontrado){
				if(listaZombies.getZombieInPos(i).getX() == x && listaZombies.getZombieInPos(i).getY() == y){
				encontrado = true;
				}
			i++;
			}
		}
		
		return encontrado;
	}
	
	
	public void ataqueNormalDerecha(int x, int y, int fuerza){
		int i = y + 1;
		boolean encontrado = false;
		
		while(i < 8 && !encontrado){
			if(hayUnZombie(x, i)){
				encontrado = true;
				if(tablero[x][i].quitarVida(fuerza)){
					eliminarDeTablero(x, i);
				}
			}
			i++;
		}
	}
	
	public void ataqueCasillasAlrededor(int x, int y, int fuerza){
		
		if(hayUnZombie(x, y+1)){
			if(tablero[x][y+1].quitarVida(fuerza)){
				eliminarDeTablero(x, y+1);
			}
		}
		
		if(hayUnZombie(x, y-1)){
			if(tablero[x][y-1].quitarVida(fuerza)){
				eliminarDeTablero(x, y-1);
			}
		}
		
		if(hayUnZombie(x-1, y+1)){
			if(tablero[x-1][y+1].quitarVida(fuerza)){
				eliminarDeTablero(x-1, y+1);
			}
		}
		
		if(hayUnZombie(x+1, y+1)){
			if(tablero[x+1][y+1].quitarVida(fuerza)){
				eliminarDeTablero(x+1, y+1);
			}
		}
		
		if(hayUnZombie(x-1, y)){
			if(tablero[x-1][y].quitarVida(fuerza)){
				eliminarDeTablero(x-1, y);
			}
		}
		
		if(hayUnZombie(x-1, y-1)){
			if(tablero[x-1][y-1].quitarVida(fuerza)){
				eliminarDeTablero(x-1, y-1);
			}
		}
		
		if(hayUnZombie(x+1, y)){
			if(tablero[x+1][y].quitarVida(fuerza)){
				eliminarDeTablero(x+1, y);
			}
		}
		
		if(hayUnZombie(x+1, y-1)){
			if(tablero[x+1][y-1].quitarVida(fuerza)){
				eliminarDeTablero(x+1, y-1);
			}
		}
		
		eliminarDeTablero(x, y);
		
	}
	
	public void ZombieAtaca(int x, int y, int danyo){
		if(!hayUnZombie(x, y-1)){
			if(tablero[x][y-1].quitarVida(danyo)){
				eliminarDeTablero(x, y-1);
			}
		}
	}
	
	public void update(){
		listaPlantas.update();
		listaZombies.update();
	}
	

	public  String toString() {
		
		String[][] board;
		board = new String[filas][columnas];
		
		final String space = " ";
		for(int i = 0; i < filas; i++) {
			for(int j = 0; j < columnas; j++) {

				board[i][j] = space;
				
			if(!casillaVacia(i, j))
					
				board[i][j] = tablero[i][j].infoRelease();
				
				else board[i][j] = space;

			}
		}
		
		

			int cellSize = 7;
			int marginSize = 2;
			String vDelimiter = "|";
			String hDelimiter = "-";
			
			String rowDelimiter = MyStringUtilsPR1.repeat(hDelimiter, (columnas * (cellSize + 1)) - 1);
			String margin = MyStringUtilsPR1.repeat(space, marginSize);
			String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
			
			StringBuilder str = new StringBuilder();
			
			str.append(lineDelimiter);
			
			for(int i=0; i<filas; i++) {
					str.append(margin).append(vDelimiter);
					for (int j=0; j<columnas; j++) {
						str.append( MyStringUtilsPR1.centre(board[i][j], cellSize)).append(vDelimiter);
					}
					str.append(lineDelimiter);
			}
			return str.toString();
		}

	public void addZombie(Zombie zombie){
		if(casillaVacia(zombie.getX(), zombie.getY())){
			listaZombies.anyadir(zombie);
			tablero[zombie.getX()][zombie.getY()] = zombie;
			zombie.setGame(juego);
		}
	}
	
	public void anyadeSuncoins(int suncoins){
		juego.anyadeSoles(suncoins);
	}
	
	public void addPlant(Plant planta){
		
		if(casillaVacia(planta.getX(), planta.getY())){
			listaPlantas.anyadir(planta);
			tablero[planta.getX()][planta.getY()] = planta;
			planta.setGame(juego);
		}
	}
	
	public String getInfoListas() {
        String aux = "|";
        aux += listaPlantas.info() + listaZombies.info();

        return aux;
    }

	public int zombiesLeftToAppear() {
		return listaZombies.getNum();
	}

	public boolean gananZombies() {
		int i = 0;
		boolean encontrado = false;
		while(i < listaZombies.getNum() && !encontrado){
			if(listaZombies.getZombieInPos(i).getY() == 0){
				encontrado = true;
			}
			else i++;
		}
		return encontrado;
	}

	public void setTablero(GameObject[][] tablero) {
		this.tablero = tablero;
	}

	public void zombieSeMueve(int x, int y, Zombie zombie) {
		tablero[x][y] = null;
		zombie.setY(y - 1);
		tablero [x][y - 1] = zombie;
	}

	public ListaPlantas getListaPlantas() {
		return listaPlantas;
	}

	public ListaZombies getListaZombies() {
		return listaZombies;
	}
	
	
	
	
}
