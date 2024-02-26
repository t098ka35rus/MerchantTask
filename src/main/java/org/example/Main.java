package org.example;


public class Main {
    public static void main(String[] args) throws Throwable  {
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
        Orders.AddOrder(1);





    }

}