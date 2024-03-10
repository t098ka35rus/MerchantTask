package org.example;

public class Main {
    private static int clientId;
    private static int end = 777;

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
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
            System.out.println();
            System.out.print("Введите ваше имя (либо exit для выхода): ");
            inputstring = InputHolder.GetString();
            if (inputstring.equals("exit")) {
                break;
            }
            if (Clients.isNewClient(inputstring)) {
                Clients.AddClient(inputstring);
                System.out.println("Вы наш новый клиент. Ваш Id = " + Clients.FindClient(inputstring).getClientId() + "!");
                clientId = Clients.FindClient(inputstring).getClientId();
                Goods.ListDiscount();
                CommonActions();

            } else {
                System.out.println("Добрый день Дружище " + Clients.FindClient(inputstring).getName() + "!");
                clientId = Clients.FindClient(inputstring).getClientId();
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
            System.out.print("ВАШ ВЫБОР? (end - выход): ");
            int choice = InputHolder.GetInt();
            if (choice == end) {
               InputHolder.SkipSc();
                break;
            }
            switch (choice) {
                case 1:
                    Goods.PrintForSaleGoods();
                    break;
                case 2: {
                    System.out.print("Введите название товара: ");
                    String name = InputHolder.GetString();
                    Goods.GetGoodsByName(name);
                    break;
                }
                case 3: {
                    int price = 0;
                     System.out.println("Введите цену товара");
                     price = InputHolder.GetInt();
                     if(price == end){
                         break;
                     }
                     Goods.GetGoodsByPrice(price);
                     break;
                }
                case 4: {
                    System.out.println("Введите название вендора");
                    String vendor = InputHolder.GetString();
                    Goods.GetGoodsByVendor(vendor);
                    break;
                }
                case 5: {
                    Orders.AddOrder(clientId);
                    break;
                }
                case 6: {
                    int orderId = InputHolder.GetIntWithPrompt("Чтобы повторить, введите номер заказа (end - выход):", clientId);
                    if (orderId == end) break;
                    Orders.RepeatOrder(orderId);
                    Orders.PrintClientOrders(clientId);
                    break;
                }
                case 7: {
                    int orderId = InputHolder.GetIntWithPrompt("Чтобы отменить, введите номер заказа (end - выход): ", clientId);
                    if (orderId == end) break;
                    Orders.RemoveOrder(orderId);
                    Orders.PrintClientOrders(clientId);
                    break;
                }
                case 8: {
                    int orderId = InputHolder.GetIntWithPrompt("Чтобы посмотреть, введите номер заказа (end - выход): ", clientId);
                    if (orderId == end) break;
                    Orders.ProcessingOrder(orderId);
                    break;

                }



            }

        }
    }


}