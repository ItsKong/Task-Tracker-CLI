public class FileEditor {
    private static FileEditor instance;
    private String[] argument;
    
    public FileEditor(String[] args) {
        this.argument = args.clone();     
    }

    public static FileEditor getInstance(String[] args) {
        if (FileEditor.instance != null) {
            return new FileEditor(args);
        } else {
            return instance;
        }
    }

    public void addTask() {
        for (int i=0; i<argument.length; i++) {
            System.out.print(argument[i] + " ");
        }
    }
}
