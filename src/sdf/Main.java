package sdf;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Console cons = System.console();
        String input = "";
        String dirPath = "\\shoppingcart";
        String loginName = "";
        List<String> cartItems = new ArrayList<String>();

        File newDir = new File(dirPath);
        if (newDir.exists()) {
            System.out.println("Directory already exists\n");
        } else {
            newDir.mkdir();
        }

        while (!input.equals("exit")) {

            System.out.println("Enter 'login <ID>' login to your account\n");
            System.out.println("Enter 'list' to view your shopping cart\n");
            System.out.println("Enter \"add <fruits>\" to add them to your shopping cart \n");
            System.out.println("Enter \"delete <fruits>\" to delete them to your shopping cart \n");
            System.out.println("Enter 'exit' to exit shopping cart'\n");
            input = cons.readLine("What would you like to do?");


            if (input.startsWith("login")) {
                Scanner scan = new Scanner(input.substring(5));
                while (scan.hasNext()) {
                    loginName = scan.next().trim();
                }
                File fileName = new File(dirPath + File.separator + loginName);
                if (fileName.exists()) {
                    System.out.println("You are logged in as " + loginName);
                } else {
                    fileName.createNewFile();
                    System.out.println("Your account is created\n" + fileName.getCanonicalPath());
                }
                // FileReader fr = new FileReader(fileName);
                // BufferedReader br = new BufferedReader(fr);
                // String line = "";
                // while (null != (line = br.readLine())) {

                //     cartItems.add(line);
                // }

                // br.close();
                // fr.close();
            }

            if (input.startsWith("list")) {

                // cartItems = new ArrayList<String>();

                File file = new File(newDir + File.separator + loginName);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String line = "";
                while (null != (line = br.readLine())) {
                    System.out.println(line);
                    cartItems.add(line);
                }

                br.close();
                fr.close();
            }

            if (input.startsWith("add")) {

                input = input.replace(",", " ");
                String strInput = "";
                FileWriter fw = new FileWriter(newDir + File.separator + loginName, true);
                PrintWriter pw = new PrintWriter(fw);

                
                Scanner scan = new Scanner(input.substring(3));
                while (scan.hasNext()) {
                    strInput = scan.next();
                    System.out.printf("%s\n", strInput);
                    cartItems.add(strInput);
                    pw.write(strInput + "\n");
                }
                pw.flush();
                fw.flush();
                pw.close();
                fw.close();

            }

            if (input.startsWith("delete")) {
                String strInput = "";
                Scanner scan = new Scanner(input.substring(6));

                while (scan.hasNext()) {
                    strInput = scan.next();
                    for(String item:cartItems) {
                        if (strInput.equals(item)) {
                            cartItems.remove(strInput);
                        }
                    }
                }

                System.out.println(cartItems.size());
            }

        }

    
    }


}