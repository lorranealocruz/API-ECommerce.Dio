package br.com.ecommerce.api_ecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender; 

    public void enviarEmailDeConfirmacao(String emailCliente, String nomeCliente) {
        
        LOG.info("Tentando enviar e-mail de confirmação para: " + emailCliente);

        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(emailCliente); 
        message.setSubject("Cadastro/Alteração no ECommerce - API"); 
        message.setText("Olá, " + nomeCliente + ".\n\nSeus dados foram atualizados com sucesso em nosso sistema.\n\nAtenciosamente,\nEquipe API ECommerce"); 

        try {
            emailSender.send(message);
            LOG.info("E-mail enviado com sucesso!");

        } catch (MailException e) {
            LOG.error("Erro ao enviar e-mail para " + emailCliente + ": " + e.getMessage());
        }
    }
}