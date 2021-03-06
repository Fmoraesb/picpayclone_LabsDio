package br.com.fernandomoraes.picpayclone.service.impl;

import br.com.fernandomoraes.picpayclone.conversor.UsuarioConversor;
import br.com.fernandomoraes.picpayclone.dto.UsuarioDTO;
import br.com.fernandomoraes.picpayclone.exceptions.NegocioException;
import br.com.fernandomoraes.picpayclone.model.Transacao;
import br.com.fernandomoraes.picpayclone.model.Usuario;
import br.com.fernandomoraes.picpayclone.repository.UsuarioRepository;
import br.com.fernandomoraes.picpayclone.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioConversor usuarioConversor;

    @Override
    public Usuario consultarEntidade(String login) {
        return usuarioRepository.findByLogin(login);
    }

    @Override
    public void validar(Usuario...usuarios) {
        Arrays.asList(usuarios).stream().forEach(usuario -> {
            if(usuario == null) {
                throw new NegocioException("O Usuário informado não existe");
            }
        });
    }

    @Override
    @Async("asyncExecutor")
    public void atualizarSaldo(Transacao transacao, Boolean isCartaoCredito) {
        decrementarSaldo(transacao, isCartaoCredito);
        incrementarSaldo(transacao);
    }

    private void incrementarSaldo(Transacao transacaoSalva) {
        usuarioRepository.updateIncrementarSaldo(transacaoSalva.getDestino().getLogin(),
                transacaoSalva.getValor());
    }

    private void decrementarSaldo(Transacao transacaoSalva, Boolean isCartaoCredito) {
        if (!isCartaoCredito) {
            usuarioRepository.updateDecrementarSaldo(transacaoSalva.getOrigem().getLogin(),
                    transacaoSalva.getValor());
        }
    }

    @Override
    public UsuarioDTO consultar(String login) {
        Usuario usuario = consultarEntidade(login);
        return usuarioConversor.converterEntidadeParaDto(usuario);
    }

    @Override
    public List<UsuarioDTO> listar(String login) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Usuario> usuariosFiltrados = usuarios.stream().filter(v -> !v.getLogin().equals(login))
                .collect(Collectors.toList());
        return usuarioConversor.converterEntidadesParaDtos(usuariosFiltrados);
    }
}
