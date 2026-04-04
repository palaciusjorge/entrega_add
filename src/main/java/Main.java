import dao.*;
import model.*;

public class Main {
    public static void main(String[] args) {

        ClienteDAO clienteDAO = new ClienteDAO();
        CocheDAO cocheDAO = new CocheDAO();
        MecanicoDAO mecanicoDAO = new MecanicoDAO();
        ReparacionDAO reparacionDAO = new ReparacionDAO();
        FacturaDAO facturaDAO = new FacturaDAO();
        PiezaDAO piezaDAO = new PiezaDAO();
        PiezaReparacionDAO piezaReparacionDAO = new PiezaReparacionDAO();

        try {
            System.out.println("🔹 INICIO TEST COMPLETO 🔹");

            // 🔹 1. CLIENTE
            Cliente cliente = new Cliente(0, "Test", "Final", "600000000", "test@test.com", "TestDir");
            clienteDAO.insertarCliente(cliente);
            Cliente c = clienteDAO.listarClientes().get(clienteDAO.listarClientes().size() - 1);

            // 🔹 2. COCHE
            Coche coche = new Coche(0, c.getId(), "TEST999", "MarcaTest", "ModeloTest", 2024);
            cocheDAO.insertarCoche(coche);
            Coche co = cocheDAO.listarCochesPorCliente(c.getId()).get(0);

            // 🔹 3. MECANICO
            Mecanico mecanico = new Mecanico(0, "MecTest", "General", "611111111");
            mecanicoDAO.insertarMecanico(mecanico);
            Mecanico m = mecanicoDAO.listarMecanicos()
                    .get(mecanicoDAO.listarMecanicos().size() - 1);

            // 🔹 4. REPARACION
            Reparacion reparacion = new Reparacion(0, co.getId(), m.getId());
            reparacionDAO.insertarReparacion(reparacion);
            Reparacion r = reparacionDAO.listarReparaciones()
                    .get(reparacionDAO.listarReparaciones().size() - 1);

            // 🔹 5. PIEZA
            Pieza pieza = new Pieza(0, "PiezaTest", 50.0, 10);
            piezaDAO.insertarPieza(pieza);
            Pieza p = piezaDAO.listarPiezas()
                    .get(piezaDAO.listarPiezas().size() - 1);

            // 🔹 6. RELACION PIEZA-REPARACION
            piezaReparacionDAO.anadirPiezaAReparacion(r.getId(), p.getId(), 2);

            System.out.println("🔹 Piezas en reparación:");
            piezaReparacionDAO.listarPiezasDeReparacion(r.getId())
                    .forEach(System.out::println);

            // 🔹 7. FACTURA
            facturaDAO.insertarFactura(r.getId(), 200.0);

            System.out.println("🔹 Facturas del cliente:");
            facturaDAO.listarFacturasPorCliente(c.getId())
                    .forEach(f -> System.out.println("Factura ID: " + f.getId()));

            Factura f = facturaDAO.listarFacturasPorReparacion(r.getId()).get(0);

            // 🔹 8. LIMPIEZA DE DATOS DE PRUEBA
            facturaDAO.eliminarFactura(f.getId());
            piezaReparacionDAO.eliminarPiezaDeReparacion(r.getId(), p.getId());
            reparacionDAO.eliminarReparacion(r.getId());
            cocheDAO.eliminarCoche(co.getId());
            clienteDAO.eliminarCliente(c.getId());
            mecanicoDAO.eliminarMecanico(m.getId());
            piezaDAO.eliminarPieza(p.getId());

            System.out.println("🧹 TEST COMPLETADO Y LIMPIO");

        } catch (Exception e) {
            System.out.println("❌ Error en el test: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
