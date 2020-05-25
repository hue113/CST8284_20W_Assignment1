package cst8284.asgmt1.roomScheduler;

/* Course Name: CST8284 
 * Student Name: Pham Thanh Hue 
 * Class Name: 310
 * Date: 10 Feb 2020
 */
public class Activity {

    private String description, category;

    public Activity(String category, String description) {
        this.setDescription(description);
        this.setCategory(category);
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String toString() {
        return "Event: " + getCategory() + "\nDescription: " + getDescription()+"\n";
    }
}
