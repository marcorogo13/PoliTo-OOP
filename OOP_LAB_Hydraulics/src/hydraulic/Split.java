package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends ElementExt {
	
	/**
	 * Constructor
	 * @param name
	 */
	public Split(String name) {
		super(name, 2);
		//TODO: complete
	}
	
	public Split(String name, int numOutput) {
		super(name, numOutput);
		//TODO: complete
	}
    
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
   

    /**
     * connect one of the outputs of this split to a
     * downstream component.
     * 
     * @param elem  the element to be connected downstream
     * @param noutput the output number to be used to connect the element
     */
	public void connect(Element elem, int noutput){
		//TODO: complete
		switch (noutput) {
		case 0:
			downStreams[0] = elem;
			System.out.println("connected: " + this.getName() + " with: " + elem.getName() + " in port: " + noutput);
			break;
		case 1:
			downStreams[1] = elem;
			System.out.println("connected: " + this.getName() + " with: " + elem.getName() + " in port: " + noutput);
			break;

		default:
			System.out.println("Wrong output number for T split!");
			break;
		}
	}
	
	public void simulate(SimulationObserver observer){
		// TODO: to be implemented
		outputF = inputF/2;
		this.downStreams[0].setInput(outputF);  
		this.downStreams[1].setInput(outputF);
		observer.notifyFlow(this.getClass().toString(),this.getName(), this.inputF, this.outputF, this.outputF);
		this.downStreams[0].simulate(observer);
		this.downStreams[1].simulate(observer);
	}
	
	public void simulate(SimulationObserverExt observer){
		// TODO: to be implemented
		if (this.maxFlow<this.inputF) {
			observer.notifyFlowError(this.getClass().toString(), this.getName(), this.inputF, this.maxFlow);	
		}
		outputF = inputF/2;
		this.downStreams[0].setInput(outputF);  
		this.downStreams[1].setInput(outputF);
		observer.notifyFlow(this.getClass().toString(),this.getName(), this.inputF, this.outputF, this.outputF);
		this.downStreams[0].simulate(observer);
		this.downStreams[1].simulate(observer);
	}
	
	
}
