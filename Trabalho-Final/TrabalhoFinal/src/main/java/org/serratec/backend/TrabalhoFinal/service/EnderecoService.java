package org.serratec.backend.TrabalhoFinal.service;

import org.serratec.backend.TrabalhoFinal.domain.Endereco;
import org.serratec.backend.TrabalhoFinal.dto.EnderecoResponseDTO;

public interface EnderecoService {
	
	Endereco buscarCep(String cep);
	EnderecoResponseDTO inserirCep(Endereco endereco);
}
