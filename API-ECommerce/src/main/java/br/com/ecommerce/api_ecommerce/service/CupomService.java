package br.com.ecommerce.api_ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecommerce.api_ecommerce.domain.Cupom;
import br.com.ecommerce.api_ecommerce.dto.CupomDTO;
import br.com.ecommerce.api_ecommerce.repository.CupomRepository;
import br.com.ecommerce.api_ecommerce.exception.ResourceNotFoundException;
import br.com.ecommerce.api_ecommerce.exception.BadRequestException;

@Service
public class CupomService {

    @Autowired
    private CupomRepository repository;

    @Transactional(readOnly = true)
    public List<CupomDTO> findAll() {
        List<Cupom> list = repository.findAll();
        return list.stream().map(CupomDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CupomDTO findById(Long id) {
        Cupom entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cupom não encontrado com ID: " + id));
        return new CupomDTO(entity);
    }

    @Transactional
    public CupomDTO insert(CupomDTO dto) {
        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new BadRequestException("Código de cupom já cadastrado: " + dto.getCodigo());
        }

        Cupom entity = new Cupom();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CupomDTO(entity);
    }

    @Transactional
    public CupomDTO update(Long id, CupomDTO dto) {
        Cupom entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cupom não encontrado com ID: " + id));

        if (!entity.getCodigo().equals(dto.getCodigo()) && repository.existsByCodigo(dto.getCodigo())) {
             throw new BadRequestException("Código de cupom já cadastrado: " + dto.getCodigo());
        }

        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CupomDTO(entity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Cupom não encontrado com ID: " + id);
        }
        try {
            repository.deleteById(id);
        }
        catch (Exception e) {
            throw new BadRequestException("Não foi possível deletar o cupom. Possível causa: " + e.getMessage());
        }
    }

    private void copyDtoToEntity(CupomDTO dto, Cupom entity) {
        entity.setCodigo(dto.getCodigo());
        entity.setPercentualDesconto(dto.getPercentualDesconto());
        entity.setDataValidade(dto.getDataValidade());
        if (dto.getAtivo() != null) {
            entity.setAtivo(dto.getAtivo());
        }
    }
}