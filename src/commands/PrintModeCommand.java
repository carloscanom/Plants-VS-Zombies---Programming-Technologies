package commands;
import excepciones.ImprimeDebugException;
import resto.DebugPrinter;
import resto.Game;
import resto.ReleasePrinter;

public class PrintModeCommand extends Command{
	
	protected final static String nombre = "PrintMode";
	protected final static String mote = "P";
	
	public PrintModeCommand(String nombre, String impresion) {
		super(nombre);
		if (impresion.equalsIgnoreCase("Debug"))
			new DebugPrinter();
		else
			new ReleasePrinter();
	}

	public PrintModeCommand() {
		super(nombre, "[P]rintMode: change print mode [Release|Debug]", "Cambia el modo de impresion del juego");
	}


	@Override
	public Command parse(String[] args) {
		if(args.length == 2){
	        if(args[0].equalsIgnoreCase(nombre) || args[0].equalsIgnoreCase(mote)){
	            return new PrintModeCommand(nombre, args[1]);
	        }
		}
	    
	    return null;
	}

	@Override
	public boolean execute(Game juego) throws ImprimeDebugException {
		//controller.imprimeDebug();
		throw new ImprimeDebugException();
	}

}
