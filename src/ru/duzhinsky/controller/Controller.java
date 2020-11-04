package ru.duzhinsky.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JToggleButton;

import ru.duzhinsky.model.Model;
import ru.duzhinsky.model.SelectedMode;
import ru.duzhinsky.view.View;
import ru.duzhinsky.view.View.TreeNode;

public class Controller {
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		
		view.setButtonActionListener(new ModeSelectButtonListener());
		view.setTreePanelListener(new TreePanelClickListener());
		view.setNodeListener(new NodeClickListener());
	}
	
	private class ModeSelectButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			SelectedMode mode = view.getModeFromButton( (JToggleButton)e.getSource() );
			if(mode != null)
				model.setMode(mode); 
			if(mode == SelectedMode.makeVertex || mode == SelectedMode.getPath)
				requireNodesSelection(2);
		}
	}
	
	private class TreePanelClickListener implements MouseListener {
		@Override
		public void mousePressed(MouseEvent e) {
			if(model.getMode() == SelectedMode.addNode) 
				model.addNode(e.getX(), e.getY());
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
	
	private class NodeClickListener implements MouseListener {
		@Override
		public void mousePressed(MouseEvent e) {
			int index = ((TreeNode)e.getSource()).getIndex();
			for(int i = nodeClickBufferSize-1; i > 0; --i)
				nodeClickBuffer[i] = nodeClickBuffer[i-1];
			nodeClickBuffer[0] = index;
			
			if(model.getMode() == SelectedMode.deleteNode) 
				model.deleteNode(index);
			
			if(nodesSelectRequired > 0) {
				model.clearNodesSelection();
				for(int i = 0; i < nodesSelectRequired; ++i)
					if(nodeClickBuffer[i] != -1)
						model.setNodeSelected(nodeClickBuffer[i], true);
				--nodesSelectRequired;
			}
			
			if(nodesSelectRequired == 0 && model.getMode() == SelectedMode.makeVertex) {
				if(nodeClickBuffer[1] != nodeClickBuffer[0])
					model.addVertex(nodeClickBuffer[1], nodeClickBuffer[0]);
				requireNodesSelection(2);
			}
			
			if(nodesSelectRequired == 0 && model.getMode() == SelectedMode.getPath) {
				if(nodeClickBuffer[1] != nodeClickBuffer[0])
					model.getPath(nodeClickBuffer[1], nodeClickBuffer[0]);
				requireNodesSelection(2);
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
	
	private void requireNodesSelection(int count) {
		for(int i = 0; i < nodeClickBufferSize; ++i)
			nodeClickBuffer[i] = -1;
		nodesSelectRequired = count;
	}

	
	private final int nodeClickBufferSize = 2;
	private int[] nodeClickBuffer = new int[nodeClickBufferSize];
	private int nodesSelectRequired = 0;
	
	private final Model model;
	private final View view;
}
