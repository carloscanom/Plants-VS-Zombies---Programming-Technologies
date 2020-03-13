package commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import excepciones.CommandExecuteException;
import excepciones.CommandParseException;
import excepciones.FileContentsException;
import resto.Game;

public class SaveCommand extends Command {
	
	protected final static String nombre = "Save";
	protected final static String mote = "S";
	
	private String nombreFichero;
	
	public SaveCommand(){
		super(nombre, "[S]ave <filename>", "Save the state of the game to a file.");
	}

	public SaveCommand(String nombreFichero) {
		this();
		this.nombreFichero = nombreFichero;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Command parse(String[] args) throws CommandParseException {
		if(args.length == 2){
			if(args[0].equalsIgnoreCase(nombre)||args[0].equalsIgnoreCase(mote)){
				return new SaveCommand(args[1]);
			}
		}
		return null;
		
	}

	@Override
	public boolean execute(Game juego) throws CommandExecuteException, FileContentsException {
		
		/*File f = new File(nombreFichero);
		System.out.println("File exists?: " + f.exists());
		System.out.println("Path?: " + f.getAbsolutePath());
		System.out.println("Readable?: " + f.canRead());
		System.out.println("Writable?: " + f.canWrite());*/
		
		try(BufferedWriter escritor = new BufferedWriter(new FileWriter(new File(nombreFichero + ".dat")))){
			
			escritor.write("Plants Vs Zombies v3.0");
			escritor.newLine();
			
			juego.store(escritor);
			
			System.out.println("Game successfully saved to file " + nombreFichero + ".dat; use the load command to reload it.");
			
			escritor.close();
			
			
		}catch(IOException e){
			throw new CommandExecuteException("Error en el archivo");
		}
		
		
		
		return false;
	}

}
