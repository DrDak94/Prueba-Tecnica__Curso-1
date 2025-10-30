import controllers.ClienteController;
import entities.Cliente;

import java.util.List;
import java.util.Scanner;

public class Main {

    // Scanner único para leer entrada desde consola
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Mensaje de bienvenida
        System.out.println("Bienvenido a GestUser!");
        System.out.println("El programa Nª1 para la gestión de Clientes!");

        // Bucle principal del programa que muestra el menú hasta que el usuario elija salir
        while (true) {
            mostrarMenu();
            int opcion;

            try {
                // Leer la opción introducida por el usuario
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Si la entrada no es un número, avisar y volver a mostrar el menú
                System.out.println("❌ Entrada inválida. Introduzca un número del 0 al 5.");
                continue;
            }

            // Ejecutar la acción elegida por el usuario
            switch (opcion) {
                case 0 -> {
                    // Salir del programa
                    System.out.println("Adiós! Gracias por utilizar GestUser!");
                    return;
                }
                case 1 -> registrarCliente();           // Crear nuevo cliente
                case 2 -> listarClientes();             // Listar todos los clientes
                case 3 -> modificarCliente();           // Modificar un cliente existente
                case 4 -> eliminarCliente();            // Eliminar un cliente por ID
                case 5 -> buscarClientePorCiudad();     // Buscar clientes por ciudad
                default -> System.out.println("⚠️ Opción no disponible. Intente de nuevo. ⚠️");
            }
        }
    }

    // ----------------------- Métodos de funcionalidad -----------------------

    // Pide los datos al usuario y llama al controlador para registrar un nuevo cliente.
    private static void registrarCliente() {
        try {
            // Pedir los distintos campos al usuario con validación de longitud
            String nombre = pedirInput("Nombre", 30);
            String apellido = pedirInput("Apellido", 30);
            char sexo = pedirSexo("Sexo (M/F): ");
            String ciudad = pedirInput("Ciudad", 50);
            String fechaNacimiento = pedirInput("Fecha de Nacimiento", 30);
            String telefono = pedirInput("Teléfono", 30);
            String correo = pedirInput("Correo electrónico", 50);

            // Llamada al controlador para persistir el cliente
            ClienteController.registrarCliente(nombre, apellido, sexo, ciudad, fechaNacimiento, telefono, correo);
            System.out.println("✅ Nuevo cliente registrado!");
        } catch (IllegalArgumentException e) {
            // Mostrar error si algún campo no cumple validaciones
            System.out.println("❌ Error: " + e.getMessage() + " ❌");
        }
    }

    // Muestra por consola la lista de clientes obtenida desde el controlador.
    private static void listarClientes() {
        List<Cliente> clientes = ClienteController.listadoClientes();
        if (clientes.isEmpty()) {
            // Si no hay clientes, avisar al usuario
            System.out.println("No hay clientes registrados!");
        } else {
            // Imprimir cada cliente
            System.out.println("Listado de clientes:");
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }

    // Permite modificar los campos de un cliente ya existente.
    // Se pide el ID, se carga el cliente y se permite cambiar campos en un submenú.
    private static void modificarCliente() {
        try {
            // Pedir ID del cliente a modificar
            Long idModificar = Long.parseLong(pedirInput("Ingrese el ID del Cliente que desea modificar: ", 20));
            Cliente clienteExistente = ClienteController.buscarClientePorId(idModificar);

            if (clienteExistente == null) {
                // Si no existe ese ID, informar y volver
                System.out.println("❗ Cliente no encontrado! ❗");
                return;
            }

            // Bucle que permite modificar varios campos hasta que el usuario termine
            boolean seguirModificando = true;
            while (seguirModificando) {
                System.out.println("\nCliente actual: " + clienteExistente);
                mostrarMenuModificar();

                int optMod;
                try {
                    // Leer la opción de modificación
                    optMod = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("❌ Opción inválida. ❌");
                    continue;
                }

                // Según la opción, actualizar el campo correspondiente en el objeto cliente
                switch (optMod) {
                    case 1 -> clienteExistente.setNombre(pedirInput("Nuevo nombre: ", 40));
                    case 2 -> clienteExistente.setApellido(pedirInput("Nuevo apellido: ", 40));
                    case 3 -> clienteExistente.setSexo(pedirSexo("Nuevo sexo (M/F): "));
                    case 4 -> clienteExistente.setCiudad(pedirInput("Nueva ciudad: ", 60));
                    case 5 -> clienteExistente.setFechaNacimiento(pedirInput("Nueva fecha de nacimiento: ", 40));
                    case 6 -> clienteExistente.setTelefono(pedirInput("Nuevo teléfono: ", 40));
                    case 7 -> clienteExistente.setCorreo(pedirInput("Nuevo correo electrónico: ", 80));
                    case 0 -> {
                        // Terminar modificaciones
                        seguirModificando = false;
                        System.out.println("✅ Modificación finalizada! ✅");
                    }
                    default -> System.out.println("❌ Opción no válida. ❌");
                }
            }

            // Guardar los cambios en la base de datos mediante el controlador
            if (ClienteController.modificarCliente(clienteExistente)) {
                System.out.println("✅ Cliente modificado correctamente en la base de datos! ✅");
            } else {
                System.out.println("❌ Error al modificar el cliente en la base de datos! ❌");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido. ❌");
        }
    }

    // Pide un ID y solicita al controlador la eliminación del cliente.
    private static void eliminarCliente() {
        try {
            Long idCliente = Long.parseLong(pedirInput("Ingrese el ID del Cliente que desea eliminar: ", 20));
            if (ClienteController.eliminarCliente(idCliente)) {
                System.out.println("✅ Cliente eliminado!");
            } else {
                System.out.println("❌ Cliente no encontrado! ❌");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido. ❌");
        }
    }

    // Busca y muestra los clientes que pertenecen a la ciudad indicada.
    private static void buscarClientePorCiudad() {
        String ciudadBuscar = pedirInput("Ingrese la Ciudad para buscar Clientes: ", 10);
        List<Cliente> clientesCiudad = ClienteController.buscarClientesPorCiudad(ciudadBuscar);

        if (clientesCiudad.isEmpty()) {
            System.out.println("No se encontraron Clientes en " + ciudadBuscar);
        } else {
            System.out.println("Clientes encontrados en " + ciudadBuscar + ":");
            for (Cliente c : clientesCiudad) {
                System.out.println(c);
            }
        }
    }

    // ----------------------- Métodos auxiliares -----------------------

    /*
     * Metodo auxiliar para pedir una entrada de texto y validar:
     * - No puede estar vacío
     * - No puede superar la longitud máxima indicada
     */
    private static String pedirInput(String mensaje, int maxLength) {
        System.out.print(mensaje + ": ");
        String input = scanner.nextLine().trim();
        if (input.isBlank()) {
            throw new IllegalArgumentException("Este campo no puede estar vacío.");
        }
        if (input.length() > maxLength){
            throw new IllegalArgumentException("El valor excede el máximo de " + maxLength + " caracteres.");
        }
        return input;
    }

    /*
     * Pide el sexo al usuario hasta que introduzca 'M' o 'F' (se acepta en mayúscula o minúscula).
     * Devuelve el primer carácter ('m' o 'f').
     */
    private static char pedirSexo(String mensaje){
        while (true){
            System.out.print(mensaje);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("m") || input.equals("f")){
                return input.charAt(0);
            } else {
                // Si la entrada no es válida, avisar y pedir de nuevo
                System.out.println("❌ Sexo inválido. Ingrese 'M' o 'F'. ❌");
            }
        }
    }

    // Muestra el menú principal de la aplicación.
    private static void mostrarMenu() {
        System.out.println("\nCRUD de Clientes");
        System.out.println("1 - Crear nuevo Cliente");
        System.out.println("2 - Listar Clientes");
        System.out.println("3 - Modificar un Cliente");
        System.out.println("4 - Eliminar un Cliente");
        System.out.println("5 - Buscar Clientes por Ciudad");
        System.out.println("0 - Salir del programa");
        System.out.print("Elija una opción: ");
    }

    // Muestra el submenú para seleccionar qué campo modificar del cliente.
    private static void mostrarMenuModificar() {
        System.out.println("\nQué desea modificar?");
        System.out.println("1 - Nombre");
        System.out.println("2 - Apellido");
        System.out.println("3 - Sexo");
        System.out.println("4 - Ciudad");
        System.out.println("5 - Fecha Nacimiento");
        System.out.println("6 - Teléfono");
        System.out.println("7 - Correo Electrónico");
        System.out.println("0 - Terminar modificación");
        System.out.print("Opción: ");
    }
}
