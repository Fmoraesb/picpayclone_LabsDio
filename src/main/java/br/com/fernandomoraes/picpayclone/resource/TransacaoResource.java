package br.com.fernandomoraes.picpayclone.resource;

import br.com.fernandomoraes.picpayclone.dto.TransacaoDTO;
import br.com.fernandomoraes.picpayclone.service.ITransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.awt.print.Pageable;

@RestController
@RequestMapping("/transacoes")
public class TransacaoResource extends ResourceBase<TransacaoDTO>{

    @Autowired
    private ITransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<TransacaoDTO> salvar(@RequestBody @Valid TransacaoDTO transacaoDTO,
                                               UriComponentsBuilder uribuilder) {
        TransacaoDTO transacaoRetornoDTO = transacaoService.processar(transacaoDTO);
        return responderItemCriadoComUri(transacaoRetornoDTO, uribuilder,"/transacoes/{codigo}",
                transacaoRetornoDTO.getCodigo());
    }

    @GetMapping
    public ResponseEntity<Page<TransacaoDTO>> listar(@PageableDefault(page = 0, size = 20)
                                                           Pageable paginacao,
                                               @RequestParam String login) {
        Page<TransacaoDTO> transacoes = transacaoService.listar(paginacao,login);
        return responderListaDeItensPaginada(transacoes);
    }
}
