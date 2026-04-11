package ui;

import java.util.Scanner;
import java.util.List;

import dao.MecanicoDAO;
import model.Mecanico;

public class MenuMecanico {

    static Scanner sc = new Scanner(System.in); //Son static para poder llamarlos desde el main sin crear una instancia de MenuMecanico, con mostrar() es suficiente.
    static MecanicoDAO mecanicoDAO = new MecanicoDAO();

    public static void mostrar() {
        int opcion;

        do {
            System.out.println("\n--- MECANICOS ---");
            System.out.println("1. Insertar");
            System.out.println("2. Listar por especialidad");
            System.out.println("3. Eliminar por ID");
            System.out.println("0. Volver");

            System.out.print("Selecciona una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Especialidad: ");
                    String especialidad = sc.nextLine();

                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine();

                    Mecanico m = new Mecanico(0, nombre, especialidad, telefono);
                    mecanicoDAO.insertarMecanico(m);
                }

                case 2 -> {
                    System.out.print("Especialidad a listar: ");
                    String especialidad = sc.nextLine();

                    List<Mecanico> lista = mecanicoDAO.listarMecanicosPorEspecialidad(especialidad);
                    lista.forEach(m -> System.out.println(m.getId() + " - " + m.getNombre()));
                }

                case 3 -> {
                    System.out.print("ID a eliminar: ");
                    int id = sc.nextInt();
                    mecanicoDAO.eliminarMecanico(id);
                }
            }

        } while (opcion != 0);
    }
}
