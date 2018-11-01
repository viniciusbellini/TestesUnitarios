package br.ce.wcaquino.servicos;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.FilmeBuilder;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	private LocacaoService service;
	private Usuario usuario;
	
	@Rule
	public ErrorCollector erros = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() {
		service = new LocacaoService();
		usuario = new Usuario("Vinicius");
	}
	
	@Test
	public void deveAlugarFilme() throws Exception {
		FilmeBuilder filme = new FilmeBuilder();
		
		
		List<Filme> filmes = asList(new FilmeBuilder().setNome("Titanic").setEstoque(5).setPrecoLocacao(10.0).build());
		Locacao locacao = service.alugarFilme(usuario, filmes);

		erros.checkThat(locacao.getValor(), is(10.0));
		erros.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		erros.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
	}

	@Test(expected = FilmeSemEstoqueException.class)
	public void naoDeveAlugarFilmeSemEstoque() throws Exception {
		List<Filme> filmes = asList(new FilmeBuilder().setNome("Titanic").setEstoque(0).setPrecoLocacao(10.0).build());
		service.alugarFilme(usuario, filmes);
	}

	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
		List<Filme> filmes = asList(new FilmeBuilder().setNome("E o vento levou").setEstoque(5).setPrecoLocacao(18.9).build());

		try {
			service.alugarFilme(null, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}

	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");

		service.alugarFilme(usuario, null);
	}
	
	@Test
	public void devePagar75PctNoFilme3() throws FilmeSemEstoqueException, LocadoraException {
		List<Filme> filmes = Arrays.asList(new FilmeBuilder().setNome("Filme 1").setEstoque(2).setPrecoLocacao(4.0).build(),
				new FilmeBuilder().setNome("Filme 2").setEstoque(2).setPrecoLocacao(4.0).build(),
				new FilmeBuilder().setNome("Filme 3").setEstoque(2).setPrecoLocacao(4.0).build());
		
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(11.0));
	}
	
	@Test
	public void devePagar50PctNoFilme4() throws FilmeSemEstoqueException, LocadoraException {
		List<Filme> filmes = Arrays.asList(new FilmeBuilder().setNome("Filme 1").setEstoque(2).setPrecoLocacao(4.0).build(),
				new FilmeBuilder().setNome("Filme 2").setEstoque(2).setPrecoLocacao(4.0).build(),
				new FilmeBuilder().setNome("Filme 3").setEstoque(2).setPrecoLocacao(4.0).build(),
				new FilmeBuilder().setNome("Filme 4").setEstoque(2).setPrecoLocacao(4.0).build());
		
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(13.0));
	}
	
	@Test
	public void devePagar25PctNoFilme5() throws FilmeSemEstoqueException, LocadoraException {
		List<Filme> filmes = Arrays.asList(new FilmeBuilder().setNome("Filme 1").setEstoque(2).setPrecoLocacao(4.0).build(),
				new FilmeBuilder().setNome("Filme 2").setEstoque(2).setPrecoLocacao(4.0).build(),
				new FilmeBuilder().setNome("Filme 3").setEstoque(2).setPrecoLocacao(4.0).build(),
				new FilmeBuilder().setNome("Filme 4").setEstoque(2).setPrecoLocacao(4.0).build(),
				new FilmeBuilder().setNome("Filme 5").setEstoque(2).setPrecoLocacao(4.0).build());
		
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(14.0));
	}
	
	@Test
	public void devePagar0NoFilme6() throws FilmeSemEstoqueException, LocadoraException {
		List<Filme> filmes = Arrays.asList(new FilmeBuilder().setNome("Filme 1").setEstoque(2).setPrecoLocacao(4.0).build(),
				new FilmeBuilder().setNome("Filme 2").setEstoque(2).setPrecoLocacao(4.0).build(),
				new FilmeBuilder().setNome("Filme 3").setEstoque(2).setPrecoLocacao(4.0).build(),
				new FilmeBuilder().setNome("Filme 4").setEstoque(2).setPrecoLocacao(4.0).build(),
				new FilmeBuilder().setNome("Filme 5").setEstoque(2).setPrecoLocacao(4.0).build(),
				new FilmeBuilder().setNome("Filme 6").setEstoque(2).setPrecoLocacao(4.0).build());
		
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(14.0));
	}
}
