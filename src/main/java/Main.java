import dao.ClienteDao;
import dao.CocheDao;
import model.Cliente;
import model.Coche;

public class Main {
    public static void main(String[] args) {

        ClienteDao clienteDao = new ClienteDao();
        CocheDao cocheDAO = new CocheDao();

        // 🔹 1. INSERTAR CLIENTE TEMPORAL
        Cliente clienteTest = new Cliente(0, "TestNombre", "TestApellido", "600000000", "test@test.com", "TestDir");
        clienteDao.insertarCliente(clienteTest);

        // 🔹 2. OBTENER EL ÚLTIMO CLIENTE INSERTADO
        Cliente ultimoCliente = clienteDao.listarClientes()
                .get(clienteDao.listarClientes().size() - 1);

        System.out.println("Cliente insertado: " + ultimoCliente.getId());

        // 🔹 3. INSERTAR COCHE ASOCIADO
        Coche cocheTest = new Coche(
                0,
                ultimoCliente.getId(),
                "TEST123",
                "MarcaTest",
                "ModeloTest",
                2024
        );

        cocheDAO.insertarCoche(cocheTest);

        // 🔹 4. OBTENER COCHE INSERTADO
        Coche cocheInsertado = cocheDAO.listarCochesPorCliente(ultimoCliente.getId())
                .get(0);

        System.out.println("Coche insertado: " + cocheInsertado.getId());

        // 🔹 5. ELIMINAR COCHE
        cocheDAO.eliminarCoche(cocheInsertado.getId());

        // 🔹 6. ELIMINAR CLIENTE
        clienteDao.eliminarCliente(ultimoCliente.getId());

        System.out.println("🧹 Datos de prueba eliminados correctamente");
    }
}

