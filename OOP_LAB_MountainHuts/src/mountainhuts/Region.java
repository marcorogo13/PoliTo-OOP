package mountainhuts;
import java.util.*;
import static java.util.stream.Collectors.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * Class {@code Region} represents the main facade
 * class for the mountains hut system.
 * 
 * It allows defining and retrieving information about
 * municipalities and mountain huts.
 *
 */





public class Region {
	
	class AltitudeRange {
		int low, high;
		
		AltitudeRange(int low, int high){
			this.high = high;
			this.low = low;
		}
		
		boolean contains(Integer i) {
			
			if (i > low && i <= high) {
				return true;
			}
			
			return false;
		}
		
		@Override
		public String toString() {
			if(low == +1 && high == -1)
				return "0-INF";
				
			return low + "-" + high;
		}
	}
	
	
	
	String name;

	AltitudeRange noAlt = new AltitudeRange(+1, -1);
	
	Map<AltitudeRange, List<MountainHut>> hutsByAlt;
	Map<String, Municipality> municipalities;
	Map<String, MountainHut> huts;
	
	/**
	 * Create a region with the given name.
	 * 
	 * @param name
	 *            the name of the region
	 */
	public Region(String name) {
		this.name = name;
		
		this.hutsByAlt = new HashMap<AltitudeRange, List<MountainHut>>();
		this.municipalities = new HashMap<>();
		this.huts= new HashMap<>();
		
		hutsByAlt.put(noAlt, new ArrayList<MountainHut>());
		
	}

	/**
	 * Return the name of the region.
	 * 
	 * @return the name of the region
	 */
	public String getName() {
		return name;
	}

	/**
	 * Create the ranges given their textual representation in the format
	 * "[minValue]-[maxValue]".
	 * 
	 * @param ranges
	 *            an array of textual ranges
	 */
	public void setAltitudeRanges(String... ranges) {
		
		for(int i = 0; i < ranges.length; i++) {
			String[] s = ranges[i].split("-");
			AltitudeRange tmp = new AltitudeRange(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
			List<MountainHut> tmpList = new ArrayList<MountainHut>(); 
			hutsByAlt.put(tmp, tmpList);
		}
	}

	/**
	 * Return the textual representation in the format "[minValue]-[maxValue]" of
	 * the range including the given altitude or return the default range "0-INF".
	 * 
	 * @param altitude
	 *            the geographical altitude
	 * @return a string representing the range
	 */
	public String getAltitudeRange(Integer altitude) {
		
		for(AltitudeRange a : hutsByAlt.keySet()) {
			if (a.contains(altitude)) {
				return a.toString();
			}
		}
		
		return "0-INF";
	}
	
	public String getAltitudeRangeS(MountainHut m) {
		
		
		if (m.getAltitude().isEmpty()) {
			
			for(AltitudeRange a : hutsByAlt.keySet()) {
				if (a.contains(m.getMunicipality().getAltitude())) {
					return a.toString();
				}
			}
		}else {
			for(AltitudeRange a : hutsByAlt.keySet()) {
				if (a.contains(m.getAltitude().get())) {
					return a.toString();
				}
			}
		}
		
		
		
		
		return "0-INF";
	}
	

	/**
	 * Create a new municipality if it is not already available or find it.
	 * Duplicates must be detected by comparing the municipality names.
	 * 
	 * @param name
	 *            the municipality name
	 * @param province
	 *            the municipality province
	 * @param altitude
	 *            the municipality altitude
	 * @return the municipality
	 */
	public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {
		
		Municipality tmp = new Municipality(name, province, altitude);
		
		if(municipalities.containsKey(name)) {
			return municipalities.get(name);
		}else {
			municipalities.put(name, tmp);
			return municipalities.get(name);
		}
		
	}

	/**
	 * Return all the municipalities available.
	 * 
	 * @return a collection of municipalities
	 */
	public Collection<Municipality> getMunicipalities() {
		return municipalities.values();
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 *
	 * @param name
	 *            the mountain hut name
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return the mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, String category, Integer bedsNumber,
			Municipality municipality) {
	
		MountainHut tmp = new MountainHut(name, category, bedsNumber, municipality);
		
		if(huts.containsKey(tmp.getName())) {
			return huts.get(name);
		}else {
			huts.put(name, tmp);
			hutsByAlt.get(noAlt).add(tmp);
			return huts.get(name);
		}
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 * 
	 * @param name
	 *            the mountain hut name
	 * @param altitude
	 *            the mountain hut altitude
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return a mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, Integer bedsNumber,
			Municipality municipality) {
		MountainHut tmp = new MountainHut(name, category, bedsNumber, municipality, altitude);
		
		if(huts.containsKey(tmp.getName())) {
			return huts.get(name);
		}else {
			huts.put(name, tmp);
			
			AltitudeRange altTmp = noAlt;

			for(AltitudeRange a : hutsByAlt.keySet()) {
				if (a.contains(altitude)) {
					altTmp = a;
				}
			}

			hutsByAlt.get(altTmp).add(tmp);
			
			return huts.get(name);
		}
	}

	/**
	 * Return all the mountain huts available.
	 * 
	 * @return a collection of mountain huts
	 */
	public Collection<MountainHut> getMountainHuts() {
		return huts.values();
	}

	/**
	 * Factory methods that creates a new region by loadimg its data from a file.
	 * 
	 * The file must be a CSV file and it must contain the following fields:
	 * <ul>
	 * <li>{@code "Province"},
	 * <li>{@code "Municipality"},
	 * <li>{@code "MunicipalityAltitude"},
	 * <li>{@code "Name"},
	 * <li>{@code "Altitude"},
	 * <li>{@code "Category"},
	 * <li>{@code "BedsNumber"}
	 * </ul>
	 * 
	 * The fields are separated by a semicolon (';'). The field {@code "Altitude"}
	 * may be empty.
	 * 
	 * @param name
	 *            the name of the region
	 * @param file
	 *            the path of the file
	 */
	public static Region fromFile(String name, String file) {
		Region r = new Region(name);
		List<String> lines = readData(file);
		
		for (int i = 0; i < lines.size(); i++) {
			if (i == 0 )
				continue;
			
			String ss[] = lines.get(i).split(";");
			String province = ss[0];
			String municipality = ss[1];
			String malt = ss[2];
			String nameh = ss[3];
			String alt = ss[4];
			String category = ss[5];
			String beds = ss[6];
			
			Municipality muni = r.createOrGetMunicipality(municipality, province, Integer.parseInt(malt));
			
			if (alt.equals("")) {
				r.createOrGetMountainHut(nameh, category, Integer.parseInt(beds), muni);
			}else {
				r.createOrGetMountainHut(nameh, Integer.parseInt(alt), category, Integer.parseInt(beds), muni);
			}
			
		}
		
		
		return r;
	}

	/**
	 * Internal class that can be used to read the lines of
	 * a text file into a list of strings.
	 * 
	 * When reading a CSV file remember that the first line
	 * contains the headers, while the real data is contained
	 * in the following lines.
	 * 
	 * @param file the file name
	 * @return a list containing the lines of the file
	 */
	@SuppressWarnings("unused")
	private static List<String> readData(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Count the number of municipalities with at least a mountain hut per each
	 * province.
	 * 
	 * @return a map with the province as key and the number of municipalities as
	 *         value
	 */
	public Map<String, Long> countMunicipalitiesPerProvince() {
		
		Map<String, Long> toReturn = municipalities.values().stream().collect(Collectors.groupingBy(e -> e.getProvince(), Collectors.counting()));
		
		for (String s : toReturn.keySet()) {
			   System.out.println(s);
			   System.out.println(toReturn.get(s));
			 }
		
		return toReturn;
	}

	/**
	 * Count the number of mountain huts per each municipality within each province.
	 * 
	 * @return a map with the province as key and, as value, a map with the
	 *         municipality as key and the number of mountain huts as value
	 */
	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
		
		Map<String, Map<String, Long>> toReturn = huts.values().stream().collect(Collectors.groupingBy(e -> e.getMunicipality().getProvince(), Collectors.groupingBy(x -> x.getMunicipality().getName(),Collectors.counting())));
		
		for (String s : toReturn.keySet()) {
			   System.out.println(s);
			   System.out.println(toReturn.get(s).size());
			 }
		
		return toReturn;
	}

	/**
	 * Count the number of mountain huts per altitude range. If the altitude of the
	 * mountain hut is not available, use the altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the number of mountain huts
	 *         as value
	 */
	public Map<String, Long> countMountainHutsPerAltitudeRange() {
		
		Map<String, Long> toReturn = new HashMap<String, Long>();
		
		toReturn = huts.values().stream().collect(Collectors.groupingBy(m -> getAltitudeRangeS(m), Collectors.counting()));
		
		
		
		final Map<String, Long> toReturnF = toReturn;
		
		toReturn.keySet().stream().forEach(x -> {System.out.println(x);
												System.out.println(toReturnF.get(x));});
		
		return toReturn;
	}

	/**
	 * Compute the total number of beds available in the mountain huts per each
	 * province.
	 * 
	 * @return a map with the province as key and the total number of beds as value
	 */
	public Map<String, Integer> totalBedsNumberPerProvince() {
		
		Map <String, Integer> toReturn = huts.values().stream().collect(Collectors.groupingBy(e -> e.getMunicipality().getProvince(), Collectors.summingInt(e->e.getBedsNumber()))); 
		
		return toReturn;
	}

	/**
	 * Compute the maximum number of beds available in a single mountain hut per
	 * altitude range. If the altitude of the mountain hut is not available, use the
	 * altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the maximum number of beds
	 *         as value
	 */
	public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		Map <String, Optional<Integer>> toReturn = new HashMap<String, Optional<Integer>>();
		
		toReturn = huts.values().stream().collect(Collectors.groupingBy(m -> getAltitudeRangeS(m), Collectors.mapping(MountainHut::getBedsNumber, Collectors.maxBy(Comparator.naturalOrder()))));
		
		final Map<String, Optional<Integer>> toReturnF = toReturn;
		toReturn.keySet().stream().forEach(x -> {System.out.println(x);
		System.out.println(toReturnF.get(x));});
		
		return toReturn;
	}

	/**
	 * Compute the municipality names per number of mountain huts in a municipality.
	 * The lists of municipality names must be in alphabetical order.
	 * 
	 * @return a map with the number of mountain huts in a municipality as key and a
	 *         list of municipality names as value
	 */
	public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
		
		Map<Long, List<String>> toReturn = new HashMap<Long, List<String>>();
		
		toReturn = huts.values().stream().collect(Collectors.groupingBy(x-> x.getMunicipality().getName(), () -> new TreeMap<String, Long>(Comparator.naturalOrder()), Collectors.counting())).entrySet()
					.stream().collect(Collectors.groupingBy(x -> x.getValue(), Collectors.mapping(x -> x.getKey(), Collectors.toList())));
		
		return toReturn;
	}

}
