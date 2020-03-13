package commands;
import excepciones.UpdateException;
import resto.Game;

public class NoneCommand extends NoParamsCommand{
	
	protected final static String nombre = "None";
	protected final static String mote = "N";


	public NoneCommand() {
		super(nombre, "[N]one: skyps cycle", "salta el ciclo actual");
	}

	@Override
	public Command parse(String[] args) {
		if(args.length == 1 || args == null){
			if(args[0].equalsIgnoreCase(nombre) || args[0].equalsIgnoreCase(mote)){
				return new NoneCommand();
			}
		}
		return null;
	}

	@Override
	public boolean execute(Game juego) throws UpdateException {
		//controller.update();
		throw new UpdateException();
	}

}
