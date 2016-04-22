package com.des.util;


import java.io.File;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class MailUtil
{
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	private static final String SUBJECT = "Greetings From Harshvardhan Group";
	
	private static final String HOST = "smtp.gmail.com";
	private static final String PORT = "465";
	
	public static boolean sendMail(List<String> to)
	{
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);
		props.put("mail.stmp.user", USERNAME);
		// If you want you use TLS
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.password", PASSWORD);
		// If you want to use SSL
		props.put("mail.smtp.socketFactory.port", PORT);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", PORT);
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				String username = USERNAME;
				String password = PASSWORD;
				return new PasswordAuthentication(username, password);
			}
		});
		String from = USERNAME;
		String subject = SUBJECT;
		MimeMessage msg = new MimeMessage(session);
		try
		{
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] addressTo = new InternetAddress[to.size()];
			for (int i = 0; i < to.size(); i++)
			{
				addressTo[i] = new InternetAddress(to.get(i));
			}
			msg.setRecipients(RecipientType.TO, addressTo);
			msg.setSubject(subject);
			// Fill the message
			String vmFileContent = getMsgBody();
			// Send the complete message parts
			msg.setContent(vmFileContent,"text/html");
			Transport transport = session.getTransport("smtp");
			transport.send(msg);
			return true;
		}
		catch (Exception exc)
		{
			return false;
		}
	}

	private static String getMsgBody() throws Exception
	{
		/*  first, get and initialize an engine  */
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        /*  next, get the Template  */
        File f = new File("specialday.vm");
        Template t = ve.getTemplate( "specialday.vm" );
        /*  create a context and add data */
        VelocityContext context = new VelocityContext();
        context.put("name", "World");
        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        /* show the World */
		return writer.toString();
	}
}
