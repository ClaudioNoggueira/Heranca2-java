package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		List<Product> products = new ArrayList<>();
		try {
			System.out.print("Entre com o número de produtos: ");
			int numberOfProducts = input.nextInt();
			
			int i = 1;
			while (i <= numberOfProducts) {
				System.out.println("\nDados do " + i + "º produto:");
				System.out.print("Comum, usado ou importado (c/u/i)? ");
				char answer = input.next().charAt(0);
				if (answer != 'c' && answer != 'u' && answer != 'i') {
					System.out.println("Você não escolheu uma das opções (c/u/i)");
				} else {
					System.out.print("Nome do produto: ");
					input.nextLine();
					String name = input.nextLine();
					System.out.print("Preço: R$ ");
					double price = input.nextDouble();
					switch (answer) {
					case 'c':
						products.add(new Product(name, price));
						break;
					case 'u':
						System.out.print("Data de fabricação (DD/MM/YYYY): ");
						Date manufactureDate = sdf.parse(input.next());
						products.add(new UsedProduct(name, price, manufactureDate));
						break;
					case 'i':
						System.out.print("Taxa alfandegária: R$ ");
						double customFee = input.nextDouble();
						products.add(new ImportedProduct(name, price, customFee));
						break;
					}
					i++;
				}
			}
			System.out.println("\nPRODUTOS:");
			for (Product prod : products) {
				System.out.println(prod.priceTag());
			}
		} catch (RuntimeException e) {
			System.out.println();
			System.out.println("Você inseriu um tipo de dado inadequado para a operação!");
			System.out.println("Reinicie o programa para tentar novamente.");
		} catch (ParseException e) {
			System.out.println();
			System.out.println("Não foi possível converter a data de fabricação do produto usado.");
			System.out.println("Certifique-se de seguir o padrão DD/MM/YYYY. Ex: 31/12/2021");
			System.out.println("Reinicie o programa para tentar novamente.");
		} finally {
			input.close();
		}
	}
}
