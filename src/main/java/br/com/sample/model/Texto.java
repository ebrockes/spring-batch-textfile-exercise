package br.com.sample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Texto {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer numberOfOcurrences;
	private String caracter;
	private String texto;
	
	public Texto() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumberOfOcurrences() {
		return numberOfOcurrences;
	}

	public void setNumberOfOcurrences(Integer numberOfOcurrences) {
		this.numberOfOcurrences = numberOfOcurrences;
	}

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
