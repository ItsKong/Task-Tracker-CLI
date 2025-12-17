public class TaskTracker {
    public static void main(String[] args) {
        FileEditor instance = FileEditor.getInstance(args);
        switch (args[0]) {
            case "add":
                instance.addTask();
                break;
            case "update":
                break;
            case "delete":
                break;
            case "list":
                break;
            default:
                break;
        }
    }
}