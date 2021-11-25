package it.polito.oop.vaccination;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Vaccines {

	public final static int CURRENT_YEAR = java.time.LocalDate.now().getYear();

	Map<String, Person> persons = new TreeMap<String, Person>();
	Map<Integer, String> ageIntervals = new TreeMap<Integer, String>(Collections.reverseOrder());
	Map<String, Hub> hubs = new TreeMap<>();

	Map<Integer, Integer> hoursPerDay = new HashMap<Integer, Integer>();
	Map<Integer, List<String>> slotsPerDay = new HashMap<Integer, List<String>>();
	BiConsumer<Integer, String> listener = null; 

	// R1
	/**
	 * Add a new person to the vaccination system.
	 *
	 * Persons are uniquely identified by SSN (italian "codice fiscale")
	 *
	 * @param firstName first name
	 * @param last      last name
	 * @param ssn       italian "codice fiscale"
	 * @param y         birth year
	 * @return {@code false} if ssn is duplicate,
	 */
	public boolean addPerson(String firstName, String last, String ssn, int y) {

		if (this.persons.containsKey(ssn)) {
			return false;
		}

		persons.put(ssn, new Person(ssn, firstName, last, y));

		return true;
	}

	/**
	 * Count the number of people added to the system
	 *
	 * @return person count
	 */
	public int countPeople() {
		return this.persons.keySet().size();
	}

	/**
	 * Retrieves information about a person. Information is formatted as ssn, last
	 * name, and first name separate by {@code ','} (comma).
	 *
	 * @param ssn "codice fiscale" of person searched
	 * @return info about the person
	 */
	public String getPerson(String ssn) {

		return persons.get(ssn).toString();
	}

	/**
	 * Retrieves of a person given their SSN (codice fiscale).
	 *
	 * @param ssn "codice fiscale" of person searched
	 * @return age of person (in years)
	 */
	public int getAge(String ssn) {

		Integer toRet = CURRENT_YEAR - persons.get(ssn).getBirthYear();
		System.out.println(toRet);
		return toRet;
	}

	/**
	 * Define the age intervals by providing the breaks between intervals. The first
	 * interval always start at 0 (non included in the breaks) and the last interval
	 * goes until infinity (not included in the breaks). All intervals are closed on
	 * the lower boundary and open at the upper one.
	 * <p>
	 * For instance {@code setAgeIntervals(40,50,60)} defines four intervals
	 * {@code "[0,40)", "[40,50)", "[50,60)", "[60,+)"}.
	 *
	 * @param brks the array of breaks
	 */
	public void setAgeIntervals(int... brks) {
		for (int i = 0; i < brks.length; i++) {
			if (i == brks.length - 1) {
				this.ageIntervals.put(brks[i], "[" + brks[i] + "," + "+" + ")");
			}

			if (i == 0) {
				this.ageIntervals.put(0, "[" + "0" + "," + brks[i] + ")");
			}

			if (i != 0 && i < brks.length) {
				this.ageIntervals.put(brks[i - 1], "[" + brks[i - 1] + "," + brks[i] + ")");
			}
		}

	}

	/**
	 * Retrieves the labels of the age intervals defined.
	 *
	 * Interval labels are formatted as {@code "[0,10)"}, if the upper limit is
	 * infinity {@code '+'} is used instead of the number.
	 *
	 * @return labels of the age intervals
	 */
	public Collection<String> getAgeIntervals() {

		return this.ageIntervals.values();
	}

	/**
	 * Retrieves people in the given interval.
	 *
	 * The age of the person is computed by subtracting the birth year from current
	 * year.
	 *
	 * @param interval age interval label
	 * @return collection of SSN of person in the age interval
	 */
	public Collection<String> getInInterval(String interval) {

		List<String> toRet = new ArrayList<>();

		toRet = persons.values().stream().filter(p -> p.fitInterval(CURRENT_YEAR, interval) == true).map(Person::getSsn)
				.collect(Collectors.toList());

		return toRet;
	}

	// R2
	/**
	 * Define a vaccination hub
	 *
	 * @param name name of the hub
	 * @throws VaccineException in case of duplicate name
	 */
	public void defineHub(String name) throws VaccineException {
		if (this.hubs.containsKey(name)) {
			throw new VaccineException();
		}
		this.hubs.put(name, new Hub(name));
	}

	/**
	 * Retrieves hub names
	 *
	 * @return hub names
	 */
	public Collection<String> getHubs() {
		return this.hubs.keySet();
	}

	/**
	 * Define the staffing of a hub in terms of doctors, nurses and other personnel.
	 *
	 * @param name         name of the hub
	 * @param countDoctors number of doctors
	 * @param countNurse   number of nurses
	 * @param o            number of other personnel
	 * @throws VaccineException in case of undefined hub, or any number of personnel
	 *                          not greater than 0.
	 */
	public void setStaff(String name, int countDoctors, int countNurse, int o) throws VaccineException {
		if (!this.hubs.containsKey(name)) {
			throw new VaccineException();
		}
		if (countDoctors < 0 || countNurse < 0 || o < 0) {
			throw new VaccineException();
		}
		this.hubs.get(name).setPersonel(countDoctors, countNurse, o);

	}

	/**
	 * Estimates the hourly vaccination capacity of a hub
	 *
	 * The capacity is computed as the minimum among 10*number_doctor,
	 * 12*number_nurses, 20*number_other
	 *
	 * @param hub name of the hub
	 * @return hourly vaccination capacity
	 * @throws VaccineException in case of undefined or hub without staff
	 */
	public int estimateHourlyCapacity(String hub) throws VaccineException {
		if (!this.hubs.containsKey(hub)) {
			throw new VaccineException();
		}

		if (!this.hubs.get(hub).isSet()) {
			throw new VaccineException();
		}

		return hubs.get(hub).vaccinationCapacity();
	}

	// R3
	/**
	 * Load people information stored in CSV format.
	 *
	 * The header must start with {@code "SSN,LAST,FIRST"}. All lines must have at
	 * least three elements.
	 *
	 * In case of error in a person line the line is skipped.
	 *
	 * @param people {@code Reader} for the CSV content
	 * @return number of correctly added people
	 * @throws IOException      in case of IO error
	 * @throws VaccineException in case of error in the header
	 */
	public long loadPeople(Reader people) throws IOException, VaccineException {
		// Hint:
		BufferedReader br = new BufferedReader(people);
		String line = br.readLine();

		int i = 0;
		long count = 0;
		while (line != null) {
			String original = line;

			if (i == 0) {
				line.trim();
				String header[] = line.split(",");
				System.out.println(header[0]);
				if (header.length != 4 ||
						header[0].compareTo("SSN") != 0 || header[1].compareTo("LAST") != 0
						|| header[2].compareTo("FIRST") != 0 || header[3].compareTo("YEAR") != 0) {
					if (listener != null)
						listener.accept(i + 1, original);
					throw new VaccineException();

				}

			}

			if (i > 0) {
				line.trim();
				String data[] = line.split(",");

				if (data.length == 4 && addPerson(data[2], data[1], data[0], Integer.parseInt(data[3]))) {
					count++;
				} else {
					if (listener != null)
						listener.accept(i + 1, original);
				}
			}

			line = br.readLine();
			i++;
		}

		br.close();
		return count;
	}

	// R4
	/**
	 * Define the amount of working hours for the days of the week.
	 *
	 * Exactly 7 elements are expected, where the first one correspond to Monday.
	 *
	 * @param hours workings hours for the 7 days.
	 * @throws VaccineException if there are not exactly 7 elements or if the sum of
	 *                          all hours is less than 0 ore greater than 24*7.
	 */
	public void setHours(int... hours) throws VaccineException {
		if (hours.length != 7) {
			throw new VaccineException();
		}

		for (int i : hours) {
			if (i > 12) {
				throw new VaccineException();
			}
		}
		
		for (int i = 0; i < hours.length; i++) {
			if (hours[i] < 0)
				throw new VaccineException();
			this.hoursPerDay.put(i, hours[i]);
		}
		for (Integer i : hoursPerDay.keySet()) {
			List<String> slots = new ArrayList<String>();
			for (int hh = 9; hh < 9 + hoursPerDay.get(i); hh++) {
				for (int t = 0; t < 60; t += 15)
					slots.add(String.format("%02d:%02d", hh, t));
			}
			slotsPerDay.put(i, slots);
		}

	}

	/**
	 * Returns the list of standard time slots for all the days of the week.
	 *
	 * Time slots start at 9:00 and occur every 15 minutes (4 per hour) and they
	 * cover the number of working hours defined through method {@link #setHours}.
	 * <p>
	 * Times are formatted as {@code "09:00"} with both minuts and hours on two
	 * digits filled with leading 0.
	 * <p>
	 * Returns a list with 7 elements, each with the time slots of the corresponding
	 * day of the week.
	 *
	 * @return the list hours for each day of the week
	 */
	public List<List<String>> getHours() {
		return slotsPerDay.values().stream().collect(Collectors.toList());
	}

	/**
	 * Compute the available vaccination slots for a given hub on a given day of the
	 * week
	 * <p>
	 * The availability is computed as the number of working hours of that day
	 * multiplied by the hourly capacity (see {@link #estimateCapacity} of the hub.
	 *
	 * @return
	 */
	public int getDailyAvailable(String hub, int d) {
		return hoursPerDay.get(d) * hubs.get(hub).vaccinationCapacity();
	}

	/**
	 * Compute the available vaccination slots for each hub and for each day of the
	 * week
	 * <p>
	 * The method returns a map that associates the hub names (keys) to the lists of
	 * number of available hours for the 7 days.
	 * <p>
	 * The availability is computed as the number of working hours of that day
	 * multiplied by the capacity (see {@link #estimateCapacity} of the hub.
	 *
	 * @return
	 */
	public Map<String, List<Integer>> getAvailable() {
		return hubs.keySet().stream().collect(Collectors.toMap(x -> x,
				x -> slotsPerDay.keySet().stream().map(y -> getDailyAvailable(x, y)).collect(Collectors.toList())));
	}

	/**
	 * Computes the general allocation plan a hub on a given day. Starting with the
	 * oldest age intervals 40% of available places are allocated to persons in that
	 * interval before moving the the next interval and considering the remaining
	 * places.
	 * <p>
	 * The returned value is the list of SSNs (codice fiscale) of the persons
	 * allocated to that day
	 * <p>
	 * <b>N.B.</b> no particular order of allocation is guaranteed
	 *
	 * @param hub name of the hub
	 * @param d   day of week index (0 = Monday)
	 * @return the list of daily allocations
	 */
	public List<String> allocate(String hub, int d) {
		List<String> toRet = new ArrayList<String>();
		Long limit, loopcount = (long) 1, n = (long) getDailyAvailable(hub, d);
		while (n > 0) {
			for (String s : ageIntervals.values()) {
				Integer tmp = toRet.size();
				List<String> personsInInterval = new ArrayList<String>(getInInterval(s));
				limit = ((loopcount > 1) ? n : (long) Math.floor(n * 0.4));
				personsInInterval.stream().map(p -> persons.get(p)).filter(p -> p.allocated == false).limit(limit)
						.forEach(p -> {
							toRet.add(p.getSsn());
							p.allocated = true;
						});
				n = n - toRet.size() + tmp;
			}
			loopcount++;
		}
		return toRet;
	}

	/**
	 * Removes all people from allocation lists and clears their allocation status
	 */
	public void clearAllocation() {
		for (Person p : persons.values()) 
			p.allocated = false;
	}

	/**
	 * Computes the general allocation plan for the week. For every day, starting
	 * with the oldest age intervals 40% available places are allocated to persons
	 * in that interval before moving the the next interval and considering the
	 * remaining places.
	 * <p>
	 * The returned value is a list with 7 elements, one for every day of the week,
	 * each element is a map that links the name of each hub to the list of SSNs
	 * (codice fiscale) of the persons allocated to that day in that hub
	 * <p>
	 * <b>N.B.</b> no particular order of allocation is guaranteed but the same
	 * invocation (after {@link #clearAllocation}) must return the same allocation.
	 *
	 * @return the list of daily allocations
	 */
	public List<Map<String, List<String>>> weekAllocate() {
		return hoursPerDay.keySet().stream()
				.map(x -> hubs.keySet().stream().collect(Collectors.toMap(y -> y, y -> allocate(y, x))))
				.collect(Collectors.toList());
	}

	// R5
	/**
	 * Returns the proportion of allocated people w.r.t. the total number of persons
	 * added in the system
	 *
	 * @return proportion of allocated people
	 */
	public double propAllocated() {
		return (double) (persons.values().stream().filter(p -> p.allocated == true).count()) / persons.size();
	}

	/**
	 * Returns the proportion of allocated people w.r.t. the total number of persons
	 * added in the system, divided by age interval.
	 * <p>
	 * The map associates the age interval label to the proportion of allocates
	 * people in that interval
	 *
	 * @return proportion of allocated people by age interval
	 */
	public Map<String, Double> propAllocatedAge() {
		return ageIntervals.values().stream()
				.collect(
						Collectors.toMap(s -> s,
								s -> (double) (double) (persons.values().stream()
										.filter(p -> p.fitInterval(CURRENT_YEAR, s)).filter(p -> p.allocated == true)
										.count()) / persons.size()));
	}

	/**
	 * Retrieves the distribution of allocated persons among the different age
	 * intervals.
	 * <p>
	 * For each age intervals the map reports the proportion of allocated persons in
	 * the corresponding interval w.r.t the total number of allocated persons
	 *
	 * @return
	 */
	public Map<String, Double> distributionAllocated() {	
		return ageIntervals.values().stream()
				.collect(Collectors.toMap(s -> s,
						s -> ((double) persons.values().stream().filter(p -> p.fitInterval(CURRENT_YEAR, s))
								.filter(p -> p.allocated == true).count()
								/ (double) persons.values().stream().filter(p -> p.allocated == true).count())));
	}

	// R6
	/**
	 * Defines a listener for the file loading method. The {@ accept()} method of
	 * the listener is called passing the line number and the offending line.
	 * <p>
	 * Lines start at 1 with the header line.
	 *
	 * @param lsnr the listener for load errors
	 */
	public void setLoadListener(BiConsumer<Integer, String> lsnr) {
		this.listener = lsnr;
	}
}
