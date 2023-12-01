package JeysonAmadoA.TaskManager.Exception.Task;

public class CreateTaskException extends Exception {

    public CreateTaskException(String error) {
        super("Error al crear la tarea. " + error);
    }

}
