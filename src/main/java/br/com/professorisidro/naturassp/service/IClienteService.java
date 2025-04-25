package br.com.professorisidro.naturassp.service;

import br.com.professorisidro.naturassp.model.Cliente;

public interface IClienteService {

    public Cliente buscarPeloTelefone(String telefone);
    public Cliente atualizarDados(Cliente dadosOriginais);
}
