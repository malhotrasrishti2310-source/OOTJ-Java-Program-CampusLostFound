
package campuslostfound;

public class Item {
    private int id;
    private String name;
    private String category;
    private String location;
    private String type;
    private String status;
    private String contact;

    public Item(int id, String name, String category,
                String location, String type, String contact) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.location = location;
        this.type = type;
        this.contact = contact;
        this.status = "Pending";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getContact() {
        return contact;
    }

    public void markClaimed() {
        status = "Claimed";
    }
}
