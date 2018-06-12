package person.davino.dp.iterator.v1;

import person.davino.dp.iterator.MenuItem;

import java.util.List;

public class Client {
    public static void main(String[] args) {
        List<MenuItem> items = new PancakeHouseMune().getMuneItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }

        MenuItem[] menuItems = new DinerMenu().getMuneItems();
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println(menuItems[i]);
        }
    }
}
