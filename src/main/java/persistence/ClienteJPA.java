package persistence;

import entities.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteJPA {

    // ----------------------- Registrar cliente -----------------------
    // Guarda un nuevo cliente en la base de datos.
    public static void registrarCliente(Cliente cliente) {
        try (EntityManager em = ConfigJPA.getEM()) {
            em.getTransaction().begin();        // Inicia una transacción
            em.persist(cliente);                // Guarda el objeto cliente en la base de datos
            em.getTransaction().commit();       // Confirma los cambios
        } catch (Exception e) {
            // Si ocurre un error, se muestra un mensaje
            System.out.println("❌ Error al registrar cliente: " + e.getMessage() + " ❌");
        }
    }

    // ----------------------- Listar clientes -----------------------
    // Devuelve una lista con todos los clientes registrados.
    public static List<Cliente> listadoClientes() {
        try (EntityManager em = ConfigJPA.getEM()) {
            // Ejecuta una consulta JPQL que selecciona todos los objetos Cliente
            return em.createQuery("FROM Cliente", Cliente.class).getResultList();
        } catch (Exception e) {
            System.out.println("❌ Error al listar clientes: " + e.getMessage() + " ❌");
            return List.of();       // Devuelve lista vacía si hay error
        }
    }

    // ----------------------- Buscar cliente por ID -----------------------
    // Busca un cliente específico por su ID.
    public static Cliente buscarClientePorId(Long id) {
        try (EntityManager em = ConfigJPA.getEM()) {
            // Usa el metodo find() para obtener el cliente por su clave primaria
            return em.find(Cliente.class, id);
        } catch (Exception e) {
            System.out.println("❌ Error al buscar cliente: " + e.getMessage() + " ❌");
            return null;        // Devuelve null si hay error
        }
    }

    // ----------------------- Modificar cliente -----------------------
    // Actualiza los datos de un cliente existente.
    public static boolean modificarCliente(Cliente clienteActualizado) {
        try (EntityManager em = ConfigJPA.getEM()) {
            em.getTransaction().begin();        // Inicia transacción
            em.merge(clienteActualizado);       // Actualiza el cliente (merge reemplaza el existente)
            em.getTransaction().commit();       // Confirma los cambios
            return true;
        } catch (Exception e) {
            System.out.println("❌ Error al modificar cliente: " + e.getMessage() + " ❌");
            return false;
        }
    }

    // ----------------------- Eliminar cliente -----------------------
    // Elimina un cliente de la base de datos según su ID.
    public static boolean eliminarCliente(Long idCliente) {
        try (EntityManager em = ConfigJPA.getEM()) {
            // Primero busca el cliente por su ID
            Cliente cliente = em.find(Cliente.class, idCliente);
            if (cliente == null) return false;      // Si no existe, devuelve false

            em.getTransaction().begin();            // Inicia transacción
            em.remove(cliente);                     // Elimina el cliente de la base de datos
            em.getTransaction().commit();           // Confirma los cambios
            return true;
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar cliente: " + e.getMessage() + " ❌");
            return false;
        }
    }

    // ----------------------- Buscar clientes por ciudad -----------------------
    // Devuelve una lista de clientes que viven en una ciudad específica.
    public static List<Cliente> buscarClientesPorCiudad(String ciudad) {
        try (EntityManager em = ConfigJPA.getEM()) {
            // Consulta JPQL con parámetro: ciudad
            return em.createQuery("FROM Cliente c WHERE c.ciudad = :ciudad", Cliente.class)
                    .setParameter("ciudad", ciudad)         // Asigna el valor al parámetro
                    .getResultList();                          // Obtiene la lista de resultados
        } catch (Exception e) {
            System.out.println("❌ Error al buscar clientes por ciudad: " + e.getMessage() + " ❌");
            return List.of();
        }
    }
}
