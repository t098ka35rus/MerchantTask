package org.example;

import java.util.ArrayList;

public class Clients implements Dealable {
    private static int clientsCounter;
    private static int currentClient;
    private int clientId;
    private String name;
    private static final ArrayList<Clients> clientsArrayList = new ArrayList<>();

    private Clients (){};
    public static void AddClient(String name) {
        Clients client = new Clients();
        client.name = name;
        client.clientId = currentClient;
        currentClient ++;
        clientsCounter ++;
        clientsArrayList.add(client);
        System.out.println(client);
    }
    public static boolean FindClient (String name){
        for (Clients client : clientsArrayList) {
            if (client.name == name) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void makeDeal() {
        Orders.AddOrder(clientId);
    }

    @Override
    public String toString() {
        System.out.println("Создан клиент. Id = " + clientId + " Name = " + name + " : ");
        return super.toString();
    }
}
