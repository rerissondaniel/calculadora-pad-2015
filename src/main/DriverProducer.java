package main;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class DriverProducer {

	public void produce(int numProductions) {
		FileManager out = new FileManager("feed");
		FileManager in = new FileManager("feedback");
		Random generator = new Random();

		int num1, num2, op;
		while(true){
			if(out.isEmpty()){
				for (int i = 0; i < numProductions; i++) {
					do
						num1 = generator.nextInt();
					while (num1 == -1);
					do
						num2 = generator.nextInt();
					while (num2 == -1);
					op = generator.nextInt(4);
					switch (op) {
						case 0:
							out.addContent(String.format("%d %c %d", num1, '+', num2));
							break;
						case 1:
							out.addContent(String.format("%d %c %d", num1, '-', num2));
							break;
						case 2:
							out.addContent(String.format("%d %c %d", num1, '*', num2));
							break;
						case 3:
							out.addContent(String.format("%d %c %d", num1, '/', num2));
							break;
					}
				}
			}else if(!in.isEmpty()){
				Scanner input = null;
				try {
					if(!in.getFile().exists()){
						in.getFile().createNewFile();
					}
					input = new Scanner(in.getFile());
				} catch (IOException e) {
					e.printStackTrace();
				}
				while(input.hasNext()){
					System.out.println(input.nextLine());
				}
				in.erase();
			}
		}
	}

	public static void main(String[] args) {
		new DriverProducer().produce(10);
	}
}
