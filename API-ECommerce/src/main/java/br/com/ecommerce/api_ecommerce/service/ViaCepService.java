package br.com.ecommerce.api_ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ecommerce.api_ecommerce.dto.EnderecoViaCepDTO;
import br.com.ecommerce.api_ecommerce.service.exceptions.CepNaoEncontradoException;

@Service
public class ViaCepService {
    
    public EnderecoViaCepDTO consultarCep(String cep) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            EnderecoViaCepDTO enderecoDTO = restTemplate.getForObject(url, EnderecoViaCepDTO.class);

            if (enderecoDTO == null || enderecoDTO.isErro()) {
                throw new CepNaoEncontradoException("CEP não encontrado ou inválido: " + cep);
            }

            return enderecoDTO;

        } catch (Exception e) {
            throw new CepNaoEncontradoException("Não foi possível consultar o CEP: " + cep + ".Erro" + e.getMessage());
        }
    }
}
