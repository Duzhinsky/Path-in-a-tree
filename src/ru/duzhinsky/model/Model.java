package ru.duzhinsky.model;

import java.util.ArrayList;

import ru.duzhinsky.model.tree.Tree;
import ru.duzhinsky.view.SelectedMode;

public class Model {
	private ArrayList<Observer> listeners = new ArrayList<>();
	
	public Model() {
		
	}

	public void addListener(Observer listener) {
		listeners.add(listener);
	}
	
	private void notifyUpdate(String what) {
		for(Observer listener : listeners)
			listener.update(what);
	}
	
	public void setMode(SelectedMode mode) {
		this.mode = mode;
		notifyUpdate("mode");
	}
	
	public SelectedMode getMode() {
		return mode;
	}
	
	
	private Tree tree = new Tree();
	private SelectedMode mode = SelectedMode.addNode;
	
}
