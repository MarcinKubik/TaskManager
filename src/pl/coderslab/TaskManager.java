package pl.coderslab;


import pl.coderslab.ConsoleColors;

import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        //loadFromFile();
        showMethods();
        selectOption();

    }

    public static void showMethods(){
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        System.out.print(ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");

    }

    public static String selectOption(){
        Scanner scan=new Scanner(System.in);
        String input;

        do {

            input=scan.next();
            switch (input){
                case "add":
                   // addTask();
                    break;
                case "remove":
                    //removeTask();
                    break;
                case "list":
                   // listTask();
                    break;
                case "exi":
                   // exitTask();
                    break;

                default:
                    System.out.println("Please select a correct option.");
            }

        }while(!(input.equals("add") || input.equals("remove") || input.equals("lsit") || input.equals("exit") ));


        return input;
    }

    public static String[][] 

}
