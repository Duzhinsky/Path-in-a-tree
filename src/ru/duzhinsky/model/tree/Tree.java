package ru.duzhinsky.model.tree;

import java.util.ArrayList;

public class Tree {
	public ArrayList<Node> tree = new ArrayList<>();
	
	public Tree() {
		
	}
	
	public void addNode() {
		tree.add(new Node());
	}
	
	public boolean addVertex(int lhs, int rhs) {
		if(rhs > tree.size() || lhs > tree.size()) return false;
		tree.get(lhs).childs.add(rhs);
		tree.get(rhs).childs.add(lhs);
		if(hasCycle(lhs)) {
			tree.get(lhs).childs.remove( tree.get(lhs).childs.size()-1 );
			tree.get(rhs).childs.remove( tree.get(rhs).childs.size()-1 );
			return false;
		}
		return true;
	}
	
	public void deleteNode(int index) {
		tree.remove(index);
		for(Node n : tree) {
			for(int child : n.childs)
				n.childs.remove(child);
		}
	}
	
	private boolean hasCycle(int rootVertex) {
		int[] entriesCount = new int[ tree.size() ];
		return hasCycleDFS(rootVertex, entriesCount, -1);
	}
	
	private boolean hasCycleDFS(int vertex, int[] entriesCount, int parent) {
		entriesCount[vertex] = 1;
		for(int child : tree.get(vertex).childs) {
			if(child == parent) continue;
			if(entriesCount[child] == 0) {
				if(hasCycleDFS(child, entriesCount, vertex)) return true;
			} else if(entriesCount[child] == 1) {
				return true;
			}
		}
		entriesCount[vertex] = 2;
		return false;
	}
}