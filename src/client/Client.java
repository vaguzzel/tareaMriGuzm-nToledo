package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import common.InterfazDeServer;
import common.Persona;

public class Client {
    private InterfazDeServer server;

    public Client() {}

    public void startClient() throws RemoteException, NotBoundException {
        // Conectar al registro RMI en el puerto 1099
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        server = (InterfazDeServer) registry.lookup("server");  // Buscar el objeto remoto
    }

    public void mostrarPersonas() throws RemoteException {
        // Obtener la lista de personas del servidor
        ArrayList<Persona> personas = server.getPersona();

        // Indicar si la lista está vacía
        if (personas.isEmpty()) {
            System.out.println(">> No hay personas registradas en el servidor."); // Mensaje claro
        } else {
            // Si no está vacía, imprimir cada persona
            System.out.println(">> Lista de Personas:");
            for (Persona persona : personas) {
            	// Aquí puedes mejorar el formato o usar persona.toString() si lo implementas después
                System.out.println("   - Nombre: " + persona.getNombre() + ", Edad: " + persona.getEdad());
            }
        }
    }

    public void crearPersona(String nombre, int edad) throws RemoteException {
        // Crear una nueva persona en el servidor
        server.agregarPersona(nombre, edad);
    }
}
