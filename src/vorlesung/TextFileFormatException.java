package vorlesung;


public class TextFileFormatException extends Exception{

	private static final long serialVersionUID = 5268000154878869902L;

	public TextFileFormatException() {

	}

	public TextFileFormatException(String message) {
		super(message);
	}

	public TextFileFormatException(int message) {
		switch (message) {
		case 0: System.out.println("database to small"); //
		break;
		
		case 1: System.out.println("database to big"); //
		break;
		
		case 2: System.out.println("database has empty item"); //
		break;
		
		case 3: System.out.println("database has corrupted item"); //
		break;
		}
		// zu wenige strings, zu viele strings, ein leerer string, ein falscher string
	}


}
