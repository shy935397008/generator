package com.yang.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.junit.Test;

public class SendEmail {
	@Test
	public void send() throws EmailException {
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("D:/jdk1.6/src.zip");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Picture of John");
		attachment.setName("John.zip");

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.163.com");
		// email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(
				"yanghongsheng89@163.com", "896638"));
		email.addTo("shy935397008@163.com", "John Doe");
		email.setFrom("yanghongsheng89@163.com", "Me");
		email.setSubject("The picture");
		email.setMsg("Here is the picture you wanted");

		// add the attachment
		email.attach(attachment);

		// send the email
		email.send();
	}
}
