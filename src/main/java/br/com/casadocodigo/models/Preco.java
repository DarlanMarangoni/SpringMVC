package br.com.casadocodigo.models;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import br.com.casadocodigo.models.enuns.TipoPreco;

@Embeddable
public class Preco {

	private BigDecimal valor;
	private TipoPreco tipoPreco;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal preco) {
		this.valor = preco;
	}

	public TipoPreco getTipoPreco() {
		return tipoPreco;
	}

	public void setTipoPreco(TipoPreco tipoPreco) {
		this.tipoPreco = tipoPreco;
	}

}
