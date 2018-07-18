package com.codecool.expertsystem.repositories;

import com.codecool.expertsystem.iterators.FactIterator;
import com.codecool.expertsystem.questionnaire.Fact;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FactRepository {

    private List<Fact> factsList;

    public FactRepository() {

        this.factsList = new ArrayList<>();

    }

    public void addFacts(Fact fact) {

        factsList.add(fact);

    }

    public Iterator<Fact> getIterator() {

        return new FactIterator(factsList);

    }

}