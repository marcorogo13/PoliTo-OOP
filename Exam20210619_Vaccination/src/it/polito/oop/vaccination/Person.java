package it.polito.oop.vaccination;

public class Person {
	String ssn;
	String name;
	String lastName;
	Integer birthYear;
	boolean allocated = false; // added
	
	public Person(String ssn, String name, String lastName, Integer birthYear) {
		this.ssn = ssn;
		this.name = name;
		this.lastName = lastName;
		this.birthYear = birthYear;
	}
	
	
	
	
	public String getSsn() {
		return ssn;
	}




	public String getName() {
		return name;
	}




	public String getLastName() {
		return lastName;
	}




	public Integer getBirthYear() {
		return birthYear;
	}


	boolean fitInterval(Integer year, String Interval) {
		
		
		String startString = Interval.split(",")[0];
		startString = startString.replaceAll("\\[", "");
		
		Integer start = Integer.parseInt(startString);
		
		startString = Interval.split(",")[1];
		startString = startString.replaceAll("\\)", "");
		Integer end;
		if (startString.compareTo("+") == 0) {
			end = 10000000;
		}else {
			end = Integer.parseInt(startString);
		}
		
		if (year- birthYear >= start && year - birthYear < end) {
			return true;
		}
		
		
		return false;
	}
	

	@Override
	public String toString() {
		
		return ssn + "," + lastName + "," + name;	
	}
	
}
