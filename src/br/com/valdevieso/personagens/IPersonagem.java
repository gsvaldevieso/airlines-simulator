package br.com.valdevieso.personagens;

import br.com.valdevieso.exceptions.ComissariaPilotoSozinhosException;
import br.com.valdevieso.exceptions.OficialSozinhoComChefeServicoException;
import br.com.valdevieso.exceptions.PresidiarioSemFiscalizacaoPolicialException;

import java.util.List;

public interface IPersonagem {
    void validarRestricoes(List<IPersonagem> personagens) throws ComissariaPilotoSozinhosException, OficialSozinhoComChefeServicoException, PresidiarioSemFiscalizacaoPolicialException;
    String getDescricao();
}
