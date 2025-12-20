import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private static TaskManager instance;
    // private ArrayList<Task> tasks = new ArrayList<Task>();
    private HashMap<Integer, Task> tasks;
    private String[] arguments;
    private FileEditor fileEditor;
    
    public TaskManager(String[] arguments) {
        this.fileEditor = new FileEditor();
        this.arguments = arguments;
        setTasks(this.fileEditor.checkIfFileExit());
    }

    public static TaskManager getInstance(String[] arguments) {
        if (instance == null) {
            return new TaskManager(arguments);
        } else {
            return instance;
        }
    }

    private int getValidID() {
        int i = 1;
        while(true){
            if(!tasks.containsKey(i)) break;
            i++;
        }
        return i;
    }

    public void addTask() {
        int newID = getValidID();
        Task newTask = new Task(newID, arguments[1]);
        tasks.put(newID, newTask);
        fileEditor.writeJSON(tasks);
    }

    public void updateTask() {
        if (arguments.length <= 1) {
            System.err.println("Please enter id");
            return;
        }
        if (!isValidID(arguments[1])) return;
        try {
            tasks.get(Integer.parseInt(arguments[1])).setDescription(arguments[2]).setUpdateAt();
            fileEditor.writeJSON(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void markStatus() {
        if (!(arguments.length > 1)) {
            System.err.println("Please enter ID.");
            return;
        }
        if (!isValidID(arguments[1])) return;
        switch (arguments[0]) {
            case "mark-in-progress":
                setTaskStatusbyID(Status.INPROG.getStatus());
                break;
            case "mark-done":
                setTaskStatusbyID(Status.DONE.getStatus());
                break;
            default:
                System.err.println("Please enter correct command.");
                break;
        }
    }

    public void deleteTask() {
        if (arguments.length <= 1) {
            System.err.println("Please enter id");
            return;
        }
        if (!isValidID(arguments[1])) return;
        try {
            tasks.remove(Integer.parseInt(arguments[1]));
            fileEditor.writeJSON(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void list() {
        if(arguments.length > 1) {
            switch (arguments[1]) {
                case "done":
                    getTaskByStatus(Status.DONE.getStatus());
                    break;
                case "todo":
                    getTaskByStatus(Status.TODO.getStatus());
                    break;
                case "in-progress":
                    getTaskByStatus(Status.INPROG.getStatus());
                    break;
                default:
                    System.out.println("Please enter the following command [done, todo, in-progress] only!");
                    break;
            }
        } else {
            for(Map.Entry<Integer,Task> entry : tasks.entrySet()) {
                System.err.println(entry.getValue().getTaskJSON());
            }
        }
    }

    private boolean isValidID(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid ID.");
            return false;
        }
    }

    private void setTaskStatusbyID(String status) {
        try {
            this.tasks.get(Integer.parseInt(arguments[1])).setStatus(status).setUpdateAt();
            fileEditor.writeJSON(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private void getTaskByStatus(String status) {
        for(Map.Entry<Integer,Task> entry : tasks.entrySet()) {
            if(entry.getValue().getStatus().equals(status)) {
                System.err.println(entry.getValue().getTaskJSON());
            }
        } 
    }

    private void setTasks(HashMap<Integer, Task> task) {
        this.tasks = task;
    }
}
