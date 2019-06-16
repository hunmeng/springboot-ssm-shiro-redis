package com.hunmengwl.projectlove.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PropertiesFile {

    /**
     * 根据文件里的所有key和value
     * read properties file
     *
     * @param paramFile file path
     * @throws Exception
     */
    public static Map<String,Object> inputFile(String paramFile) throws Exception {
        Properties props = new Properties();//使用Properties类来加载属性文件
        Map<String,Object> map = new HashMap<>();
        Properties datas = new Properties();
        FileInputStream inputStream = new FileInputStream(paramFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
        props.load(br);
        /**begin*******直接遍历文件key值获取*******begin*/
        Iterator<String> iterator = props.stringPropertyNames().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (key!=null) {
                /**end*******直接遍历文件key值获取*******end*/
                map.put(key,props.getProperty(key));
            }
        }
        br.close();
        inputStream.close();
        return map;
    }

    /**
     * 根据key拿到单个的value
     * @param paramFile
     * @param key
     * @return
     * @throws Exception
     */
    public static String getInputFile(String paramFile,String key) throws Exception {
        Properties props = new Properties();//使用Properties类来加载属性文件
        Map<String,Object> map = new HashMap<>();
        FileInputStream inputStream = new FileInputStream(paramFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
        props.load(br);
        String property = props.getProperty(key);
        br.close();
        inputStream.close();
        return property;
    }

    /**
     * write properties file
     *
     * @param paramFile file path
     * @throws IOException
     */
    public static int outputFile(String paramFile,Map<String,Object> map ) throws IOException {
        ///保存属性到b.properties文件
        Properties props = new Properties();
        FileOutputStream oFile = new FileOutputStream(paramFile);//true表示追加打开
            for (Map.Entry<String, Object> s : map.entrySet()) {
                props.setProperty(s.getKey(),s.getValue()+"");
            }
        //store(OutputStream,comments):store(输出流，注释)  注释可以通过“\n”来换行
        props.store(oFile, null);
        oFile.close();
        return 0;
    }

    public static void main(String[] args) {
        PropertiesFile propertiesFile = new PropertiesFile();
        // 赋值更新
       try {
            String url = "E:\\idea_project\\zKing_item_ssm\\src\\main\\resources\\properties\\ditu.properties";
            Map<String, Object> map = propertiesFile.inputFile(url);
            for (Map.Entry<String, Object> s : map.entrySet()) {
                System.out.println(s.getKey()+":"+s.getValue());
            }
            map.put("x","132.12");
            map.put("y","543.45345345");
            map.put("title","天地物流有限责任公司");
            int i = PropertiesFile.outputFile(url, map);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
