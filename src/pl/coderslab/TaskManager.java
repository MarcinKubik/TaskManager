package pl.coderslab;


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

        loadFromFile();
        //showMethods();
       // selectOption();

       /* String [][] aaa= {{"aaa", "bbb", "ccc"},{ "ddd", "eee", "fff"}, {"ggg", "hhh", "iii"}};
        for (int i = 0; i < aaa.length; i++)
        {
            for (int j = 0; j <3 ; j++)
            {
                System.out.print(aaa[i][j]+" ");
            }
            System.out.println();
        }
        aaa[2]=Arrays.copyOf(aaa[2], aaa.length+1);

        System.out.println(aaa[2][3]);*/

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
                        System.out.println(data[i][j]);
                        System.out.println(i+" "+j);
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
        System.out.println(data.length);

        for (int i = 0; i <data.length ; i++)
        {
            for (int j = 0; j <data[i].length; j++)
            {
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("ccc");
        return data;
    }

}
