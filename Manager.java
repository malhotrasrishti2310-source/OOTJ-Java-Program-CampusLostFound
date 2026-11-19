
package campuslostfound;

import java.util.ArrayList;

public class Manager {

    private ArrayList<Item> items = new ArrayList<>();
    private int nextId = 1;

    public Manager() {
        addItem("Black Wallet", "Accessories",
                "Library", "Lost", "9876543210");

        addItem("Water Bottle", "Personal Items",
                "Cafeteria", "Found", "9876543211");

        addItem("Scientific Calculator", "Electronics",
                "Block A", "Lost", "9876543212");
    }

    public void addItem(String name, String category,
                        String location, String type, String contact) {

        Item item = new Item(nextId++, name, category,
                             location, type, contact);

        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Item> searchItems(String keyword) {
        ArrayList<Item> results = new ArrayList<>();

        for (Item item : items) {
            if (item.getName().toLowerCase().contains(keyword.toLowerCase())
                || item.getCategory().toLowerCase().contains(keyword.toLowerCase())
                || item.getLocation().toLowerCase().contains(keyword.toLowerCase())) {

                results.add(item);
            }
        }

        return results;
    }

    public int countType(String type) {
        int count = 0;

        for (Item item : items) {
            if (item.getType().equalsIgnoreCase(type)
                && !item.getStatus().equals("Claimed")) {
                count++;
            }
        }

        return count;
    }

    public int countClaimed() {
        int count = 0;

        for (Item item : items) {
            if (item.getStatus().equals("Claimed")) {
                count++;
            }
        }

        return count;
    }
}

