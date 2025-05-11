package br.com.professorisidro.naturassp.service;

import br.com.professorisidro.naturassp.model.Cliente;

public interface IClienteService {

    public Cliente buscarPeloCPF(String cpf);
    public Cliente atualizarDados(Cliente dadosOriginais);


}
