package com.cloud.demo;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@ComponentScan({"com.cloud.demo"})
@EnableJpaRepositories("com.cloud.demo")
@EntityScan("com.cloud.demo")   
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RestController
public class CloudProjectApplication {
	
	
	private final UniteService uniteService;

    private final UniteRepository uniteRepository;


    public CloudProjectApplication(UniteService uniteService, UniteRepository uniteRepository) {
        this.uniteService = uniteService;
        this.uniteRepository = uniteRepository;
    }
	
	@GetMapping("/message")
	public String showMessage() {
		return "helloe world cloud";
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudProjectApplication.class, args);
	}
	
	@GetMapping("/unites/create")
    public ResponseEntity<UniteDTO> createUnite() throws URISyntaxException {
		UniteDTO uniteDTO =new  UniteDTO();
		uniteDTO.setNom("Test");
       
        if (uniteDTO.getId() != null) {
            //throw new BadRequestAlertException("A new unite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UniteDTO result = uniteService.save(uniteDTO);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    /**
     * {@code PUT  /unites/:id} : Updates an existing unite.
     *
     * @param id the id of the uniteDTO to save.
     * @param uniteDTO the uniteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uniteDTO,
     * or with status {@code 400 (Bad Request)} if the uniteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uniteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/unites/{id}")
    public ResponseEntity<UniteDTO> updateUnite(@PathVariable(value = "id", required = false) final Long id, @RequestBody UniteDTO uniteDTO)
        throws URISyntaxException {
      
        if (uniteDTO.getId() == null) {
            //throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, uniteDTO.getId())) {
           // throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uniteRepository.existsById(id)) {
           // throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UniteDTO result = uniteService.save(uniteDTO);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    /**
     * {@code PATCH  /unites/:id} : Partial updates given fields of an existing unite, field will ignore if it is null
     *
     * @param id the id of the uniteDTO to save.
     * @param uniteDTO the uniteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uniteDTO,
     * or with status {@code 400 (Bad Request)} if the uniteDTO is not valid,
     * or with status {@code 404 (Not Found)} if the uniteDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the uniteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/unites/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UniteDTO> partialUpdateUnite(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UniteDTO uniteDTO
    ) throws URISyntaxException {
       
        if (uniteDTO.getId() == null) {
           // throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, uniteDTO.getId())) {
           // throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uniteRepository.existsById(id)) {
         //   throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UniteDTO> result =null;// uniteService.partialUpdate(uniteDTO);

        return new ResponseEntity(result,HttpStatus.OK);
    }

    /**
     * {@code GET  /unites} : get all the unites.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of unites in body.
     */
    @GetMapping("/unites")
    public ResponseEntity<List<UniteDTO>> getAllUnites() {
      
        List<UniteDTO> page = uniteService.findAll();
        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().
        		//headers(headers).
        		body(page);
    }

    /**
     * {@code GET  /unites/count} : count all the unites.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     
    @GetMapping("/unites/count")
    public ResponseEntity<Long> countUnites(UniteCriteria criteria) {
        log.debug("REST request to count Unites by criteria: {}", criteria);
        return ResponseEntity.ok().body(uniteQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /unites/:id} : get the "id" unite.
     *
     * @param id the id of the uniteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uniteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/unites/{id}")
    public ResponseEntity<UniteDTO> getUnite(@PathVariable Long id) {
       
        UniteDTO uniteDTO = uniteService.findOne(id);
        return new ResponseEntity(uniteDTO,HttpStatus.OK);
    }

    /**
     * {@code DELETE  /unites/:id} : delete the "id" unite.
     *
     * @param id the id of the uniteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/unites/{id}")
    public ResponseEntity<Void> deleteUnite(@PathVariable Long id) {
        
       uniteService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
