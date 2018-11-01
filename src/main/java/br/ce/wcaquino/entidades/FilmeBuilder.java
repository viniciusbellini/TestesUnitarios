package br.ce.wcaquino.entidades;

public class FilmeBuilder {

	private String nome;

	private Integer estoque;
	private Double precoLocacao;

	public FilmeBuilder setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public FilmeBuilder setEstoque(Integer estoque) {
		this.estoque = estoque;
		return this;
	}

	public FilmeBuilder setPrecoLocacao(Double precoLocacao) {
		this.precoLocacao = precoLocacao;
		return this;
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

	public Filme build() {
		return new Filme(this);
	}

}
