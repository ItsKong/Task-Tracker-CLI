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

    public static TaskManager createInstance(String[] arguments) {
        if (instance == null) {
            return new TaskManager(arguments);
        } else {
            return instance;
        }
    }

    public static TaskManager getInstance() {
        return instance;
    }

    private int getValidID() {
        int i = 1;
        while(true){
            if(!tasks.containsKey(i)) break;
            i++;
        }
        return i;
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public void addTask() {
        int newID = getValidID();
        Task newTask = new Task(newID, arguments[1]);
        tasks.put(newID, newTask);
        fileEditor.writeJSON(tasks);
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
