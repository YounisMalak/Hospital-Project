package Model;

import Utils.Condition;
import Utils.ReleaseNote;
import Exceptions.StatusOutOfRangeException;

public class Patient extends HospitalUser implements Comparable<Patient>{

	private static int ID=1;
	private int status;
	private Condition condition;
	private Disease disease;

	public Patient(String fname, String lname, SubDepartment s, Disease disease) {
		super(ID++,fname, lname, s); //incrementing id each time an object is created
		this.disease = disease;


	}
	Patient(int id) {
		super(id);
	}

	public int getStatus() {
		return status;
	}

	public String setStatus(int status){
		if(status<0) { //returning exception and setting status to 0
			StatusOutOfRangeException e = new StatusOutOfRangeException(status); 
			status = 0;
			condition = Condition.CRITICAL; //updating condition
			return e.toString();
		}
		if(status>100) {  //returning exception and setting status to 100
			StatusOutOfRangeException e = new StatusOutOfRangeException(status);
			status = 100;
			condition = Condition.GOOD; //updating condition 
			return e.toString();
		}
		//setting condition based on status

		if(status >= 0 && status < 40) {
			condition = Condition.CRITICAL;
		}
		else if(status >= 40 && status < 60) {
			condition = Condition.SERIOUS;
		}
		else if(status >= 60 && status < 80) {
			condition = Condition.FAIR;
		}
		else {
			condition = Condition.GOOD;
		}
		this.status = status;
		return "Success";
	}


	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Disease getDisease() {
		return disease;
	}


	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public String toString()
	{
		return String.format("Patient name: %s",super.toString());
	}



	public String toStringLong()
	{
		return String.format("%s, Status is: %s, Condition %s",toString(),
				getStatus(),condition);
	}

	public ReleaseNote checkCondition() { //returning appropriate release note based on condition

		if(condition==Condition.CRITICAL || condition==Condition.SERIOUS)
			return ReleaseNote.STANDBY;
		else if(condition==Condition.FAIR)
			return ReleaseNote.MOVE_TO_HOTEL;
		else if(condition==Condition.GOOD)
			return ReleaseNote.CAN_GO_HOME;
		else
			return null;

	}


	@Override 
	public int compareTo(Patient p) { //comparing first based on last name then first 
		String fullName1 = this.getLname() + this.getFname();
		String fullName2 = p.getLname() + p.getFname();

		return fullName2.compareTo(fullName1); //descending order
	}
}
