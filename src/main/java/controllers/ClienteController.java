package controllers;

import entities.Cliente;
import persistence.ClienteJPA;

import java.util.List;

public class ClienteController {

    // ----------------------- Crear cliente -----------------------
    // Recibe los datos del nuevo cliente, válida los campos y lo guarda en la base de datos.
    public static void registrarCliente(String nombre, String apellido, char sexo, String ciudad, String fechaNacimiento, String telefono, String correo) {
        validarCampos(nombre, apellido, ciudad, fechaNacimiento, telefono, correo);
        Cliente cliente = new Cliente(nombre, apellido, sexo, ciudad, fechaNacimiento, telefono, correo);
        ClienteJPA.registrarCliente(cliente);
    }

    // ----------------------- Listar clientes -----------------------
    // Devuelve una lista con todos los clientes de la base de datos.
    public static List<Cliente> listadoClientes() {
        return ClienteJPA.listadoClientes();
    }

    // ----------------------- Eliminar cliente -----------------------
    // Elimina un cliente de la base de datos según su ID.
    public static boolean eliminarCliente(Long idCliente) {
        return ClienteJPA.eliminarCliente(idCliente);
    }

    // ----------------------- Modificar cliente -----------------------
    // Valida los campos y actualiza los datos de un cliente existente.
    public static boolean modificarCliente(Cliente clienteActualizado) {
        validarCampos(clienteActualizado.getNombre(), clienteActualizado.getApellido(), clienteActualizado.getCiudad(), clienteActualizado.getFechaNacimiento(), clienteActualizado.getTelefono(), clienteActualizado.getCorreo());
        return ClienteJPA.modificarCliente(clienteActualizado);
    }

    // ----------------------- Buscar cliente por ID -----------------------
    // Busca un cliente en la base de datos usando su ID.
    public static Cliente buscarClientePorId(Long id) {
        return ClienteJPA.buscarClientePorId(id);
    }

    // ----------------------- Buscar clientes por ciudad -----------------------
    // Devuelve una lista de clientes que viven en la ciudad indicada.
    public static List<Cliente> buscarClientesPorCiudad(String ciudad) {
        return ClienteJPA.buscarClientesPorCiudad(ciudad);
    }

    // ----------------------- Validar campos obligatorios -----------------------
    // Comprueba que ningún campo esté vacío o nulo antes de guardar/modificar el cliente.
    private static void validarCampos(String nombre, String apellido, String ciudad, String fechaNacimiento, String telefono, String correo) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío ❌");
        if (apellido == null || apellido.isBlank())
            throw new IllegalArgumentException("El apellido no puede estar vacío ❌");
        if (ciudad == null || ciudad.isBlank()) throw new IllegalArgumentException("La ciudad no puede estar vacía ❌");
        if (fechaNacimiento == null || fechaNacimiento.isBlank())
            throw new IllegalArgumentException("La fecha de nacimiento no puede estar vacía ❌");
        if (telefono == null || telefono.isBlank())
            throw new IllegalArgumentException("El teléfono no puede estar vacío ❌");
        if (correo == null || correo.isBlank()) throw new IllegalArgumentException("El correo no puede estar vacío ❌");
    }
}
