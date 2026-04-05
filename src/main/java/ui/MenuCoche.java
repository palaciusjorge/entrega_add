package ui;

import dao.CocheDAO;
import model.Coche;

import java.util.List;
import java.util.Scanner;

public class MenuCoche {

    static Scanner sc = new Scanner(System.in);
    static CocheDAO cocheDAO = new CocheDAO();

    public static void mostrar() {

        int opcion;

        do {
            System.out.println("\n--- COCHES ---");
            System.out.println("1. Insertar coche");
            System.out.println("2. Listar coches por cliente");
            System.out.println("3. Eliminar coche");
            System.out.println("0. Volver");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                // INSERTAR
                case 1 -> {
                    System.out.print("ID cliente: ");
                    int idCliente = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Matrícula: ");
                    String matricula = sc.nextLine();

                    System.out.print("Marca: ");
                    String marca = sc.nextLine();

                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();

                    System.out.print("Año: ");
                    int anho = sc.nextInt();
                    sc.nextLine();

                    Coche c = new Coche(0, idCliente, matricula, marca, modelo, anho);
                    cocheDAO.insertarCoche(c);
                }

                // LISTAR
                case 2 -> {
                    System.out.print("ID cliente: ");
                    int idCliente = sc.nextInt();

                    List<Coche> lista = cocheDAO.listarCochesPorCliente(idCliente);

                    lista.forEach(c ->
                            System.out.println("ID: " + c.getId() +
                                    " | " + c.getMarca() +
                                    " " + c.getModelo() +
                                    " (" + c.getMatricula() + ")"));
                }

                // ELIMINAR
                case 3 -> {
                    System.out.print("ID coche a eliminar: ");
                    int id = sc.nextInt();

                    cocheDAO.eliminarCoche(id);
                }
            }

        } while (opcion != 0);
    }
}