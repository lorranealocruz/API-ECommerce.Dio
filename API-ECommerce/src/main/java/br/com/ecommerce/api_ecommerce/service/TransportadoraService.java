package br.com.ecommerce.api_ecommerce.service;


import br.com.ecommerce.api_ecommerce.domain.Transportadora;
import br.com.ecommerce.api_ecommerce.dto.TransportadoraDTO;
import br.com.ecommerce.api_ecommerce.repository.TransportadoraRepository;
import br.com.ecommerce.api_ecommerce.exception.DatabaseException; 
import br.com.ecommerce.api_ecommerce.exception.ResourceNotFoundException; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportadoraService {

    @Autowired
    private TransportadoraRepository repositorio;

   
    @Transactional(readOnly = true) 
    public List<TransportadoraDTO> listarTodas() {
        List<Transportadora> listaDoBanco = repositorio.findAll();
   
        return listaDoBanco.stream().map(TransportadoraDTO::new).collect(Collectors.toList());
    }

 
    @Transactional(readOnly = true)
    public TransportadoraDTO buscarPorId(Long id) {
        Transportadora entidade = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transportadora não encontrada. ID: " + id));
        return new TransportadoraDTO(entidade);
    }


    @Transactional
    public TransportadoraDTO inserir(TransportadoraDTO dto) {
        Transportadora entidade = new Transportadora();
        copiarDtoParaEntidade(dto, entidade);
        entidade = repositorio.save(entidade);
        return new TransportadoraDTO(entidade);
    }


    @Transactional
    public TransportadoraDTO atualizar(Long id, TransportadoraDTO dto) {
  
        Transportadora entidade = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transportadora não encontrada. ID: " + id));

        copiarDtoParaEntidade(dto, entidade);
        entidade = repositorio.save(entidade);
        return new TransportadoraDTO(entidade);
    }


    public void deletar(Long id) {

        if (!repositorio.existsById(id)) {
            throw new ResourceNotFoundException("Transportadora não encontrada. ID: " + id);
        }
        try {
            repositorio.deleteById(id);
        }

        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não é possível deletar. Transportadora pode estar em uso.");
        }
    }


    private void copiarDtoParaEntidade(TransportadoraDTO dto, Transportadora entidade) {
        entidade.setNome(dto.getNome());
        entidade.setTelefone(dto.getTelefone());
        entidade.setTaxaBaseFrete(dto.getTaxaBaseFrete());
    }
}