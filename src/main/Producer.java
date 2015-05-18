package main;

import java.util.Random;

public class Producer implements Runnable {

	Resource resource;
	int id;

	public Producer(Resource resource, int id) {
		super();
		this.resource = resource;
		this.id = id;
	}

	public String produce() {
		Random generator = new Random();

		int num1, num2, op;
		do
			num1 = generator.nextInt(100);
		while (num1 == -1);
		do
			num2 = generator.nextInt(100);
		while (num2 == -1);
		op = generator.nextInt(4);
		switch (op) {
		case 0:
			return (String.format("%d %c %d ", num1, '+', num2)) + id;
		case 1:
			return (String.format("%d %c %d ", num1, '-', num2)) + id;
		case 2:
			return (String.format("%d %c %d ", num1, '*', num2)) + id;
		case 3:
			return (String.format("%d %c %d ", num1, '/', num2)) + id;
		}
		return "";
	}

	@Override
	public void run() {
		while (true) {
			resource.setResource(this.produce());
		}
	}
}
