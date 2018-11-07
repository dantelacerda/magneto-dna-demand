package com.mercadolivre.model;

import org.decimal4j.util.DoubleRounder;

public class Stats {

	private double quantidadeMutantes;
	private double quantidadeHumanos;
	private double proporcao;

	public Stats(double quantidadeMutantes, double quantidadeHumanos) {
		this.quantidadeMutantes = quantidadeMutantes;
		this.quantidadeHumanos = quantidadeHumanos;
		this.proporcao = (quantidadeHumanos != 0) ? quantidadeMutantes / quantidadeHumanos : 0;
		this.proporcao = DoubleRounder.round(proporcao, 2);
	}

	public double getQuantidadeMutantes() {
		return quantidadeMutantes;
	}

	public void setQuantidadeMutantes(double quantidadeMutantes) {
		this.quantidadeMutantes = quantidadeMutantes;
	}

	public double getQuantidadeHumanos() {
		return quantidadeHumanos;
	}

	public void setQuantidadeHumanos(double quantidadeHumanos) {
		this.quantidadeHumanos = quantidadeHumanos;
	}

	public double getProporcao() {
		return proporcao;
	}

	public void setProporcao(double ratio) {
		this.proporcao = ratio;
	}

}
