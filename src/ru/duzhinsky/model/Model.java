package ru.duzhinsky.model;

import java.awt.Point;
import java.util.ArrayList;

import ru.duzhinsky.model.tree.Tree;

public class Model {
	private ArrayList<Observer> listeners = new ArrayList<>();
	
	public Model() {
		
	}

	public void addListener(Observer listener) {
		listeners.add(listener);
	}
	
	private void notifyUpdate(String what, Object... params) {
		for(Observer listener : listeners)
			listener.update(what, params);
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
		isNodeSelected.add(false);
		notifyUpdate("tree");
	}
	
	public void deleteNode(int index) {
		tree.deleteNode(index);
		nodesPositions.remove(index);
		isNodeSelected.remove(index);
		notifyUpdate("tree");
	}
	
	public int getNodesCount() {
		return tree.tree.size();
	}
	
	public void setNodeSelected(int index, boolean selected) {
		if(selected == isNodeSelected.get(index)) return;
		isNodeSelected.set(index, selected);
		notifyUpdate("node_selection", index);
	}
	
	public boolean isNodeSelected(int index) {
		return isNodeSelected.get(index);
	}
	
	public void clearNodesSelection() {
		for(int i = 0; i < isNodeSelected.size(); ++i)
			if(isNodeSelected.get(i)) 
				setNodeSelected(i, false);
	}
	
	public Point getNodePosition(int index) {
		return nodesPositions.get(index);
	}
	
	public boolean addVertex(int index1, int index2) {
		boolean res = tree.addVertex(index1, index2);
		if(res) notifyUpdate("tree");
		return res;
	}
	
	public ArrayList<int[]> getVertexes() {
		ArrayList<int[]> list = new ArrayList<>();
		for(int i = 0; i < tree.tree.size(); ++i)
			for(int child : tree.tree.get(i).childs)
				list.add(new int[] {i, child});
		return list;
	}
	
	
	private Tree tree = new Tree();
	private ArrayList<Point> nodesPositions = new ArrayList<>();
	private ArrayList<Boolean> isNodeSelected = new ArrayList<>(); 
	private SelectedMode mode = SelectedMode.addNode;
	
}
