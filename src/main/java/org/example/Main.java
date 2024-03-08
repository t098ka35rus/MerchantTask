package org.example;


import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    private static int clientId;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputstring;
        System.out.println("Поехали!");
        System.out.println("Добавляем товары");
        Goods.AddGood("яблоки", 2, "турция");
        Goods.AddGood("апельсины", 5, "египет");
        Goods.AddGood("бананы", 1, "эквадор");
        Goods.AddGood("клюква", 4, "россия");
        Goods.AddGood("баклажаны", 3, "азербайджан");
        Goods.AddGood("апельсины", 7, "турция");
        Goods.PrintForSaleGoods();
        System.out.println("Устанавливаем скидки");
        Goods.SetDiscount(5, 15);
        Goods.SetDiscount(1, 20);
        System.out.println("Работаем с клиентом");
        while (true) {
            System.out.print("Введите ваше имя (либо exit для выхода): ");
            inputstring = scanner.nextLine();
            if (inputstring.equals("exit")) {
                break;
            }
            if (Clients.isNewClient(inputstring)) {
                Clients.AddClient(inputstring);
                System.out.println("Вы наш новый клиент. Ваш Id = " + clientId);
                Goods.ListDiscount();
                CommonActions();

            } else {
                System.out.println("Добрый день Дружище " + Clients.FindClient(inputstring).getName() + "!");
                Goods.ListDiscount();
                CommonActions();
            }
        }

    }

    public static void CommonActions() {

        while (true) {
            System.out.println("Выберите операцию");
            System.out.println("1 - Вывод доступных для покупки товаров");
            System.out.println("2 - Фильтрация товаров по ключевым словам");
            System.out.println("3 - Фильтрация товаров по ценам");
            System.out.println("4 - Фильтрация товаров по производителям");
            System.out.println("5 - Разместить заказ");
            System.out.println("6 - Повторить заказ");
            System.out.println("7 - Отменить заказ");
            System.out.println("8 - Статус заказа");
            System.out.println("Выход - end");

            String inputstring = scanner.nextLine();
            if (inputstring.equals("end")) {
                break;
            }
            int choice = Integer.parseInt(inputstring);
            switch (choice) {
                case 1:
                    Goods.PrintForSaleGoods();
                    break;
                case 2: {
                    System.out.print("Введите название товара: ");
                    String name = scanner.nextLine();
                    Goods.GetGoodsByName(name);
                    break;
                }
                case 3: {
                    System.out.println("Введите цену товара");
                    int price = Integer.parseInt(scanner.nextLine());
                    Goods.GetGoodsByPrice(price);
                    break;
                }
                case 4: {
                    System.out.println("Введите название вендора");
                    String vendor = scanner.nextLine();
                    Goods.GetGoodsByVendor(vendor);
                    break;
                }
                case 5: {
                    Orders.AddOrder(Main.clientId);
                    break;
                }
                case 6: {
                    Orders.PrintClientOrders(Main.clientId);
                    System.out.print("Чтобы повторить, введите номер заказа (end - выход): ");
                    String input = scanner.nextLine();
                    if (input.equals("end")) {
                        break;
                    }
                    int orderId = Integer.parseInt(input);
                    Orders.RepeatOrder(orderId);
                    Orders.PrintClientOrders(Main.clientId);
                    break;
                }
                case 7: {
                    Orders.PrintClientOrders(Main.clientId);
                    System.out.print("Чтобы отменить, введите номер заказа (end - выход): ");
                    int orderId = Integer.parseInt(scanner.nextLine());
                    if (scanner.nextLine().equals("end")) {
                        break;
                    }
                    Orders.RemoveOrder(orderId);
                    Orders.PrintClientOrders(Main.clientId);
                    break;
                }
                case 8: {
                    /*
                    Orders.PrintClientOrders(Main.clientId);
                    System.out.print("Чтобы посмотреть статус, введите номер заказа (end - выход): ");
                    String input = scanner.nextLine();
                    if (input.equals("end")) {
                        break;
                    }
                    int orderId = Integer.parseInt(input);
                    Orders.ProcessingOrder(orderId);
                    break;
                */
                    String input = InputHolder("Чтобы посмотреть статус, введите номер заказа (end - выход): ");

                    if (input.equals("end")) {
                        break;
                    }
                    System.out.println("!!! " + input);
                    int orderId = Integer.parseInt(input);
                    Orders.ProcessingOrder(orderId);
                    break;

                }
            }

        }
    }


    public static String InputHolder(String prompt) {
        Orders.PrintClientOrders(Main.clientId);
        System.out.print(prompt);
        if (scanner.hasNext("^\\d+$")) {
            String s = scanner.nextLine();
            System.out.println("!!! " + s);
            return s;
        }
        return "end";
    }
}