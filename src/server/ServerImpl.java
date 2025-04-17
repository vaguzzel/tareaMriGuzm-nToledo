package server;

import common.InterfazDeServer;
import common.Persona;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerImpl extends UnicastRemoteObject implements InterfazDeServer {
    // Lista de personas en el servidor
    private ArrayList<Persona> personas;

    // Constructor
    public ServerImpl() throws RemoteException {
        super();
        personas = new ArrayList<>();
        // Inicializando con valores de prueba
        personas.add(new Persona("Valentina Guzmán E", 22));
        personas.add(new Persona("Claudio Toledo M", 21));
    }

    @Override
    public ArrayList<Persona> getPersona() throws RemoteException {
        return personas;
    }

    @Override
    public void agregarPersona(String nombre, int edad) throws RemoteException {
        Persona nuevaPersona = new Persona(nombre, edad);
        personas.add(nuevaPersona);
    }
}
