package Model;

import java.util.HashSet;

public class SubDepartment {

	private static int ID=1;

	private int id;
	private HashSet<Patient> patients;
	private HashSet<Doctor> doctors;
	private HashSet<Nurse> nurses;
	private HashSet<PatientReport> reports;
	private Department department;


	public SubDepartment(Department department) {
		super();
		id = ID++; //incrementing id each time a new object is created
		patients = new HashSet<>();
		doctors = new HashSet<>();
		nurses = new HashSet<>();
		reports =  new HashSet<>();
		this.department = department;
	}

	public SubDepartment(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}



	public HashSet<Patient> getPatients() {
		return patients;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPatients(HashSet<Patient> patients) {
		this.patients = patients;
	}

	public HashSet<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(HashSet<Doctor> doctors) {
		this.doctors = doctors;
	}

	public HashSet<Nurse> getNurses() {
		return nurses;
	}

	public void setNurses(HashSet<Nurse> nurses) {
		this.nurses = nurses;
	}

	public HashSet<PatientReport> getReports() {
		return reports;
	}

	public void setReports(HashSet<PatientReport> reports) {
		this.reports = reports;
	}	

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}


	@Override
	public String toString() {
		return "SubDepartment [id=" + id + ", department=" + department + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubDepartment other = (SubDepartment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public boolean findDoctor(Doctor doc){
		return getDoctors().contains(doc);
	}
	public boolean findNurse(Nurse nurse){
		return getNurses().contains(nurse);
	}

	public boolean findPatient(Patient patient){
		return getPatients().contains(patient);
	}

	public boolean findPatientReport(PatientReport patientReport){
		return getReports().contains(patientReport);
	}

	public void addDcotor(Doctor doc) {
		if(findDoctor(doc))
			System.err.printf("%s should not be in Sdept %d\n" , doc,getId());
		getDoctors().add(doc);

	}
	public void addNurse(Nurse nurse) {
		if(findNurse(nurse))
			System.err.printf("%s should not be in Sdept %d\n" , nurse,getId());
		getNurses().add(nurse);

	}

	public void addPatient(Patient patient) {
		if(findPatient(patient))
			System.err.printf("%s should not be in Sdept %d\n" , patient,getId());
		getPatients().add(patient);

	}

	public void addPatientReport(PatientReport patientReport) {
		if(findPatientReport(patientReport))
			System.err.printf("%s should not be in Sdept %d\n" , patientReport,getId());
		getReports().add(patientReport);

	}


	public boolean removeDoctor(Doctor doc) {
		return getDoctors().remove(doc);

	}
	public boolean removeNurse(Nurse nurse) {
		return getNurses().remove(nurse);

	}

	public boolean removePatient(Patient patient) {

		return getPatients().remove(patient);

	}

	public boolean removePatientReport(PatientReport patientReport) {

		return getReports().remove(patientReport);

	}



}
