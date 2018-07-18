package com.codecool.expertsystem.questionnaire.value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleValue extends Value {
    private String param;
    private boolean selectionType;

    public SingleValue(String param, boolean selectionType) {
        this.param = param;
        this.selectionType = selectionType;
    }

    @Override
    public List<String> getInputPattern() {
        return new ArrayList<String>(Arrays.asList(param));
    }

    @Override
    public boolean getSelectionType() {
        return selectionType;
    }


}
