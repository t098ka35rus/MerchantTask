package org.example;

import java.util.ArrayList;

public class Goods {
    private static final ArrayList<Goods> goodsArrayList = new ArrayList<>();
    private static int numberOfgoods;
    private static int goodsIdCurrent;
    private int goodsId;
    private int goodsPrice;
    private int ordered;
    private String goodsName;
    private String vendor;
    private boolean forsale;


    private Goods() {
    }

    public static int getNumberOfgoods() {
        return numberOfgoods;
    }

    public static void SoldOff(int goodsId) {
        for (Goods goods : goodsArrayList) {
            if (goods.goodsId == goodsId) {
                goods.forsale = false;
            }
        }
    }

    public static void AddGood(String name, int price, String vendor) {
        Goods goods = new Goods();
        goods.forsale = true;
        goods.goodsName = name;
        goods.goodsPrice = price;
        goods.vendor = vendor;
        numberOfgoods++;
        goods.goodsId = goodsIdCurrent;
        goodsIdCurrent++;
        goodsArrayList.add(goods);
    }

    public static void RemoveGood(int goodsId) {
        for (int i = 0; i < goodsArrayList.size(); i++) {
            if (goodsArrayList.get(i).goodsId == goodsId) {
                goodsArrayList.remove(goodsArrayList.get(i));
            }
        }
    }

    public static void GetGoodsByName(String keyword) {
        if (keyword.isEmpty()) {
            System.out.println("keyword is empty");
        } else {

            for (Goods goods : goodsArrayList) {
                if (goods.goodsName.contains(keyword)) {
                    System.out.println(goods);
                }
            }
        }
    }

    public static void GetGoodsByPrice(int goodsPrice) {
        for (Goods goods : goodsArrayList) {
            if (goods.goodsPrice == goodsPrice) {
                System.out.println(goods);
            }
        }
    }

    public static void GetGoodsByVendor(String vendor) {
        if (vendor.isEmpty()) {
            System.out.println("keyword is empty");
        } else {

            for (Goods goods : goodsArrayList) {
                if (goods.vendor.equals(vendor)) {
                    System.out.println(goods);
                }
            }
        }
    }

    public static void PrintAllGoods() {
        for (Goods goods : goodsArrayList) {
            System.out.println("Id = " + goods.goodsId +
                    ";" + goods.goodsName +
                    ";" + goods.goodsPrice +
                    ";" + goods.vendor +
                    "; ForSale = " + goods.forsale);
        }
    }

    public static void PrintForSaleGoods() {
        for (Goods goods : goodsArrayList) {
            if (goods.forsale) {
                System.out.println(goods);
            }
        }
    }

    public static boolean FindGoods (int goodsId) {
        for (Goods goods : goodsArrayList) {
            if (goodsId == goods.goodsId) {
                return true;
            }
        }
        return false;
    }

    public static void ChangeOrdered(int goodsId, int quantityOfgoods, boolean oper) {

        for (Goods goods : goodsArrayList) {
            if (goods.goodsId == goodsId && oper) {
                goods.ordered = goods.ordered + quantityOfgoods;
            }
            if (goods.goodsId == goodsId && !oper) {
                goods.ordered = goods.ordered - quantityOfgoods;
            }

        }
    }

    public static String getGoodsName(int goodsId) {
        for (Goods goods : goodsArrayList) {
            if (goods.goodsId == goodsId) {
                return goods.goodsName;
            }
        }
        return null;
    }

    public static int getGoodsPrice(int goodsId) {
        for (Goods goods : goodsArrayList) {
            if (goods.goodsId == goodsId) {
                return goods.goodsPrice;
            }
        }
        return 7777777;
    }

    public String getVendor() {
        return vendor;
    }

    public int getGoodsId() {
        return goodsId;
    }


    @Override
    public String toString() {
        System.out.print("Id = " + goodsId + "; Name = " + goodsName +
                "; Price = " + goodsPrice + "; Vendor =  " + vendor + "; ForSale =  " + forsale + "; Ordered = " + ordered + "; Object = ");
        return super.toString();
    }
}


