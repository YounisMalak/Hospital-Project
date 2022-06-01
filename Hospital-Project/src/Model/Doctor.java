package Model;
import java.util.Date;
import Utils.Specialization;
import Utils.Symptoms;
import java.util.HashMap;
import java.util.HashSet;

public class Doctor extends HospitalUser{

	private static int ID=1;
	private Specialization spec;
	private int shiftCounter;

	public Doctor(String fname, String lname, Specialization spec, SubDepartment s) {
		super(ID++,fname, lname, s); //incrementing id each time a doctor is added
		this.spec = spec;
		shiftCounter = 0;
	}

	Doctor(int id) {
		super(id);
	}
	public Specialization getSpec() {
		return spec;
	}

	public void setSpec(Specialization spec) {
		this.spec = spec;
	}


	public int getShiftCounter() {
		return shiftCounter;
	}

	public void setShiftCounter(int shiftCounter) {
		this.shiftCounter = shiftCounter;
	}

	public String toString()
	{
		return String.format("Doctor name: %s",super.toString());
	}

	public String toStringLong()
	{
		return String.format("%s, Specialization: %s",toString(),getSpec());
	}

	public void updateShiftCounter() { //Incrementing Shift Counter
		shiftCounter++;
	}

	public boolean checkPatient(Patient p) {
		if(p==null)
			return false;

		updateShiftCounter(); //Incrementing Shift Counter

		Hospital.getInstance().addPatientReport(p, this, new Date(), p.getDisease(), p.checkCondition()); //adding patient report to Hospital
		return hasTreatedPatient(p);
	}


	public boolean checkDisease(Patient p) {

		if(p==null) {
			return false;
		}

		Disease disease = p.getDisease();


		if((disease.getSymptoms().contains(Symptoms.FEVER)) && (disease.getSymptoms().contains(Symptoms.DIFFICULTY_BREATHING))) { //if patient has both FEVER and DIFFICULTY_BREATHING symptoms 
			ChronicDisease cDisease = (ChronicDisease) disease; //setting disease as chronic
			p.setDisease(cDisease);

		}
		else {
			ViralDisease vDisease = (ViralDisease) disease; //else setting disease as viral
			p.setDisease(vDisease);

		}

		return hasTreatedPatient(p);

	}

	public boolean hasTreatedPatient(Patient p) {
		if(p == null)
			return false;

		HashMap<Patient, HashSet<Doctor>> doctorsByPatient = Hospital.getInstance().getDoctorsByPatient(); 
		if (!doctorsByPatient.containsKey(p)) { //checking if patient has not already been checked by doctor
			HashSet<Doctor> doctors = new HashSet<Doctor>();
			doctors.add(this); //adding the doctor to HashSet
			doctorsByPatient.put(p, doctors); //adding doctor to doctorsByPatient HashMap in Hospital
		} 

		else { //if patient has already been checked
			HashSet<Doctor> doctors = doctorsByPatient.get(p);
			if (doctors == null) {
				doctors = new HashSet<Doctor>(); //creating new HashSet
			}
			if (!doctors.contains(this)) { //id doctor doesn't exist, we add doctor
				doctors.add(this);
			}
			doctorsByPatient.put(p, doctors);
		}

		Hospital.getInstance().setDoctorsByPatient(doctorsByPatient);  //updating HashMap in Hospital
		return true; 
	}			


}
