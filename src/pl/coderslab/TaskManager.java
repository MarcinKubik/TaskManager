package pl.coderslab;


import org.apache.commons.lang3.math.NumberUtils;
import pl.coderslab.ConsoleColors;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


public class TaskManager {

    public static void main(String[] args) {

        String[][] data= new String[1][1];
        data=loadFromFile();
        selectOption(data);
       // selectOption();


    }

    public static void showMethods(){
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        System.out.print(ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");

    }

    public static void selectOption(String[][] data){
        Scanner scan=new Scanner(System.in);
        String input;

        do {

            input=scan.next();
            switch (input){
                case "add":
                    addTask(data);
                    break;
                case "remove":
                    //removeTask();
                    break;
                case "list":
                   // listTask();
                    break;
                case "exit":
                   // exitTask();
                    break;

                default:
                    System.out.println("Please select a correct option.");
            }

        }while(!(input.equals("add") || input.equals("remove") ||
                input.equals("list") || input.equals("exit") ));

    }

    public static String[][] loadFromFile(){

        Path path= Paths.get("example.csv");
        File file=new File("example.csv");
        String[][] data= new String[1][1];

        try {
            if (!(Files.exists(path)))
            {
                Files.createFile(path);
            }
        }catch (IOException ex)
        {
            System.out.println("File creation error");
        }

        try{
            Scanner scan= new Scanner(file);
            int i = 0;

            while (scan.hasNextLine())
            {

                String[] strsplit=scan.nextLine().split(",");


                    for ( int j = 0 ; j < strsplit.length; j++)
                    {
                        data[i][j]=strsplit[j];
                        data[i]= Arrays.copyOf(data[i], data[i].length+1);
                    }


                    data[i]= Arrays.copyOf(data[i], data[i].length-1);
                    i++;
                    data=Arrays.copyOf(data, data.length+1);
                    data[i]=new String[1];
            }

            data=Arrays.copyOf(data, data.length-1);


        }catch (FileNotFoundException ex){
            System.out.println("File not found");
        }


        return data;
    }

    public static String[][] addTask(String[][] data){

        System.out.println("add");
        System.out.println("Please add task description");
        Scanner scan= new Scanner(System.in);
        String description=ConsoleColors.GREEN+scan.nextLine();
        boolean bool1=true;
        boolean bool2=true;
        boolean bool3=true;
        String date;



        do {
            System.out.println("Please add task due date(yyyy-mm-dd)");
            date=scan.next();
            StringBuilder str1=new StringBuilder();
            StringBuilder str2=new StringBuilder();
            StringBuilder str3=new StringBuilder();

            while(date.length()<10){
                System.out.println("Wrong date format");
                date=scan.next();
            }

            for (int i = 0; i <4 ; i++)
            {
                str1.append(date.charAt(i));
            }

            str2.append(date.charAt(5));
            str2.append(date.charAt(6));
            str3.append(date.charAt(8));
            str3.append(date.charAt(9));
            System.out.println(ConsoleColors.RESET+str1.toString());
            bool1= NumberUtils.isParsable(str1.toString());
            bool2=NumberUtils.isParsable(str2.toString());
            bool3=NumberUtils.isParsable(str3.toString());

            if (bool1==false)
            {
                System.out.println("Wrong year format");
            }

            if (bool2==false)
            {
                System.out.println("Wrong month format");
            }

            if (bool3==false)
            {
                System.out.println("Wrong day format");
            }


        }while(!(bool1 && bool2 && bool3));

        String taskImportance;
        do {
            System.out.println("Is your task important: true/false");
            taskImportance=scan.next();
        }while (!(taskImportance.equals("true") || taskImportance.equals("false")));


        System.out.println(description+date+taskImportance);
        return data;
    }

}
