package ru.duzhinsky.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ru.duzhinsky.controller.Controller;
import ru.duzhinsky.Constants;

public class View  extends JFrame {
	
	public View(Controller controller) {
		super();
		this.controller = controller;
		
		
		/*
		 *  Frame initialization	
		 */
		this.setLocationRelativeTo(null); // Place at the center of a screen
		this.setTitle(Constants.window_title);
		this.setSize(Constants.window_width, Constants.window_height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		
		/*
		 * Main panels 
		 */
		treePanel.setBounds(0, 0, Constants.window_width, Constants.tree_panel_height);
		treePanel.setBackground(Color.white);
		treePanel.setLayout(null);
		getContentPane().add(treePanel);
		
		buttonsPanel.setBounds(0, Constants.tree_panel_height, Constants.window_width, Constants.window_height - Constants.tree_panel_height);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		getContentPane().add(buttonsPanel);
		
		/*
		 * Buttons
		 */
		buttonsPanel.add(addNodeButton);
		buttonsPanel.add(makeVertexButton);
		buttonsPanel.add(deleteButton);
		buttonsPanel.add(clearButton);
		buttonsPanel.add(getPathButton);
		selectedButton.setSelected(true);
		
		this.setVisible(true);
	}
	
	public void update(Object what) {
		System.out.println("auf");
 	}
	
	
	private JToggleButton getButtonForMode(SelectedMode mode) {
		switch(mode) {
			case addNode: return addNodeButton;
			case makeVertex: return makeVertexButton;
			case deleteNode: return deleteButton;
			default: return null;
		}
	}

	private JPanel treePanel    = new JPanel();
	private JPanel buttonsPanel = new JPanel();
	
	private SelectedMode mode = SelectedMode.addNode;
	
	private JToggleButton makeVertexButton = new JToggleButton("Make Vertex");
	private JToggleButton addNodeButton    = new JToggleButton("Add Node");
	private JToggleButton deleteButton     = new JToggleButton("Delete");
	private JButton       getPathButton    = new JButton("Get Path");
	private JButton       clearButton      = new JButton("Clear");
	
	private JToggleButton selectedButton = getButtonForMode(mode);
	
	private Controller controller;
}
