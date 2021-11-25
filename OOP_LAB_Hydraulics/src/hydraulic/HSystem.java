package hydraulic;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystem {
	private final static int MAX_ELE = 100;
	Element[] elements = new Element [MAX_ELE];
	int elementCounter;
	Element start;
	
	/**
	 * Adds a new element to the system
	 * @param elem
	 */
	public void addElement(Element elem){
		// TODO: to be implemented
		elements[elementCounter++] = elem;
	}
	
	/**
	 * returns the element added so far to the system.
	 * If no element is present in the system an empty array (length==0) is returned.
	 * 
	 * @return an array of the elements added to the hydraulic system
	 */
	public Element[] getElements(){
		// TODO: to be implemented
		Element[] toReturn = new Element[elementCounter];
		for (int i = 0; i<elementCounter ; i++) {
			toReturn[i] = elements[i];
		}
		return toReturn;
	}
	
	/**
	 * starts the simulation of the system
	 */
	public void simulate(SimulationObserver observer){
		// TODO: to be implemented
		for (Element e : elements) {
			if ( e != null && e instanceof Source)
				e.simulate(observer);
		}
	}

}
