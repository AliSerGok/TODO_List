/**
      *Ali Şer Gök
      *ID: 2022719075
      *Date: 08/01/2023
 *
 * ToDoList class definition and constructor
 * addTask method to add a new task to the task list and write it to the input file
 * removeTask method to delete a task from the task list and update the input file
 * search method to search for tasks containing a specific keyword in the task list
 * listTasks method to list all tasks in the task list
 */
import java.io.*;
import java.util.ArrayList;

public class ToDoList{

    private ArrayList<Task> tasks=new ArrayList<>(); //An ArrayList to store tasks in the to-do list.

    /**
     * Constructor to create an empty to-do list.
     */
    public ToDoList(){}

    /**
     * Method to set the task list.
     *
     * @param tasks ArrayList of 'Task' objects representing the new task list.
     *              The purpose of writing this setter method is to update the 'tasks'
     *              ArrayList that stores Task objects when the program is started.
     *              To do this, all elements found in the 'input.txt' file are read in the Main class
     *              and placed into a new ArrayList called 'newTasks'. This ArrayList is then passed
     *              as a parameter to the 'setTasks' method, and the 'tasks' ArrayList used by
     *              the main program is updated.
     */
    public void setTasks(ArrayList<Task> tasks){
        this.tasks=tasks;
    }

    /**
     * Method to add a new task to  the list.
     *
     * @param task With this method, the 'Task' object created with the user inputs in the main section
     *             is both written to the 'input.txt' file and added to the Task ArrayList.
     */
    public void addTask(Task task){
        this.tasks.add(task);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt", true))) {
            writer.write(task.toString());             //By setting the second parameter to true, we are able to append the string
            writer.newLine();                          // representation of the object being added to the end of the input.txt file.
            writer.close();                            // This allows us to avoid having to read the list from the beginning, creating
        } catch (IOException e) {                      // a new ArrayList, and writing it out again.
            e.printStackTrace();
        }

    }

    /**
     * This method finds and removes the task that the user wants to delete from the list.
     *
     * @param taskID  The user enters the ID number of the task he/she wants to delete
     *                and requests that it be deleted based on this number.
     *
     *                Here, we first need to read the entire input.txt file and add all the elements in
     *                the list to a Task ArrayList, because the delete operation needs to be performed
     *                based on the ID number of the object to be deleted. Then, the new, reduced list needs
     *                to be written back to the file.
     */
    public void removeTask(int taskID){

        String[] list;
        ArrayList<Task> newTasks =new ArrayList<>();
        ArrayList<String> list1=new ArrayList<>();

        /**
         * Since each element in the file is a string value, they are all added to a String ArrayList after
         * they are read. Then, since each element in this ArrayList contains the information for a Task object,
         * this information needs to be extracted and used to create new objects and store them in a new ArrayList.
         */

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                list1.add(line);
                line=reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Each value extracted from the ArrayList is first split into a list array based on the "," delimiter, so that
         * the last element of this array contains the task description. Then, the first element of this array is
         * split again based on the "=" delimiter and stored in the id array. This way, the second element of the id
         * array holds the task ID. Finally, the second element of the list array is split again based on the "=" delimiter
         * and stored in the priority array. The second element of this array holds the priority number. Then, we use the
         * information in these arrays to create new objects, and we add these objects to a new object ArrayList using a for loop.
         *
         */
        int pr;
        int iD;
        String str;

        for (int i=0;i<list1.size();i++){
        list= list1.get(i).split(",");
        String[] id=list[0].split("=");
        String[] priority=list[1].split("=");

        pr=Integer.parseInt(priority[1]);
        iD=Integer.parseInt(id[1]);
        str=list[2].trim();

        Task a=new Task(pr,str);
        a.setTaskID(iD);           //Since taskCounter is static, we need to use the setter method to write each
                                   // element's original ID value. This way, the original ID values of the Task objects are preserved.
        a.setTaskCounter(iD);      // We also update the static taskCounter variable according to the current object.

             /**
              * If we have reached the last element, we update the static taskCounter variable to be one more than
              * the ID of the last element. This way,the new Task object to be added can have this ID number (i.e., one
              * more than the ID of the last element).
              */
        newTasks.add(a);
            if(i==list1.size()-1){
                Task b=new Task();
                b.setTaskCounter(iD+1);

            }
        }

        int index = 0;
        for (int j = 0; j< newTasks.size(); j++){
            if (newTasks.get(j).getTaskID()==taskID){  //The element to be removed from the new ArrayList is found and deleted based on the taskID number.
                newTasks.remove(j);
                index++;
                break;
            }
        }

        tasks=newTasks;

        if (index != 0) {                              //If the element to be removed is found, its index number will have a non-zero value, and the user will
                                                       //be informed that the value has been found and deleted based on this.
            System.out.println("Task with ID: " + taskID + " is removed.");
        } else {
            System.out.println("Task with ID: " + taskID + " is not found in the Todo List");
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt"))) {
            for (Task t : newTasks) {
                writer.write(t.toString());             //The new list is written to the input.txt file from the beginning to the end again.
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  A method that reads the file from beginning to the end and prints all the data in the file to the console.
     */
    public void listTasks(){

        System.out.println("There are "+tasks.size()+" tasks in the Todo List:");
        System.out.println();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();

            while (line != null) {
                System.out.println(line+".");
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * This method reads the list from start to finish and splits each element into an array based on the ","
     * delimiter. Each element in this array is checked using a for loop. If the keyword is found in the array,
     * this element is printed to the console.
     *
     * @param keyword The keyword that the user is searching for.
     */
    public void search(String keyword){
       int counter=0;

       System.out.println("Search results for the keyword:"+keyword);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] array=line.split(" ");
                for (int j=0;j<array.length;j++){
                    if(array[j].trim().toLowerCase().equals(keyword.toLowerCase())){
                        System.out.print("\n"+line+".");
                        counter++;       //If the keyword is not found, the counter value will not change and
                                         // the user will be informed that the keyword was not found in any task descriptions.
                    }
                }
                line = reader.readLine();

            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(counter!=0){
            System.out.println("\nFound "+counter+" tasks in the Todo List");
        }

        if(counter==0){
            System.out.println("\nFound 0 tasks in the Todo List");
        }
        System.out.println();

    }


}









