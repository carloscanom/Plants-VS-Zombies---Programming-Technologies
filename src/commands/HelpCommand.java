package commands;
import excepciones.ImprimeAyudaException;
import resto.Game;

public class HelpCommand extends NoParamsCommand{
	
	protected final static String nombre = "Help";
	protected final static String mote = "h";

	public HelpCommand() {
		super(nombre, "[H]elp: print this help message", "Muestra esta ayuda");
	}

	@Override
	public Command parse(String[] args) {
		if (args.length == 1){
			if(args[0].equalsIgnoreCase(nombre) || args[0].equalsIgnoreCase(mote)){
				return new HelpCommand();
			}
		}
		return null;
	}

	@Override
	public boolean execute(Game juego) throws ImprimeAyudaException {
		
		throw new ImprimeAyudaException();
	}

}
