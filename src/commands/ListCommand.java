package commands;
import excepciones.ListPlantsException;
import resto.Game;

public class ListCommand extends NoParamsCommand{
	
	protected final static String nombre = "List";
	protected final static String mote = "l";

	public ListCommand() {
		super(nombre, "[L]ist: print the list of avalaible plants", "imprime las listas de las plantas disponbles");
	}

	@Override
	public Command parse(String[] args) {
		if (args.length == 1){
			if(args[0].equalsIgnoreCase(nombre) || args[0].equalsIgnoreCase(mote)){
				return new ListCommand();
			}
		}
		return null;
	}

	@Override
	public boolean execute(Game juego) throws ListPlantsException {
		//controller.listPlants();
		throw new ListPlantsException();
		
	}

}
