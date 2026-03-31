import dao.ClienteDao;
import model.Cliente;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ClienteDao dao = new ClienteDao();

        dao.eliminarCliente(6);
        dao.eliminarCliente(7);
        dao.eliminarCliente(8);

        dao.listarClientes().forEach(System.out::println);

    }
}
