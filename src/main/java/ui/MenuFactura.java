package ui;

import java.util.Scanner;

import dao.FacturaDAO;

public class MenuFactura {

    static Scanner sc = new Scanner(System.in);
    static FacturaDAO facturaDAO = new FacturaDAO();

    public static void mostrar() {
        int opcion;

        do {
            System.out.println("\n--- FACTURAS ---");
            System.out.println("1. Insertar");
            System.out.println("2. Listar por reparación");
            System.out.println("3. Listar por cliente");
            System.out.println("4. Eliminar por ID");
            System.out.println("0. Volver");

            System.out.print("Selecciona una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1 -> {
                    System.out.print("ID Reparación: ");
                    int id_reparacion = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Total: ");
                    double total = sc.nextDouble();
                    sc.nextLine();


                    facturaDAO.insertarFactura(id_reparacion, total);
                }

                case 2 -> {
                    System.out.print("ID Reparación: ");
                    int id_reparacion = sc.nextInt();
                    sc.nextLine();
                    facturaDAO.listarFacturasPorReparacion(id_reparacion).forEach(f -> System.out.println("ID: " + f.getId() + " Total: " + f.getTotal()));;
                }

                case 3 -> {
                    System.out.print("ID Cliente: ");
                    int idCliente = sc.nextInt();
                    sc.nextLine();

                    facturaDAO.listarFacturasPorCliente(idCliente).forEach(f -> System.out.println("ID: " + f.getId() + " Total: " + f.getTotal()));
                }

                case 4 -> {
                    System.out.print("ID a eliminar: ");
                    int id = sc.nextInt();
                    facturaDAO.eliminarFactura(id);
                }

            }
        }
            while (opcion != 0) ;
    }
}

