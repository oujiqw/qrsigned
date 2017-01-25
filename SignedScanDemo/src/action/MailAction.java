package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import util.JsonUtil;

/*@WebServlet(name="loginFilter",urlPatterns="/loginFilter")*/
public class MailAction extends SuperAction{
	/**
	 * 简单发邮件方法
	 * @return 
	 * @throws EmailException 
	 */
	public void simple() throws EmailException{
	SimpleEmail email=new SimpleEmail();
	email.setHostName("smtp.163.com");//设置邮箱服务器
	email.setAuthentication("qw123hello", "782453628978as");//设置发件人邮箱用户名和密码
	try {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");//设置HTTP响应的编码方式
		response.setContentType("text/html;charset=utf-8");//设置前端浏览器显示数据的编码方式
	 	/*email.addTo("1438580533@qq.com");//设置收件人邮箱
*/		/*email.addTo("qw12345haha@163.com");//设置收件人邮箱
		email.setFrom("qw123hello@163.com");//设置发件人邮箱
		email.setSubject("aSubject");//设置邮件主题
		email.setMsg("msg");//发送邮件的信息
		email.send();*/
		Properties props = new Properties();  
        props.put("username", "qw123hello@163.com");   
        props.put("password", "782453628978as");   
        props.put("mail.transport.protocol", "smtp" );  
        props.put("mail.smtp.host", "smtp.163.com");  
        props.put("mail.smtp.port", "25" );  
        
        String qqnumber=request.getParameter("qqnumber")+"@qq.com";
        String qrpositon=request.getParameter("username");
       /* System.out.print("qqnumber=");
        System.out.print(qqnumber);
        System.out.print(qrpositon);*/
        String root = request.getSession().getServletContext().getRealPath("/");
        /*System.out.print(root);*/
        String file=root+"QrImages/"+qrpositon+".jpg";//设置发送文件路径
        Session mailSession = Session.getDefaultInstance(props);  
        Message msg = new MimeMessage(mailSession);     
        msg.setFrom(new InternetAddress("qw123hello@163.com"));  
        msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(qqnumber));   
        msg.setSubject("您好，您签到用的二维码！");   
        msg.setContent("<h1>您好，这是用来给你签到的二维码，请下载保存到手机中！</h1>","text/html;charset=UTF-8");
        Multipart mp = new MimeMultipart();  
        MimeBodyPart mbp = new MimeBodyPart();  
        FileDataSource fds=new FileDataSource(file); //得到数据源  
        mbp.setDataHandler(new DataHandler(fds)); //得到附件本身并至入BodyPart  
        mbp.setFileName(MimeUtility.encodeText(fds.getName()));  //得到文件名同样至入BodyPart  
       /* System.out.print(MimeUtility.encodeText(fds.getName()));*/
       /* MimeUtility.encodeText(fds.getName());*/
        mp.addBodyPart(mbp); 
        
        msg.setContent(mp); //Multipart加入到信件  
        msg.setSentDate(new Date());     //设置信件头的发送日期  
        
        msg.saveChanges();  
        Transport transport = mailSession.getTransport("smtp");  
        transport.connect(props.getProperty("mail.smtp.host"), props  
                .getProperty("username"), props.getProperty("password"));   
        transport.sendMessage(msg, msg.getAllRecipients());  
        transport.close();    
        JsonUtil.writeToResponse("done");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	}
    
	/**
	 * 带附件发邮件方法
	 * @throws EmailException 
	 * @throws IOException 
	 */
	public void  attachmentSend() throws EmailException, IOException{
	String root = request.getSession().getServletContext().getRealPath("/");
	System.out.print(root);
    response.setContentType("text/html;charset=utf-8");
    PrintWriter out=response.getWriter();
    EmailAttachment attachment=new EmailAttachment();
    attachment.setPath(root+"/QrImages/全伟聪.jpg");//附件路径
    attachment.setDisposition(EmailAttachment.ATTACHMENT);
    MultiPartEmail email=new MultiPartEmail();
    email.setHostName("smtp.163.com");//设置邮箱服务器
	email.setAuthentication("qw12345haha", "782453628978as");//
	try {
		email.addTo("qw123hello@163.com");//设置收件人邮箱
		email.setFrom("qw12345haha@163.com");//设置发件人邮箱用户名和密码
		email.setSubject("aSubject");//设置邮件主题
		email.setMsg("msg");//发送邮件的信息
		attachment.setName("22.mp3");    
        email.attach(attachment);    
		email.send();
		out.print("邮件发送成功！");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		out.print("邮件发送不成功！");
	}
	}
	
	/**
	 * //发送HTML形式邮件方法
	 * @throws EmailException 
	 */
	public void htmlSend() throws EmailException{
	HtmlEmail email=new HtmlEmail();
	email.setHostName("smtp.163.com");//设置邮箱服务器
	email.setAuthentication("userName", "password");//
	email.addTo("email");//设置发件人邮箱
	email.setFrom("email");//设置发件人邮箱用户名和密码
	email.setSubject("aSubject");//设置邮件主题
	email.setMsg("msg");//发送邮件的信息
	email.send();
	
	}
	
	 public static boolean sendMail(String subject, String toMail,  
	            String content, String... files) {  
	        boolean isFlag = false;  
	        try {  
	  
	            String smtpFromMail = "2603653389@qq.com";  //账号  
	            String pwd = "huangfei"; //密码  
	            int port = 25; //端口  
	            String host = "smtp.qq.com"; //端口  
	  
	            Properties props = new Properties();  
	            props.put("mail.smtp.host", host);  
	            props.put("mail.smtp.auth", "true");  
	            Session session = Session.getDefaultInstance(props);  
	            session.setDebug(false);  
	  
	            MimeMessage message = new MimeMessage(session);  
	            try {  
	                message.setFrom(new InternetAddress(smtpFromMail, "QQ邮件测试"));  
	                message.addRecipient(Message.RecipientType.TO,  
	                        new InternetAddress(toMail));  
	                message.setSubject(subject);  
	                message.addHeader("charset", "UTF-8");  
	                  
	                /*添加正文内容*/  
	                Multipart multipart = new MimeMultipart();  
	                BodyPart contentPart = new MimeBodyPart();  
	                contentPart.setText(content);  
	  
	                contentPart.setHeader("Content-Type", "text/html; charset=GBK");  
	                multipart.addBodyPart(contentPart);  
	                  
	                /*添加附件*/  
	                for (String file : files) {  
	                    File usFile = new File(file);  
	                    MimeBodyPart fileBody = new MimeBodyPart();  
	                    DataSource source = new FileDataSource(file);  
	                    fileBody.setDataHandler(new DataHandler(source));  
	                   /* sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();  
	                    fileBody.setFileName("=?GBK?B?"  
	                            + enc.encode(usFile.getName().getBytes()) + "?=");  */
	                    multipart.addBodyPart(fileBody);  
	                }  
	  
	                message.setContent(multipart);  
	                message.setSentDate(new Date());  
	                message.saveChanges();  
	                Transport transport = session.getTransport("smtp");  
	  
	                transport.connect(host, port, smtpFromMail, pwd);  
	                transport.sendMessage(message, message.getAllRecipients());  
	                transport.close();  
	                isFlag = true;  
	            } catch (Exception e) {  
	                isFlag = false;  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return isFlag;  
	    }  
	  
	    public static void main(String[] args) {  
	       sendMail("你好", "834865081@qq.com", "朋友好久不见" ,   
	                "C:/中文.jpg");  
	    }  
}
