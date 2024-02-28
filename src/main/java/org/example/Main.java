package org.example;


import java.util.Scanner;

public class Main {

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
                System.out.println("Вы наш новый клиент. Ваш Id = " + Clients.FindClient(inputstring).getClientId());
            } else {
                System.out.println("Добрый день Дружище " + Clients.FindClient(inputstring).getName() + "!");
            }

        }


    }

}