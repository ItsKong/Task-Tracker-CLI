public class TaskTracker {
    public static void main(String[] args) {
        TaskManager taskManager = TaskManager.getInstance(args);
        if (args.length < 1) {
            System.out.println("Please enter command.");
            return;
        }
        switch (args[0]) {
            case "add":
                taskManager.addTask();
                break;
            case "update":
                taskManager.updateTask();
                break;
            case "mark-in-progress":
                taskManager.markStatus();
                break;
            case "mark-done":
                taskManager.markStatus();
                break;
            case "delete":
                taskManager.deleteTask();
                break;
            case "list":
                taskManager.list();
                break;
            default:
                System.err.println("Please enter correct command.");
                break;
        }
    }
}