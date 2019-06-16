package com.hunmengwl.projectlove.commons;

public enum SumEnum {

    ONE("1","星期一"),
    TWO("2","星期二"),
    THREE("3","星期三");

    String id;
    String name;

    SumEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
