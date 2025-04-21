package server;

import common.InterfazDeServer;
import common.Persona;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerImpl extends UnicastRemoteObject implements InterfazDeServer {
    // lista de personas en el servidor
    private ArrayList<Persona> personas;

    // constructor
    public ServerImpl() throws RemoteException {
        super();
        personas = new ArrayList<>();
        // Datos iniciales (las personas se mostrarán al ocupar la opción dos del menú en la consola)
        System.out.println("Inicializando con datos de prueba...");
        personas.add(new Persona("Valentina Guzmán E", 22));
        personas.add(new Persona("Claudio Toledo M", 21));
        personas.add(new Persona("Matías García", 27));
        System.out.println("Datos de prueba agregados. Total: " + personas.size());
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
