package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RunClient {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Client client = new Client();
        client.startClient();  // Conectar al servidor

        System.out.println("Cliente conectado al servidor.");
        client.mostrarPersonas();  // Mostrar las personas iniciales

        // Crear nueva persona
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la nueva persona:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la edad de la nueva persona:");
        int edad = scanner.nextInt();

        client.crearPersona(nombre, edad);  // Crear persona en el servidor

        // Mostrar la lista de personas nuevamente
        System.out.println("Lista de personas después de agregar la nueva:");
        client.mostrarPersonas();
    }
}
