package org.conocer.citas.service;

import org.conocer.citas.dto.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

@Service("mailService")
public class MailServiceImpl implements MailService{

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    public void sendEmail(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), emailFrom));
            mimeMessageHelper.setTo(mail.getMailTo());
            //mimeMessageHelper.setText(mail.getMailContent());
            mimeMessageHelper.setText ( "<html>" +
                    " <body>" +
                    " <div><strong>Estimado Usuario: </strong><div>" +
                    " <br>" +
                    "<div>Lamentamos informarle que el motivo de la cancelaci&oacute;n de la videollamada es el siguiente:</div>" +
                    "<div><strong>" + mail.getMailContent() + " </strong></div>" +
                    " <br>" +
                    "<div>Le invitamos a agendar nuevamente una cita.</div>" +
                    " <br>" +
                    " <br>" +
                    "<img src='cid:buttonImg' style='float:center;width:50%;height:30%;'/>" +
                    "</div> " +
                    "</body>" +
                    "</html>" , true);
            java.net.URL url = getClass().getResource("/image/conocer.png");
            File file = new File(url.getPath());
            mimeMessageHelper.addInline("buttonImg", file);
            mailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
