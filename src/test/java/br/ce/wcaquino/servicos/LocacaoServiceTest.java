package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	private LocacaoService service;
	
	@Rule
	public ErrorCollector erros = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();


	@Before
	public void setup() {
		service = new LocacaoService();
	}
	
	@Test
	public void teste() throws Exception {
		// cenario
		Usuario usuario = new Usuario("Vinicius");
		Filme filme = new Filme("Titanic", 5, 10.0);

		// acao
		Locacao locacao = service.alugarFilme(usuario, filme);

		erros.checkThat(locacao.getValor(), is(10.0));
		erros.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		erros.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				is(true));
	}

	@Test(expected = FilmeSemEstoqueException.class)
	public void testLocacaoFilmeSemEstoque() throws Exception {
		// cenario
		Usuario usuario = new Usuario("Vinicius");
		Filme filme = new Filme("Titanic", 0, 10.0);

		// acao
		service.alugarFilme(usuario, filme);
	}

	@Test
	public void testLocacaoUsuarioVazio() throws FilmeSemEstoqueException {
		// cenario
		Filme filme = new Filme("E o vento levou", 5, 18.9);

		// acao
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuario vazio"));
		}

	}

	@Test
	public void testLocacaoFilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
		// cenario
		Usuario usuario = new Usuario("Teste");

		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		// acao
		service.alugarFilme(usuario, null);

	}
}
