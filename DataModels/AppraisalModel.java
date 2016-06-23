import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class AppraisalModel{	

	DataAccess dataAccess; 

	LowAppraisalComponent eventComponent = new LowAppraisalComponent();

	public HashMap<String,Object> getAppraisalsOfDept(int deptID){
		HashMap<String,Object> dataset = new HashMap<String,Object>();		
		try {
			dataAccess = new DataAccess();
			dataset = dataAccess.getAppraisalsOfDepartment(deptID);
			if(dataset.size()>0){
				if((int)dataset.get("Rating") < 3){
					int managerID = (int)dataset.get("ManagerID");
					//eventComponent.addEventListener(new LowAppraisalEventListener(){
					//public void lessRatingEventRecieved(LowAppraisalEvent event){
					sendMailToManager(managerID);
					//});					 
					//}
				}
				printAppraisal(dataset);
			}
			else
				System.out.println("Invalid department id or there are no records");
		} 
		catch(Exception e) {
			e.printStackTrace();
		}	
		return dataset;		
	}

	private void sendMailToManager(int managerID) {

		dataAccess = new DataAccess();		
		String to = dataAccess.getEmailId(managerID);

		Properties mailServerProperties;
		Session getMailSession;
		MimeMessage generateMailMessage;
		String uname="JavaProjectHR@gmail.com";
		String pwd="javaisinteresting";
		String smtphost="smtp.gmail.com";
		String smtpPort="587";
		String senderName="HR";

		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.host", smtphost);
		mailServerProperties.put("mail.smtp.port", smtpPort);
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");

		try {
			getMailSession = Session.getInstance(mailServerProperties,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(uname, pwd);
				}
			});



			generateMailMessage = new MimeMessage(getMailSession);
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			generateMailMessage.setSubject("Assign trainings to employees for performance improvement");
			generateMailMessage.setFrom(senderName);
			String emailBody = ("Dear Manager, <br> <br>"
					+ "This is an auto generated e-mail.<br> <br>"
					+ "Kindly assign the trainings for the employees in your department whose rating is below average.<br> <br>"
					+ "Regards,<br>"
					+ "HR");
			generateMailMessage.setContent(emailBody, "text/html");

			Transport.send(generateMailMessage);
			System.out.println("\n\n Email sent Successfully to Manager");
		} catch (AddressException e) {

		} catch (MessagingException e) {

			e.printStackTrace();
		}
	}




	private void printAppraisal(HashMap<String,Object> data){

		Object[] columns = data.keySet().toArray();
		Object[] values = data.values().toArray();
		String leftAlignFormat = "| %-5s ";
		System.out.format(
				"%n+--------------------------------------------------------------------------------------------+%n");
		for (int j = 0; j < columns.length; j++) {
			System.out.format("|" + columns[j] + "	");
		}
		System.out.format(
				"%n+--------------------------------------------------------------------------------------------+%n");
		for (int j = 0; j < values.length; j++) {
			System.out.format(leftAlignFormat, values[j].toString());
		}
		System.out.format(
				"%n+--------------------------------------------------------------------------------------------+%n");
	}

}
