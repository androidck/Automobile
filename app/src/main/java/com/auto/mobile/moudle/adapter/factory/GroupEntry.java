package com.auto.mobile.moudle.adapter.factory;

import java.util.ArrayList;

/**
 * 组数据
 */
public class GroupEntry {

    private String head;

    private ArrayList<Factory> factories;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public ArrayList<Factory> getFactories() {
        return factories;
    }

    public void setFactories(ArrayList<Factory> factories) {
        this.factories = factories;
    }

    public GroupEntry(String head, ArrayList<Factory> factories) {
        this.head = head;
        this.factories = factories;
    }
}
