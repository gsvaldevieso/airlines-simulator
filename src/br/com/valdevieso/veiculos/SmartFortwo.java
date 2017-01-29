package br.com.valdevieso.veiculos;

import br.com.valdevieso.exceptions.MotoristaNaoHabilitadoException;
import br.com.valdevieso.personagens.ChefeServico;
import br.com.valdevieso.personagens.IPersonagem;
import br.com.valdevieso.personagens.Piloto;
import br.com.valdevieso.personagens.Policial;

import java.util.ArrayList;
import java.util.List;

public class SmartFortwo implements IVeiculo {
    private IPersonagem motorista;
    private IPersonagem carona;

    @Override
    public IPersonagem getMotorista() {
        return motorista;
    }

    public void setMotorista(IPersonagem motorista) throws MotoristaNaoHabilitadoException {
        if(motorista == null) {
            this.motorista = null;
            return;
        }

        if(motorista instanceof Piloto || motorista instanceof Policial || motorista instanceof ChefeServico) {
            this.motorista = motorista;
        } else
            throw new MotoristaNaoHabilitadoException("O motorista nao esta habilitado a dirigir o veiculo.");
    }

    @Override
    public IPersonagem getCarona() {
        return carona;
    }

    @Override
    public List<IPersonagem> getTripulantes() {
        List<IPersonagem> tripulantes = new ArrayList<IPersonagem>();
        tripulantes.add(this.getMotorista());
        tripulantes.add(this.getCarona());

        return tripulantes;
    }

    public void setCarona(IPersonagem carona) {
        this.carona = carona;
    }
}
