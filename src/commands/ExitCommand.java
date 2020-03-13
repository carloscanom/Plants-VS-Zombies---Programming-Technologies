package commands;
import excepciones.ExitException;
import resto.Game;

public class ExitCommand extends NoParamsCommand{
	
	protected final static String nombre = "Exit";
	protected final static String mote = "E";


	public ExitCommand() {
		super(nombre, "[E]xit: terminate the program", "Sale del programa");
	}

	@Override
	public Command parse(String[] args) {
		if(args.length == 1){
			if(args[0].equalsIgnoreCase(nombre) || args[0].equalsIgnoreCase(mote)){
				return new ExitCommand();
			}
		}
		return null;
	}

	@Override
	public boolean execute(Game juego) throws ExitException {
		//System.exit(0);
		//return false;
		throw new ExitException();
	}

}
