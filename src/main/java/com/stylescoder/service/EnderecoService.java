package com.stylescoder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stylescoder.entity.Endereco;

@Service
public class EnderecoService {

    public Endereco buscarEnderecoPorCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://viacep.com.br/ws/" + cep + "/json/", Endereco.class);
    }
}
