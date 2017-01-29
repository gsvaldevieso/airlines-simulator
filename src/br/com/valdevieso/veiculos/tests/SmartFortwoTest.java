package br.com.valdevieso.veiculos.tests;

import br.com.valdevieso.exceptions.MotoristaNaoHabilitadoException;
import br.com.valdevieso.personagens.ChefeServico;
import br.com.valdevieso.personagens.Comissaria;
import br.com.valdevieso.personagens.Piloto;
import br.com.valdevieso.personagens.Policial;
import br.com.valdevieso.veiculos.IVeiculo;
import br.com.valdevieso.veiculos.SmartFortwo;
import org.junit.Test;

import static org.junit.Assert.*;

public class SmartFortwoTest {
    @Test(expected = MotoristaNaoHabilitadoException.class)
    public void deveLancarExcessaoMotoristaNaoHabilitado() throws MotoristaNaoHabilitadoException {
        IVeiculo smart = new SmartFortwo();
        smart.setMotorista(new Comissaria());
    }

    @Test
    public void naoDeveLancarExcessaoMotoristaPiloto() throws MotoristaNaoHabilitadoException {
        IVeiculo smart = new SmartFortwo();
        smart.setMotorista(new Piloto());
    }

    @Test
    public void naoDeveLancarExcessaoMotoristaChefeServico() throws MotoristaNaoHabilitadoException {
        IVeiculo smart = new SmartFortwo();
        smart.setMotorista(new ChefeServico());
    }

    @Test
    public void naoDeveLancarExcessaoMotoristaPolicial() throws MotoristaNaoHabilitadoException {
        IVeiculo smart = new SmartFortwo();
        smart.setMotorista(new Policial());
    }
}