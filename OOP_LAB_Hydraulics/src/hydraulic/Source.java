package hydraulic;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * The status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends ElementExt{
	
	double flow;
	
	
	public Source(String name) {
		super(name);
		//TODO: complete
	}

	/**
	 * defines the flow produced by the source
	 * 
	 * @param flow
	 */
	public void setFlow(double flow){
		//TODO: complete
		this.flow = flow;
	}
	
	
	public void simulate(SimulationObserver observer){
		// TODO: to be implemented
		this.downStreams[0].setInput(flow);
		observer.notifyFlow(this.getClass().toString(),this.getName(), SimulationObserver.NO_FLOW, this.flow);
		downStreams[0].simulate(observer);
	}
	
	public void simulate(SimulationObserverExt observer){
		this.downStreams[0].setInput(flow);
		observer.notifyFlow(this.getClass().toString(),this.getName(), SimulationObserver.NO_FLOW, this.flow);
		downStreams[0].simulate(observer);
	}
	
	@Override
	public void setMaxFlow(double maxFlow) {
		// TODO: to be implemented
		return;
	}

}
