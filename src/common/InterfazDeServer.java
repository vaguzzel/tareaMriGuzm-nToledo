package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InterfazDeServer extends Remote {
    // Obtener la lista de personas
    public ArrayList<Persona> getPersona() throws RemoteException;

    // Agregar una persona a la lista
    public void agregarPersona(String nombre, int edad) throws RemoteException;
}
