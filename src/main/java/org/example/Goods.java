package org.example;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Locale;

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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    private int discount;


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

    public static void GetGoodsByName(@NotNull String keyword) {
        String lkeyword = keyword.toLowerCase(Locale.ROOT);
        if (keyword.isEmpty()) {
            System.out.println("keyword is empty");
        } else {
            for (Goods goods : goodsArrayList) {
                if (goods.goodsName.contains(lkeyword)) {
                    System.out.println(goods);
                }

            }

        }
    }

    public static void GetGoodsByPrice(int goodsPrice) {
        for (Goods goods : goodsArrayList) {
            if (goods.goodsPrice <= goodsPrice) {
                System.out.println(goods);
            }
        }
    }

    public static void GetGoodsByVendor(@NotNull String vendor) {
      String lv =vendor.toLowerCase(Locale.ROOT);
        if (vendor.isEmpty()) {
            System.out.println("keyword is empty");
        } else {

            for (Goods goods : goodsArrayList) {
                if (goods.vendor.contains(lv)) {
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

    public static boolean FindGoods(int goodsId) {
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

    @Contract(pure = true)
    public static @Nullable String getGoodsName(int goodsId) {
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

    public static void SetDiscount(int goodsId, int discount){
        for (Goods goods : goodsArrayList) {
            if (goods.goodsId == goodsId){
                goods.setDiscount(discount);
            }
        }
    }
    public static void ListDiscount(){
        System.out.println("КУПИТЕ ТОВАРЫ СО СКИДКОЙ!");
        for (Goods goods : goodsArrayList) {
            if (goods.discount > 0){
                System.out.println("Id = " + goods.goodsId
                        + "; Name = " + goods.goodsName
                        +"; Price = " + goods.goodsPrice
                        + "; Vendor =  " + goods.vendor
                        + "; Discount = " + goods.discount);
            }
        }
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
                "; Price = " + goodsPrice + "; Vendor =  " + vendor + "; ForSale =  " + forsale + "; Ordered = " + ordered + "; Discount = " + discount + "; Object = ");
        return super.toString();
    }
}


