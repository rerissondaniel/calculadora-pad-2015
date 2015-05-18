package main;

public class DriverConsumer implements Runnable {
	Calculadora calc = new Calculadora();
	Resource resource;

	public DriverConsumer(Resource resource) {
		super();
		this.resource = resource;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void operate(String data) {
		int num1 = 0, num2 = 0;
		String result = "";
		char op = '.';
		String[] tokens = data.split(" ");
		num1 = Integer.parseInt(tokens[0]);
		op = tokens[1].charAt(0);
		num2 = Integer.parseInt(tokens[2]);
		switch (op) {
		case '+': {
			result = String.format("%d + %d = %d ", num1, num2,
					calc.soma(num1, num2));
			break;
		}
		case '-': {
			result = String.format("%d - %d = %d ", num1, num2,
					calc.subtracao(num1, num2));
			break;
		}
		case '*': {
			result = String.format("%d * %d = %d ", num1, num2,
					calc.multiplicacao(num1, num2));
			break;
		}
		case '/': {
			if (num2 == 0) {
				result = String.format("%d / %d = %s ", num1, num2,
						"Indefinido");
			} else
				result = String.format("%d / %d = %.0f ", num1, num2,
						calc.divisao(num1, num2));
			break;
		}
		}
		result += tokens[3];
		System.out.println(result);

	}

	public static void main(String[] args) {
		Resource res = new Resource();
		Thread t1 = new Thread(new DriverConsumer(res)), 
				t2;
		Producer[] producers = new Producer[5];
		for(int i = 0; i < 5; i++){
			producers[i] = new Producer(res, i + 1);
			t2 = new Thread(producers[i]);
			t2.start();
		}
		t1.start();
	}

	@Override
	public void run() {
		while (true) {
			operate(resource.getResource());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}