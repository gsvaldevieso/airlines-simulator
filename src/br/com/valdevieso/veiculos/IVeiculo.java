package br.com.valdevieso.veiculos;

import br.com.valdevieso.exceptions.MotoristaNaoHabilitadoException;
import br.com.valdevieso.personagens.IPersonagem;

import java.util.List;

public interface IVeiculo {
    void setMotorista(IPersonagem motorista) throws MotoristaNaoHabilitadoException;
    void setCarona(IPersonagem carona);
    IPersonagem getMotorista();
    IPersonagem getCarona();
    List<IPersonagem> getTripulantes();
}
