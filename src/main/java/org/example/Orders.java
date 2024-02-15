package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Orders {
    private static int numberOfOrders;
    private static int orderIdCurrent;
    private static int productParams = 2;
    private static String [] parts;

    private static Scanner scanner = new Scanner(System.in);
    private int orderId;
    private long orderDate;
    private int clientId;
    private String orderStatus;


   private ArrayList <int []> goodsOfOrder = new ArrayList<>();
   private static final ArrayList<Orders> ordersArrayList = new ArrayList<>();



    public static void AddOrder (int clientId) {
        Orders order = new Orders();
        order.orderId = orderIdCurrent;
        orderIdCurrent++;
        order.clientId = clientId;
        order.orderDate = System.currentTimeMillis();
        order.orderStatus = "Заказ размещен";
        ordersArrayList.add(order);
        numberOfOrders ++;
        ArrayList <int []> orderedGoods = new ArrayList<>();
            int i = 0;
        while (true) {
            System.out.println("Введите ID товара и количество через пробел, end для завершения");
            String inputString = scanner.nextLine();
            if (!inputString.equals("end")) {
                parts = inputString.split(" ");
                int productId = Integer.parseInt(parts[0]);
                int productCount = Integer.parseInt(parts[1]);
                int [] productPosition = new int[productParams];
                productPosition[0] = productId;
                productPosition[1] = productCount;
                Goods.ChangeOrdered(productId,productCount,true);
                System.out.println("i = " + i);
                orderedGoods.add(i,productPosition);
                for (int j = 0; j < orderedGoods.size(); j++) {
                    for (int k = 0; k < productPosition.length; k++) {
                        int [] buffer = orderedGoods.get(j);
                        System.out.print(buffer[k] + ",");
                    }
                    System.out.println();
                }
                i++;
            } else {
                for (int j = 0; j < orderedGoods.size(); j++) {
                    order.goodsOfOrder.add(orderedGoods.get(j));
                }
                orderedGoods.clear();
                Orders.PrintOrder(order.orderId);
                break;
            }
        }
    }

    public static void PrintOrder(int orderId){
        int totalCost = 0;
        for (Orders order : ordersArrayList) {
            if (order.orderId == orderId) {
                System.out.println("ВАШ ЗАКАЗ");
                System.out.println("Id заказа = " + order.orderId);
                for (int i = 0; i < order.goodsOfOrder.size(); i++) {
                    for (int j = 0; j < productParams - 1; j++) {
                        int buffer[] = order.goodsOfOrder.get(i);
                        int price = Goods.getGoodsPrice(buffer[j]);
                        int cost = price * buffer[j + 1];
                        totalCost += cost;
                        System.out.println("Id Товара = " + buffer[j] + ";"
                                + Goods.getGoodsName(buffer[j]) + ";"
                                + "Количество = " + buffer[j + 1] + ";"
                                + "Цена = " + price + ";"
                                + "Стоимость = " + cost);
                    }
                }
                System.out.println("СТОИМОСТЬ ЗАКАЗА = " + totalCost);
            }
        }
    }

    public static void ProcessingOrder (int orderId){
        Orders order = FindOrder(orderId);
        int paidLimit = 10000;
        int assemblyLimit = 10000;
        int deliveryLimit = 5000;



        long delta =  System.currentTimeMillis() - order.orderDate;
        if (delta < paidLimit) {
            order.orderStatus = StatusOfOrder.PAID.toString();
            System.out.println("Заказ № " + orderId + "в статусе " + order.orderStatus);
        }

        if (delta < 10000) {
            order.orderStatus = StatusOfOrder.PAID.toString();
            System.out.println("Заказ № " + orderId + "в статусе " + order.orderStatus);
        }
        if (delta < 10000) {
            order.orderStatus = StatusOfOrder.PAID.toString();
            System.out.println("Заказ № " + orderId + "в статусе " + order.orderStatus);
        }



        switch ((int) (System.currentTimeMillis() - order.orderDate)){
            case (10000):
                order.orderStatus = StatusOfOrder.PAID.toString();
                System.out.println("Заказ № " + orderId + "в статусе " + order.orderStatus);
                break;
            case (20000):
                order.orderStatus = StatusOfOrder.ASSEMBLY.toString();
                System.out.println("Заказ № " + orderId + "в статусе " + order.orderStatus);
                break;
            case (45000):
                order.orderStatus = StatusOfOrder.DELIVERY.toString();
                System.out.println("Заказ № " + orderId + "в статусе " + order.orderStatus);
                break;
            case (60000):
                order.orderStatus = StatusOfOrder.READY.toString();
                System.out.println("Заказ № " + orderId + "в статусе " + order.orderStatus);
                break;

        }
    }

public static Orders FindOrder (int orderId){
    for (Orders order : ordersArrayList) {
        if (order.orderId == orderId){
            return order;
        }
    }
    System.out.println("Заказ " + orderId + " не найден");
    return null;
}


}


