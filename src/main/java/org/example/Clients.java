package org.example;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Clients implements Dealable {
    private static final ArrayList<Clients> clientsArrayList = new ArrayList<>();
    private static int clientsCounter;
    private static int currentClient;
    private int clientId;
    private String name;

    private Clients() {
    }

    public static void AddClient(String name) {
        Clients client = new Clients();
        client.name = name;
        client.clientId = currentClient;
        currentClient++;
        clientsCounter++;
        clientsArrayList.add(client);
    }

    public static boolean isNewClient(String name) {
        for (Clients client : clientsArrayList) {
            if (client.name.equals(name)) {
                return false;
            }
        }
        return true;
    }

    @Contract(pure = true)
    public static @Nullable Clients FindClient(String name) {
        for (Clients client : clientsArrayList) {
            if (client.name.equals(name)) {
                return client;
            }
        }
        return null;
    }

    public static Clients GetClient(int clientId) {
        for (Clients client : clientsArrayList) {
            if (client.getClientId() == clientId) {
                return client;
            }
        }
        return null;
    }

    public int getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        System.out.println("Создан клиент. Id = " + clientId + " Name = " + name + " : ");
        return super.toString();
    }

    @Override
    public void makeDeal() {     // Принцип замены Барбары Лисков. Наследник может играть за предка.
        Orders.AddOrder(clientId);

    }
}
