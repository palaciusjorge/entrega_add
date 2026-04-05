package ui;

import dao.ReparacionDAO;
import dao.PiezaReparacionDAO;
import model.Reparacion;

import java.util.List;
import java.util.Scanner;

public class MenuReparacion {

    static Scanner sc = new Scanner(System.in);
    static ReparacionDAO reparacionDAO = new ReparacionDAO();
    static PiezaReparacionDAO piezaReparacionDAO = new PiezaReparacionDAO();

    public static void mostrar() {

        int opcion;

        do {
            System.out.println("\n--- REPARACIONES ---");
            System.out.println("1. Crear reparación");
            System.out.println("2. Listar reparaciones");
            System.out.println("3. Añadir pieza a reparación");
            System.out.println("4. Ver piezas de una reparación");
            System.out.println("5. Eliminar reparación");
            System.out.println("0. Volver");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                // INSERTAR REPARACION
                case 1 -> {
                    System.out.print("ID coche: ");
                    int idCoche = sc.nextInt();

                    System.out.print("ID mecánico: ");
                    int idMecanico = sc.nextInt();

                    Reparacion r = new Reparacion(0, idCoche, idMecanico); // El ID se genera automáticamente en la base de datos, por eso se pone 0 aquí.
                    reparacionDAO.insertarReparacion(r);
                }

                // LISTAR
                case 2 -> {
                    List<Reparacion> lista = reparacionDAO.listarReparaciones();

                    lista.forEach(r ->
                            System.out.println("ID: " + r.getId() +
                                    " | Coche: " + r.getIdCoche() +
                                    " | Mecánico: " + r.getIdMecanico()));
                }

                // AÑADIR PIEZA
                case 3 -> {
                    System.out.print("ID reparación: ");
                    int idR = sc.nextInt();

                    System.out.print("ID pieza: ");
                    int idP = sc.nextInt();

                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();

                    piezaReparacionDAO.anadirPiezaAReparacion(idR, idP, cantidad);
                }

                // VER PIEZAS
                case 4 -> {
                    System.out.print("ID reparación: ");
                    int idR = sc.nextInt();

                    piezaReparacionDAO
                            .listarPiezasDeReparacion(idR)
                            .forEach(System.out::println);
                }

                // ELIMINAR POR ID
                case 5 -> {
                    System.out.print("ID reparación a eliminar: ");
                    int id = sc.nextInt();

                    reparacionDAO.eliminarReparacion(id);
                }
            }

        } while (opcion != 0);
    }
}
