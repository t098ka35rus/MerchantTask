package org.example;

import java.util.Scanner;

public class InputHolder {
    private static Scanner sc = new Scanner(System.in);
    private static int end = 777;
    private final InputHolder inputHolder = new InputHolder();

    public static void main(String[] args) {
        int a = GetInt();
        System.out.println("a = " + a);

    }


    public static int GetInt() {
        if (sc.hasNext("end")){
            sc.skip(".*\\n");
            return end;
        }
        while (true) {
            //System.out.println("Число: ");
            if (sc.hasNextInt())
                break;
            System.out.println("Не получилось!");
            sc.skip(".*\\n");
        }
        return sc.nextInt();
    }

    public static int GetIntWithPrompt(String prompt, int clientId) { // Чтобы не повторять вывод заказов
        Orders.PrintClientOrders(clientId);                           // и скан номера заказа создал класс
        System.out.print(prompt);                                     // обрабочик ввода. DRY
        int i = GetInt();
        return i;
    }

    public static String GetString() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        return s;
    }

    public static void SkipSc (){
        sc.skip(".*\\n");
    }
}


