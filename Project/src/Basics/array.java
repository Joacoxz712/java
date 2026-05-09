package Basics;

import java.util.*;

public class array {

    static Scanner sc = new Scanner(System.in);
    public static void exe1() {
        System.out.println("Ejecutando ejercicio 1");
        System.out.print("Ingresa 10 numeros (separados por espacio): ");

        String[] stringArray = sc.nextLine().split(" ");
        int length = stringArray.length;

        if (length != 10) {
            System.out.println("Deben ser 10");
            sc.close();
            return;
        }

        int [] nms = new int[length];
        for (int i = 0; i < length; i++) {
            nms[i] = Integer.parseInt(stringArray[i]);
        }

        System.out.println("Array: " + Arrays.toString(nms));

        exe2();
    }

    public static void exe2() {
        System.out.println("Ejecutando ejercicio 2");

        Random Rand = new Random();

        int [] Numbers = new int[Rand.nextInt(11) + 1];
        int max = 0, min = 0;

        for (int i = 0; i < Numbers.length; i++) {
            Numbers[i] = Rand.nextInt(100);
            max = Math.max(Numbers[i], max);
            min = Math.min(Numbers[i], min);
        }
        System.out.println("El maximo de : " + Arrays.toString(Numbers) + " es " + max + " y el minimo es " + min);

        exe3();
    }

    public static void exe3() {
        System.out.println("Ejecutando ejercicio 3");

        System.out.print("Cuantos numeros? ");
        int N = sc.nextInt();
        sc.nextLine();

        double [] reales = new double [N];

        double suma = 0.0;

        for (int i = 0; i < N; i++) {
            System.out.print("Numero " + (i + 1) + ": ");
            reales[i] = sc.nextDouble();

            suma = suma + reales[i];
        }

        double promedio = (double) suma / N;

        System.out.println("Suma: " + suma);
        System.out.println("Promedio: " + promedio);
        exe4();
    }

    public static void exe4() {
        System.out.println("Ejecutando ejercicio 4");

        int [] values = new int[7];

        for (int i = 0; i < values.length; i++) {
            values[i] = new Random().nextInt(100);
        }

        int [] inversed = new int[values.length];

        for (int i = values.length - 1; i >= 0; i--) {
            inversed[values.length - 1 - i] = values[i];
        }

        System.out.println("Ordenado: " + Arrays.toString(values));
        System.out.println("Desordenado: " + Arrays.toString(inversed));

        exe5();
    }

    public static void exe5() {
        System.out.println("Ejecutando ejercicio 5");

        System.out.print("Ingresa una frase: ");
        sc.nextLine();
        String frase = sc.nextLine();

        char [] charsArray = new char[frase.length()];
        for (int i = 0; i < frase.length(); i++) {
            charsArray[i] = frase.charAt(i);
        }

        int vocales = frase.replaceAll("[AEIOUaeiouÁÉÍÓÚáéíóú]", "").length();

        System.out.println("Cantidad de vocales: " + vocales);

        // Otra forma:
        System.out.println(Arrays.toString(frase.toCharArray()));
        sc.close();
    }

    static void main() {
        exe1();
    }
}
