package br.com.valdevieso.personagens;

import br.com.valdevieso.exceptions.OficialSozinhoComChefeServicoException;

import java.util.List;

public class Oficial implements IPersonagem {

    @Override
    public void validarRestricoes(List<IPersonagem> personagens) throws OficialSozinhoComChefeServicoException {
        if(personagens.size() == 2){
            for(IPersonagem personagem : personagens)
                if(personagem instanceof ChefeServico)
                    throw new OficialSozinhoComChefeServicoException("Oficial e chefe de serviço ficaram sozinhos.");
        }
    }

    @Override
    public String getDescricao() {
        return "Oficial";
    }

}
