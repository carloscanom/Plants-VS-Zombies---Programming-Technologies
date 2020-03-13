package commands;
import excepciones.CommandParseException;

public class CommandParser {
	
	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new ListCommand(),
			new NoneCommand(),
			new PrintModeCommand(),
			new LoadCommand(),
			new SaveCommand()
	};
	
	public static Command parseCommand(String [] args) throws CommandParseException{
		int i = 0;
		boolean encontrado = false;
		while(i < availableCommands.length && !encontrado){
			Command aux;
			aux = availableCommands[i].parse(args);
			if(aux != null) return aux;
			
			i++;
		}
		
		return new NoneCommand();
	}
	
	public static String listaAyudas(){
		String ayuda = "";
		for(int i = 0; i < availableCommands.length; i++){
			ayuda += availableCommands[i].helpText();
		}
		return ayuda;
	}

}
