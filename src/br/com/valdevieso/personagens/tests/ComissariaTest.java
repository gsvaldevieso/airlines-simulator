package br.com.valdevieso.personagens.tests;

import br.com.valdevieso.exceptions.ComissariaPilotoSozinhosException;
import br.com.valdevieso.personagens.ChefeServico;
import br.com.valdevieso.personagens.Comissaria;
import br.com.valdevieso.personagens.IPersonagem;
import br.com.valdevieso.personagens.Piloto;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;

public class ComissariaTest {

    @org.junit.Test(expected = ComissariaPilotoSozinhosException.class)
    public void deveLancarExcessaoParaPilotoComissariaSozinhos() throws Exception {
        IPersonagem comissaria = new Comissaria();

        List<IPersonagem> personagens = new ArrayList<>();
        personagens.add(new Piloto());
        personagens.add(new Comissaria());

        comissaria.validarRestricoes(personagens);
    }

    @org.junit.Test
    public void naoDeveLancarExcessaoParaPilotoComissariaAcompanhados() throws Exception {
        IPersonagem comissaria = new Comissaria();

        List<IPersonagem> personagens = new ArrayList<>();
        personagens.add(new Piloto());
        personagens.add(new Comissaria());
        personagens.add(new ChefeServico());

        comissaria.validarRestricoes(personagens);
    }

    @org.junit.Test
    public void deveRetornarDescricaoCorretaComissaria() throws Exception {
        IPersonagem comissaria = new Comissaria();
        Assert.assertEquals(comissaria.getDescricao(),"Comissária");
    }
}