package br.com.fernandomoraes.picpayclone.service;

import br.com.fernandomoraes.picpayclone.dto.TransacaoDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface ITransacaoService {

    TransacaoDTO processar(TransacaoDTO transacaoDTO);

    Page<TransacaoDTO> listar(Pageable paginacao, String login);
}
