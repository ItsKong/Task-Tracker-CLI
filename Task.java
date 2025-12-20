import java.time.LocalTime;
import java.time.LocalDate;

public class Task {
   private int id;
   private String description;
   private String status;
   private String createdAt;
   private String updatedAt;

   public Task(int id, String description) {
      this.id = id;
      this.description = description;
      this.status = Status.TODO.getStatus();
      this.createdAt = getCurrentDateTime();
      this.updatedAt = this.createdAt;
   }

   public String getTaskJSON() {
      return "{\"id\":" + id + ", \"description\":\"" + description.strip() + "\", \"status\":\"" + status.toString() +
                "\", \"createdAt\":\"" + createdAt + "\", \"updatedAt\":\"" + updatedAt + "\"}";
   }

   public static Task loadFromJSON(String taskStr) {
      String[] splitField = taskStr.replace("{", "")
                              .replace("\"", "")
                              .replace("}", "")
                              .split(",");
      String id = splitField[0].split(":", 2)[1].trim();
      String description = splitField[1].split(":", 2)[1].trim();
      String status = splitField[2].split(":", 2)[1].trim();
      String createAt = splitField[3].split(":", 2)[1].trim();
      String updateAt = splitField[4].split(":", 2)[1].trim();

      Task task = new Task(Integer.parseInt(id), description);
      task.status = status;
      task.createdAt = createAt;
      task.updatedAt = updateAt;

      return task;
   }

   private String getCurrentDateTime() {
      return LocalDate.now() + " " + LocalTime.now().toString().substring(0, 9);
   }

   public int getID() {
      return this.id;
   }

   public String getStatus() {
      return this.status;
   }

   public Task setStatus(String status) {
      this.status = status;
      return this;
   }

   public Task setDescription(String des) {
      this.description = des;
      return this;
   }

   public Task setUpdateAt() {
      this.updatedAt = getCurrentDateTime();
      return this;
   }
}
