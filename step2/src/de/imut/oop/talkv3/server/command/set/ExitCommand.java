package step2.src.de.imut.oop.talkv3.server.command.set;
import step2.src.de.imut.oop.talkv3.common.SystemExitCode;

/**
 * ExitCommand.java
 *
 * The class for the regular exit of the program.
 *
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public class ExitCommand extends ServerCommand {

	// default serialVersionUID
	private static final long serialVersionUID = 1L;

	// The SystemExitCode
	private SystemExitCode sec;

	/**
	 * The constructor of the Exitcommand.
	 *
	 * @param code
	 * 			- the code of the exit-Command.
	 */
	public ExitCommand(SystemExitCode sec) {
		this.sec = sec;
	}
	
	@Override
	public void execute() {
		switch(sec) {
			case NORMAL:
				System.exit(SystemExitCode.NORMAL.returnExitCode());
				break;
			case ABORT:
				System.exit(SystemExitCode.ABORT.returnExitCode());
				break;
			case BACK:
				System.exit(SystemExitCode.BACK.returnExitCode());
				break;
			default:
				System.exit(SystemExitCode.NORMAL.returnExitCode());
		}
	}
}