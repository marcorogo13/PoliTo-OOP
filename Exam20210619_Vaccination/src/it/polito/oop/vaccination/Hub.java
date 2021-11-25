package it.polito.oop.vaccination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Hub {
	String name;
	Integer doctors = 0;
	Integer nurses = 0;
	Integer others = 0;
	
	
	
	public Hub(String name) {
		super();
		this.name = name;

	}
	
	
	void setPersonel (Integer doctors, Integer nurses, Integer others) {
		this.doctors = doctors;
		this.nurses = nurses;
		this.others = others;
		
	}
	
	
	boolean isSet() {
		if (doctors == 0 && nurses == 0 && others == 0) {
			return false;
		}
		return true;
	}
	
	 /**
     * Estimates the hourly vaccination capacity of a hub
     *
     * The capacity is computed as the minimum among
     * 10*number_doctor, 12*number_nurses, 20*number_other
     *
     * @param hub name of the hub
     * @return hourly vaccination capacity
     * @throws VaccineException in case of undefined or hub without staff
     */
	
	Integer vaccinationCapacity() {
		List<Integer> cap = new ArrayList<Integer>();
		cap.add(doctors * 10);
		cap.add(nurses * 12);
		cap.add(others * 20);
		Collections.sort(cap);
		return cap.get(0);
	}

}
