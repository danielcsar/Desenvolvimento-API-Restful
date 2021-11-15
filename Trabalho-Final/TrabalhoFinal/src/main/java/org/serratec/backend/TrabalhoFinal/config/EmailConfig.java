package org.serratec.backend.TrabalhoFinal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailConfig {
	
	@Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("grupo01testeapi@gmail.com");
        message.setTo(para);
        message.setSubject(assunto);
        message.setText("Pedido nº: \n " + texto + "\n \n Serratec Residência Teresópolis");
        javaMailSender.send(message);
    }
}
