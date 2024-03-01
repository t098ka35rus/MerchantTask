package org.example;


import java.util.Scanner;

public class Main {
    private static int clientId;

    public static void main(String[] args) throws Throwable {
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
        System.out.println("Работаем с клиентом");
        while (true) {
            System.out.print("Введите ваше имя (либо exit для выхода): ");
            inputstring = scanner.nextLine();
            if (inputstring.equals("exit")) {
                break;
            }
            if (Clients.isNewClient(inputstring)) {
                Clients.AddClient(inputstring);
                Main.clientId = Clients.FindClient(inputstring).getClientId();
                System.out.println("Вы наш новый клиент. Ваш Id = " + clientId);
                CommonActions();


            } else {
                Main.clientId = Clients.FindClient(inputstring).getClientId();
                System.out.println("Добрый день Дружище " + Clients.FindClient(inputstring).getName() + "!");
                CommonActions();
            }


        }


    }

    public static void CommonActions() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите операцию");
            System.out.println("1 - Вывод доступных для покупки товаров");
            System.out.println("2 - Фильтрация товаров по ключевым словам");
            System.out.println("3 - Фильтрация товаров по ценам");
            System.out.println("4 - Фильтрация товаров по производителям");
            System.out.println("5 - Разместить заказ");
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
                case 5:
                    Orders.AddOrder(clientId);


            }
        }


    }

}