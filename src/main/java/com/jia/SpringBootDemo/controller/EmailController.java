package com.jia.SpringBootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@RestController
public class EmailController {
/*
    @Autowired
    JavaMailSender javaMailSender;

    @RequestMapping(value = "/sendMail")
    public String sendMail() throws IOException, MessagingException {
        //mail();
        mail1();
        return "发送成功";
    }

    private void mail() {
        // 发送简单邮件，无附件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hongjiajia1666@163.com");
        message.setSubject("邀请函");
        message.setText("发来一封邀请函");
        message.setTo("2086543608@qq.com");
        javaMailSender.send(message);
    }

    // 复杂邮件 = 正文 + 附件
    private void mail1() throws MessagingException, IOException {

        // 设置邮件服务器的属性
        Properties properties=new Properties();
        properties.put("mail.host","smtp.163.com");
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.port","465");

        Session session=Session.getDefaultInstance(properties);

        MimeMessage mimeMessage = new MimeMessage(session);

        // 设置邮件来源
        mimeMessage.setFrom(new InternetAddress("hongjiajia1666@163.com",false));

        // 设置邮件接收方
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse("2086543608@qq.com"));

        // 设置邮件主题
        mimeMessage.setSubject("一封邮件");

        // 设置邮件日期
        mimeMessage.setSentDate(new Date());

        // 正文部分
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setText("发来一封邀请函，带附件");

        // 附件部分
        MimeBodyPart attachPart = new MimeBodyPart();
        attachPart.attachFile("D:/pictures/pickGirl.jpg");

        // 消息载体
        MimeMultipart mimeMultipart = new MimeMultipart();
        mimeMultipart.addBodyPart(mimeBodyPart);
        mimeMultipart.addBodyPart(attachPart);

        // 将消息载体作为整个邮件的主体内容
        mimeMessage.setContent(mimeMultipart);


        Transport transport=session.getTransport();

        // 连接发送端
        transport.connect("smtp.163.com","hongjiajia1666@163.com","QSTSIXQQLJUIGWQV");

        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
    }

*/
}
