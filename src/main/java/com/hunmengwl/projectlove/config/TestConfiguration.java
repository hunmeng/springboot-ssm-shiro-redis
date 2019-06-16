package com.hunmengwl.projectlove.config;

import lombok.val;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class TestConfiguration {


//    public TestConfiguration() {
//        System.out.println("TestConfiguration启动成功。。。。。。");
//    }

//    @Bean(name = "testBean",initMethod = "start",destroyMethod = "cleanUp")
//    @Scope(value = "prototype")
//    public TestBean testBean(){
//        return new TestBean();
//    }

    public void aopTest(){


    }

}
