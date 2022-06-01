package Model;

import java.util.Date;

import Utils.ReleaseNote;

public class PatientReport {

	private static int ID=1;

	private int id;
	private Patient patient;
	private Doctor doctor;
	private Date date;
	private Disease disease;
	private SubDepartment sdept;
	private ReleaseNote rNote;


	public PatientReport(Patient patient, Doctor doctor, Date date,
			Disease disease, ReleaseNote rNote) {
		super();
		this.id = ID++; //Incrementing id  each time an object is created
		this.patient = patient;
		this.doctor = doctor;
		this.date =date;
		this.disease = disease;
		this.rNote=rNote;
		this.sdept = patient.getsDepartment();

	}

	PatientReport(int id) {
		this.id = id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public int getId() {
		return id;
	}
	public Patient getPatient() {
		return patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public Date getDate() {
		return date;
	}
	public Disease getDisease() {
		return disease;
	}
	public SubDepartment getSdept() {
		return sdept;
	}


	public void setSdept(SubDepartment sdept) {
		this.sdept = sdept;
	}


	public ReleaseNote getrNote() {
		return rNote;
	}

	public void setrNote(ReleaseNote rNote) {
		this.rNote = rNote;
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
		PatientReport other = (PatientReport) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PatientReport [patient=" + patient + ", doctor=" + doctor + ", disease=" + disease
				+ "]";
	}



}
