package br.com.fernandomoraes.picpayclone.repository;

import br.com.fernandomoraes.picpayclone.model.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    Page<Transacao> findByOrigem_LoginOrDestino_Login(String login, String login1, Pageable paginacao);
}
