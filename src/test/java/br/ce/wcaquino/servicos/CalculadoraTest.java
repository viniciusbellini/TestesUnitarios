package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;

public class CalculadoraTest {

	private Calculadora calc;

	@Before
	public void setUp() {
		calc = new Calculadora();
	}
	
	@Test
	public void deveSomarDoisValores() {
		int a = 5;
		int b = 3;
		
		Assert.assertEquals(8, calc.somar(a, b));
	}
	
	@Test
	public void deveSubtrairDoisValores() {
		int a = 5;
		int b = 3;
		
		Assert.assertEquals(2, calc.subtrair(a, b));
	}
	
	@Test
	public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
		int a = 15;
		int b = 3;
		
		Assert.assertEquals(5, calc.dividir(a, b));
	}
	
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
		int a = 5;
		int b = 0;
		
		calc.dividir(a, b);
	}
}
