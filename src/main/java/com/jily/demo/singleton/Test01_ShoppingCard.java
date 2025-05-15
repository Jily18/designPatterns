package com.jily.demo.singleton;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

//练习题：[单例模式-小明的购物车](https://kamacoder.com/problempage.php?pid=1074)
//题目描述
//小明去了一家大型商场，拿到了一个购物车，并开始购物。请你设计一个购物车管理器，记录商品添加到购物车的信息（商品名称和购买数量），并在购买结束后打印出商品清单。（在整个购物过程中，小明只有一个购物车实例存在）。
//输入描述
//输入包含若干行，每行包含两部分信息，分别是商品名称和购买数量。商品名称和购买数量之间用空格隔开。
//输出描述
//输出包含小明购物车中的所有商品及其购买数量。每行输出一种商品的信息，格式为 "商品名称 购买数量"。
//输入示例
//Apple 3
//Banana 2
//Orange 5
//输出示例
//Apple 3
//Banana 2
//Orange 5
//提示信息
//本道题目请使用单例设计模式：
//使用私有静态变量来保存购物车实例。
//使用私有构造函数防止外部直接实例化。
public class Test01_ShoppingCard {
    public static void main(String[] args){
        ShoppingCard card = ShoppingCard.getInstance();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String itemName = scanner.next();
            Integer quantity = scanner.nextInt();
            card.addToCard(itemName,quantity);
        }
        card.viewCart();
    }

}

class ShoppingCard{
    //饿汉式单例模式
    private static final ShoppingCard instance = new ShoppingCard();

    //购物车存储商品和数量的映射,不过有一个问题是，输出错误了，因为HashMap不保证顺序
    //HashMap的存储位置是由键的哈希值决定的，所以遍历顺序可能与插入顺序完全不同。
    private Map<String,Integer> cart;

    private ShoppingCard(){
//        cart = new HashMap<>();
        //用linked可以保持输出顺序
        //用treeMap还可以按照商品字母顺序排序
        cart = new LinkedHashMap<>();
    }

    public static ShoppingCard getInstance(){
        return instance;//获得购物车实例
    }

    public void addToCard(String name,int quantity){
        cart.put(name,quantity);
    }

    public void viewCart(){
        for(Map.Entry<String,Integer> entry:cart.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }

}
