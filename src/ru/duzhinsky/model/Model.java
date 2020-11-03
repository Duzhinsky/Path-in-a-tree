package ru.duzhinsky.model;

import java.awt.Point;
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
	
	public void addNode(int x, int y) {
		tree.addNode();
		nodesPositions.add(new Point(x, y));
		notifyUpdate("tree");
	}
	
	public void deleteNode(int index) {
		tree.deleteNode(index);
		nodesPositions.remove(index);
		notifyUpdate("tree");
	}
	
	public int getNodesCount() {
		return tree.tree.size();
	}
	
	public Point getNodePosition(int index) {
		return nodesPositions.get(index);
	}
	
	
	private Tree tree = new Tree();
	private ArrayList<Point> nodesPositions = new ArrayList<>();
	private SelectedMode mode = SelectedMode.addNode;
	
}
