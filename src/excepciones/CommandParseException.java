package excepciones;

@SuppressWarnings("serial")
public class CommandParseException extends Exception{
	
	public CommandParseException(){}
	
	public CommandParseException(String msj){
		
		super(msj);
		
	}

}
