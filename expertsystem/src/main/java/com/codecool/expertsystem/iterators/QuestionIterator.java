package com.codecool.expertsystem.iterators;

import com.codecool.expertsystem.questionnaire.Question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuestionIterator implements Iterator {

    private List<Question> questionList;
    private int index = 0;

    public QuestionIterator(List<Question> questionList) {
        this.questionList = questionList;

    }

    public boolean hasNext() {
        return index < this.questionList.size();
    }

    public Question next() {
        return this.questionList.get(index++);
    }
}