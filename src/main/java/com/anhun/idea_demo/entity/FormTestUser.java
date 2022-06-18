package com.anhun.idea_demo.entity;

import java.util.Arrays;

public class FormTestUser {
    private String single;
    private String[] plural;

    @Override
    public String toString() {
        return "FormTestUser{" +
                "single='" + single + '\'' +
                ", plural=" + Arrays.toString(plural) +
                '}';
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String[] getPlural() {
        return plural;
    }

    public void setPlural(String[] plural) {
        this.plural = plural;
    }
}
