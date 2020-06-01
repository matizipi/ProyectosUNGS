package helper;

public class LogWriter {
	
	public static int INFO = 0;
	public static int ERROR = 1;
	
	private LogWriter() {
		
	}
	
//	public static void writeLog(Object _class, String msg) {
//		System.out.println("[" + _class.getClass().getSimpleName() + "] - " + msg);
//	}
	
	public static void writeLog(Object _class, int type, String msg) {
		switch (type) {
			case 0: System.out.println("[" + _class.getClass().getSimpleName() + "] - " + "[INFO ] - " + msg);
					break;
			case 1: System.out.println("[" + _class.getClass().getSimpleName() + "] - " + "[ERROR] - " + msg);
					break;
			default: System.out.println("[" + _class.getClass().getSimpleName() + "] - " + msg);
					break;
		}
	}
}