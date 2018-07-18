package com.codecool.expertsystem.repositories;

import com.codecool.expertsystem.iterators.QuestionIterator;
import com.codecool.expertsystem.questionnaire.Question;
import java.util.*;

public class RuleRepository {

    private Map<String, Question> questionMap;

    public RuleRepository() {

        this.questionMap = new HashMap<>();

    }

    public void addQuestion(Question question) {

        this.questionMap.put(question.getId(), question);

    }

    public Iterator<Question> getIterator() {

        return new QuestionIterator(getQuestionList());

    }

    private List<Question> getQuestionList() {

        List<Question> keyList = new ArrayList<>();

        for (String key : questionMap.keySet()) {
            keyList.add(questionMap.get(key));
        }

        return keyList;
    }

}
