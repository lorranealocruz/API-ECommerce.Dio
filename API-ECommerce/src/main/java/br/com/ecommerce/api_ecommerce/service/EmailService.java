package br.com.ecommerce.api_ecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
    
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    public void enviarEmailDeConfirmacao(String emailCliente, String nomeCliente) {
        LOG.info("--Simulando envio de email--");
        LOG.info("Para: " + emailCliente);
        LOG.info("Assunto: Cadastro/Alteração no ECommerce");
        LOG.info("Corpo: Olá, " + nomeCliente + ". Seus dados foram atualizados com sucesso.");
        LOG.info("--Fim da simulação--");
    }
}
