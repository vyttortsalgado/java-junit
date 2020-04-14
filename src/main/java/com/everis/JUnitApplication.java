package com.everis;

import com.everis.domain.Pessoa;

public class JUnitApplication {

	public static void main(String[] args) {
		Pessoa pessoa = new Pessoa( 1L, "Vyttor T A", "Salgado" );
		System.out.println( pessoa );
	}

}