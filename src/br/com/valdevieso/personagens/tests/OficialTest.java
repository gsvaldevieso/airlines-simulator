package br.com.valdevieso.personagens.tests;

import br.com.valdevieso.exceptions.OficialSozinhoComChefeServicoException;
import br.com.valdevieso.personagens.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OficialTest {
    @Test(expected = OficialSozinhoComChefeServicoException.class)
    public void deveLancarExcessaoOficialChefeServicoSozinhos() throws Exception {
        IPersonagem oficial = new Oficial();

        List<IPersonagem> personagens = new ArrayList<>();
        personagens.add(new Oficial());
        personagens.add(new ChefeServico());

        oficial.validarRestricoes(personagens);
    }

    @Test
    public void naoDeveLancarExcessaoOficialChefeServicoAcompanhados() throws Exception {
        IPersonagem oficial = new Oficial();

        List<IPersonagem> personagens = new ArrayList<>();
        personagens.add(new Oficial());
        personagens.add(new ChefeServico());
        personagens.add(new Comissaria());

        oficial.validarRestricoes(personagens);
    }

    @org.junit.Test
    public void deveRetornarDescricaoCorretaOficial() throws Exception {
        IPersonagem comissaria = new Oficial();
        Assert.assertEquals(comissaria.getDescricao(),"Oficial");
    }

}