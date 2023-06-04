package com.cloud.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;



/**
 * Mapper for the entity {@link Unit} and its DTO {@link UnitDTO}.
 */
@Component
public class UniteMapper {

	public UniteDTO toDTO(Unite unite) {
		UniteDTO uniteDTO = new UniteDTO();
		uniteDTO.setId(unite.getId());
		uniteDTO.setNom(unite.getNom());

		return uniteDTO;

	}

	public Unite toEntity(UniteDTO uniteDTO) {
		Unite unite = new Unite();
		unite.setId(uniteDTO.getId());
		unite.setNom(uniteDTO.getNom());

		return unite;

	}

	public List<UniteDTO> toDtO(List<Unite> units) {

		List<UniteDTO> result = new ArrayList<>();
		for (Unite unit : units) {
			UniteDTO unitDTO = toDTO(unit);
			result.add(unitDTO);
		}

		return result;
	}

}
