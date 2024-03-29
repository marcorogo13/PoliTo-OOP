package mountainhuts;

/**
 * Represents a municipality
 *
 */
public class Municipality {
	String name; 
	String province;
	Integer altitude;
	
	
	
	
	
	
	
	public Municipality(String name, String province, Integer altitude) {
		this.name = name;
		this.province = province;
		this.altitude = altitude;
	}

	/**
	 * Name of the municipality.
	 * 
	 * Within a region the name of a municipality is unique
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Province of the municipality
	 * 
	 * @return province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Altitude of the municipality
	 * 
	 * @return altitude
	 */
	public Integer getAltitude() {
		return altitude;
	}

}
