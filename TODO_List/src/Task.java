/**
       *Ali Şer Gök
       *ID: 2022719075
       *Date: 08/01/2023
 *
 * This class represents a task in a to-do list. Each task has a unique ID, a priority level, and a description.
 * The taskCounter variable is used to automatically assign a unique ID to each new task.
 * The setTaskCounter method updates the taskCounter value when the list is read and written again.
 * The setTaskID method allows each new object created when the input.txt file is read to take its original Task ID number.
 * The getTaskID method is a getter for the task ID.
 * The toString method returns a string representation of all information about a Task object.
 */
public class Task {
    private static int taskCounter=100;

    private int taskID;
    private int priority;
    private String explanation;

    /**
     * Constructor to create an empty task.
     */
    public Task(){}

    /**
     * A constructor that creates a Task object by taking the task description and task priority number.
     *
     * @param priority The task priority number is entered by the user
     * @param explanation is the description entered by the user
     */
    public Task(int priority,String explanation){
        this.priority=priority;
        this.explanation=explanation;
        taskID=taskCounter++;    // Since taskCounter is static, the taskID is automatically determined every time a new task is entered.
    }

    /**
     * This method updates the static taskCounter value every time the list is read and written again. This way, when a new task is added,
     * the task ID does not start from 100, but rather is updated based on the ID number at the end of the list.
     *
     * @param taskID Original ID number of each task pulled from file
     */
    public void setTaskCounter(int taskID){
        taskCounter=taskID;
    }

    /**
     * A setter method that allows each new object created when input.txt is read to take its original Task ID number.
     *
     * @param id The original Task ID number of each element read from the input.txt file.
     */
    public void setTaskID(int id){
        this.taskID=id;
    }

    /**
     * A getter method for task ID.
     *
     * @return returning integer value of task ID;
     */
    public int getTaskID(){
        return this.taskID;
    }

    /**
     * A method to print all information about a Task object.
     *
     * @return returning string value of information about a Task object.
     */
    public String toString(){
        return "Task ID="+this.taskID+", Priority="+this.priority+", "+this.explanation;
    }
}
