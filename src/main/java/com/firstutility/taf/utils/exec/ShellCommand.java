package com.firstutility.taf.utils.exec;

public class ShellCommand implements ICommandBuilder {
	
	private Command command;
	private String shellCommand;
	
	public ShellCommand() {
		this.command = new Command();
		this.shellCommand = "";
	}
	
	@Override
	public ShellCommand add(String command) {
		shellCommand = shellCommand + command + " ; ";
		return this;
	}

	@Override
	public Command build() {
		command.setCommand(shellCommand);
		return command;
	}

}
