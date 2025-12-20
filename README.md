# Task-Tracker-CLI
Simple command line interface task tracking program from [task-tracker](https://roadmap.sh/projects/task-tracker).

# How to run
Clone the project.
```bash
git clone https://github.com/ItsKong/Task-Tracker-CLI.git
cd Task-Tracker-CLI
```
Build project using javac.
```bash 
javac TaskTracker.java -d ./output
cd output
```
The list of command:
```bash
# Adding Task
java TaskTracker add "Task 1"
# Output: Task added successfully (ID: 1)

# Update Task
java TaskTracker update 1 "Task 1 buy stuff"
# Output: Task updated successfully (ID: 1)

# Delete Task
java TaskTracker delete 1
# Output: Task deleted successfully (ID: 1)

# Mark Status
java TaskTracker mark-in-progress 1
java TaskTracker mark-done 1
# Output: Task marked successfully (ID: 1)

# List Task
java TaskTracker list

# List Task by status
java TaskTracker list todo
java TaskTracker list in-progress
java TaskTracker list done
```