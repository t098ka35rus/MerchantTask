package org.example;

public enum StatusOfOrder {
    PAID ("Оплачен"),
    ASSEMBLY ("Сбока заказа"),
    DELIVERY ("Доставка заказа"),
    READY ("Готов к выдаче");

    private String title;

    StatusOfOrder(String title) {
    }

    public String toString() {
        return title;
    }
}


