package com.hunmengwl.projectlove.utils;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class EmailUtil {

    public  String rootEmailAddress = "";
    //客户端授权密码
    public  String rootEmailPassword = "";
    public  String rootEmailSMTPHost = "";
    public  String TITLE = "";
    public  String USERTEXT = "";

    public EmailUtil(){
    }

    /**
     * 初始化
     * 加载配置文件里的内容
     * @param realPath
     * @return
     * @throws Exception
     */
    public String init(String realPath) throws Exception{
        Map<String, Object> map = PropertiesFile.inputFile(realPath);
        rootEmailAddress = map.get("mail")+"";
        rootEmailPassword =map.get("password")+"";
        rootEmailSMTPHost = map.get("smtphost")+"";
        TITLE = map.get("title")+"";
        USERTEXT = map.get("usertext")+"";
        return "初始化成功";
    }

    /**
     * 发送指定content的邮件至用户邮箱
     * @param targetEmail
     * @param username
     * @param content
     * @return
     */
    public boolean sendEmail(String targetEmail, String username, String content,String title,String userText) {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", rootEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        //根据配置创建会话对象，用于和邮件服务器交互
        Session session = Session.getInstance(props);
        session.setDebug(true);

        //创建一封邮件
        try {
            MimeMessage message = createMimeMessage(session,targetEmail,username,content,title,userText);
            Transport transport = session.getTransport();

            transport.connect(rootEmailAddress, rootEmailPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 创建邮件
     * @param session
     * @param targetEmail
     * @param username
     * @param content
     * @return
     * @throws Exception
     */
    public MimeMessage createMimeMessage(Session session, String targetEmail, String username, String content, String title, String userText) throws Exception {
        MimeMessage message = new MimeMessage(session);
        if (userText==""||username==null) {
            userText = USERTEXT;
        }
        if (title==""||title==null) {
            title = TITLE;
        }
        message.setFrom(new InternetAddress(rootEmailAddress,userText,"UTF-8"));//root即是发件人name
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(targetEmail,username,"UTF-8"));
        message.setSubject(title, "UTF-8");
        message.setContent(content, "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

    public static void main(String[] args) {
//    		String content = "";
//    		String targetEmail = "";
//    		String name = "";
//        boolean sendEmail = EmailUtil.sendEmail("2377178424@qq.com", "石狮市", "eeeeeeeee","","");
//        System.out.println("sendEmail:"+sendEmail);
    }

}
