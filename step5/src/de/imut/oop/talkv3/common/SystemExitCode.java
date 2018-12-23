package step5.src.de.imut.oop.talkv3.common;

/**
 * SystemExitCode.java
 * 
 * An enum for the return of the exit-code.
 * 
 * @author Gruppe 1 - PFW WS 2018/19
 * @version 1.01, 22.12.2018
 *
 */
public enum SystemExitCode {
	ABORT {
		public int returnExitCode() {
			return 1;
		}
	}, NORMAL {
		public int returnExitCode() {
			return 0;
		}
	}, BACK {
		public int returnExitCode() {
			return -1;
		}
	};
	public abstract int returnExitCode();
}
