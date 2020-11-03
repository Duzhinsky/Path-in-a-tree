package ru.duzhinsky.model;

import java.util.ArrayList;

import ru.duzhinsky.view.SelectedMode;
import ru.duzhinsky.view.View;

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
	
	private SelectedMode mode = SelectedMode.addNode;
	
}
