package br.com.fernandomoraes.picpayclone.service.impl;

import br.com.fernandomoraes.picpayclone.conversor.CartaoCreditoConversor;
import br.com.fernandomoraes.picpayclone.dto.CartaoCreditoDTO;
import br.com.fernandomoraes.picpayclone.model.CartaoCredito;
import br.com.fernandomoraes.picpayclone.repository.CartaoCreditoRepository;
import br.com.fernandomoraes.picpayclone.service.ICartaoCreditoService;
import br.com.fernandomoraes.picpayclone.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoCreditoService implements ICartaoCreditoService {

    @Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;

    @Autowired
    private CartaoCreditoConversor cartaoCreditoConversor;

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public CartaoCreditoDTO salvar(CartaoCreditoDTO cartaoCreditoDTO) {
        CartaoCreditoDTO cartaoCreditoRetorno = null;
        if (cartaoCreditoDTO.getIsSalva()) {
            CartaoCredito cartaoCredito = cartaoCreditoConversor.converterDtoParaEntidade(cartaoCreditoDTO);
            usuarioService.validar(cartaoCredito.getUsuario());
            CartaoCredito cartaoCreditoSalvo = cartaoCreditoRepository.save(cartaoCredito);
            cartaoCreditoRetorno = cartaoCreditoConversor.converterEntidadeParaDto(cartaoCreditoSalvo);
        }
        return cartaoCreditoRetorno;
    }
}
