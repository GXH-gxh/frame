package com.bdqn.travel.utils;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QQEmailUtils {
	
	private static final String ACCOUNT = "kazugin.liang@qq.com";//�˺�,������
	private static final String PASSWORD = "womlfwwqyvqkjgef";//��Կ
	
	/**
	 * �����ʼ�
	 * @param sender	���ͷ�
	 * @param receiver	���շ�
	 * @param subject	����
	 * @param content	����
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
	      * ����ռ��������ַ�����
	      * 1,�������(���˷���)����setRecipient(str);����String����
	      * 2,�������(Ⱥ��)����setRecipients(list);����list��������
	      * 3,�������(Ⱥ��)����setRecipients(sb);����StringBuffer����
	      */
	     List<String> list = new ArrayList<String>();
	     list.add(receiver);
	     mail.setRecipients(list);
	     mail.setSubject(subject);
	     //mail.setText("лл����");
	     mail.setDate(new Date());
	     mail.setFrom(sender);
	     mail.setContent(content, "text/html; charset=UTF-8");
	     mail.sendMessage();
	}

	 
}
