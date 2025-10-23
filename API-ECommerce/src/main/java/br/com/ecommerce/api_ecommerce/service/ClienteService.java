package br.com.ecommerce.api_ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.api_ecommerce.dto.ClienteInsertDTO;
import br.com.ecommerce.api_ecommerce.dto.ClienteResponseDTO;
import br.com.ecommerce.api_ecommerce.dto.EnderecoViaCepDTO;
import br.com.ecommerce.api_ecommerce.entity.Cliente;
import br.com.ecommerce.api_ecommerce.entity.Endereco;
import br.com.ecommerce.api_ecommerce.exception.CpfJaCadastradoException;
import br.com.ecommerce.api_ecommerce.exception.EmailJaCadastradoException;
import br.com.ecommerce.api_ecommerce.repository.ClienteRepository;
import br.com.ecommerce.api_ecommerce.service.exceptions.CepNaoEncontradoException; 

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private EmailService emailService;

    public ClienteResponseDTO inserir(ClienteInsertDTO dto) {
        if (repository.existsByCpf(dto.getCpf())) {
            throw new CpfJaCadastradoException("CPF já cadastrado!");
        }

        if (repository.existsByEmail(dto.getEmail())) {
            throw new EmailJaCadastradoException("E-mail já cadastrado!");
        }

        Cliente cliente = new Cliente(dto);

        try {
            EnderecoViaCepDTO enderecoDTO = viaCepService.consultarCep(dto.getCep());
            
            Endereco endereco = new Endereco(); 
            endereco.setLogradouro(enderecoDTO.getLogradouro());
            endereco.setBairro(enderecoDTO.getBairro());
            endereco.setLocalidade(enderecoDTO.getLocalidade());
            endereco.setUf(enderecoDTO.getUf());
            endereco.setCep(dto.getCep()); 
            
            cliente.setEndereco(endereco); 
            
        } catch (CepNaoEncontradoException e) {
            throw new CepNaoEncontradoException("CEP não encontrado: " + dto.getCep());
        }

        repository.save(cliente);
        
        emailService.enviarEmailDeConfirmacao(cliente.getEmail(), cliente.getNome());

        return new ClienteResponseDTO(cliente);
    }

    public List<ClienteResponseDTO> listar() {
        List<Cliente> clientes = repository.findAll();
        return clientes.stream()
                .map(ClienteResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO atualizar(Long id, ClienteInsertDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (!cliente.getCpf().equals(dto.getCpf()) && repository.existsByCpf(dto.getCpf())) {
            throw new CpfJaCadastradoException("CPF já cadastrado!");
        }

        if (!cliente.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())) {
            throw new EmailJaCadastradoException("E-mail já cadastrado!");
        }

        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEmail(dto.getEmail());

        try {
            EnderecoViaCepDTO enderecoDTO = viaCepService.consultarCep(dto.getCep());
            
            Endereco endereco = cliente.getEndereco(); 
            if (endereco == null) {
                endereco = new Endereco(); 
            }
            
            endereco.setLogradouro(enderecoDTO.getLogradouro());
            endereco.setBairro(enderecoDTO.getBairro());
            endereco.setLocalidade(enderecoDTO.getLocalidade());
            endereco.setUf(enderecoDTO.getUf());
            endereco.setCep(dto.getCep()); 
            
            cliente.setEndereco(endereco); 
            
        } catch (CepNaoEncontradoException e) {
            throw new CepNaoEncontradoException("CEP não encontrado: " + dto.getCep());
        }
        
        repository.save(cliente);
        
        emailService.enviarEmailDeConfirmacao(cliente.getEmail(), cliente.getNome());

        return new ClienteResponseDTO(cliente);
    }

    public void deletar(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        repository.delete(cliente);
    }
}