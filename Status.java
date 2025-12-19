public enum Status {
    TODO("todo"),
    INPROG("in-progress"),
    DONE("done");

    private String description;
   
    private Status(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.description;
    }
}
