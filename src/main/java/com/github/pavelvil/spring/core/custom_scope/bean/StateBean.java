package com.github.pavelvil.spring.core.custom_scope.bean;

import com.github.pavelvil.spring.core.custom_scope.annotation.Restore;
import com.github.pavelvil.spring.core.custom_scope.annotation.Save;

public class StateBean {
    private String data;

    public StateBean() {
    }

    public StateBean(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Restore
    public StateBean restore(int version) {
        return null;
    }

    @Save
    public void save() {

    }

    @Override
    public String toString() {
        return "StateBean{" +
                "data='" + data + '\'' +
                '}';
    }
}
