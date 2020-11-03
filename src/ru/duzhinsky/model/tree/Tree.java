package ru.duzhinsky.model.tree;

import java.util.ArrayList;
import java.util.Stack;

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
		if(hasCycle()) {
			tree.remove(tree.size()-1);
			return false;
		}
		return true;
	}
	
	private boolean hasCycle() {
		int[] entriesCount = new int[ tree.size() ];
		Stack<Integer> dfs_stack = new Stack<>();
		dfs_stack.push(0);
		while(!dfs_stack.empty()) {
			int node = dfs_stack.pop();
			++entriesCount[node];
			for(int child_node : tree.get(node).childs) {
				if(entriesCount[child_node] != 0) return true;
				else {
					dfs_stack.push(child_node);
				}
			}
			++entriesCount[node];
		}
		return false;
	}
}