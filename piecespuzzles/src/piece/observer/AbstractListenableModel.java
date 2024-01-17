package piece.observer;

import java.util.ArrayList;

public abstract class AbstractListenableModel implements ListenableModel {
	public ArrayList<ListeningModel> listeners;
	
	public AbstractListenableModel() {
		super();
		this.listeners = new ArrayList<ListeningModel>(); // BP: Pas besoin de répéter le type, utilisez l'opérateur diamant. Ex: `new ArrayList<>()`
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
