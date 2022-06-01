package Exceptions;

public class StatusOutOfRangeException extends Exception{


	private static final long serialVersionUID = 1L; 
	private int status;

	public StatusOutOfRangeException(int status) {
		super();
		this.status = status;
	}

	@Override
	public String toString() {
		return "Exceptions.StatusOutOfRangeException: The Status " + status + " is Not In Range Of 0 To 100";
	}


}
