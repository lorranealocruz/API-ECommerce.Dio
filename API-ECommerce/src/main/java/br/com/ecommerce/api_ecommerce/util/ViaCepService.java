package br.com.ecommerce.api_ecommerce.util;

import br.com.ecommerce.api_ecommerce.entity.Endereco;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private static final String URL = "https://viacep.com.br/ws/";

    public Endereco buscarEnderecoPorCep(String cep) {
        RestTemplate rest = new RestTemplate();
        return rest.getForObject(URL + cep + "/json/", Endereco.class);
    }
}