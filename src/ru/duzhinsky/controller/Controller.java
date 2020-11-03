package ru.duzhinsky.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import ru.duzhinsky.model.Model;
import ru.duzhinsky.view.SelectedMode;
import ru.duzhinsky.view.View;

public class Controller {
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		
		view.setButtonActionListener(new ModeSelectButtonListener());
	}
	
	private class ModeSelectButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			SelectedMode mode = view.getModeFromButton( (JToggleButton)e.getSource() );
			if(mode != null)
				model.setMode(mode); 
		}
	}
	
	private final Model model;
	private final View view;
}
