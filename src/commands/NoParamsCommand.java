package commands;

public abstract class NoParamsCommand extends Command{

	public NoParamsCommand(String commandName, String helpText, String helpInfo) {
		super(commandName, helpText, helpInfo);
	}

}
