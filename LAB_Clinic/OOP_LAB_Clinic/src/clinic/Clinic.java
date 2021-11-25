package clinic;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a clinic with patients and doctors.
 * 
 */
public class Clinic {
	Map <String, Patient> patients;
	Map <Integer, Doctor> doctors;
	
	
	
	public Clinic() {
		patients = new HashMap<String, Patient>();
		doctors = new HashMap<Integer, Doctor>();
	}
	
	
	/**
	 * Add a new clinic patient.
	 * 
	 * @param first first name of the patient
	 * @param last last name of the patient
	 * @param ssn SSN number of the patient
	 */
	public void addPatient(String first, String last, String ssn) {
		Patient tmp = new Patient(first, last, ssn);
		patients.put(ssn, tmp);
	}


	/**
	 * Retrieves a patient information
	 * 
	 * @param ssn SSN of the patient
	 * @return the object representing the patient
	 * @throws NoSuchPatient in case of no patient with matching SSN
	 */
	public String getPatient(String ssn) throws NoSuchPatient {
		if (patients.get(ssn) == null) {
			throw new NoSuchPatient();
		}
		return patients.get(ssn).toString();
	}

	/**
	 * Add a new doctor working at the clinic
	 * 
	 * @param first first name of the doctor
	 * @param last last name of the doctor
	 * @param ssn SSN number of the doctor
	 * @param docID unique ID of the doctor
	 * @param specialization doctor's specialization
	 */
	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
		Doctor tmp = new Doctor(first, last, ssn, docID, specialization);
		addPatient(first, last, ssn);
		doctors.put(docID, tmp);
	}

	/**
	 * Retrieves information about a doctor
	 * 
	 * @param docID ID of the doctor
	 * @return object with information about the doctor
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public String getDoctor(int docID) throws NoSuchDoctor {
		if (doctors.get(docID) == null) {
			throw new NoSuchDoctor();
		}
		
		return doctors.get(docID).toString();
	}
	
	/**
	 * Assign a given doctor to a patient
	 * 
	 * @param ssn SSN of the patient
	 * @param docID ID of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
		if (doctors.get(docID) == null) {
			throw new NoSuchDoctor();
		}
		Doctor doc = doctors.get(docID);
		
		if (patients.get(ssn) == null) {
			throw new NoSuchPatient();
		}
		Patient pat = patients.get(ssn);
		
		doc.addPat(pat);
		pat.assignDoc(doc);
	}
	
	/**
	 * Retrieves the id of the doctor assigned to a given patient.
	 * 
	 * @param ssn SSN of the patient
	 * @return id of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor has been assigned to the patient
	 */
	public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
		if (patients.get(ssn) == null) {
			throw new NoSuchPatient();
		}
		Patient pat = patients.get(ssn);
		
		if (pat.getAssigned() == null) {
			throw new NoSuchDoctor();
		}
		
		return pat.getAssigned().getDocID();
	}
	
	/**
	 * Retrieves the patients assigned to a doctor
	 * 
	 * @param id ID of the doctor
	 * @return collection of patient SSNs
	 * @throws NoSuchDoctor in case the {@code id} does not match any doctor 
	 */
	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {
		if (doctors.get(id) == null) {
			throw new NoSuchDoctor();
		}
		Doctor doc = doctors.get(id);
		
		return doc.getPatients();
	}


	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and specialization.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method should be able
	 * to ignore the row and skip to the next one.<br>

	 * 
	 * @param readed linked to the file to be read
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader) throws IOException {
		
		List<String> ls = readData(reader);
		int numbOfLines = 0;
		
		for (String s : ls) {
			
			s = s.replace("\n", "").replace("\r", "").replace(" ", "");
			
			List <String> args = new ArrayList<String>();
			args.addAll((Arrays.asList(s.split(";"))));
			if (args.size() < 4) {
				continue;
			}
			
			if (s.charAt(0) == 'P') {
				
				if (args.size() != 4) {
					continue;
				}
				this.addPatient(args.get(1), args.get(2), args.get(3));
				
			}
			if (s.charAt(0) == 'M') {
				
				if (args.size() != 6) {
					continue;
				}
				this.addDoctor(args.get(2), args.get(3), args.get(4), Integer. parseInt(args.get(1)), args.get(5));
			}	
		
			numbOfLines ++;
		}
		return numbOfLines;
	
	}



	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and speciality.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method calls the
	 * {@link ErrorListener#offending} method passing the line itself,
	 * ignores the row, and skip to the next one.<br>

	 * 
	 * @param reader reader linked to the file to be read
	 * @param listener listener used for wrong line notifications
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader, ErrorListener listener) throws IOException {
		
		List<String> ls = readData(reader);
		int numbOfLines = 0;

		for (String s : ls) {
			
			s = s.replace("\n", "").replace("\r", "").replace(" ", "");
			
			List <String> args = new ArrayList<String>();
			args.addAll((Arrays.asList(s.split(";"))));
			if (args.size() < 4) {
				listener.offending(s);
				continue;
			}
			
			if (s.charAt(0) == 'P') {
				

				if (args.size() != 4) {
					listener.offending(s);
					continue;
				}
				this.addPatient(args.get(1), args.get(2), args.get(3));
				
			}
			if (s.charAt(0) == 'M') {

				if (args.size() != 6) {
					listener.offending(s);
					continue;
				}
				this.addDoctor(args.get(2), args.get(3), args.get(4), Integer. parseInt(args.get(1)), args.get(5));
			}	
		
			numbOfLines ++;
		}
		
		return numbOfLines;
	}

	
	
	
	private static List<String> readData(Reader reader) {
		try (BufferedReader in = new BufferedReader(reader)) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	
	
	

	
		
	/**
	 * Retrieves the collection of doctors that have no patient at all.
	 * The doctors are returned sorted in alphabetical order
	 * 
	 * @return the collection of doctors' ids
	 */
	public Collection<Integer> idleDoctors(){
		return doctors.values().stream().filter(d -> d.getPatients().size() == 0)
								.sorted(Comparator.comparing(Doctor::getLast).thenComparing(Doctor::getFirst))
								.map(e -> e.getDocID()).collect(Collectors.toList());
		 
	}

	/**
	 * Retrieves the collection of doctors having a number of patients larger than the average.
	 * 
	 * @return  the collection of doctors' ids
	 */
	public Collection<Integer> busyDoctors(){
		double avg =  doctors.values().stream().map(d -> d.getPatients().size()).collect(Collectors.averagingDouble(e -> e));
		
		return doctors.values().stream().filter(d -> d.getPatients().size() > avg).map(e -> e.getDocID()).collect(Collectors.toList());
	}

	/**
	 * Retrieves the information about doctors and relative number of assigned patients.
	 * <p>
	 * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
	 * represent the number of patients (printed on three characters).
	 * <p>
	 * The list is sorted by decreasing number of patients.
	 * 
	 * @return the collection of strings with information about doctors and patients count
	 */
	public Collection<String> doctorsByNumPatients(){
		return doctors.values().stream().sorted(Comparator.comparing((Doctor d)->d.getPatients().size()).reversed())
				.map(d -> String.format("%3d : %d %s %s", d.getPatients().size(), d.getDocID(), d.getLast(), d.getFirst())).collect(Collectors.toList());

	}
	
	/**
	 * Retrieves the number of patients per (their doctor's)  speciality
	 * <p>
	 * The information is a collections of strings structured as {@code ### - SPECIALITY}
	 * where {@code SPECIALITY} is the name of the speciality and 
	 * {@code ###} is the number of patients cured by doctors with such speciality (printed on three characters).
	 * <p>
	 * The elements are sorted first by decreasing count and then by alphabetic speciality.
	 * 
	 * @return the collection of strings with speciality and patient count information.
	 */
	public Collection<String> countPatientsPerSpecialization(){
		
		Map<String, Integer> patientsPer = new TreeMap<>();
		
		for (Doctor d : doctors.values()) {
			if (patientsPer.containsKey(d.getSpecialization())) {
				patientsPer.put(d.getSpecialization(), patientsPer.get(d.getSpecialization()) + d.getPatients().size());
			}else {
				patientsPer.put(d.getSpecialization(), d.getPatients().size());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		List <String> toReturn = new ArrayList<>();
		
		Map<String, Integer> sorted = patientsPer.entrySet().stream()
			    				.sorted(Map.Entry.comparingByValue()) 			
			    				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
			    				(oldValue, newValue) -> oldValue, HashMap::new));
					
				
				//.stream().sorted(Map.Entry.comparingByValue())
					//.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		

		for (String s : sorted.keySet()) {
			sb.append(String.format("%3d", patientsPer.get(s))).append(" - ").append(s);
			toReturn.add(sb.toString());
			sb = new StringBuilder();
		}
		
	
		return toReturn;
	}
	
}
