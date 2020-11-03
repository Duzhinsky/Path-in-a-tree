package ru.duzhinsky;

import ru.duzhinsky.model.Model;
import ru.duzhinsky.view.View;
import ru.duzhinsky.controller.Controller;

public class MainClass {

	public static void main(String[] args) {
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(view, model);
	}

}
