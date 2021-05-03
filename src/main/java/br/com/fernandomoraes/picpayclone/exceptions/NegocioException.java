package br.com.fernandomoraes.picpayclone.exceptions;

public class NegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NegocioException(String mensagem) {
        super(mensagem);
    }

    public NegocioException(String mensagem, Exception exception) {
        super(mensagem,exception);
    }
}
