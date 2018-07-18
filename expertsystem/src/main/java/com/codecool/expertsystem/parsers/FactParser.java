package com.codecool.expertsystem.parsers;

import com.codecool.expertsystem.questionnaire.Fact;
import com.codecool.expertsystem.repositories.FactRepository;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FactParser extends XMLParser {

    public FactRepository getFactRepository() {

        FactRepository factRepository = new FactRepository();

        super.loadXMLdocument("expertsystem/src/main/resources/facts.xml");
        NodeList factList = doc.getElementsByTagName("Fact");

        for (int i = 0; i < factList.getLength(); i++) {

            Node node = factList.item(i);
            Element factElement = (Element) node;


            NodeList descList = factElement.getElementsByTagName("Description");

            Node descriptionNode = descList.item(0);
            Element descriptionElement = (Element) descriptionNode;
            String factId = factElement.getAttribute("id");
            String descriptionValue = descriptionElement.getAttribute("value");

            NodeList evalList = factElement.getElementsByTagName("Eval");

            Fact fact = new Fact(factId, descriptionValue);

            for (int k = 0; k < evalList.getLength(); k++){
                Node evalNode = evalList.item(k);
                Element evalElement = (Element) evalNode;
                String evalText = evalElement.getTextContent();
                String evalId = evalElement.getAttribute("id");
                Boolean value = Boolean.valueOf(evalText);

                fact.setFactValueById(evalId, value);
            }
            factRepository.addFacts(fact);
        }

        return factRepository;
    }
}