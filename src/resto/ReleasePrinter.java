package resto;

public class ReleasePrinter extends BoardPrinter{
	
	
public ReleasePrinter(){
		
	}

	public BoardPrinter getImpresion(){
		return new ReleasePrinter();
	}

	@Override
	public String printGame(Game juego) {
		return juego.getCabecera() + juego.getTablero();
	}


}
