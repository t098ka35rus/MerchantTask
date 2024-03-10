package org.example;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Scanner;

import static org.example.Goods.getGoodsPrice;
import static org.example.StatusOfOrder.PAID;

public class Orders { //Single-responsibility principle. public методы, все остальное внутри класса `Orders
    private static final ArrayList<Orders> ordersArrayList = new ArrayList<>();
    private static final int productParams = 2;
    private static final int goodsIdPosition = 0; // Magics, вместо числа, переменная для номера элемента в массиве
    private static final int goodsQuantPosition = 1;

    private static final Scanner scanner = new Scanner(System.in);
    private static int numberOfOrders;
    private static int orderIdCurrent;
    private ArrayList<int[]> goodsOfOrder = new ArrayList<>();
    private int orderId;
    private long orderDate;
    private String orderStatus;
    private int orderTotalCost;
    private int clientId;

    public static void AddOrder(int clientId) {
        Orders order = new Orders();
        order.orderId = orderIdCurrent;
        order.clientId = clientId;
        orderIdCurrent++;
        numberOfOrders++;
        order.orderDate = System.currentTimeMillis();
        order.orderStatus = "Заказ размещен";
        ordersArrayList.add(order);
        numberOfOrders++;
        int productId = 0;
        int productCount = 0;
        while (true) {
            System.out.println("Введите ID товара и количество через пробел, end для завершения");
            String inputString = scanner.nextLine();
            if (!inputString.equals("end")) {
                String[] parts = inputString.split(" ");
                try {
                    productId = Integer.parseInt(parts[0]);
                    productCount = Integer.parseInt(parts[1]);
                } catch (Exception e) {
                    System.out.println("Внимание! Введите два числа, через пробел");
                    continue;
                }
                int[] productPosition = new int[productParams];
                productPosition[0] = productId;
                productPosition[1] = productCount;
                if (!Goods.FindGoods(productId)) {
                    System.out.println("Нет такого товара");
                    continue;
                }
                if (order.goodsOfOrder.isEmpty()) {
                    System.out.println("Добавлен первый товар");
                    order.goodsOfOrder.add(productPosition);
                    order.orderTotalCost = order.orderTotalCost + Goods.getGoodsPrice(productId) * productCount;
                    continue;
                }
                if (!Orders.FindProductInOrder(order.orderId, productId)) {
                    System.out.println("Добавлен новый товар");
                    order.goodsOfOrder.add(productPosition);
                    order.orderTotalCost = order.orderTotalCost + Goods.getGoodsPrice(productId) * productCount;
                    continue;
                }
                if (Orders.FindProductInOrder(order.orderId, productId)) {
                    for (int j = 0; j < order.goodsOfOrder.size(); j++) {
                        int[] prod = order.goodsOfOrder.get(j);
                        int id = prod[goodsIdPosition]; //     Magics вместо числа - переменная.
                        int quant = prod[goodsQuantPosition];
                        if (id == productId) {
                            prod[goodsQuantPosition] = quant + productCount;
                            order.goodsOfOrder.set(j, prod);
                            System.out.println("Увеличили количество товара.");
                            order.orderTotalCost = order.orderTotalCost + Goods.getGoodsPrice(productId) * productCount;
                        }
                    }
                }
            } else {
                Orders.PrintOrder(order.orderId);
                break;
            }
        }
    }

    public static void RepeatOrder(int orderId) {
        Orders order = FindOrder(orderId);
        Orders newOrder = new Orders();
        newOrder.orderId = orderIdCurrent;
        orderIdCurrent++;
        numberOfOrders++;
        newOrder.clientId = order.clientId;
        newOrder.goodsOfOrder = order.goodsOfOrder;
        ordersArrayList.add(newOrder);
    }

    public static void PrintOrder(int orderId) {
        int totalCost = 0;
        for (Orders order : ordersArrayList) {
            if (order.orderId == orderId) {
                System.out.println("ВАШ ЗАКАЗ");
                System.out.println("Id заказа = " + order.orderId);
                PrintGoodsOfOrder(order);
                System.out.println("СТОИМОСТЬ ЗАКАЗА = " + order.orderTotalCost);
            }
        }
    }

    public static void ProcessingOrder(int orderId) {
        Orders order = FindOrder(orderId);
        //StatusOfOrder statusOfOrder;
        //statusOfOrder = DELIVERY;
        int paidLimit = 10000;
        int assemblyLimit = 15000;
        int deliveryLimit = 10000;
        int readyLimit = 4000;


        assert order != null;
        long delta = System.currentTimeMillis() - order.orderDate;
        System.out.println("delta = " + delta);
        //System.out.println(statusOfOrder);

        if (delta < paidLimit) {
            order.orderStatus = PAID.toString();
            System.out.println("Заказ № " + orderId + " в статусе " + order.orderStatus);
        }

        if (delta < paidLimit + assemblyLimit && delta > paidLimit) {
            order.orderStatus = StatusOfOrder.ASSEMBLY.toString();
            System.out.println("Заказ № " + orderId + " в статусе " + order.orderStatus);
        }
        if (delta < paidLimit + assemblyLimit && delta < paidLimit + assemblyLimit + deliveryLimit) {
            order.orderStatus = StatusOfOrder.DELIVERY.toString();
            System.out.println("Заказ № " + orderId + " в статусе " + order.orderStatus);
        }

        if (delta > paidLimit + assemblyLimit + deliveryLimit && delta < paidLimit + assemblyLimit + deliveryLimit + readyLimit) {
            order.orderStatus = StatusOfOrder.CHECK.toString();
            System.out.println("Заказ № " + orderId + " в статусе " + order.orderStatus);
        }
        if (delta > paidLimit + assemblyLimit + deliveryLimit + readyLimit) {
            order.orderStatus = StatusOfOrder.READY.toString();
            System.out.println("Заказ № " + orderId + " в статусе " + order.orderStatus);
        }
    }

    public static @Nullable Orders FindOrder(int orderId) {
        for (Orders order : ordersArrayList) {
            if (order.orderId == orderId) {
                return order;
            }
        }
        System.out.println("Заказ " + orderId + " не найден");
        return null;
    }

    public static boolean FindProductInOrder(int orderId, int goodsId) {
        Orders order = FindOrder(orderId);
        for (int i = 0; i < order.goodsOfOrder.size(); i++) {
            int[] goods = order.goodsOfOrder.get(i);
            if (goods[goodsIdPosition] == goodsId) {
                return true;
            }
        }
        return false;
    }


    public static void RemoveOrder(int orderId) {
        Orders order = Orders.FindOrder(orderId);
        ordersArrayList.remove(order);
    }

    public static void PrintClientOrders(int clientId) {
        System.out.println("ВОТ ВАШИ ЗАКАЗЫ " + Clients.GetClient(clientId).getName() + " !");
        for (Orders order : ordersArrayList) {
            if (order.clientId == clientId) {
                Orders.PrintOrder(order.orderId);
            }
        }
    }

    private static void PrintGoodsOfOrder(@NotNull Orders order) {
        int totalCost = 0;
        for (int i = 0; i < order.goodsOfOrder.size(); i++) {
            for (int j = 0; j < productParams - 1; j++) {
                int[] buffer = order.goodsOfOrder.get(i);
                int price = getGoodsPrice(buffer[j]);
                int cost = price * buffer[j + 1];
                totalCost += cost;
                System.out.println("Id Товара = " + buffer[j] + ";"
                        + Goods.getGoodsName(buffer[j]) + ";"
                        + "Количество = " + buffer[j + 1] + ";"
                        + "Цена = " + price + ";"
                        + "Стоимость = " + cost);
            }
        }
    }

    @Override
    public String toString() {

        return super.toString();
    }
}




