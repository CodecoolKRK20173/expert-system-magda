package com.codecool.expertsystem.view;

import com.codecool.expertsystem.questionnaire.Answer;
import com.codecool.expertsystem.questionnaire.value.Value;
import sun.plugin.javascript.navig.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Display {
    private Scanner userInput = new Scanner(System.in);


    public void printMessage(String message) {
        System.out.println(message);

    }


    public String getStringInput(String message) {

        printMessage(message);
        return userInput.nextLine().toLowerCase();
    }

    public String getStringInput(String... messages) {
        printMessage(messages[0]);


        ArrayList<String> correctInput= new ArrayList<>();
        String stringInput = "";

        while(correctInput.isEmpty()) {
            stringInput = userInput.nextLine().toLowerCase();
            for (int i = 1; i < messages.length; i++) {
                if (messages[i].equals(stringInput)) {
                    correctInput.add(messages[i]);
                }
            }
            if (correctInput.isEmpty()) {
                printMessage("Wrong input. Please enter correct form.");
            }
        }
        return stringInput;
    }



}
