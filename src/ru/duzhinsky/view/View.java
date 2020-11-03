package ru.duzhinsky.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.OverlayLayout;

import ru.duzhinsky.model.Model;
import ru.duzhinsky.model.Observer;
import ru.duzhinsky.Constants;

public class View  extends JFrame implements Observer {
	
	public View(Model model) {
		super();
		this.model = model;
		model.addListener(this);
		
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
		selectedButton = getButtonFromMode(model.getMode());
		selectedButton.setSelected(true);
		
		this.setVisible(true);
	}
	
	public void update(String what) {
		switch(what) {
			case "mode": {
				setSelectedToggleButton(model.getMode());
				break;
			}
			case "tree": {
				for(Component comp : treePanel.getComponents()) {
					treePanel.remove(comp);
				}
				nodes.clear();
				for(int i = 0; i < model.getNodesCount(); ++i) {
					nodes.add(new TreeNode(model.getNodePosition(i), i));
					treePanel.add(nodes.get(i));
					nodes.get(i).addMouseListener(nodeClickListener);
				}
				treePanel.updateUI();
			}
		}
 	}
	
	public void setButtonActionListener(ActionListener listener) {
		addNodeButton.addActionListener(listener);
		makeVertexButton.addActionListener(listener);
		deleteButton.addActionListener(listener);
	}
	
	public void setNodeListener(MouseListener listener) {
		this.nodeClickListener = listener;
	}
	
	public void setTreePanelListener(MouseListener listener) {
		treePanel.addMouseListener(listener);
	}
	
	public void setSelectedToggleButton(SelectedMode mode) {
		selectedButton.setSelected(false);
		selectedButton = getButtonFromMode(mode);
		selectedButton.setSelected(true);
	}
	
	
	public SelectedMode getModeFromButton(JToggleButton button) {
		if(button == addNodeButton) return SelectedMode.addNode;
		else if(button == makeVertexButton) return SelectedMode.makeVertex;
		else if(button == deleteButton) return SelectedMode.deleteNode;
		else return null;
	}
	
	private JToggleButton getButtonFromMode(SelectedMode mode) {
		switch(mode) {
			case addNode: return addNodeButton;
			case makeVertex: return makeVertexButton;
			case deleteNode: return deleteButton;
			default: return null;
		}
	}
	
	public class TreeNode extends JPanel {
		private final int index;
		
		public TreeNode(Point pos, int index) {
			setBounds(pos.x - Constants.node_size/2, pos.y - Constants.node_size/2,
					Constants.node_size, Constants.node_size);
			this.index = index;
        }
		
		public int getIndex() { return index; }

        @Override
        public void paintComponent(Graphics g) {
        	g.setColor(Constants.nodeFillColor);
        	g.fillOval(0, 0, Constants.node_size-1, Constants.node_size-1);
        	g.setColor(Constants.nodeBorderColor);
        	g.drawOval(0, 0, Constants.node_size-1, Constants.node_size-1);
        }
	}
	
	private JPanel treePanel    = new JPanel();
	private JPanel buttonsPanel = new JPanel();
	private ArrayList<TreeNode> nodes = new ArrayList<>();
	private MouseListener nodeClickListener;
	
	private JToggleButton makeVertexButton = new JToggleButton("Make Vertex");
	private JToggleButton addNodeButton    = new JToggleButton("Add Node");
	private JToggleButton deleteButton     = new JToggleButton("Delete");
	private JButton       getPathButton    = new JButton("Get Path");
	private JButton       clearButton      = new JButton("Clear");
	
	private JToggleButton selectedButton;
	
	private final Model model;
}
