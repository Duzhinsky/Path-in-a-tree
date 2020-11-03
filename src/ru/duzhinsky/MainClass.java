package ru.duzhinsky;

import ru.duzhinsky.model.Model;
import ru.duzhinsky.model.tree.Tree;
import ru.duzhinsky.view.View;
import ru.duzhinsky.controller.Controller;

public class MainClass {

	public static void main(String[] args) {
		Model model = new Model();
		View view = new View(model);
		@SuppressWarnings("unused")
		Controller controller = new Controller(view, model);
		
		Tree t = new Tree();
		t.addNode();
		t.addNode();
		t.addNode();
		
		System.out.println(t.addVertex(0, 1));
		System.out.println(t.addVertex(1, 2));
		System.out.println(t.addVertex(2, 0));
	}

}
