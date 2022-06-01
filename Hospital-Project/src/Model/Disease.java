package Model;

import java.util.HashSet;
import Exceptions.DiseaseWithNoSymptomsException;
import Utils.Symptoms;

public class Disease implements Comparable<Disease> {

	private static int ID=1;
	private int id;
	private String name;
	private HashSet<Symptoms> symptoms;


	public Disease(String name) {
		super();
		this.id = ID++; //Incrementing id each time an object is created
		this.name = name;
		symptoms =  new HashSet<>();
	}


	public Disease(int id, String name, HashSet<Symptoms> symptoms) {
		super();
		this.id = id;
		this.name = name;
		symptoms = new HashSet<>();
		setSymptoms(symptoms); 
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Disease(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	public HashSet<Symptoms> getSymptoms() {
		return symptoms;
	}

	public String setSymptoms(HashSet<Symptoms> symptoms) { 
		if(symptoms==null || symptoms.isEmpty()) {
			ID--;
			DiseaseWithNoSymptomsException e = new DiseaseWithNoSymptomsException(); //returning exceptions if method didn't receive any symptoms
			return e.toString();
		}

		for(Symptoms symp : symptoms) { //adding symptoms 
			getSymptoms().add(symp);
		}

		return "Success";
	}


	@Override
	public String toString() {
		return "Disease [name=" + name + ", symptoms=" + symptoms + "]";
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
		Disease other = (Disease) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public int compareTo(Disease o) { //comparing disease based on disease name
		String name1 = this.getName();
		String name2 = o.getName();

		return name2.compareTo(name1); // descending order 
	}



}
