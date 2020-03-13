package commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import excepciones.CommandExecuteException;
import excepciones.FileContentsException;
import excepciones.imprimeTableroException;
import resto.Game;
import resto.MyStringUtils;

public class LoadCommand extends Command{
	
	protected final static String nombre = "Load";
	protected final static String mote = "Lo";
	
	private String ruta;
	
	public LoadCommand() {
		super(nombre, "[Lo]ad <filename>", "Load the state of the game from a file.");
	}
	
	public LoadCommand(String ruta){
		this();
		this.ruta = ruta;
	}



	@Override
	public Command parse(String[] args){
		if(args.length == 2){
			if(args[0].equalsIgnoreCase(nombre)||args[0].equalsIgnoreCase(mote)){
				return new LoadCommand(args[1]);
			}
		}
		return null;
	}

	@Override
	public boolean execute(Game juego) throws CommandExecuteException, FileContentsException, imprimeTableroException{
		
		if(MyStringUtils.isReadable(this.ruta) && MyStringUtils.isValidFilename(this.ruta) && MyStringUtils.fileExists(this.ruta)){
		
		
		try(
			BufferedReader inStream = new BufferedReader(new FileReader(this.ruta))){
			String firstLine = inStream.readLine().trim();
			if(!firstLine.equals("Plants Vs Zombies v3.0")) throw new CommandExecuteException("Falta cabecera");
			//inStream.readLine();
			juego.load(inStream);
			
			//inStream.close();
			//return true;
		throw new imprimeTableroException();
			
		}
		
		catch(IOException ioe){
			ioe.printStackTrace();
			throw new CommandExecuteException("Error en el fichero");
		}
		
	}
		
		return false;
		
	}

}
