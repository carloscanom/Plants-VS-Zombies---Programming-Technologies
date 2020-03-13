package resto;

import excepciones.MainException;

public class PlantsVSZombies {
	
	public static void main(String[] args) throws MainException {
		
		if(args.length < 1 || args.length > 2){
			System.out.println("NUMERO DE PARAMETROS INCORRECTO");
		}
		else{
			Level nivel;
			switch(args[0].toUpperCase()){
			case "EASY": nivel = Level.EASY; break;
			case "HARD": nivel = Level.HARD; break;
			case "INSANE": nivel = Level.INSANE; break;
			default: nivel = Level.HARD;
			}
			
			if(args.length == 1){
				new Controller(nivel).run();
			}
			else{
				try{
				int seed = Integer.parseInt(args[1]);
				new Controller(nivel, seed).run();
				}
				catch(NumberFormatException e){
					System.out.println("Se esperaba un entero para la semilla");
				}
			}
		}

	}
}
