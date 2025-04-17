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
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        server = (InterfazDeServer) registry.lookup("server");
    }
    public void mostrarPersonas() throws RemoteException {
        ArrayList<Persona> personas = server.getPersona();
        for (Persona persona : personas) {
            System.out.println(persona.getNombre() + " " + persona.getEdad());
        }
    }

    public void crearPersona() throws RemoteException {
        server.crearPersona();
    }
}