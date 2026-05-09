package Basics;

// public = publico global
// private = usarse en esta clase

// class = clase actual
// static = pertenece a la clase y no al objeto -> tener un objeto no es necesario
// void = no devuelve nada (para metodos)


public class Class { // clase publica para todos
    public int addNumbers(int a, int b){
        int sum = a + b;
        return sum;
    } // metodo de clase publico que retorna suma de 2 numeros dados

    public static void main (String[] args) {
        int n1 = 25;
        int n2 = 15;

        Class obj = new Class();
        int result = obj.addNumbers(n1, n2);
        System.out.println(result);

    } // publica usada en la clase y no en el objeto, ademas no retorna nada
}
