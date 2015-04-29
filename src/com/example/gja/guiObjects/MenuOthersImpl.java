package com.example.gja.guiObjects;

public class MenuOthersImpl {
	
	public MenuOthersImpl(com.vaadin.ui.MenuBar /*menuOthers*/barmenu) {
		
		// A top-level menu item that opens a submenu
		com.vaadin.ui.MenuBar.MenuItem drinks = barmenu.addItem("Beverages", null, null);

		// Submenu item with a sub-submenu
		com.vaadin.ui.MenuBar.MenuItem hots = drinks.addItem("Hot", null, null);
		hots.addItem("Tea", null, null);
		hots.addItem("Coffee", null, null);
/*
		// Another submenu item with a sub-submenu
		com.vaadin.ui.MenuBar.MenuItem colds = drinks.addItem("Cold", null, null);
		colds.addItem("Milk",      null, null);
		colds.addItem("Weissbier", null, null);

		// Another top-level item
		com.vaadin.ui.MenuBar.MenuItem snacks = barmenu.addItem("Snacks", null, null);
		snacks.addItem("Weisswurst", null, null);
		snacks.addItem("Bratwurst",  null, null);
		snacks.addItem("Currywurst", null, null);
		        
		// Yet another top-level item
		com.vaadin.ui.MenuBar.MenuItem servs = barmenu.addItem("Services", null, null);
		servs.addItem("Car Service", null, null);*/
	}
}
