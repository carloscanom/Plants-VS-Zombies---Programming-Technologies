package excepciones;

@SuppressWarnings("serial")
public class CommandExecuteException extends Exception{
	

	public CommandExecuteException(){}
	
	public CommandExecuteException(String msj){
		
		super(msj);
		
	}

}
