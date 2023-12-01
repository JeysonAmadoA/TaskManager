package JeysonAmadoA.TaskManager.Exception.Task;

public class UpdateTaskException extends Exception {

    public UpdateTaskException(String error) {
        super("Error al actualizar la tarea. " + error);
    }

}