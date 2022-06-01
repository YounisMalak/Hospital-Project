package Model;
import java.util.HashMap;
import java.util.HashSet;

import Utils.Treatments;

public class Nurse extends HospitalUser{

	private static int ID=1;
	private Treatments treat;
	private int shiftCounter;

	public Nurse(String fname, String lname, Treatments treat, SubDepartment s) {
		super(ID++,fname, lname, s); //incrementing id each time an object is created
		this.treat = treat;
		shiftCounter = 0;
	}

	Nurse(int id) {
		super(id);
	}

	public Treatments getTreat() {
		return treat;
	}

	public void setTreat(Treatments treat) {
		this.treat = treat;
	}

	public int getShiftCounter() {
		return shiftCounter;
	}

	public void setShiftCounter(int shiftCounter) {
		this.shiftCounter = shiftCounter;
	}

	public String toString()
	{
		return String.format("Nurse name: %s",super.toString());
	}
	public String toStringLong()
	{
		return String.format("%s, Treatments: %s",toString(),getTreat());
	}

	public void updateShiftCounter() { //incrementing number of shifts 
		shiftCounter++;
	}

	public boolean checkPatient(Patient p) {
		if(p==null) {
			return false;
		}

		updateShiftCounter(); //incrementing shift counter
		int status= p.getStatus();
		p.setStatus(++status); //incrementing and updating patien's status

		return hasTreatedPatient(p);

	}

	public boolean checkDisease(Patient p) {

		if(p==null) {
			return false;
		}

		updateShiftCounter(); //Incrementing shift counter
		int status= p.getStatus();
		p.setStatus(--status); //decrementing patient's status 

		return hasTreatedPatient(p);		

	}

	public boolean hasTreatedPatient(Patient p) {

		if(p == null)
			return false;

		HashMap<Patient, HashSet<Nurse>> nurseByPatient = Hospital.getInstance().getNursesByPatient();

		if (!nurseByPatient.containsKey(p)) { //if its first time treating patient by nurse, adding then to appropriate places
			HashSet<Nurse> nurse = new HashSet<Nurse>();
			nurse.add(this);
			nurseByPatient.put(p, nurse);
		} 
		else { 
			HashSet<Nurse> nurses = nurseByPatient.get(p);
			if (nurses == null) {
				nurses = new HashSet<Nurse>();
			}

			if (!nurses.contains(this)) {
				nurses.add(this);	 //adding nurse 
			}
			nurseByPatient.put(p, nurses);
		}

		Hospital.getInstance().setNursesByPatient(nurseByPatient);  //updating nurseByPatient in hospital
		return true; 	

	}
}
