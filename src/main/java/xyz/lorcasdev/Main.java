package xyz.lorcasdev;

import xyz.lorcasdev.model.Catalog;

public class Main {

	public static void main(String[] args) {
		Catalog catalog = new Catalog(1, "Desarrollo Web", null, 20000, false, 0);
		
		System.out.println(catalog.getName());
		System.out.println(catalog.toString());
	}

}
