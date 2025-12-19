public class TaskTracker {
    public static void main(String[] args) {
        TaskManager taskManager = TaskManager.createInstance(args);

        switch (args[0]) {
            case "add":
                taskManager.addTask();
                break;
            case "update":
                break;
            case "mark-in-progress":
                break;
            case "mark-done":
                break;
            case "delete":
                break;
            case "list":
                taskManager.list();
                break;
            default:
                break;
        }
    }
}