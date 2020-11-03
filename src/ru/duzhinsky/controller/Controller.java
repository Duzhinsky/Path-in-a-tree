package ru.duzhinsky.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JToggleButton;

import ru.duzhinsky.model.Model;
import ru.duzhinsky.view.SelectedMode;
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
		}
	}
	
	private class TreePanelClickListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(model.getMode() == SelectedMode.addNode) 
					model.addNode(e.getX(), e.getY());
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
	
	private class NodeClickListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(model.getMode() == SelectedMode.deleteNode) 
				model.deleteNode( ((TreeNode)e.getSource()).getIndex() );
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}

	
	private final Model model;
	private final View view;
}
