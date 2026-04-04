package ui;

import model.Cliente;
import dao.ClienteDAO;

import java.util.List;
import java.util.Scanner;

public class MenuClientes {

    static Scanner sc = new Scanner(System.in);
    static ClienteDAO clienteDAO = new ClienteDAO();

    public static void mostrar() {
        int opcion;

        do {
            System.out.println("\n--- CLIENTES ---");
            System.out.println("1. Insertar");
            System.out.println("2. Listar");
            System.out.println("3. Eliminar");
            System.out.println("0. Volver");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    Cliente cliente = new Cliente(0, nombre, apellido, "", email, "");
                    clienteDAO.insertarCliente(cliente);
                }

                case 2 -> {
                    List<Cliente> lista = clienteDAO.listarClientes();
                    lista.forEach(c -> System.out.println(c.getId() + " - " + c.getNombre()));
                }

                case 3 -> {
                    System.out.print("ID a eliminar: ");
                    int id = sc.nextInt();
                    clienteDAO.eliminarCliente(id);
                }
            }

        } while (opcion != 0);
    }
}