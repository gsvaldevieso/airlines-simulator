package br.com.valdevieso.veiculos;

import br.com.valdevieso.personagens.IPersonagem;
import java.util.ArrayList;
import java.util.List;

public class Aviao {
    private List<IPersonagem> passageiros;

    public Aviao(){
        this.passageiros = new ArrayList<IPersonagem>();
    }
    public List<IPersonagem> getPassageiros() {
        return passageiros;
    }

    public void addPassageiro(IPersonagem personagem){
        if(personagem != null)
            this.passageiros.add(personagem);
    }

    public void removerPassageiro(IPersonagem passageiro){
        this.passageiros.remove(passageiro);
    }

    public void setPassageiros(List<IPersonagem> passageiros) {
        this.passageiros = passageiros;
    }
}
