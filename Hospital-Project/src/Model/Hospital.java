package Model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import Exceptions.CannotReleasePatientException;
import Utils.Condition;
import Utils.ReleaseNote;
import Utils.Specialization;
import Utils.Symptoms;
import Utils.Treatments;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Comparator;


public class Hospital {

	private static Hospital theHospital = null;


	private HashMap <Integer, Doctor> doctors;
	private HashMap <Integer,Nurse> nurses;
	private HashMap<Integer, PatientReport> reports;
	private HashMap <Integer, Patient> patients;
	private HashMap <Integer, Patient> hotelPatients;
	private HashMap <Integer, Disease> diseases;
	private HashMap <Integer, Department> departments;
	private HashMap<Integer, SubDepartment> subDepartments;
	private HashMap<Patient, HashSet<Doctor>> doctorsByPatient;
	private HashMap<Patient, HashSet<Nurse>> nursesByPatient;


	public static Hospital getInstance()
	{
		if(theHospital==null)
			theHospital = new Hospital();
		return theHospital;

	}

	private Hospital()
	{
		patients = new HashMap<>();
		doctors = new HashMap<>();
		nurses = new HashMap<>();
		reports =  new HashMap<>();
		hotelPatients = new HashMap<>();
		diseases = new HashMap<>();
		departments = new HashMap<>();
		subDepartments = new HashMap<>();
		doctorsByPatient = new HashMap<>();
		nursesByPatient = new HashMap<>();

	}

	public HashMap<Integer,Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(HashMap<Integer,Doctor> doctors) {
		this.doctors = doctors;
	}

	public HashMap<Integer, Nurse> getNurses() {
		return nurses;
	}

	public void setNurses(HashMap<Integer, Nurse> nurses) {
		this.nurses = nurses;
	}



	public HashMap<Integer, PatientReport> getReports() {
		return reports;
	}

	public void setReports(HashMap<Integer, PatientReport> reports) {
		this.reports = reports;
	}

	public HashMap<Integer, Patient> getPatients() {
		return patients;
	}

	public void setPatients(HashMap<Integer, Patient> patients) {
		this.patients = patients;
	}

	public HashMap<Integer, Patient> getHotelPatients() {
		return hotelPatients;
	}

	public void setHotelPatients(HashMap<Integer, Patient> hotelPatients) {
		this.hotelPatients = hotelPatients;
	}

	public HashMap<Integer, Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(HashMap<Integer, Disease> diseases) {
		this.diseases = diseases;
	}

	public HashMap<Integer, Department> getDepartments() {
		return departments;
	}

	public void setDepartments(HashMap<Integer,Department> departments) {
		this.departments = departments;
	}


	public HashMap<Integer, SubDepartment> getSubDepartments() {
		return subDepartments;
	}

	public void setSubDepartments(HashMap<Integer, SubDepartment> subDepartments2) { 
		this.subDepartments = subDepartments2;
	}


	public HashMap<Patient, HashSet<Doctor>> getDoctorsByPatient() {
		return doctorsByPatient;
	}

	public void setDoctorsByPatient(HashMap<Patient, HashSet<Doctor>> doctorsByPatient) {
		this.doctorsByPatient = doctorsByPatient;
	}

	public HashMap<Patient, HashSet<Nurse>> getNursesByPatient() {
		return nursesByPatient;
	}

	public void setNursesByPatient(HashMap<Patient, HashSet<Nurse>> nursesByPatient) {
		this.nursesByPatient = nursesByPatient;
	}



	public boolean addDoctor(Doctor doc, SubDepartment s) { 

		if(doc == null || s == null)
			return false;

		if(!getDoctors().containsKey(doc.getId())) //if doctor hasn't already been added
			getDoctors().put(doc.getId(), doc); //adding doctor to Hospital

		s.addDcotor(doc); //adding doctor to SubDepartment


		return true;
	}

	public boolean addNurse(Nurse nurse, SubDepartment s) {
		if(nurse== null || s== null)
			return false;

		if(getNurses().containsKey(nurse.getId())) //if nurse hasn't already been added
			return false;


		getNurses().put(nurse.getId(), nurse); //adding nurse to hospital
		s.addNurse(nurse); // adding nurse to SubDepartment
		return true;
	}

	public boolean addPatient(Patient patient, SubDepartment s) {
		if(patient == null || s== null)
			return false;

		if(getPatients().containsKey(patient.getId())) //if patient hasn't already been added
			return false;

		s.addPatient(patient); //adding patient to SubDepartment
		getPatients().put(patient.getId(), patient); //adding patient to hospital

		return true;
	}

	public boolean addDepartment(Department d) {
		if(d==null)
			return false;

		if(getDepartments().containsKey(d.getId())) //if department hasn't already been added
			return false;

		getDepartments().put(d.getId(), d); //adding department to hospital

		return true;
	}


	public boolean addSubDepartment(Department d, SubDepartment s) {

		if(d == null || s == null)
			return false; 

		if(!getSubDepartments().containsKey(s.getId())) //if SubDepartment hasn't already been added
			getSubDepartments().put(s.getId(), s); //adding SubDepartment to hospital


		return  d.addSubDepartment(s); //adding SubDepartment to department
	}


	public boolean removeDepartment(Department d) {
		if(d==null)
			return false;

		if(!getDepartments().containsKey(d.getId())) //if department hasn't been added we return false
			return false;

		getDepartments().remove(d.getId()); //removing department form hospital

		return true;
	}


	public boolean removeDoctor(Doctor doc){

		if(doc==null) {
			return false;
		}

		if(!doctors.containsKey(doc.getId())) //doctor doen't exist
			return false;

		doc.getsDepartment().removeDoctor(doc); //removing from sub department
		getDoctors().remove(doc.getId()); //removing doctor from hospital

		return true;

	}


	public boolean removeNurse(Nurse nurse)
	{
		if(nurse==null) {
			return false;
		}

		if(!nurse.getsDepartment().getNurses().contains(nurse) || !nurses.containsKey(nurse.getId())) //if nurse doesn't exist
			return false;

		nurse.getsDepartment().removeNurse(nurse); //removing from sub department
		getNurses().remove(nurse.getId()); //removing from hospital

		return true;

	}

	public boolean removePatient(Patient patient)
	{
		if(patient==null) {
			return false;
		}

		if(getPatients().containsKey(patient.getId())) { //if patient doesn't exist
			getPatients().remove(patient.getId()); //removing from all relevant places in hospital
			getDoctorsByPatient().remove(patient); 
			getNursesByPatient().remove(patient);
			patient.getsDepartment().removePatient(patient); //removing from sub department
			return true;

		}
		return false;

	}

	public String removeRecoverPatient(Patient patient) {

		if(patient == null)
			return "Fail";
		if(patient.checkCondition() == ReleaseNote.CAN_GO_HOME) { //if patient's condition is CAN_GO_HOME, patient is removed
			if(removePatient(patient)) {
				return "Success";
			}
			else {
				return "Fail";
			}
		}
		else { // appropriate exception returned indicating patient desn't have appropriate release note
			CannotReleasePatientException e = new CannotReleasePatientException(patient.checkCondition(), ReleaseNote.CAN_GO_HOME);
			return e.toString();
		}
	}

	public String removeToHotelPatient(Patient patient) { 
		if (patient == null) {
			return "Fail";
		}
		if(patient.checkCondition() == ReleaseNote.MOVE_TO_HOTEL) { //patient has appropriate realease note
			if(removePatient(patient)) {
				getHotelPatients().put(patient.getId(), patient); //adding patient to patients in hotel HashMap
				return "Success";
			}	
			else
				return "Fail";
		}
		else {
			CannotReleasePatientException e = new CannotReleasePatientException(patient.checkCondition(), ReleaseNote.MOVE_TO_HOTEL);
			return e.toString(); //returning appropriate message indicating release not isn't appropriate
		}			


	}



	public boolean addDisease(Disease disease) {
		if(!getDiseases().containsKey(disease.getId())) //if the disease in not already added adding it to hospital 
			getDiseases().put(disease.getId(), disease);
		else
			return false;

		return true;
	}

	public boolean removeDisease(Disease disease) { 
		if(disease==null)
			return false;

		if(!diseases.containsKey(disease.getId())) //if disease doesn't exist
			return false;

		getDiseases().remove(disease.getId()); //removing disease from hospital

		return true;
	}

	public Patient getRealPatient(int pid) { //getting the real patients
		return getPatients().get(pid);
	}

	public Doctor getRealDoctor(int did) { //getting the real doctor
		return getDoctors().get(did);
	}

	public Nurse getRealNurse(int nid) { //getting the real nurse
		return getNurses().get(nid);
	}

	public Department getRealDepartment(int did) { //getting the real department
		return getDepartments().get(did);
	}


	public Disease getRealDisease(int did) { //getting the real disease
		return getDiseases().get(did);
	}

	public SubDepartment getRealSubDepartment(int sid) //getting the real sub department
	{
		return getSubDepartments().get(sid);
	}

	public PatientReport getRealPatientReport(int prid) { //getting the real patient report
		return getReports().get(prid);
	}


	public PatientReport addPatientReport(Patient pat, Doctor doc, Date date, Disease des, ReleaseNote rNote )
	{
		if(pat==null || doc==null || date==null || des==null || rNote==null) //if any of the values is null return null
			return null;
		PatientReport pr = new PatientReport(pat, doc, date , des, rNote); //creating a new patient report

		if (getReports().containsKey(pr.getId())) //patient report already exists
			return null;

		getReports().put(pr.getId(), pr); //adding patient report to hospital
		pr.getSdept().addPatientReport(pr);//adding patient report to sub department

		return pr;

	} 

	public boolean removePatientReport(PatientReport pr) {
		if(pr == null)		
			return false;

		if(getReports().containsKey(pr.getId())) { //report already exists
			getReports().remove(pr.getId()); //remove report from hospital
			pr.getSdept().removePatientReport(pr); //remove report from sub department
			return true;
		}

		return false;
	}



	public boolean printAllDoctors(Department dep) {
		if(dep == null)
			return false;
		dep.printAllDoctors();
		return true;

	}
	public boolean printAllNurses(Department d) {
		if(d == null)
			return false;

		d.printAllNurses();
		return true;

	}
	public boolean printAllPatients(Department dep) {
		if(dep == null)
			return false;
		dep.printAllPatients();
		return true;

	}

	public ArrayList<Patient> getAllBadConditionPatients(Doctor d) {
		if(d == null)
			return null;

		TreeSet<Patient> badConditionPatients = new TreeSet<>(new Comparator<Patient>() { // a TreeSet that will contain all patients with critical or serious condition

			@Override
			public int compare(Patient o1, Patient o2) { // the TreeSet will will be in descending order by status
				Integer status1 = o1.getStatus();
				Integer status2 = o2.getStatus();
				return status2.compareTo(status1); 
			}

		});

		Iterator<Entry<Patient, HashSet<Doctor>>> iterator = getDoctorsByPatient().entrySet().iterator(); //using iterator to go over all the hashmap key-value 

		while (iterator.hasNext()) { //going over all the key-values, without repeating one 

			Map.Entry<Model.Patient, java.util.HashSet<Model.Doctor>> entry = (Map.Entry<Model.Patient, java.util.HashSet<Model.Doctor>>) iterator.next();

			Patient patient = entry.getKey();
			HashSet<Doctor> doctors = entry.getValue();

			if(doctors.contains(d)) { //if the doctor has treated the patient
				if(patient.getCondition()==Condition.CRITICAL || patient.getCondition()==Condition.SERIOUS) {
					badConditionPatients.add(patient); //adding the patient that fits the requirements to the TreeSet

				}
			}

		}
		ArrayList<Patient> badConditionPatientsList = new ArrayList<Patient>(badConditionPatients); //creating an array list and saving all sorted TreeSet elements
		return badConditionPatientsList; 

	}

	public Nurse findHardestWorkingNurse() {

		HashMap<Nurse, Integer> nursePatCount = new HashMap<Nurse, Integer>(); // a HashMap where the key is the nurse and the value is the number of patients they treated
		Iterator<Entry<Integer, Nurse>> nurseIterator1 = getNurses().entrySet().iterator(); 

		while (nurseIterator1.hasNext()) {
			Map.Entry<java.lang.Integer, Model.Nurse> entry1 = (Map.Entry<java.lang.Integer, Model.Nurse>) nurseIterator1.next();
			Nurse nurse = entry1.getValue();
			nursePatCount.put(nurse, 0); //adding nurses to the HashMap we created, with 0 patients treated		
		}

		Iterator<Entry<Patient, HashSet<Nurse>>> iterator1 = getNursesByPatient().entrySet().iterator();

		while (iterator1.hasNext()) {
			Map.Entry<Model.Patient, java.util.HashSet<Model.Nurse>> entry = (Map.Entry<Model.Patient, java.util.HashSet<Model.Nurse>>) iterator1.next();
			HashSet<Nurse> nursesList = entry.getValue();
			Iterator<Nurse> nurseIterator2 = nursesList.iterator();

			while (nurseIterator2.hasNext()) {
				Nurse nurse = (Nurse) nurseIterator2.next();
				nursePatCount.put(nurse, nursePatCount.get(nurse)+1); //incrementing the number of patients the nurse has treated		
			}

		}


		int max = 0; //containt the most patient a nurse has treated
		Nurse hardestWorking = null; //the nurse that treated the most patients

		Iterator<Entry<Nurse, Integer>> nurseIterator3 = nursePatCount.entrySet().iterator();

		while (nurseIterator3.hasNext()) {
			Map.Entry<Model.Nurse, java.lang.Integer> entry = (Map.Entry<Model.Nurse, java.lang.Integer>) nurseIterator3.next();
			Integer currentCount = entry.getValue();

			if(currentCount>max) { //updating the maximum
				max=currentCount;
				hardestWorking = entry.getKey();

			}


		}

		return hardestWorking;

	}


	public TreeSet<Patient> getCriticalSteroidsNeuPatients() { 

		TreeSet<Patient> criticalPatients = new TreeSet<Patient>(new Comparator<Patient>(){

			@Override
			public int compare(Patient o1, Patient o2) { //comparing based on last name then first name
				String name1 = o1.getLname() + o1.getFname();
				String name2 = o2.getLname() + o2.getFname();

				return name1.compareTo(name2); // Ascending order
			}
		}); 

		Iterator<Entry<Patient, HashSet<Nurse>>> iterator = nursesByPatient.entrySet().iterator();
		while (iterator.hasNext()) { //going over HashMap nursesByPatient with Iterator 
			boolean doctorIsNeeded = false;
			boolean nurseIsNeeded = false;
			boolean patientIsNeeded = false;

			Map.Entry<Model.Patient, java.util.HashSet<Model.Nurse>> entry = (Map.Entry<Model.Patient, java.util.HashSet<Model.Nurse>>) iterator.next();

			Patient patient = entry.getKey();
			HashSet<Nurse> nurses = entry.getValue();


			if ((patient.getCondition() == Condition.CRITICAL)) //checking if patient meets condition
				patientIsNeeded = true; //flag


			for (Nurse nurse : nurses) { //checking if any of the nurses meet condition
				if (nurse.getTreat() == Treatments.STEROIDS) {
					nurseIsNeeded = true; //flag
					break; 
				}
			}

			if(getDoctorsByPatient().containsKey(patient)) { 
				HashSet<Doctor> doctors = getDoctorsByPatient().get(patient);	
				for (Doctor doctor : doctors) { //going over doctors that have treated given patient
					if(doctor.getSpec() == Specialization.NEUROLOGY) { //checking if any of the doctors meets condition
						doctorIsNeeded = true; //flag
						break; 
					}
				}
			}

			if(patientIsNeeded == true && nurseIsNeeded == true && doctorIsNeeded == true) //if all conditions have been met
				criticalPatients.add(patient); //adding the patient to TreeSet
			else
				continue; //keep searching
		}

		return criticalPatients;
	}



	public TreeSet<SubDepartment> getBestStatusSubDepartments() {

		HashMap<SubDepartment, Integer> patientsInSubdepartment = new HashMap<SubDepartment, Integer>();
		Iterator<Entry<Integer, SubDepartment>> iterator = subDepartments.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<java.lang.Integer, Model.SubDepartment> entry = (Map.Entry<java.lang.Integer, Model.SubDepartment>) iterator.next();
			SubDepartment sDepartment = entry.getValue();
			int countGoodConditionPatients = 0; //counter for patient with the condition GOOD

			HashSet<Patient> patientsInSDepartment = sDepartment.getPatients();
			for (Patient patient : patientsInSDepartment) {
				if (patient.getCondition() == Condition.GOOD) {
					countGoodConditionPatients++; //Incrementing the number of GOOD condition patients and adding them as the value of the sub department
				}
			}

			patientsInSubdepartment.put(sDepartment, countGoodConditionPatients);
		}

		int maxGoodPatients = 0; //counter for maximum patients with GOOD Condition
		int otherMaxGoodPatients = 0; //counter for second biggest number of Patients with GOOD condition 
		SubDepartment maxSDepartment = null; //sub department with most good patients
		SubDepartment otherMaxSDepartment = null; //second biggest department with good patients

		Iterator<Entry<SubDepartment, Integer>> iterator2 = patientsInSubdepartment.entrySet().iterator(); 

		while (iterator2.hasNext()) {
			Map.Entry<Model.SubDepartment, java.lang.Integer> entry = (Map.Entry<Model.SubDepartment, java.lang.Integer>) iterator2.next();	
			SubDepartment sDepartment = entry.getKey();
			int currentCounter = entry.getValue().intValue();

			if (currentCounter > maxGoodPatients) { //updating values when finding a new maximum sub department
				otherMaxGoodPatients = maxGoodPatients;
				otherMaxSDepartment = maxSDepartment;
				maxGoodPatients = currentCounter;
				maxSDepartment = sDepartment;
			} 
			else {
				if (currentCounter > otherMaxGoodPatients) { //a new other max department 
					otherMaxGoodPatients = currentCounter;
					otherMaxSDepartment = sDepartment;
				}
			}
		}

		TreeSet<SubDepartment> bestTwoSDepartments = new TreeSet<SubDepartment>(new Comparator<SubDepartment>() {

			@Override
			public int compare(SubDepartment s1, SubDepartment s2) { //comperator based on number of patients with good condition 
				Integer countPatientsInS1 = Integer.valueOf(s1.getPatients().size());
				Integer countPatientsInS2 = Integer.valueOf(s2.getPatients().size());

				return countPatientsInS2.compareTo(countPatientsInS1); //descending order


			}});

		bestTwoSDepartments.add(maxSDepartment);
		bestTwoSDepartments.add(otherMaxSDepartment);

		return  bestTwoSDepartments; //returning both sub departments 
	}




	public TreeSet<Doctor> getDoctorBySpec(Specialization s) {

		HashSet<Doctor> doctorsSpecWithReport = new HashSet<Doctor>();
		Iterator<Entry<Integer, PatientReport>> ReportsIterator = getReports().entrySet().iterator();

		while (ReportsIterator.hasNext()) { //going over reports with iterator 
			Map.Entry<java.lang.Integer, Model.PatientReport> entry = (Map.Entry<java.lang.Integer, Model.PatientReport>) ReportsIterator.next();
			PatientReport report = entry.getValue();

			if(report.getDoctor().getSpec()==s) //if the doctors specialisation is s
				doctorsSpecWithReport.add(report.getDoctor()); //adding doctor to HashSet

		}

		TreeSet<Doctor> doctorsBySpec = new TreeSet<Doctor>(new Comparator<Doctor>() {

			@Override
			public int compare(Doctor o1, Doctor o2) { //comparing based on number of shifts 
				Integer shifts1 = Integer.valueOf(o1.getShiftCounter());
				Integer shifts2 = Integer.valueOf(o2.getShiftCounter());

				return shifts2.compareTo(shifts1); //descending order 
			}

		});

		Iterator<Doctor> doctorsToCopy = doctorsSpecWithReport.iterator();
		while (doctorsToCopy.hasNext()) {
			Doctor doctor = (Doctor) doctorsToCopy.next();
			doctorsBySpec.add(doctor); //Copying doctors to TreeSet using iterator 

		}

		return doctorsBySpec;

	}


	public TreeSet<Disease> getDiseasesByRange(char start, char end) {

		TreeSet<Disease> diseasesByRange = new TreeSet<Disease>();
		Iterator<Entry<Integer, Disease>> diseaseIterator = getDiseases().entrySet().iterator();

		while (diseaseIterator.hasNext()) {
			Map.Entry<java.lang.Integer, Model.Disease> entry = (Map.Entry<java.lang.Integer, Model.Disease>) diseaseIterator.next();
			Disease disease = entry.getValue();

			char diseaseFirstLetter = disease.getName().toUpperCase().charAt(0); //getting first letter in disease name capitalised 
			char startCapital = Character.toUpperCase(start); //making sure start in upper case
			char endCapital = Character.toUpperCase(end); //making sure end is upper case

			if((int)(diseaseFirstLetter-startCapital) >= 0 && (int)(diseaseFirstLetter-endCapital) <= 0) //checking if the first letter is between start and end
				diseasesByRange.add(disease); //adding disease to TreeSet
		}
		return diseasesByRange;
	}


	public LinkedList<Patient> getAllDifficultBreathingPatients(Department d) {

		if(d==null)
			return null;

		TreeSet<Patient> difBreathingPatients = new TreeSet<Patient>(new Comparator<Patient>() {

			@Override
			public int compare(Patient o1, Patient o2) { //comparing based on first subDepartment's id, then by patients last name
				Integer sDepId1 = Integer.valueOf(o1.getsDepartment().getId());
				Integer sDepId2 = Integer.valueOf(o2.getsDepartment().getId());
				int mainCompare = sDepId2.compareTo(sDepId1);
				if(mainCompare!=0)
					return sDepId2.compareTo(sDepId1); //Descending order

				String lastName1 = o1.getLname();
				String lastName2 = o2.getLname();

				return lastName2.compareTo(lastName1); //Descending order

			}
		});



		Iterator<Entry<Patient, HashSet<Nurse>>> iterator = nursesByPatient.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<Model.Patient, java.util.HashSet<Model.Nurse>> entry = (Map.Entry<Model.Patient, java.util.HashSet<Model.Nurse>>) iterator.next();

			Patient patient = entry.getKey();

			if (patient.getDisease().getSymptoms().contains(Symptoms.DIFFICULTY_BREATHING)){ //checking if DIFFICULTY_BREATHING if on of the symptoms 
				HashSet<Nurse> nurses = entry.getValue();
				for (Nurse nurse : nurses) {
					if (nurse.getTreat()==Treatments.BREATHING_SUPPORT) { //checking if patient has been treated with a nurse who treats BREATHING_SUPPORT
						difBreathingPatients.add(patient); //adding patient to TreeSet
					}

				}

			}
		}


		LinkedList<Patient> difBreathingPatientsList = new LinkedList<Patient>(); 
		for(Patient pat : difBreathingPatients) //copying values from TreeSet to linked list
			difBreathingPatientsList.add(pat);


		return difBreathingPatientsList;
	}

	public TreeSet<Patient> treatDiseases(Department d) {
		// TODO Auto-generated method stub
		return new TreeSet<Patient>();
	}

	public TreeSet<Patient> treatPatients(Department d) {
		// TODO Auto-generated method stub
		return new TreeSet<Patient>();
	}


}
