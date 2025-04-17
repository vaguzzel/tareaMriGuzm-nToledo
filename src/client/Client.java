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
        Registry registry = LocateRegistry.getRegistry("localhost", 1066);
        server = (InterfazDeServer) registry.lookup("server");  // Buscar el objeto remoto
    }

    public void mostrarPersonas() throws RemoteException {
        // Mostrar todas las personas en el servidor
        ArrayList<Persona> personas = server.getPersona();
        for (Persona persona : personas) {
            System.out.println(persona.getNombre() + " " + persona.getEdad());
        }
    }

    public void crearPersona(String nombre, int edad) throws RemoteException {
        // Crear una nueva persona en el servidor
        server.agregarPersona(nombre, edad);
    }
}
