package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RunClient {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        // TODO Auto-generated method stub
        Client client = new Client();
        client.startClient();

        System.out.println("Cliente arribal!");
        client.mostrarPersonas();
        client.crearPersona();
        System.out.println();
        client.mostrarPersonas();
    }
}
