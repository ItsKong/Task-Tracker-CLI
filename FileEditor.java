import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FileEditor {
    private static FileEditor instance;
    private final Path FILE_PATH = Path.of("tasklists.json");

    public static FileEditor getInstance() {
        if (instance == null) {
            return new FileEditor();
        } else {
            return instance;
        }
    }

    public HashMap<Integer, Task> checkIfFileExit() {
        if(Files.exists(FILE_PATH)) {
            try {
                String jsonContent = Files.readString(FILE_PATH);
                HashMap<Integer, Task> tasks = new HashMap<>();
                String[] taskList = jsonContent.replace("[", "")
                                            .replace("]", "")
                                            .replace("\t", "")
                                            .replace("\n", "")
                                            .split("},");
                for (String taskStr : taskList) {
                    // System.err.println(taskStr);
                    Task loadTask = Task.loadFromJSON(taskStr);
                    tasks.put(loadTask.getID(), loadTask);
                    // System.err.println(loadTask.getTaskJSON());
                }
                return tasks; 
            } catch (Exception e) {
                e.printStackTrace();
            } 
            return new HashMap<>();
        } else {
            return new HashMap<>();
        }
    }

    public void writeJSON(HashMap<Integer, Task> tasks) {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<Integer, Task>> iterator = tasks.entrySet().iterator();
        sb.append("[\n");
        while(iterator.hasNext()) {
            Map.Entry<Integer, Task> task = iterator.next();
            sb.append("\t" + task.getValue().getTaskJSON());
            if(!iterator.hasNext()) {
                sb.append("\n");
            } else {
                sb.append(",\n");
            }
        }
        sb.append("]");

        String jsonContent = sb.toString();
        try {
            Files.writeString(FILE_PATH, jsonContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
