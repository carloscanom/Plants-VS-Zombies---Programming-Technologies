package resto;

public class DebugPrinter extends BoardPrinter{
	
	public DebugPrinter(){
		
	}

	public BoardPrinter getImpresion(){
		return new DebugPrinter();
	}

	@Override
	public String printGame(Game juego) {
		return juego.getCabecera() + juego.getInfoEnListas();
	}

}
