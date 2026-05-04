package Arrays;
import java.util.Scanner;

public class For {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[][] people = new String[1][3];
		
		// Data Input
		
		for (int i = 0; i < people.length; i++) {
			System.out.println("Registro de la persona nª " + (i + 1) + ":");
			
			System.out.print("Ingrese su nombre: ");
			people[i][0] = sc.nextLine();
			
			System.out.print("Ingrese su apellido: ");
			people[i][1] = sc.nextLine();
			
			System.out.print("Ingrese su cedula: ");
			people[i][1] = sc.nextLine();
			
			System.out.println("-----------------------------------");
		}
		
		// Data Output
		
		System.out.println("\nLISTADO DE PERSONAS REGISTRADAS: ");
		System.out.printf("%-15s %-15s %-15s\n", "Nombre", "Apellidos", "Cedula");
		
		for (int i = 0; i < people.length; i++) {
			String nombre = people[i][0];
			String apellido = people[i][1];
			String cedula = people[i][2];
			
			System.out.printf("%-15s %-15s %-15s\n", nombre, apellido, cedula);
		}
		
		sc.close();

	}

}
