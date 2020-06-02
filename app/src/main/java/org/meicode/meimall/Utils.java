package org.meicode.meimall;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static int ID = 0;

    private static final String ALL_ITEMS_KEY = "all_items";
    private static final String DB_NAME = "fake_database";
    private static Gson gson = new Gson();
    private static Type groceryListType = new TypeToken<ArrayList<GroceryItem>>(){}.getType();

    public static void initSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), groceryListType);
        if (null == allItems) {
            initAllItems(context);
        }
    }

    public static void clearSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    private static void initAllItems(Context context) {
        ArrayList<GroceryItem> allItems = new ArrayList<>();
        GroceryItem milk = new GroceryItem("Milk",
                "Milk is a nutrient-rich, white liquid food produced by the mammary glands of mammals. It is the primary source of nutrition for infant mammals before they are able to digest other types of food.",
                "https://www.psdmockups.com/wp-content/uploads/2019/06/1L-Tetra-Pak-Carton-Boxes-PSD-Mockup.jpg",
                "drink", 2.3, 8);
        allItems.add(milk);

        GroceryItem iceCream = new GroceryItem("Ice Cream", "Delicious",
                "https://anycustombox.com/images/ice-cream-box-02.jpg",
                "food", 5.4, 10);
        iceCream.setPopularityPoint(10);
        iceCream.setUserPoint(7);
        allItems.add(iceCream);

        GroceryItem soda = new GroceryItem("Soda", "Tasty",
                "https://cdn.diffords.com/contrib/bws/2019/05/5cc9b8261f976.jpg",
                "Drink", 0.99, 15);
        soda.setPopularityPoint(5);
        soda.setUserPoint(15);
        allItems.add(soda);

        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ALL_ITEMS_KEY, gson.toJson(allItems));
        editor.commit();
    }

    public static ArrayList<GroceryItem> getAllItems(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), groceryListType);
        return allItems;
    }

    public static int getID() {
        ID++;
        return ID;
    }
}
