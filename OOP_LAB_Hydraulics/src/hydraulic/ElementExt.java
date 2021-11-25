package hydraulic;

public abstract class ElementExt extends Element{

	double maxFlow;
	
	public ElementExt(String name) {
		super(name);
	}
	
	public ElementExt(String name, int numOutputs) {
		super(name, numOutputs);
	}

	public void setMaxFlow(double maxFlow) {
		// TODO: to be implemented
		this.maxFlow = maxFlow;
	}
	
	

}
