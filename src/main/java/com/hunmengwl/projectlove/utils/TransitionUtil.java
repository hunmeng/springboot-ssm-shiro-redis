package com.hunmengwl.projectlove.utils;

import java.util.Date;

public class TransitionUtil {


    public TransitionUtil() {
    }

    /**
     * 把一个日期字符串String类型给转换成时间date类型
     * @param string
     * @return
     */
    public static Date getDate(String string,int st){
        if(string!=null&&string!=""){
            String[] split = string.split("-");
            Date date = new Date();
            date.setTime(0);
            date.setYear(Integer.parseInt(split[0])-1900);
            date.setMonth(Integer.parseInt(split[1])-1);
            date.setDate(Integer.parseInt(split[2]));
            if (st==1) {
                date.setHours(0);
                date.setMinutes(0);
                date.setSeconds(0);
            }else{
                date.setHours(23);
                date.setMinutes(59);
                date.setSeconds(59);
            }
//        System.out.println("date:"+date);
            return date;
        }else{
            return null;
        }
    }

}
