package hydraulic;
import java.util.*;
/**
 * Represents the generic abstract element of an hydraulics system.
 * It is the base class for all elements.
 *
 * Any element can be connect to a downstream element
 * using the method {@link #connect(Element) connect()}.
 */
public abstract class Element {
	
	String name;
	Element[] downStreams;
	double inputF = 0, outputF = 0;
	int numOutput;
	/**
	 * Constructor
	 * @param name the name of the element
	 */
	public Element(String name){
		// TODO: to be implemented
		this.name = name;
		if (this instanceof Sink) {
			this.numOutput = 0;
		}else {
			this.numOutput = 1;
		}
		
		downStreams = new Element[1];

	}
	
	public Element(String name, int outs){
		// TODO: to be implemented
		this.name = name;
		this.numOutput = outs;
		downStreams = new Element[outs];
		for (Element e : downStreams) {
			e = null;
		}
		
	}


	/**
	 * getter method
	 * @return the name of the element
	 */
	public String getName(){
		// TODO: to be implemented
		return this.name;
	}
	
	/**
	 * Connects this element to a given element.
	 * The given element will be connected downstream of this element
	 * @param elem the element that will be placed downstream
	 */
	public void connect(Element elem){
		// TODO: to be implemented
		if ( !(this instanceof Sink)) {
			this.downStreams[0] = elem;
			System.out.println("connected: " + this.getName() + " with: " + elem.getName());
		}
	}
	
	public void connect(Element elem, int noutput){
		// TODO: to be implemented
		if ( !(this instanceof Sink)) {
			this.downStreams[noutput] = elem;
			System.out.println("connected: " + this.getName() + " with: " + elem.getName());
		}
	}
	
	/**
	 * Retrieves the element connected downstream of this
	 * @return downstream element
	 */
	
	public Element getOutput(){
		return downStreams[0];
	}

	
	
	 public Element[] getOutputs(){
     return downStreams;
 }

	
	public double getInput() {
		return inputF;
	}

	public void setInput(double input) {
		this.inputF = input;
	}
	
	public double getOutpuF() {
		return outputF;
	}
	
	public void setOutput(double output) {
		this.outputF = output;
	}

	public void simulate(SimulationObserver observer){
		
	}
	
	public void simulate(SimulationObserverExt observer){
	
	}
	
	
	boolean replaceWith(Element e, Element r) {
		for(int i=0; i<downStreams.length; ++i) {
			if(downStreams[i] == e) {
				downStreams[i] = r;
				return true;
			}
		}
		return false;
	}

	
	
	public String layoutR(int space) {
		
		String toReturn = "";
		space = space + this.getClass().getSimpleName().length() + 2 + this.getName().length();		
		int realOutN = 0;
		int l = 0;
		for (Element j : this.downStreams) {
			if (j != null) {
				realOutN++;
			}
		}
		
		if (realOutN == 0) {
			toReturn = toReturn + "[" + this.getName() + "]" + this.getClass().getSimpleName();
			
			return toReturn;
		}
		
		if (realOutN == 1) {
			
			for ( Element k : downStreams) {
				if ( k != null) {
					toReturn = toReturn + "[" + this.getName() + "]" + this.getClass().getSimpleName() + " -> " + k.layoutR(space + 4);
					space = space - 4;
				}
			}
		}
		
		if (realOutN > 1) {
			
			toReturn = toReturn + "[" + this.getName() + "]" + this.getClass().getSimpleName();
			
			for ( Element k : downStreams) {
				
				if (k == null) {
					toReturn = toReturn + " * "; 
					toReturn = toReturn + "\n";
				}else {
					toReturn = toReturn + " +-> " + k.layoutR(space+5);
					toReturn = toReturn + "\n";
				}
			
				for (int i = 0; i < space; i++) {
					toReturn = toReturn + " " ;
					
				}
					
				if (l < realOutN-1)
						toReturn = toReturn + " |\n" ;
						
				l++;
						
				for (int z = 0; z < space; z++) {
					toReturn = toReturn + " " ;

				}
				
			}
			
			space = space - 5;
		}
		
	space = space - this.getClass().getSimpleName().length() + 2 + this.getName().length();	
	return toReturn;	
	
	}

		
}
