package org.example;

import java.util.ArrayList;

public class Clients {
    private int numberOfclients;
    private int currentClient;
    private int clientId;
    private String Name;
    private static final ArrayList<Clients> clientsArrayList = new ArrayList<>();
    public void AddClient(String name) {
        Clients client = new Clients();
        client.Name = name;
        client.clientId = currentClient;
        currentClient ++;
        numberOfclients ++;
    }
}
