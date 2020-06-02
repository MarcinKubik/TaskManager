package pl.coderslab;

import java.text.SimpleDateFormat;

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

        String[][] data = loadFromFile();
        selectOption(data);

    }

    public static void showMethods() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        System.out.print(ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");

    }

    public static void selectOption(String[][] data) {
        showMethods();
        Scanner scan = new Scanner(System.in);
        String input;

        do {

            input = scan.next();
            switch (input) {
                case "add":
                    data = addTask(data);
                    break;
                case "remove":
                    data = removeTask(data);
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

            if (!input.equals("exit")) {
                showMethods();
            }

        } while (!input.equals("exit"));
    }

    public static String[][] loadFromFile() {

        Path path = Paths.get("tasks.csv");
        File file = new File("tasks.csv");
        String[][] data = new String[1][1];

        try {
            if (!(Files.exists(path))) {
                Files.createFile(path);
            }
        } catch (IOException ex) {
            System.out.println("File creation error");
        }

        try {
            Scanner scan = new Scanner(file);
            int i = 0;

            while (scan.hasNextLine()) {

                String[] strsplit = scan.nextLine().split(",");

                for (int j = 0; j < strsplit.length; j++) {
                    data[i][j] = strsplit[j];
                    data[i] = Arrays.copyOf(data[i], data[i].length + 1);
                }

                data[i] = Arrays.copyOf(data[i], data[i].length - 1);
                i++;
                data = Arrays.copyOf(data, data.length + 1);
                data[i] = new String[1];
            }

            data = Arrays.copyOf(data, data.length - 1);

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }

        return data;
    }

    public static String[][] addTask(String[][] data) {

        System.out.println("add");
        System.out.println("Please add task description");
        Scanner scan = new Scanner(System.in);
        String description = scan.nextLine();
        String date = checkDate();

        String taskImportance;
        do {
            System.out.println("Is your task important: true/false");
            taskImportance = scan.next();
        } while (!(taskImportance.equals("true") || taskImportance.equals("false")));


        data = Arrays.copyOf(data, data.length + 1);
        data[data.length - 1] = new String[3];
        data[data.length - 1][0] = description;
        data[data.length - 1][1] = date;
        data[data.length - 1][2] = taskImportance;
        System.out.println();

        return data;
    }

    public static String[][] removeTask(String[][] data) {

        System.out.println("remove");
        System.out.println("Please select number to remove");
        Scanner scan = new Scanner(System.in);
        int numbertoremove;
        String a;

        while (true) {
            a = scan.next();
            if (NumberUtils.isParsable(a)) {
                if (Integer.parseInt(a) < 0 || Integer.parseInt(a) > data.length - 1) {
                    System.out.println("Incorrect argument passed. Please give number of task from list");
                } else {
                    break;
                }
            } else {
                System.out.println("Incorrect argument passed. Use number keys");
            }
        }

        numbertoremove = Integer.parseInt(a);

        data = ArrayUtils.remove(data, numbertoremove);
        System.out.println("Value was succesfully deleted");
        System.out.println();

        return data;
    }

    public static void listTask(String[][] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < 3; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void exitTask(String[][] data) {

        try (PrintWriter printWriter = new PrintWriter("tasks.csv")) {

            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < 3; j++) {
                    if (j == 2) {
                        printWriter.print(data[i][j]);
                    } else {
                        printWriter.print(data[i][j] + ",");
                    }
                }
                printWriter.println();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Data saving error");
        }

        System.out.println(ConsoleColors.RED + "Bye, bye.");
    }

    public static String checkDate() {
        String date;
        boolean yearFormatIsCorrect = true;
        boolean monthFormatIsCorrect = true;
        boolean dayFormatIsCorrect = true;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("Please add task due date(yyyy-mm-dd)");
            date = scan.next();

            StringBuilder yearBuilder = new StringBuilder();
            StringBuilder monthBuilder = new StringBuilder();
            StringBuilder dayBuilder = new StringBuilder();

            while (date.length() != 10) {
                System.out.println("Wrong date format(yyyy-mm-dd)");
                date = scan.next();
            }

            for (int i = 0; i < 4; i++) {
                yearBuilder.append(date.charAt(i));
            }

            monthBuilder.append(date.charAt(5));
            monthBuilder.append(date.charAt(6));
            dayBuilder.append(date.charAt(8));
            dayBuilder.append(date.charAt(9));
            yearFormatIsCorrect = NumberUtils.isParsable(yearBuilder.toString());
            monthFormatIsCorrect = NumberUtils.isParsable(monthBuilder.toString());
            dayFormatIsCorrect = NumberUtils.isParsable(dayBuilder.toString());

            if (!yearFormatIsCorrect) {
                System.out.println("Wrong year format");
            }

            if (!monthFormatIsCorrect) {
                System.out.println("Wrong month format");
            }

            if (!dayFormatIsCorrect) {
                System.out.println("Wrong day format");
            }

        } while (!(yearFormatIsCorrect && monthFormatIsCorrect && dayFormatIsCorrect));


        return date;
    }
}
