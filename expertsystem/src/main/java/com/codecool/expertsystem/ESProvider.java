package com.codecool.expertsystem;

import com.codecool.expertsystem.parsers.FactParser;
import com.codecool.expertsystem.parsers.RuleParser;
import com.codecool.expertsystem.questionnaire.Fact;
import com.codecool.expertsystem.questionnaire.Question;
import com.codecool.expertsystem.questionnaire.value.Value;
import com.codecool.expertsystem.repositories.FactRepository;
import com.codecool.expertsystem.repositories.RuleRepository;
import com.codecool.expertsystem.error.CarNotInXmlException;
import com.codecool.expertsystem.view.Display;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class ESProvider {

    private FactParser factParser;
    private RuleParser ruleParser;
    private RuleRepository ruleRepository;
    private FactRepository factRepository;
    private Display display = new Display();
    private Map<String, Boolean> answersMap = new HashMap<>();

    public ESProvider(FactParser factParser, RuleParser ruleParser) {

        this.ruleParser = ruleParser;
        this.factParser = factParser;
        this.ruleRepository = ruleParser.getRuleRepository();
        this.factRepository = factParser.getFactRepository();

    }

    public void resetAnswers() {
        answersMap = new HashMap<>();

    }

    public void collectAnswers() {

        Iterator<Question> questionIterator = ruleRepository.getIterator();

        while (questionIterator.hasNext()) {

            Question nextQuestion = questionIterator.next();
            String input = display.getStringInput(nextQuestion.getQuestion());
            boolean evaluatedAnswer = nextQuestion.getEvaluatedAnswer(input);
            answersMap.put(nextQuestion.getId(), evaluatedAnswer);

        }

    }

    public String evaluate() throws CarNotInXmlException {

        String cars = "";
        Iterator<Fact> factIterator = factRepository.getIterator();

        while (factIterator.hasNext()) {

            int thisCar = 0;
            Fact factTemp = factIterator.next();

            for (Map.Entry<String, Boolean> entry : answersMap.entrySet()) {
                String id = entry.getKey();
                boolean value = entry.getValue();

                if (value == factTemp.getValueById(id)) {
                    thisCar++;
                }
            }

            if (thisCar == answersMap.size()) {
                cars += factTemp.getDescription() + "\n";
            }
        }

        if (cars.equals(""))
            throw new CarNotInXmlException("Not found car in our system.\n");
        return cars;

    }
}
