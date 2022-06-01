package Exceptions;

public class DiseaseWithNoSymptomsException extends Exception{

	private static final long serialVersionUID = 1L;

	public DiseaseWithNoSymptomsException() {}


	@Override
	public String toString() {
		return "Exceptions.DiseaseWithNoSymptomsException: This Disease Has No Symptoms";
	}



}
