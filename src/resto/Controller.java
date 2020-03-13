package resto;
import java.util.Scanner;

import commands.Command;
import commands.CommandParser;
import excepciones.*;

public class Controller {
	
	private Game juego;
	private Scanner teclas;
	private BoardPrinter print;
	private boolean exit = false;
	
	public Controller(Level nivel){
		teclas = new Scanner(System.in);
		juego = new Game(nivel, 4, 8);
		print = new ReleasePrinter();
	}
	
	public Controller(Level nivel, long semilla){
		teclas = new Scanner(System.in);
		juego = new Game(nivel, semilla, 4, 8);
		print = new ReleasePrinter();
	}
	
	public void run(){
		
		System.out.println(print.printGame(this.juego));
		
		while(!juego.terminado() && !exit){
			System.out.println("Command >");
			String aux = teclas.nextLine();
			String [] comandos = aux.split(" ");
			
		try{
			
			Command command = CommandParser.parseCommand(comandos);
			
			if(command!=null){
				if(command.execute(juego)){
					juego.update();
					System.out.println(print.printGame(this.juego));
				}
			}
			else System.out.println("Comando irreconocible");
			
			}
		
			catch(CommandExecuteException e){
			System.out.println(e.getMessage());
			}
			catch(CommandParseException e){
				System.out.println(e.getMessage());
			}
			catch(ExitException e){
				exit();
			}
			catch(ImprimeAyudaException e){
				imprimeAyuda();
			}
			catch(ImprimeDebugException e){
				imprimeDebug();
			}
			catch(ListPlantsException e){
				listPlants();
			}
			catch(UpdateException e){
				update();
			}
			catch(FileContentsException e){
				System.out.println(e.getMessage());
			}
			catch(imprimeTableroException e){
			imprimirTablero();
			}
			
		}
		
		if(juego.gananZombies()) System.out.println("Ganan los zombies");
		else {
			if(juego.terminado())
			System.out.println("Enhorabuena, has ganado");
		}
	}

	public void update() {
		juego.update();
		System.out.println(print.printGame(this.juego));
	}

	public void exit() {
		System.out.println("Game Over");
		System.exit(0);
		//this.exit = true;
	}

	public void imprimeAyuda() {
		System.out.println(CommandParser.listaAyudas());
		
	}

	public void cambioImpresion(BoardPrinter impresion) {
		print = impresion;
		
	}

	public void imprimeDebug() {
		System.out.println(juego.getInfoEnListas());
	}

	public void listPlants() {
		System.out.println("[S]unflower: Cost: 20 suncoins Harm: 0");
		System.out.println("[P]eashooter: Cost: 50 suncoins Harm: 1");
		System.out.println("Peta[c]ereza: Cost: 50 suncoins Harm: 10");
		System.out.println("[N]uez: Cost: 50 suncoins Harm: 0");
	}
	
	public void imprimirTablero(){
		System.out.println(print.printGame(this.juego));
	}

}
