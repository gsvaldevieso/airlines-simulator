package br.com.valdevieso.personagens;

import java.util.List;

public class ChefeServico implements IPersonagem {
    @Override
    public void validarRestricoes(List<IPersonagem> personagens) {}

    @Override
    public String getDescricao() {
        return "Chefe de serviço";
    }
}
