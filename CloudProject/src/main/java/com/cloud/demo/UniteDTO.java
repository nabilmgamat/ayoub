package com.cloud.demo;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;


/**
 * A DTO for the {@link com.distribution.domain.Unit} entity.
 */
public class UniteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nom;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
