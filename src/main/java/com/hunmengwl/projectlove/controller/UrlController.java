package com.hunmengwl.projectlove.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UrlController {

//    @RequestMapping("/list")
//    public ModelAndView doIndex(ModelAndView modelAndView){
//        modelAndView.setViewName("list");
//        return modelAndView;
//    }

//    @RequestMapping("/show")
//    public String doShow(){
//        return "show";
//    }

//    @RequestMapping("/login")
//    public String doLogin(){
//        return "login";
//    }

    @GetMapping("/{html}")
    public String doUrl(@PathVariable String html){
        return html;
    }
//    @GetMapping("/{sys}/{html}")
//    public String doUrl(@PathVariable String sys,@PathVariable String html){
//        String doUrl = sys+"/"+html;
//        return doUrl;
//    }

    @RequestMapping(value = "/indexList")
    public ModelAndView doIndexList(ModelAndView modelAndView){
        modelAndView.setViewName("/index_list");
        return modelAndView;
    }




}
