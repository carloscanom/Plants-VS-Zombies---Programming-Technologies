package commands;
import excepciones.CommandExecuteException;
import excepciones.CommandParseException;
import excepciones.ExitException;
import excepciones.FileContentsException;
import excepciones.ImprimeAyudaException;
import excepciones.ImprimeDebugException;
import excepciones.ListPlantsException;
import excepciones.UpdateException;
import excepciones.imprimeTableroException;
import resto.Game;

public abstract class Command {
	
	
	private String helpText;
	private String helpInfo;
	protected String commandName;
	
	public Command(String commandName, String helpText, String helpInfo){
		this.commandName = commandName;
		this.helpInfo = helpInfo;
		this.helpText = helpText;
	}
	
	public Command(String commandName){
		this.commandName=commandName;
	}
	
	
	public abstract Command parse(String[] args) throws CommandParseException;
	
	public abstract boolean execute(Game juego) throws CommandExecuteException, ImprimeAyudaException, ExitException, UpdateException, ListPlantsException, ImprimeDebugException, FileContentsException, imprimeTableroException;
	
	public String helpText(){
		return "    " + helpText + ": " + helpInfo;
	}

}
