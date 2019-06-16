package com.hunmengwl.projectlove.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {

    public static void main(String[] args) {
        // @Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContext
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        TestBean testBean = (TestBean) context.getBean("testBean");
        testBean.configHolle(1);

        TestBean testBean1 = (TestBean) context.getBean("testBean");
        testBean1.configHolle(1);
        // 如果加载spring-context.xml文件：
        // ApplicationContext context = new
        // ClassPathXmlApplicationContext("spring-context.xml");
//        testBean.
    }


}
