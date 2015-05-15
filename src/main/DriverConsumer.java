package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DriverConsumer {
	Calculadora calc = new Calculadora();
	FileManager in = new FileManager("feed.txt");
	FileManager out = new FileManager("feedback.txt");
	FileManager log = new FileManager("results.txt");
	Scanner input;

	public DriverConsumer(Calculadora calc) {
		super();
		this.calc = calc;
		try {
			if (!in.getFile().exists()) {
				in.getFile().createNewFile();
			}
			input = new Scanner(in.getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void menu() throws FileNotFoundException {
		int num1 = 0, num2 = 0;
		String result = "";
		char op = '.';
		while (true) {
			if(!in.isEmpty()){
				input = new Scanner(in.getFile());
				String[] tokens = input.nextLine().split(" ");
				num1 = Integer.parseInt(tokens[0]);
				op = tokens[1].charAt(0);
				num2 = Integer.parseInt(tokens[2]);
				input.close();
				if (num2 == -1)
					break;
				if (op != '+' && op != '-' && op != '*' && op != '/')
					continue;
				switch (op){
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
				System.out.println(result);
				out.addContent(result);
				result += new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss").format(System.currentTimeMillis());
				log.addContent(result);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				in.erase();
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		new DriverConsumer(new Calculadora()).menu();
	}
}
