package br.ce.wcaquino.entidades;

public class Filme {

	private String nome;
	
	private Integer estoque;
	private Double precoLocacao;  
	
	public Filme(FilmeBuilder filmeBuilder) {
		this.nome = filmeBuilder.getNome();
		this.estoque = filmeBuilder.getEstoque();
		this.precoLocacao = filmeBuilder.getPrecoLocacao();
	}
	
	public String getNome() {
		return nome;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public Double getPrecoLocacao() {
		return precoLocacao;
	}

	
}	