package hydraulic;

import java.util.*;

/**
 * Represents a multisplit element, an extension of the Split that allows many outputs
 * 
 * During the simulation each downstream element will
 * receive a stream that is determined by the proportions.
 */

public class Multisplit extends Split {

	
	double[] proportions;
	/**
	 * Constructor
	 * @param name
	 * @param numOutput
	 */
	public Multisplit(String name, int numOutput) {
		super(name, numOutput); //you can edit also this line
		this.numOutput = numOutput;
		// TODO: to be implemented
	}
    
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
    public Element[] getOutputs(){
	    return downStreams;
    }

    /**
     * connect one of the outputs of this split to a
     * downstream component.
     * 
     * @param elem  the element to be connected downstream
     * @param noutput the output number to be used to connect the element
     */
	public void connect(Element elem, int noutput){
		//TODO: complete
		int i = 0;
		for (Element e : this.downStreams) {
			if (i == noutput) {
				downStreams[i] = elem;
				System.out.println("connected: " + this.getName() + " with: " + elem.getName() + " in port: " + noutput);
				break;
			}
			i++;
		}
	}
	
	/**
	 * Define the proportion of the output flows w.r.t. the input flow.
	 * 
	 * The sum of the proportions should be 1.0 and 
	 * the number of proportions should be equals to the number of outputs.
	 * Otherwise a check would detect an error.
	 * 
	 * @param proportions the proportions of flow for each output
	 */
	public void setProportions(double... proportions) {
		// TODO: to be implemented
		if(proportions.length != this.numOutput ) {
			System.err.println("Wrong number of proportions");
			return;
		}else {
			double sum = 0;
			int counter = 0; 
			for (double d : proportions) {
				sum = sum + d;
				counter++;
			}
			if (sum != 1) {
				System.err.println("Wrong sum of proportions");
				return;
			}else {
				int i = 0;
				this.proportions = new double[counter];
				for (double d : proportions) {
					this.proportions[i] = d;;	
					i++;
				}
					
			}
		}
	}
	
	
	public void simulate(SimulationObserver observer){
		// TODO: to be implemented
		
		double[] outputflows = new double[numOutput];
		
		for (int i = 0; i< numOutput; i++) {
			outputflows[i] = inputF*proportions[i];
		}
		observer.notifyFlow(this.getClass().toString(),this.getName(), this.inputF, outputflows);
		int i = 0;
		for (Element e : downStreams) {
			if (e != null) {
				this.downStreams[i].setInput(inputF*proportions[i]);
				this.downStreams[i].simulate(observer);
				outputflows[i] = inputF*proportions[i];
				i++;
			}
			
		}
	}

	
	public void simulate(SimulationObserverExt observer){
		// TODO: to be implemented
		if (this.maxFlow<this.inputF) {
			observer.notifyFlowError(this.getClass().toString(), this.getName(), this.inputF, this.maxFlow);	
		}
		
		double[] outputflows = new double[numOutput];
		
		for (int i = 0; i< numOutput; i++) {
			outputflows[i] = inputF*proportions[i];
		}
		observer.notifyFlow(this.getClass().toString(),this.getName(), this.inputF, outputflows);
		int i = 0;
		for (Element e : downStreams) {
			if (e != null) {
				this.downStreams[i].setInput(inputF*proportions[i]);
				this.downStreams[i].simulate(observer);
				outputflows[i] = inputF*proportions[i];
				i++;
			}
			
		}
	}
	
}
