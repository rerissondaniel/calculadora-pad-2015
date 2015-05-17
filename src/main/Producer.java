package main;

import java.util.Random;

public class Producer implements Runnable {

	Resource resource;
	

	public Producer(Resource resource) {
		super();
		this.resource = resource;
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
			return (String.format("%d %c %d", num1, '+', num2));
		case 1:
			return (String.format("%d %c %d", num1, '-', num2));
		case 2:
			return (String.format("%d %c %d", num1, '*', num2));
		case 3:
			return (String.format("%d %c %d", num1, '/', num2));
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
