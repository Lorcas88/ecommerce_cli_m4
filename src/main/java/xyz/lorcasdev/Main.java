package xyz.lorcasdev;

import xyz.lorcasdev.model.Service;

public class Main {

	public static void main(String[] args) {
		Service service = new Service(1, "Desarrollo Web", null, 20000, false, 0);
		
		System.out.println(service.getName());
		System.out.println(service.toString());
	}

}
