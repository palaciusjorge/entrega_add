import ui.*;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n===== TALLER MECÁNICO =====");
            System.out.println("1. Clientes");
            System.out.println("2. Coches");
            System.out.println("3. Mecánicos");
            System.out.println("4. Reparaciones");
            System.out.println("5. Facturas");
            System.out.println("6. Piezas");
            System.out.println("0. Salir");

            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {

                case 1 -> MenuClientes.mostrar();

                case 2 -> MenuCoche.mostrar();

                case 3 -> MenuMecanico.mostrar();

                case 4 -> MenuReparacion.mostrar();

                case 5 -> MenuFactura.mostrar();

                case 6 -> MenuPieza.mostrar();

                case 0 -> System.out.println("Saliendo del sistema...");

                default -> System.out.println("Opción no válida");
            }

        } while (opcion != 0);

        sc.close();
    }
}
