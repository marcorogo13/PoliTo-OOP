package hydraulic;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystemExt extends HSystem {
	
	/**
	 * Prints the layout of the system starting at each Source
	 */
	
	String layout;
	
	public String layout(){
		
		for (Element e : elements) {
			if ( e != null && e instanceof Source) {
				return e.layoutR(0);
			}

		}
		return null;
	}
	

	
	
	/**
	 * Deletes a previously added element with the given name from the system
	 */
	public boolean deleteElement(String name) {
		// TODO: to be implemented
		boolean found  = false;
		boolean found1 = false;
		int i;
		
		for (i = 0; i < elementCounter ; i++) {
			Element current = elements[i];
			if(!found && current.getName().equals(name)) {
				found = true;
				if (current instanceof Split || current instanceof Multisplit && current.downStreams.length > 1 ) {
					return false;
				}

				Element input = null;
				Element output = null;
				for (Element u : elements) {
					for (Element uu : u.downStreams) {
						if (uu.getName().compareTo(current.getName()) == 0) {
							input = uu;
							found1 = true;
							break;
						}
						if (found1) {
							break;
						}
					}
					
				}
				for(Element o : current.getOutputs()) {
					if(o!=null) {
						output = o;
						break;
					}
				}
				
				if(input!=null) {
					input.replaceWith(current, output);
				}
			
			}
			if(found)
				elements[i]=elements[i+1];
		}
		elements[i] = null;
		elementCounter--;
		return true;
	}

	/**
	 * starts the simulation of the system; if enableMaxFlowCheck is true,
	 * checks also the elements maximum flows against the input flow
	 */
	public void simulate(SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		// TODO: to be implemented
		if (enableMaxFlowCheck) {
			for (Element e : elements) {
				if ( e != null && e instanceof Source)
					e.simulate(observer);
			}
		}
	}
	
}
