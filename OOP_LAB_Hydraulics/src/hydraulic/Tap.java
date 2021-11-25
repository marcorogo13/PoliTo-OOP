package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends ElementExt {
	boolean open;
	
	public Tap(String name) {
		super(name);
		//TODO: complete
	}
	
	/**
	 * Defines whether the tap is open or closed.
	 * 
	 * @param open  opening level
	 */
	public void setOpen(boolean open){
		//TODO: complete
		this.open = open;
	}
	
	public void simulate(SimulationObserver observer){
		// TODO: to be implemented
		if(open) {
			this.outputF = this.inputF;
			this.downStreams[0].setInput(this.outputF);
			observer.notifyFlow(this.getClass().toString(),this.getName(), this.inputF, this.outputF);
			this.downStreams[0].simulate(observer);
		}else {
			this.outputF = 0;
			observer.notifyFlow(this.getClass().toString(),this.getName(), this.inputF, 0);
			this.downStreams[0].simulate(observer);
		
		}
	}
	
	public void simulate(SimulationObserverExt observer){
		// TODO: to be implemented
		if (this.maxFlow<this.inputF) {
			observer.notifyFlowError(this.getClass().toString(), this.getName(), this.inputF, this.maxFlow);
			
		}
		if(open) {
			this.outputF = this.inputF;
			this.downStreams[0].setInput(this.outputF);
			observer.notifyFlow(this.getClass().toString(),this.getName(), this.inputF, this.outputF);
			this.downStreams[0].simulate(observer);
		}else {
			this.outputF = 0;
			observer.notifyFlow(this.getClass().toString(),this.getName(), this.inputF, 0);
			this.downStreams[0].simulate(observer);
		
		}
	}
}
