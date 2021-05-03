package br.com.fernandomoraes.picpayclone.service;

import br.com.fernandomoraes.picpayclone.dto.CartaoCreditoDTO;
import br.com.fernandomoraes.picpayclone.model.CartaoCredito;


public interface ICartaoCreditoService {

    CartaoCreditoDTO salvar(CartaoCreditoDTO cartaoCredito);
}
