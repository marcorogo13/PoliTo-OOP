package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends ElementExt{
	
	
	/**
	 * Constructor
	 * @param name
	 */
	public Sink(String name) {
		super(name);
		//TODO: complete
	}
	
	@Override
	public void connect(Element elem){
		// no effect!
	}
	
	public void simulate(SimulationObserver observer){
		// TODO: to be implemented
		this.outputF = this.inputF;
		observer.notifyFlow(this.getClass().toString(),this.getName(), this.inputF, SimulationObserver.NO_FLOW);
	}
	
	public void simulate(SimulationObserverExt observer){
		// TODO: to be implemented
		if (this.maxFlow<this.inputF) {
			observer.notifyFlowError(this.getClass().toString(), this.getName(), this.inputF, this.maxFlow);	
		}
		this.outputF = this.inputF;
		observer.notifyFlow(this.getClass().toString(),this.getName(), this.inputF, SimulationObserver.NO_FLOW);
	}

	
	
}
