package com.presidio.reportcard.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.presidio.reportcard.model.MarkRecord;
import com.presidio.reportcard.model.Student;
import com.presidio.reportcard.repository.StudentRepository;
import com.sun.istack.ByteArrayDataSource;

@Service
public class ReportcardService {

	@Autowired
	private StudentRepository sr;
	
	private static final String FROM_ID = "xxxx@gmail.com";
	private static final String FROM_ID_PASSWORD = "xxxx";
	private static final String SUBJECT = "Report Card";
	private static final String CONTENT = "Dear Student,\n\tPlease find attached your updated Report Card.\n\nThanks & Regards\nHM,\nPresidio";
	
	@Transactional
	private Student findsStudentByRegisterNo(String registerNo) {
		return sr.findStudentByRegisterNo(registerNo);
	}
	
	public void generate(HttpServletResponse response, String registerNo) throws Exception {
		
		Student s = findsStudentByRegisterNo(registerNo);			
		
		Document doc = new Document(PageSize.A4);
		PdfWriter.getInstance(doc, response.getOutputStream());
		
		doc.open();
		Font font1 = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);
		Font font2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
		
		Paragraph para = new Paragraph();
		para.add(new Chunk("Presidio Training Institute\n\n", font2));
		
		para.add(Chunk.NEWLINE);
		para.add(Chunk.NEWLINE);
		
		if (s == null) {
			para.add(new Chunk("******Student not found******", font2));
			doc.add(para);
			doc.close();
			return;
		}
		
		para.add(new Chunk("Register No: " + s.getRegisterNo() + "\n", font1));
		para.add(Chunk.NEWLINE);
		para.add(new Chunk("Name: " + s.getName() + "\n", font1));	
		para.add(Chunk.NEWLINE);
		para.add(new Chunk("DOB: " + s.getDob() + "\n", font1));	
		para.add(Chunk.NEWLINE);
		para.add(new Chunk("Father's Name: " + s.getFatherName() + "\n", font1));	
		para.add(Chunk.NEWLINE);
		para.add(new Chunk("Mother's Name: " + s.getMotherName() + "\n", font1));	
		para.add(Chunk.NEWLINE);
		para.add(new Chunk("Phone: " + s.getPhone() + "\n", font1));	
		para.add(Chunk.NEWLINE);
		para.add(new Chunk("Mail: " + s.getMailId() + "\n", font1));
		para.add(Chunk.NEWLINE);
		
		doc.add(para);
		doc.newPage();
		
		List<MarkRecord> marks = s.getMarks();
		marks.sort((MarkRecord mr1, MarkRecord mr2) -> mr1.getTerm()-mr2.getTerm() );
		
		for (MarkRecord mr: marks) {
			para = new Paragraph();
			
			para.add(new Chunk("ACADEMIC PERFORMANCE:\n\n", font2));
			para.add(Chunk.NEWLINE);doc.add(Chunk.NEWLINE);
			para.add(new Chunk("Term: " + mr.getTerm() + "\n", font1));
			para.add(Chunk.NEWLINE);
			para.add(new Chunk("Maths: " + mr.getMaths() + "\n", font1));
			para.add(Chunk.NEWLINE);
			para.add(new Chunk("Physics: " + mr.getPhysics() + "\n", font1));
			para.add(Chunk.NEWLINE);
			para.add(new Chunk("Chemistry: " + mr.getChemistry() + "\n", font1));
			para.add(Chunk.NEWLINE);
			para.add(new Chunk("Biology: " + mr.getBiology() + "\n", font1));
			para.add(Chunk.NEWLINE);
			para.add(new Chunk("History: " + mr.getHistory() + "\n", font1));
			para.add(Chunk.NEWLINE);
			para.add(new Chunk("Total: " + mr.getTotal() + "\n", font1));
			para.add(Chunk.NEWLINE);
			para.add(new Chunk("Average: " + mr.getAvg() + "\n", font1));
			para.add(Chunk.NEWLINE);
			
			doc.add(para);
			doc.newPage();
		}
		
		doc.close();
		
	}
	
	private void generate(OutputStream os, Student s) throws Exception {
		
		Document doc = new Document(PageSize.A4);
		PdfWriter.getInstance(doc, os);
		
		doc.open();
		Font font1 = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);
		Font font2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
		
		Paragraph para = new Paragraph();
		para.add(new Chunk("Presidio Training Institute\n\n", font2));
		
		para.add(Chunk.NEWLINE);
		para.add(Chunk.NEWLINE);
		
		if (s == null) {
			para.add(new Chunk("******Student not found******", font2));
			doc.add(para);
			doc.close();
			return;
		}
		
		para.add(new Chunk("Register No: " + s.getRegisterNo() + "\n", font1));
		para.add(Chunk.NEWLINE);
		para.add(new Chunk("Name: " + s.getName() + "\n", font1));	
		para.add(Chunk.NEWLINE);
		para.add(new Chunk("DOB: " + s.getDob() + "\n", font1));	
		para.add(Chunk.NEWLINE);
		para.add(new Chunk("Father's Name: " + s.getFatherName() + "\n", font1));	
		para.add(Chunk.NEWLINE);
		para.add(new Chunk("Mother's Name: " + s.getMotherName() + "\n", font1));	
		para.add(Chunk.NEWLINE);
		para.add(new Chunk("Phone: " + s.getPhone() + "\n", font1));	
		para.add(Chunk.NEWLINE);
		para.add(new Chunk("Mail: " + s.getMailId() + "\n", font1));
		para.add(Chunk.NEWLINE);
		
		doc.add(para);
		doc.newPage();
		
		List<MarkRecord> marks = s.getMarks();
		marks.sort((MarkRecord mr1, MarkRecord mr2) -> mr1.getTerm()-mr2.getTerm() );
		
		for (MarkRecord mr: marks) {
			para = new Paragraph();
			
			para.add(new Chunk("ACADEMIC PERFORMANCE:\n\n", font2));
			para.add(Chunk.NEWLINE);doc.add(Chunk.NEWLINE);
			para.add(new Chunk("Term: " + mr.getTerm() + "\n", font1));
			para.add(Chunk.NEWLINE);
			para.add(new Chunk("Maths: " + mr.getMaths() + "\n", font1));
			para.add(Chunk.NEWLINE);
			para.add(new Chunk("Physics: " + mr.getPhysics() + "\n", font1));
			para.add(Chunk.NEWLINE);
			para.add(new Chunk("Chemistry: " + mr.getChemistry() + "\n", font1));
			para.add(Chunk.NEWLINE);
			para.add(new Chunk("Biology: " + mr.getBiology() + "\n", font1));
			para.add(Chunk.NEWLINE);
			para.add(new Chunk("History: " + mr.getHistory() + "\n", font1));
			para.add(Chunk.NEWLINE);
			para.add(new Chunk("Total: " + mr.getTotal() + "\n", font1));
			para.add(Chunk.NEWLINE);
			para.add(new Chunk("Average: " + mr.getAvg() + "\n", font1));
			para.add(Chunk.NEWLINE);
			
			doc.add(para);
			doc.newPage();
		}
		
		doc.close();
		
	}
	
	public int mail(String registerNo) {

		Student s = findsStudentByRegisterNo(registerNo);
		if (s == null) {
			return 1;
		}
		
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.socketFactory.port", 465);
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM_ID, FROM_ID_PASSWORD);
			}
			
		});
		
		ByteArrayOutputStream baos = null;
		try {
			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setText(CONTENT);
			
			baos = new ByteArrayOutputStream();
			generate(baos, s);
			byte[] bytes = baos.toByteArray();
			
			DataSource ds = new ByteArrayDataSource(bytes, "application/pdf");
			MimeBodyPart pdfBodyPart = new MimeBodyPart();
			pdfBodyPart.setDataHandler(new DataHandler(ds));
			pdfBodyPart.setFileName("reportcard-" + s.getRegisterNo() + ".pdf");
			
			MimeMultipart mmp = new MimeMultipart();
			mmp.addBodyPart(bodyPart);
			mmp.addBodyPart(pdfBodyPart);
			
			MimeMessage msg = new MimeMessage(session);
			msg.setSender(new InternetAddress(FROM_ID));
			msg.setSubject(SUBJECT);
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(s.getMailId()));
			msg.setContent(mmp);
			
			Transport.send(msg);			
			
		} catch (Exception e) {
			return 2;
		}
		
		return 0;
		
	}
	
}
