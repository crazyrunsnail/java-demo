package person.davino.dp.iterator.v1;

import person.davino.dp.iterator.MenuItem;

import java.util.ArrayList;

public class DinerMenu {
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[]  muneItems;

    public DinerMenu() {


        addItem("K&Bs Pancake Breakfast",
                "Pancakes with scrambled eggs, and toast", true,
                2.99);
        addItem("Regular Pancake Breakfast", "Pancakes with fried eggs, sausage", false,
                2.99);
        addItem("Blueberry Pancakes",
                "Pancakes made with fresh blueberries", true,
                3.49);
                addItem("Waffles",
                        "Waffles, with your choice of blueberries or strawberries", true,
                        3.59);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        if (muneItems.length > MAX_ITEMS) {
            System.err.print("");
        } else {
            muneItems[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

    public MenuItem[] getMuneItems() {
        return muneItems;
    }
}
