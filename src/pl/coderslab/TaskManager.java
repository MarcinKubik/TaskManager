package pl.coderslab;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import pl.coderslab.ConsoleColors;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


public class TaskManager {

    public static void main(String[] args) {

        String[][] data;
        data=loadFromFile();
        selectOption(data);


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
        showMethods();
        Scanner scan=new Scanner(System.in);
        String input;

        do {

            input=scan.next();
            switch (input){
                case "add":
                    data=addTask(data);
                    break;
                case "remove":
                    data=removeTask(data);
                    break;
                case "list":
                    listTask(data);
                    break;
                case "exit":
                    exitTask(data);
                    break;

                default:
                    System.out.println("Please select a correct option.");
            }

            if(!input.equals("exit")) {
                showMethods();
            }

        }while(!input.equals("exit"));
    }

    public static String[][] loadFromFile(){

        Path path= Paths.get("tasks.csv");
        File file=new File("tasks.csv");
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
        String description=scan.nextLine();
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

            while(date.length()!=10){
                System.out.println("Wrong date format(yyyy-mm-dd)");
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


        data=Arrays.copyOf(data, data.length+1);
        data[data.length-1]=new String[3];
        data[data.length-1][0]=description;
        data[data.length-1][1]=date;
        data[data.length-1][2]=taskImportance;
        System.out.println();

        return data;
    }

public static String[][] removeTask(String[][] data){

        System.out.println("remove");
        System.out.println("Please select number to remove");
        Scanner scan=new Scanner(System.in);
        int numbertoremove;
        String a;

        while (true)
        {
            a=scan.next();
            if(NumberUtils.isParsable(a)){
                if(Integer.parseInt(a)<0 || Integer.parseInt(a)>data.length-1)
                {
                    System.out.println("Incorrect argument passed. Please give number of task from list");
                }
                else{
                    break;
                }
            }
            else {
                System.out.println("Incorrect argument passed. Use number keys");
            }


        }

        numbertoremove=Integer.parseInt(a);


        data= ArrayUtils.remove(data, numbertoremove);
        System.out.println("Value was succesfully deleted");
        System.out.println();

        return data;
    }

    public static void listTask(String[][] data){
        for (int i = 0; i <data.length ; i++)
        {
            System.out.print(i+" : ");
            for (int j = 0; j <3 ; j++)
            {
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void exitTask(String[][] data){

        try (PrintWriter printWriter= new PrintWriter("tasks.csv")){

            for (int i = 0; i <data.length ; i++)
            {
                for (int j = 0; j <3 ; j++)
                {
                    if(j==2) {
                        printWriter.print(data[i][j]);
                    }
                    else{
                        printWriter.print(data[i][j] + ",");
                    }
                }
                printWriter.println();
            }

        }catch (FileNotFoundException ex){
            System.out.println("Data saving error");
        }

        System.out.println(ConsoleColors.RED+"Bye, bye.");
    }
}
