package com.codecool.expertsystem.parsers;

import com.codecool.expertsystem.questionnaire.Question;
import com.codecool.expertsystem.questionnaire.value.*;
import com.codecool.expertsystem.questionnaire.Answer;
import com.codecool.expertsystem.repositories.RuleRepository;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.List;

public class RuleParser extends XMLParser{
    Answer answer = new Answer();

    public RuleRepository getRuleRepository() {

        RuleRepository ruleRepository = new RuleRepository();

        super.loadXMLdocument("expertsystem/src/main/resources/rules.xml");

        NodeList rulesList = doc.getElementsByTagName("Rule");

        for (int i = 0; i < rulesList.getLength(); i++) {


            Node rule = rulesList.item(i);
            Element ruleElement = (Element) rule;

            NodeList questionsList = ruleElement.getElementsByTagName("Question");
            Node questionNode = questionsList.item(0);
            Element questionElement = (Element) questionNode;

            NodeList selectionList = ruleElement.getElementsByTagName("Selection");

            for (int j = 0; j < selectionList.getLength(); j++){

                Node selectionNode = selectionList.item(j);
                Element selectionElement = (Element) selectionNode;

                String selectionValue = selectionElement.getAttribute("value");
                Boolean selectionType = Boolean.valueOf(selectionValue);

                NodeList answersList = selectionElement.getElementsByTagName("SingleValue");

                if(answersList.getLength() == 0){
                    answersList = selectionElement.getElementsByTagName("MultipleValue");
                    String answerString = getAnswer(answersList);
                    List<String> possibleAnswers = Arrays.asList(answerString.split(","));

                    Value value = new MultipleValue(possibleAnswers, selectionType);
                    answer.addValue(value);

                }else{

                    String answerString = getAnswer(answersList);
                    Value value = new SingleValue(answerString, selectionType);
                    answer.addValue(value);
                }
            }

            String ruleId = ruleElement.getAttribute("id");
            String questionString = questionElement.getTextContent();

            Question question = new Question (ruleId, questionString, answer);

            ruleRepository.addQuestion(question);
        }

        return ruleRepository;
    }

    private String getAnswer(NodeList answersList){

        Node answerNode = answersList.item(0);
        Element answerElement = (Element) answerNode;
        String answer = answerElement.getAttribute("value");

        return answer;
    }
}
