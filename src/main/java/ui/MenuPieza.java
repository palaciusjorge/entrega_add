package ui;

import java.util.Scanner;

import dao.PiezaDAO;
import model.Pieza;

import java.util.List;

public class MenuPieza {

    static Scanner sc = new Scanner(System.in);
    static PiezaDAO piezaDAO = new PiezaDAO();

    public static void mostrar() {
        int opcion;

        do{
            System.out.println("\n--- PIEZAS ---");
            System.out.println("1. Insertar pieza");
            System.out.println("2. Listar piezas");
            System.out.println("3. Buscar pieza por ID");
            System.out.println("4. Actualizar stock de pieza");
            System.out.println("5. Eliminar pieza");
            System.out.println("0. Volver");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                // INSERTAR PIEZA
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Stock: ");
                    int stock = sc.nextInt();

                    Pieza p = new Pieza(0, nombre, precio, stock); // El ID se genera automáticamente en la base de datos, por eso se pone 0 aquí.
                    piezaDAO.insertarPieza(p);
                }

                case 2 -> {

                    // LISTAR PIEZAS

                    List<Pieza> lista = piezaDAO.listarPiezas();

                    lista.forEach(p ->
                            System.out.println("ID: " + p.getId() +
                                    " | Nombre: " + p.getNombre() +
                                    " | Precio: " + p.getPrecio() +
                                    " | Stock: " + p.getStock()));
                }

                case 3 -> {

                    // BUSCAR PIEZA POR ID


                    System.out.print("ID pieza: ");
                    int id = sc.nextInt();

                    Pieza p = piezaDAO.obtenerPiezaPorId(id);

                    if (p != null) {
                        System.out.println("ID: " + p.getId() +
                                " | Nombre: " + p.getNombre() +
                                " | Precio: " + p.getPrecio() +
                                " | Stock: " + p.getStock());
                    } else {
                        System.out.println("Pieza no encontrada");
                    }
                }

                case 4 -> {

                    // ACTUALIZAR STOCK DE PIEZA


                    System.out.print("ID pieza: ");
                    int id = sc.nextInt();

                    System.out.print("Nuevo stock: ");
                    int nuevoStock = sc.nextInt();

                    piezaDAO.actualizarStockPieza(id, nuevoStock);
                }

                case 5 -> {

                    // ELIMINAR PIEZA POR ID


                    System.out.print("ID pieza a eliminar: ");
                    int id = sc.nextInt();

                    piezaDAO.eliminarPieza(id);
                }
            }
        }
        while (opcion != 0);
    }

}
