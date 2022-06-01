package Exceptions;

import Utils.ReleaseNote;

public class CannotReleasePatientException extends Exception{


	private static final long serialVersionUID = 1L;

	private ReleaseNote note, proper;


	public CannotReleasePatientException(ReleaseNote note, ReleaseNote proper) {
		super();
		this.note = note;
		this.proper = proper;
	}


	@Override
	public String toString() {
		return "Exceptions.CannotReleasePatientException: Cannot Release This Patient Because  He Have The Note: " +note+ " And Not The Proper Release Note: " + proper;
	}

}
