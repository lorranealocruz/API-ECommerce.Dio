package br.com.ecommerce.api_ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.api_ecommerce.dto.ClienteInsertDTO;
import br.com.ecommerce.api_ecommerce.dto.ClienteResponseDTO;
import br.com.ecommerce.api_ecommerce.entity.Cliente;
import br.com.ecommerce.api_ecommerce.repository.ClienteRepository;
import br.com.ecommerce.api_ecommerce.exception.CpfJaCadastradoException;
import br.com.ecommerce.api_ecommerce.exception.EmailJaCadastradoException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    // Criar cliente
    public ClienteResponseDTO inserir(ClienteInsertDTO dto) {
        if (repository.existsByCpf(dto.getCpf())) {
            throw new CpfJaCadastradoException("CPF já cadastrado!");
        }

        if (repository.existsByEmail(dto.getEmail())) {
            throw new EmailJaCadastradoException("E-mail já cadastrado!");
        }

        Cliente cliente = new Cliente(dto);
        repository.save(cliente);

        return new ClienteResponseDTO(cliente);
    }

    // Listar clientes
    public List<ClienteResponseDTO> listar() {
        List<Cliente> clientes = repository.findAll();
        return clientes.stream()
                .map(ClienteResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Atualizar cliente
    public ClienteResponseDTO atualizar(Long id, ClienteInsertDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Validar CPF e email diferentes do próprio cliente
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

        repository.save(cliente);

        return new ClienteResponseDTO(cliente);
    }

    // Deletar cliente
    public void deletar(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        repository.delete(cliente);
    }
}
