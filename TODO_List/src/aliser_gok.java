/**
        *Ali Şer Gök
        *ID: 2022719075
        *Date: 08/01/2023
 *
 * A program is written that reads task data from an "input.txt" file and converts this data into Task objects.
 * A program is written that reads input from the user and has a menu-based user interface. This interface
 *              includes many options for the user to list their tasks, add new tasks, delete existing tasks,
 *              search tasks, and exit the program.
 *
 * The ToDoList class includes methods to add, delete, and search tasks, as well as to list all tasks.
 *
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class aliser_gok {
    public static void main(String[] args) {

        String[] list;
        ArrayList<Task> newTasks =new ArrayList<>();
        ArrayList<String> list1=new ArrayList<>();
        int fileControl=0;
/**
 * This loop reads all elements in the 'input.txt' file and adds them to the 'list1' ArrayList. This way, we can use the
 * information on each line for various operations. The 'fileControl' variable is used to check if the file is empty
 * while reading the file. If the file is empty, the 'fileControl' variable is assigned 1 and there is no need for file
 * operations in later processes.
 */
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line = reader.readLine();
            if(line==null){
                fileControl=1;
            }
            while (line != null) {
                list1.add(line);
                line=reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int pr;
        int iD;
        String str;
/**
 * The above while loop stores the data read from the 'input.txt' file in the 'list1' ArrayList. Then, a for loop is created
 * for all elements in the 'list1' ArrayList. Inside the loop, each element is split using the 'split' method based on commas.
 * The obtained pieces are then assigned as the properties 'id', 'priority', and 'description' of the 'Task' object in that
 * order, and the 'Task' object is created. This 'Task' object is then added to the 'newTasks' ArrayList.
 *
 * If the 'input.txt' file is empty, the Reader method assigns a value of 1 to the 'fileControl' variable and the for loop
 * that creates the object is not run at all if this value is 1.
 */
        if(fileControl==0) {
            for (int i = 0; i < list1.size(); i++) {
                list = list1.get(i).split(",");
                if(list.length==3){

                    String[] id = list[0].split("=");
                    String[] priority = list[1].split("=");

                    pr = Integer.parseInt(priority[1]);
                    iD = Integer.parseInt(id[1]);
                    str = list[2];

                    Task a = new Task(pr, str);
                    a.setTaskID(iD);
                    a.setTaskCounter(iD);

                    newTasks.add(a);

                }
                else {
                    continue;
                }

                /**
                 * If we have reached the last element, we update the static taskCounter variable to be one more than
                 * the ID of the last element. This way,the new Task object to be added can have this ID number (i.e., one
                 more than the ID of the last element).
                 */

                if (i == list1.size() - 1) {
                    Task b = new Task();
                    b.setTaskCounter(iD + 1);

                }
            }
        }

        ToDoList toDo =new ToDoList();
        toDo.setTasks(newTasks);  //If the file is not empty, first, a new Task ArrayList is created and the original Task ArrayList is updated.


        Scanner input=new Scanner(System.in);

        boolean key=true;

        while (key==true){
            System.out.println();
            System.out.println("ToDo List Operations:\n1: List tasks." +
                    "\n2: Add a new task."+
                    "\n3: Delete a task." +
                    "\n4: Search tasks."+
                    "\n0: Exit."+
                    "\nPlease enter your choice:");
            System.out.println();



            int number= input.nextInt();

            switch (number){
                case 1:
                    toDo.listTasks();
                    break;
                case 2:
                    System.out.println("Add a new task:");
                    System.out.println("Enter task priority (1: Low, 2: Medium, 3:High):");

                    while (true){  // The loop requests a valid number until the user enters one of the requested numbers.
                        int priority= input.nextInt();
                        if(priority==1||priority==2||priority==3){
                            System.out.println("Enter task description:");
                            input.nextLine();
                            String description=input.nextLine();

                            Task task=new Task(priority,description);
                            toDo.addTask(task);

                            System.out.println("Task added to the Todo List.");
                            break;
                        }
                        else {
                            System.out.println("Please enter a valid number!");
                        }

                    }
                    break;
                case 3:
                    System.out.println("Delete a task:");
                    System.out.println("Enter a Task ID to be deleted:");
                    int taskID=input.nextInt();
                    toDo.removeTask(taskID);

                    break;
                case 4:
                    System.out.println("Enter the search keyword:");
                    input.nextLine();
                    String keyword=input.nextLine();
                    toDo.search(keyword);
                    break;
                case 0:
                    System.out.println("Bye.");
                    key=false;

            }
        }
    }
}