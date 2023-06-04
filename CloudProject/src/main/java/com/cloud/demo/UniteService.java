package com.cloud.demo;


import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Unite}.
 */
@Service
@Transactional
public class UniteService {

    private final Logger log = LoggerFactory.getLogger(UniteService.class);

    private final UniteRepository uniteRepository;

    private final UniteMapper uniteMapper;

    public UniteService(UniteRepository uniteRepository, UniteMapper uniteMapper) {
        this.uniteRepository = uniteRepository;
        this.uniteMapper = uniteMapper;
    }

    /**
     * Save a unite.
     *
     * @param uniteDTO the entity to save.
     * @return the persisted entity.
     */
    public UniteDTO save(UniteDTO uniteDTO) {
        log.debug("Request to save Unite : {}", uniteDTO);
        Unite unite = uniteMapper.toEntity(uniteDTO);
        unite = uniteRepository.save(unite);
        return uniteMapper.toDTO(unite);
    }

    /**
     * Partially update a unite.
     *
     * @param uniteDTO the entity to update partially.
     * @return the persisted entity.
     *
    public Optional<UniteDTO> partialUpdate(UniteDTO uniteDTO) {
        log.debug("Request to partially update Unite : {}", uniteDTO);

        return uniteRepository
            .findById(uniteDTO.getId())
            .map(existingUnite -> {
                uniteMapper.partialUpdate(existingUnite, uniteDTO);

                return existingUnite;
            })
            .map(uniteRepository::save)
            .map(uniteMapper::toDto);
    }

    /**
     * Get all the unites.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UniteDTO> findAll() {
        log.debug("Request to get all Unites");
        return uniteMapper.toDtO(uniteRepository.findAll());
    }

    /**
     * Get one unite by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public UniteDTO findOne(Long id) {
        log.debug("Request to get Unite : {}", id);
        return uniteMapper.toDTO(uniteRepository.findById(id).get());
    }

    /**
     * Delete the unite by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Unite : {}", id);
        uniteRepository.deleteById(id);
    }
}
