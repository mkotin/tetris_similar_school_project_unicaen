package observer;

import java.util.ArrayList;

public abstract class AbstractListenableModel implements ListenableModel {
	private ArrayList<ListeningModel> listeners;
	
	public AbstractListenableModel() {
		super();
		this.listeners = new ArrayList<ListeningModel>();
	}
	@Override
	public void addListening(ListeningModel  e) {
		this.listeners.add(e);
		
	}	
	@Override
	public void removeListening(ListeningModel  e) {
		this.listeners.remove(e);
		
	}
	protected void fireChangement() {
		for(ListeningModel  e : listeners) {
			e.modelUpdtae(this);
		}
	}

}
