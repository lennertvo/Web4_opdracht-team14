package domain.model;

public enum Status {
    ONLINE         ("online"),
    OFFLINE        ("offline"),
    AWAY           ("away"),
    BUSY           ("busy"),
    TAKING_A_CLASS ("Taking a class");

    private String description;
    Status(String description) {
        this.description = description;
    }

    Status() {}

    public String getDescription() {
        return description;
    }
}
