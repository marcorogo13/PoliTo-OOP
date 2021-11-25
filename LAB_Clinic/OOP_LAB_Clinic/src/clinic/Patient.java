package clinic;

public class Patient {
	String first; 
	String last;
	String ssn;
	Doctor assignedDoc;
	
	Patient (String first, String last, String ssn){
		this.first = first;
		this.last = last;
		this.ssn = ssn;

	}
	
	
	void assignDoc(Doctor doc){
		assignedDoc = doc;
	}
	
	Doctor getAssigned() {
		return assignedDoc;
	}
	
	String getSsn() {
		return ssn;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(last).append(" ").append(first).append(" ").append("(" + ssn + ")");
		return sb.toString();
	}
}
