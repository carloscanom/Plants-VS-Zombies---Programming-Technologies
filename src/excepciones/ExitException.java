package excepciones;

@SuppressWarnings("serial")
public class ExitException extends Exception {
	
	public ExitException(){}
	
	public ExitException(String msj){
		
		super(msj);
	}

}
