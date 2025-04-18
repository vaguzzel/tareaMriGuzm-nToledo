package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.InputMismatchException; // Importar para manejo de errores de entrada
import java.util.Scanner;

public class RunClient {
    public static void main(String[] args) { // No necesita lanzar excepciones aquí si las manejamos dentro
        Client client = new Client();
        Scanner scanner = new Scanner(System.in); // Crear el Scanner una vez
        boolean salir = false;

        try {
            client.startClient(); // Conectar al servidor una vez al inicio
            System.out.println("Cliente conectado al servidor.");

            while (!salir) {
                // Mostrar menú de opciones
                System.out.println("\n--- Menú Cliente RMI ---");
                System.out.println("1. Mostrar lista de personas");
                System.out.println("2. Agregar nueva persona");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = -1; // Inicializar opción
                try {
                    opcion = scanner.nextInt(); // Leer la opción del usuario
                    scanner.nextLine(); // Consumir el salto de línea pendiente después de nextInt()
                } catch (InputMismatchException e) {
                    System.out.println("Error: Por favor, ingrese un número válido.");
                    scanner.nextLine(); // Consumir la entrada inválida
                    continue; // Volver al inicio del bucle while
                }


                // Procesar la opción seleccionada
                switch (opcion) {
                    case 1:
                        System.out.println("\n--- Lista de Personas ---");
                        try {
                             // Llamar al método para mostrar, asegurándose de que maneje la lista vacía
                             client.mostrarPersonas();
                        } catch (RemoteException e) {
                            System.err.println("Error al obtener la lista del servidor: " + e.getMessage());
                            // Opcionalmente, intentar reconectar o salir
                        }
                        break;
                    case 2:
                        System.out.println("\n--- Agregar Nueva Persona ---");
                        try {
                            System.out.print("Ingrese el nombre: ");
                            String nombre = scanner.nextLine();

                            System.out.print("Ingrese la edad: ");
                            int edad = -1;
                            try {
                                edad = scanner.nextInt();
                                scanner.nextLine(); // Consumir salto de línea
                                if (edad < 0) { // Validación simple de edad
                                     System.out.println("Edad inválida, no se agregará la persona.");
                                     continue;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: La edad debe ser un número entero.");
                                scanner.nextLine(); // Consumir entrada inválida
                                continue; // Volver al menú
                            }


                            client.crearPersona(nombre, edad); // Crear persona en el servidor
                            System.out.println("Persona agregada exitosamente.");

                        } catch (RemoteException e) {
                            System.err.println("Error al agregar persona en el servidor: " + e.getMessage());
                        } catch (Exception e) { // Captura otras posibles excepciones de entrada
                             System.err.println("Error en la entrada de datos: " + e.getMessage());
                             // Asegurarse de limpiar el buffer del scanner si es necesario
                             if (scanner.hasNextLine()) scanner.nextLine();
                        }
                        break;
                    case 0:
                        salir = true; // Establecer la bandera para salir del bucle
                        System.out.println("Desconectando cliente...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
            }

        } catch (RemoteException | NotBoundException e) {
            System.err.println("Error crítico de conexión con el servidor: " + e.getMessage());
            // e.printStackTrace(); // Descomentar para depuración
        } finally {
             scanner.close(); // Cerrar el Scanner al final
             System.out.println("Cliente terminado.");
        }
    }
}