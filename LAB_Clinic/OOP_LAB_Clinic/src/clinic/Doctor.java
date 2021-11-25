package clinic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Doctor {
	String first; 
	String last;
	String ssn;
	int docID;
	String specialization;
	List<Patient> patients;
	
	Doctor(String first, String last, String ssn, int docID, String specialization){
		this.first = first;
		this.last = last;
		this.ssn = ssn;
		this.docID= docID;
		this.specialization = specialization;
		patients = new ArrayList<Patient>();
		
	}
	
	void addPat(Patient pat) {
		patients.add(pat);
	}
	
	
	int getDocID() {
		return docID;
	}
	String getLast() {
		return last;
	}
	String getFirst() {
		return first;
	}
	public String getSpecialization() {
		return specialization;
	}

	Collection <String> getPatients(){
		return patients.stream().map(p -> p.getSsn()).collect(Collectors.toList());
	}
	
	

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(last).append(" ").append(first).append(" ").append("(" + ssn + ")").append(" ").append("[" + docID + "]")
						.append(":").append(" ").append(specialization);
		return sb.toString();
	}

}
