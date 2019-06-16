package com.hunmengwl.projectlove.config;

import org.springframework.stereotype.Component;

@Component
public class TestBean {

    private int id;
    private String name;

    public TestBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TestBean() {
        System.out.println("new TestBean().... ");
    }
    public void configHolle(int id){
        System.out.println("第一次进来了configHolle");
//        System.out.println("new configHolle。。。。"+id/0);
    }

    public void start(){
        System.out.println("start初始化了、、、、、、");
    }

    public void cleanUp(){
        System.out.println("cleanUp销毁了、、、、、、");
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
