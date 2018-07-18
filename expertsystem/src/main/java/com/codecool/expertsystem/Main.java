package com.codecool.expertsystem;

import com.codecool.expertsystem.view.Display;
import com.codecool.expertsystem.parsers.FactParser;
import com.codecool.expertsystem.parsers.RuleParser;
import com.codecool.expertsystem.error.CarNotInXmlException;

public class Main {

    public static void main( String[] args ) {
        String answer;
        ESProvider esProvider = new ESProvider(new FactParser(), new RuleParser());
        Display display = new Display();

        display.printMessage("Welcome to: \nCAR BUYING ADVISOR\n******************");

        boolean isFinish = false;
        while(!isFinish){
            esProvider.resetAnswers();
            esProvider.collectAnswers();

            try {
                display.printMessage("We found car/s for you:\n" + esProvider.evaluate());
            } catch (CarNotInXmlException e) {
                display.printMessage("We don't found car matching your requirements.");
            }
            finally{
                answer = display.getStringInput("Do you want to answer questions again? (y/n)", "y", "n");
                if(answer.equals("n")) {
                    isFinish = true;
                }

            }

        }


    }
}
