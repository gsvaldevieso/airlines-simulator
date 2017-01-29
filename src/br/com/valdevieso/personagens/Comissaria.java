package br.com.valdevieso.personagens;

import br.com.valdevieso.exceptions.ComissariaPilotoSozinhosException;

import java.util.List;

public class Comissaria implements IPersonagem {

    @Override
    public void validarRestricoes(List<IPersonagem> personagens) throws ComissariaPilotoSozinhosException {
        if(personagens.size() == 2){
            for(IPersonagem personagem : personagens)
                if(personagem instanceof Piloto)
                    throw new ComissariaPilotoSozinhosException("Comissária e piloto ficaram sozinhos.");
        }
    }

    @Override
    public String getDescricao() {
        return "Comissária";
    }
}
