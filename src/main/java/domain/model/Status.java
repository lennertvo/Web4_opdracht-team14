package domain.model;

public enum Status {
    ONLINE         ("Online"),
    OFFLINE        ("Offline"),
    AWAY           ("Away"),
    BUSY           ("Busy"),
    TAKING_A_CLASS ("Taking a class");

    public String description;
    private Status(String description) {
        this.description = description;
    }



    public String getDescription() {
        return description;
    }
}
