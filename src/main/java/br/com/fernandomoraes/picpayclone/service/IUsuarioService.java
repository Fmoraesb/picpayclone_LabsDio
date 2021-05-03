package br.com.fernandomoraes.picpayclone.service;

import br.com.fernandomoraes.picpayclone.dto.UsuarioDTO;
import br.com.fernandomoraes.picpayclone.model.Transacao;
import br.com.fernandomoraes.picpayclone.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario consultarEntidade(String login);

    void validar(Usuario...usuarios);

    void atualizarSaldo(Transacao transacao, Boolean isCartaoCredito);

    UsuarioDTO consultar(String login);

    List<UsuarioDTO> listar(String login);
}
