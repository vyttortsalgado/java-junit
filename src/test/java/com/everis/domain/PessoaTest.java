package com.everis.domain;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.everis.exceptions.WithoutSobrenomeException;

public class PessoaTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testAsserts() {
		int a = 1;
		int b = 2;
		int c = a;

		//Aqui eu espero que a condição seja verdadeira
		//O Teste só vai falhar se a condição for diferente de verdadeiro
		assertTrue( a == 1 );
		
		//Aqui o teste espera que a condição seja falsa
		//Se a condição for verdadeira o teste falhará
		assertFalse( a == 2 );
		
		//Aqui eu espero que ambos os valores do mesmo tipo devem ser iguais
		//Não recomendado uso ao efetuar comparação de strings// Nesse caso seria melhor ou implementar o toString dos objetos
		//Ou usar o assertTrue com a condição que deseja internamente
		assertEquals(a, c);
		
		double myPi = 3.14;

		//No AssertEquals em Floats, Doubles e numeros flutuantes ou melhor que tenham virgula
		//a função possui o delta, que é o quanto de diferença a função aceitará de diferença entra ambos
		//Exemplo 3,14 e 3,141592...
		assertEquals(Math.PI, myPi, 0.01);
		
		Pessoa p1 = new Pessoa(1L, "Vyttor T A", "Salgado");
		Pessoa p2 = new Pessoa(1L, "Vyttor TAvares do Amaral", "Salgado");
		
		//Para fazer verificação de igualdade entre objetos, o objeto deve conter
		//o método Equals implementado da forma que desejar
		//Normalmente usando apenas o ID do objeto
		//Exemplo Classe Pessoa contem método Equals fazendo a comparação apenas do campo ID;
		assertEquals(p1, p2);
		
		//Temos tb como criar casos de testes verificando se um objeto está null
		//Assim só passará no teste caso o objeto seja null
		assertNull(null);

		//Asserts tb possuem Not
		//Se valores forem diferentes, sucesso no teste!
		assertNotEquals(1, 2);
		assertNotNull(1);
		
		//Além de todas as assertivas demonstradas acima, temos também
		//o asserThat que podemos usar pra deixar nossos testes mais legiveis
		//Ele são assertivas mais genéricas
		//Exemplos abaixo
		
		assertThat(1, CoreMatchers.is( 1 ));
		
		//Negação Usando assertThat
		assertThat(1, CoreMatchers.not( CoreMatchers.is( 2 ) ) );
	}
	
	
	//Pra entender erros, failures
	//Devemos lembrar que falha é quando aquilo que esperamos não foi satisfeito
	//Erro é quando algum problema impede que o teste seja concluido
	
	//Basicamente existem 4 formas de tratar excessões
	//Primeira forma é try...catch
	@Test
	public void testException() {
		try {
			//Para testar, retire o sobrenome no construtor de p1 abaixo
			Pessoa p1 = new Pessoa(1L, "Vyttor", "Salgado");
			String nomeCompleto = p1.getNomeCompleto();
		} catch (Exception e) {
			
			//Aqui se houver erros, lançamos Falha, no entanto não conseguimos rastrear
			fail(e.getMessage());
		}
	}
	
	//Segunda Forma, deixar que o JUnit trate a exceção, e ele faz isso muito bem
	//Ideal usar essa forma quando a exception são privadas, criadas pelo programador
	@Test
	public void testException2() throws WithoutSobrenomeException {
		//Para testar, retire o sobrenome no construtor de p1 abaixo
		Pessoa p1 = new Pessoa(1L, "Vyttor", "Salgado");
		String nomeCompleto = p1.getNomeCompleto2();
	}
	
	//Terceira forma, e a que mais indico, seria usando a Rules ExpectedException
	@Test
	public void testException3() throws WithoutSobrenomeException {
		
		//Essa função está esperando que haja uma exception, então caso coloque um sobreonem, e essa exception não ocorra o teste falha
		Pessoa p1 = new Pessoa(1L, "Vyttor", "");
		
		expectedException.expect( WithoutSobrenomeException.class );
		
		String nomeCompleto = p1.getNomeCompleto2();
	}
	
	//Quarta forma de teste de exception
	@Test( expected = WithoutSobrenomeException.class )
	public void testException4() throws WithoutSobrenomeException {
		//Essa função está esperando que haja uma exception, então caso coloque um sobreonem, e essa exception não ocorra o teste falha
		Pessoa p1 = new Pessoa(1L, "Vyttor", "");
				
		String nomeCompleto = p1.getNomeCompleto2();
	}
	
	//Caso tratemos exception genéricas como Exception ou afins, sempre bom verificarmos a msg que esperamos
	@Test
	public void testException5() throws Exception {
		Pessoa p1 = new Pessoa(1L, "Vyttor", "");
		
		expectedException.expect( Exception.class );
		
		//Essa função aceita um Matcher igual o assertThat, fazer condições que forem necessárias
		expectedException.expectMessage("Sem sobrenome, não é possível completar nome");
				
		String nomeCompleto = p1.getNomeCompleto();
	}
}