package com.bdqn.travel.utils;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QQEmailUtils {
	
	private static final String ACCOUNT = "kazugin.liang@qq.com";//账号,发件人
	private static final String PASSWORD = "womlfwwqyvqkjgef";//密钥
	
	/**
	 * 发送邮件
	 * @param sender	发送方
	 * @param receiver	接收方
	 * @param subject	主题
	 * @param content	内容
	 * @throws Exception
	 */
	public static void sendMail(String sender,String receiver,String subject,String content) throws Exception{
		Map<String,String> map= new HashMap<String,String>();
	     SendMail mail = new SendMail(ACCOUNT,PASSWORD);
	     map.put("mail.smtp.host", "smtp.qq.com");
	     map.put("mail.smtp.auth", "true");
	     map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	     map.put("mail.smtp.port", "465");
	     map.put("mail.smtp.socketFactory.port", "465");
	     mail.setPros(map);
	     mail.initMessage();
	     /*
	      * 添加收件人有三种方法：
	      * 1,单人添加(单人发送)调用setRecipient(str);发送String类型
	      * 2,多人添加(群发)调用setRecipients(list);发送list集合类型
	      * 3,多人添加(群发)调用setRecipients(sb);发送StringBuffer类型
	      */
	     List<String> list = new ArrayList<String>();
	     list.add(receiver);
	     mail.setRecipients(list);
	     mail.setSubject(subject);
	     //mail.setText("谢谢合作");
	     mail.setDate(new Date());
	     mail.setFrom(sender);
	     mail.setContent(content, "text/html; charset=UTF-8");
	     mail.sendMessage();
	}

	 
}
