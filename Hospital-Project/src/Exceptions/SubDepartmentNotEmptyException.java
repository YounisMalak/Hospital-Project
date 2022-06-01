package Exceptions;

public class SubDepartmentNotEmptyException extends Exception{

	private static final long serialVersionUID = 1L;
	private int id;

	public SubDepartmentNotEmptyException(int id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Exceptions.SubDepartmentNotEmptyException: Cannot Delete SubDepartment With ID: "+id+" Beacuse Its Not Empty";
	}

}

