package ru.duzhinsky;

import ru.duzhinsky.model.Model;
import ru.duzhinsky.view.View;
import ru.duzhinsky.controller.Controller;

public class MainClass {

	public static void main(String[] args) {
		Controller controller = new Controller();
		View view = new View(controller);
		Model model = new Model(view);
		controller.setModel(model);
	}

}
