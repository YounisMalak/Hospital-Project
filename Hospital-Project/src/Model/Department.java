package Model;

import java.util.ArrayList;
import java.util.HashSet;
import Exceptions.SubDepartmentNotEmptyException;
import Utils.Specialization;

public class Department {

	private static int ID=1;

	private int id;

	private String deptName;
	private ArrayList<SubDepartment> sdepts;
	private Specialization spec;

	public Department(String deptName, Specialization spec) {
		super();
		this.id = ID++; //Incrementing id each time an object is created
		this.deptName = deptName;
		this.spec = spec;
		this.sdepts = new ArrayList<SubDepartment>();
	}

	Department(int id) {
		this.id = id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public ArrayList<SubDepartment> getSdepts() {
		return sdepts;
	}

	public void setSdepts(ArrayList<SubDepartment> sdepts) {
		this.sdepts = sdepts;
	}

	public Specialization getSpec() {
		return spec;
	}

	public void setSpec(Specialization spec) {
		this.spec = spec;
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
		Department other = (Department) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Department [deptName=" + deptName + ", spec=" + spec + "]";
	}


	public boolean addSubDepartment(SubDepartment s) {

		if(s == null)
			return false; 

		if (getSdepts().contains(s) || Hospital.getInstance().getSubDepartments().containsKey(s.getId())) //checking if SubDepartment has already been added
			return false; 

		Hospital.getInstance().getSubDepartments().put(s.getId(), s); //adding SubDepartment to the SubDepartment's HashMap in Hospital
		getSdepts().add(s); //adding SubDepartment to the department's List

		return true;		
	}

	public boolean removeSubDepartment(SubDepartment s) {

		if(s == null)
			return false;

		if (!getSdepts().contains(s) || !Hospital.getInstance().getSubDepartments().containsKey(s.getId())) //checking if SubDepartment hasn't already been added
			return false;

		getSdepts().remove(s); //removing SubDepartment from the department's List
		Hospital.getInstance().getSubDepartments().remove(s.getId()); //removing SubDepartment from the SubDepartment's HashMap in Hospital
		return true;		
	}


	public String printAllDoctors()
	{
		String toReturn = "All doctors for "+this +"\n";
		for (SubDepartment s : getSdepts() ) {
			for(Doctor d : s.getDoctors())
				toReturn += d.toStringLong() + "\n";
		}
		return toReturn;
	}
	public String printAllNurses()
	{
		String toReturn = "All nurses for "+this +"\n";
		for (SubDepartment s : getSdepts() ) {
			for(Nurse n : s.getNurses())
				toReturn += n.toStringLong() + "\n";
		}
		return toReturn;
	}
	public String printAllPatients()
	{
		String toReturn = "All patients for "+this +"\n";
		for (SubDepartment s : getSdepts() ) {
			for(Patient p : s.getPatients())
				toReturn += p.toStringLong() + "\n";
		}
		return toReturn;
	}

	public String removeSubDepartment(SubDepartment toDelete, SubDepartment toMoveTo) {

		if(toDelete == null || toMoveTo == null) //if either parameters is null we return fail
			return "Fail";

		//if all elements in SubDepartment toDelete are empty, we delete it and return success
		if(toDelete.getPatients().isEmpty() && toDelete.getDoctors().isEmpty() && toDelete.getNurses().isEmpty() && toDelete.getReports().isEmpty()) {
			if(removeSubDepartment(toDelete))
				return "Success";
			return "Fail";	

		}

		//if one or more of the fields aren't empty, we move the field values to the toMoveTo SubDepartment and return appropriate exception
		else {
			moveSubDepartment(toDelete, toMoveTo);
			SubDepartmentNotEmptyException e = new SubDepartmentNotEmptyException(toDelete.getId()); 
			removeSubDepartment(toDelete); //removing SubDepartment after moving all its values
			return e.toString();
		}		

	}

	public void moveSubDepartment(SubDepartment a, SubDepartment b) {
		if(!a.getPatients().isEmpty()) { //if there are patients in the list
			HashSet<Patient> patientsToDelete = a.getPatients();
			HashSet<Patient> patientsToMoveTo = b.getPatients();

			for(Patient pat : patientsToDelete) { //adding patients from list a to list b
				pat.setsDepartment(b); //setting the SubDepartment of each patient to b 
				patientsToMoveTo.add(pat); //adding patients to SubDepartment b

			}
			b.setPatients(patientsToMoveTo); //updating the toMoveTo SubDepartment patient HashSet

		}

		if(!a.getDoctors().isEmpty()) { //if there are doctors in the list
			HashSet<Doctor> doctorsToDelete = a.getDoctors();
			HashSet<Doctor> doctorsToMoveTo = b.getDoctors();

			for(Doctor doc : doctorsToDelete) { // moving doctors from SubDepartment a to SubDepartment b
				doc.setsDepartment(b); //setting the SubDepartment of each doctor to b 
				doctorsToMoveTo.add(doc); //adding doctors to SubDepartment b

			}
			b.setDoctors(doctorsToMoveTo); //updating the toMoveTo SubDepartment doctor HashSet

		}

		if(!a.getNurses().isEmpty()) { //if there are nurses in the list
			HashSet<Nurse> nursesToDelete = a.getNurses();
			HashSet<Nurse> nursesToMoveTo = b.getNurses();

			for(Nurse nrc : nursesToDelete) { //adding nurses from SubDepartment a to SubDepartment b
				nrc.setsDepartment(b);	//setting the SubDepartment of each nurse to b 
				nursesToMoveTo.add(nrc); //adding nurses to SubDepartment b

			}
			b.setNurses(nursesToMoveTo); //updating the toMoveTo SubDepartment nurse HashSet

		}

		if(!a.getReports().isEmpty()) { //if there are reports in the list
			HashSet<PatientReport> reportsToDelete = a.getReports();
			HashSet<PatientReport> reportsToMoveTo = b.getReports();

			for(PatientReport rep : reportsToDelete) { //moving report from SubDepartment a to b
				rep.setSdept(b); //setting SubDepartment to newly added reports to b
				reportsToMoveTo.add(rep); //adding the report to SubDepartment b

			}
			b.setReports(reportsToMoveTo); //updating the toMoveTo SubDepartment report HashSet 

		}

	}


	public SubDepartment getRealSubDepartment(SubDepartment s) { //checking if SubDepartment is valid
		int ind = sdepts.indexOf(s);
		if(ind<0)
			return null;
		else 
			return sdepts.get(ind);
	}
}
