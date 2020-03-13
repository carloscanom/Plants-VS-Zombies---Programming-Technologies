package commands;
import elementos.Plant;
import excepciones.CommandExecuteException;
import excepciones.CommandParseException;
import excepciones.UpdateException;
import resto.Game;
import factoryManager.PlantFactory;

public class AddCommand extends Command{
	
	private int x;
	private int y;
	private String nombrePlanta;
	
	private static String mote = "a";
	private static String nombre = "add";
	
	

	public AddCommand() {
		super(nombre, "[A]dd <plant> <x> <y>", "Anyade planta en la posicion x, y");
	}
	
	public AddCommand(String nombrePlanta, int x, int y){
		super(nombre, "[A]dd <plant> <x> <y>", "Anyade planta en la posicion x, y");
		this.x = x;
		this.y = y;
		this.nombrePlanta = nombrePlanta;
	}

	@Override
	public Command parse(String[] args) throws CommandParseException {
		if(args.length == 4){
			if(args[0].equalsIgnoreCase(nombre)||args[0].equalsIgnoreCase(mote)){
				try{
				x = Integer.parseInt(args[2]);
				y = Integer.parseInt(args[3]);
				}catch(NumberFormatException e){
					throw new CommandParseException("Se esperaban dos enteros: " + args[2] + ", " + args[3]);
				}
				nombrePlanta = args[1];
				
				return new AddCommand(nombrePlanta, x, y);
			}
		}
		return null;
	}

	@Override
	public boolean execute(Game juego) throws CommandExecuteException, UpdateException{
		
		Plant planta = PlantFactory.getPlanta(nombrePlanta);
		
		if((x >= 0 && x < 4) && (y >= 0 && y < 7)){
			if(juego.celdaVacia(x,y)){ 
				if(juego.suficientesSuncoins(planta.getCoste())){
					juego.addPlanta(planta,x,y);
					planta.setGame(juego);
					//controller.update();
					//throw new updateException();
					return true;
				}
				
				else throw new CommandExecuteException("No hay suficientes suncoins");
			}
			
			else throw new CommandExecuteException("Casilla Ocupada");
		}
		
		else throw new CommandExecuteException("Coordenadas fuera de rango");
		
		
	}
	
	
}
