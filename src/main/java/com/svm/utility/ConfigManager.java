package com.svm.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ConfigManager {

	public static Properties prop;
	public static String Env;
	public static String browser;
	public static String sendEmail;
	public static XLUtility excel= null;

	public static void loadconfig() {
		prop = new Properties();
		FileInputStream FIS;
		try {
			FIS = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
			prop.load(FIS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (prop == null) {
			throw new NullPointerException("Properties object is not initialized.");
		}
		browser = prop.getProperty("Browser");
		Env = prop.getProperty("Env");
		sendEmail = prop.getProperty("SendEmail");

		if (sendEmail == null || sendEmail.isEmpty())
			throw new IllegalArgumentException("SendEmail Propery is not defiled.  Please check configuration file");

		if (browser == null || browser.isEmpty()) {
			throw new IllegalArgumentException("Browser property is not specified in the configuration file.");
		}

		if (!browser.equalsIgnoreCase("Chrome") && !browser.equalsIgnoreCase("Firefox")
				&& !browser.equalsIgnoreCase("Edge")) {
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		if (Env == null || Env.isEmpty()) {
			throw new IllegalArgumentException("Environment property is not specified in the configuration file.");
		}

		if (!Env.equalsIgnoreCase("QA") && !Env.equalsIgnoreCase("Staging")) {
			throw new IllegalArgumentException("Unsupported Environment: " + Env);
		}

		if (ConfigManager.Env.equalsIgnoreCase("QA")) {
			excel = new XLUtility(
					System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\QA\\testdata.xlsx");
		} else {
			excel = new XLUtility(
					System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\Staging\\stagingtestdata.xlsx");
		}
		
	}
	public static void intiDriver() {

		utils.setup(browser, Env);

	}

	public static void SendMailSSLWithAttachment() {

		// Create object of Property file
		Properties props = new Properties();

		// this will set host of server- you can change based on your
		// requirement
		props.put("mail.smtp.host", "smtp.gmail.com");

		// set the port of socket factory
		props.put("mail.smtp.socketFactory.port", "465");

		// set socket factory
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// set the authentication to true
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.starttls.enable", "true");

		// set the port of SMTP server
		props.put("mail.smtp.port", "465");

		final String userName = "ballabhsharma292@gmail.com";
		final String passWord = "qaki cjzv avlg ufcu";

		// This will handle the complete authentication
		Session session = Session.getInstance(props,

				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

						return new PasswordAuthentication(userName, passWord);
					}

				});
		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress("ballabhsharma292@gmail.com"));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO, InternetAddress
					.parse("gaurav.sharma2089@gmail.com,Arpit.Tripathi@rsystems.com,gaurav.sharma1@rsystems.com"));

			// Add the subject link
			message.setSubject("Test Execution Report-" + new Date());

			// Create object to add multimedia type content
			BodyPart messageBodyPart = new MimeBodyPart();

			String messageBody = "<html>\r\n" + "  <body style='font-family: Arial, sans-serif;'>\r\n"
					+ "    <div style='margin: 20px;'>\r\n" + "      <p style='margin-bottom: 20px;'>Hi,</p>\r\n"
					+ "      <p>Below is the test result summary for " + Env + "</p> <!-- Added line -->\r\n"
					+ "      <table border='1' cellpadding='10' style='border-collapse: collapse; width: 50%;'>\r\n"
					+ "        <tr style='background-color: #f2f2f2;'>\r\n"
					+ "          <th style='padding: 10px; width: 30px;'><strong>Result</strong></th>\r\n"
					+ "          <th style='padding: 10px; width: 30px;'><strong>Count</strong></th>\r\n"
					+ "        </tr>\r\n" + "        <tr>\r\n"
					+ "          <td style='padding: 10px; width: 30px;'>Pass</td>\r\n"
					+ "          <td style='padding: 10px; width: 30px;'>" + ExtentReportManager.passCount + "</td>\r\n"
					+ "        </tr>\r\n" + "        <tr>\r\n"
					+ "          <td style='padding: 10px; width: 30px;'>Fail</td>\r\n"
					+ "          <td style='padding: 10px; width: 30px;'>" + ExtentReportManager.failCount + "</td>\r\n"
					+ "        </tr>\r\n" + "      </table>\r\n" + "      <p style='margin-top: 20px;'>Thanks,</p>\r\n"
					+ "      <p>SVM Testing Team</p>\r\n" + "    </div>\r\n" + "  </body>\r\n" + "</html>\r\n" + "";
			// Set the body of email
			messageBodyPart.setContent(messageBody, "text/html; charset=utf-8");

			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Mention the file which you want to send
			String filename = System.getProperty("user.dir") + "\\reports\\" + ExtentReportManager.repName;

			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName("PaymentAdminPortalTestExecutionReport.html");

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);

			System.out.println("=====Email Sent=====");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}

	}
}
