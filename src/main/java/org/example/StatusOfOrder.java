package org.example;

public enum StatusOfOrder {
    PAID("\"Оплачен\""),
    ASSEMBLY("\"Сборка заказа\""),
    DELIVERY("\"Доставка заказа\""),
    READY("\"Готов к выдаче\"");

    private final String title;

    StatusOfOrder(String title) {
        this.title = title;
    }

    public String toString() {
        return title;
    }
}


