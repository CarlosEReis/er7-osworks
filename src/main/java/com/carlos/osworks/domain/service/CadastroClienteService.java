package com.carlos.osworks.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos.osworks.domain.exception.NegocioException;
import com.carlos.osworks.domain.model.Cliente;
import com.carlos.osworks.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		Optional<Cliente> clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		if (clienteExistente.isPresent() && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastradcom este e-mail.");
		}
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}
