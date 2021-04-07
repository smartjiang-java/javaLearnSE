package jqk.learn.hutool;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件发送
 *
 * @Author:JQK
 * @Date:2021/3/22 11:14
 **/

public class EmailTest {

    /**
     * 推荐创建不可变静态类成员变量
     */
    private static final Log log = LogFactory.get();

    public static void main(String[] args) throws MessagingException {
        Properties properties = new Properties();
        // 连接协议
        properties.put("mail.transport.protocol", "smtp");
        // 主机名
        properties.put("mail.smtp.host", "smtp.qq.com");
        // 端口号
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.auth", "true");
        //设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.smtp.ssl.enable", "true");
        //设置是否显示debug信息 true 会在控制台显示相关信息
        properties.put("mail.debug", "true");
        //得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        //设置发件人邮箱地址
        message.setFrom(new InternetAddress("490409692@qq.com"));
        //设置收件人地址
        message.setRecipients(MimeMessage.RecipientType.TO, new InternetAddress[]{new InternetAddress("1677081700@qq" +
                ".com")});
        //设置邮件标题
        message.setSubject("这是第一封Java邮件");
        //设置邮件内容
        message.setText("内容为： 这是第一封java发送来的邮件。");
        //得到邮差对象
        Transport transport = session.getTransport();
        //连接自己的邮箱账户,密码为刚才得到的授权码
        transport.connect("490409692@qq.com", "jupqguumseksbjbi");
        //发送邮件
        transport.sendMessage(message, message.getAllRecipients());
    }

}