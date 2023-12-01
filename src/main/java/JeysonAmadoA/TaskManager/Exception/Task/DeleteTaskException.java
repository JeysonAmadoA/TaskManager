package JeysonAmadoA.TaskManager.Exception.Task;

public class DeleteTaskException extends Exception {

    public DeleteTaskException(String error) {
        super("Error al eliminar la tarea. " + error);
    }

}
