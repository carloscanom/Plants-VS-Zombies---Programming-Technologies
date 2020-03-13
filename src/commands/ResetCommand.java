package commands;
import resto.Game;

public class ResetCommand extends NoParamsCommand{
	
	protected final static String nombre = "Reset";
	protected final static String mote = "R";

	public ResetCommand() {
		super(nombre, "[R]eset: Starts a new game", "Resetea el juego");
	}

	@Override
	public Command parse(String[] args) {
		if (args.length == 1){
			if(args[0].equalsIgnoreCase(nombre) || args[0].equalsIgnoreCase(mote)){
				return new ResetCommand();
			}
		}
		return null;
	}

	@Override
	public boolean execute(Game juego) {
		juego.reset();
		return false;
	}

}
