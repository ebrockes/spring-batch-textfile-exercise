package br.com.sample.batch;

import java.util.Random;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import br.com.sample.model.Texto;

@Component
public class Processor implements ItemProcessor<Texto, Texto> {

	@Override
	public Texto process(Texto texto) throws Exception{
		String linha = texto.getTexto();
		String caracter = this.defineCaracter();
		texto.setTexto(linha);
		texto.setCaracter(caracter);
		texto.setNumberOfOcurrences(this.identificarOcorrencias(caracter, linha));
		return texto;
	}

	private String defineCaracter() {
		Random rnd = new Random();
		char c = (char) (rnd.nextInt(26) + 'a');
		return String.valueOf(c);
	}

	private Integer identificarOcorrencias(String caracter, String texto) {
		String[] temp = texto.split(caracter);
		return temp.length - 1;
	}
}
