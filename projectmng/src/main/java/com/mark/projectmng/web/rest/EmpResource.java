package com.mark.projectmng.web.rest;

import com.mark.projectmng.domain.Emp;
import com.mark.projectmng.repository.EmpRepository;
import com.mark.projectmng.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mark.projectmng.domain.Emp}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EmpResource {

    private final Logger log = LoggerFactory.getLogger(EmpResource.class);

    private static final String ENTITY_NAME = "emp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmpRepository empRepository;

    public EmpResource(EmpRepository empRepository) {
        this.empRepository = empRepository;
    }

    /**
     * {@code POST  /emps} : Create a new emp.
     *
     * @param emp the emp to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new emp, or with status {@code 400 (Bad Request)} if the emp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/emps")
    public ResponseEntity<Emp> createEmp(@Valid @RequestBody Emp emp) throws URISyntaxException {
        log.debug("REST request to save Emp : {}", emp);
        if (emp.getId() != null) {
            throw new BadRequestAlertException("A new emp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Emp result = empRepository.save(emp);
        return ResponseEntity
            .created(new URI("/api/emps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /emps/:id} : Updates an existing emp.
     *
     * @param id the id of the emp to save.
     * @param emp the emp to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emp,
     * or with status {@code 400 (Bad Request)} if the emp is not valid,
     * or with status {@code 500 (Internal Server Error)} if the emp couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/emps/{id}")
    public ResponseEntity<Emp> updateEmp(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Emp emp)
        throws URISyntaxException {
        log.debug("REST request to update Emp : {}, {}", id, emp);
        if (emp.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, emp.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!empRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Emp result = empRepository.save(emp);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, emp.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /emps/:id} : Partial updates given fields of an existing emp, field will ignore if it is null
     *
     * @param id the id of the emp to save.
     * @param emp the emp to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emp,
     * or with status {@code 400 (Bad Request)} if the emp is not valid,
     * or with status {@code 404 (Not Found)} if the emp is not found,
     * or with status {@code 500 (Internal Server Error)} if the emp couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/emps/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Emp> partialUpdateEmp(@PathVariable(value = "id", required = false) final Long id, @NotNull @RequestBody Emp emp)
        throws URISyntaxException {
        log.debug("REST request to partial update Emp partially : {}, {}", id, emp);
        if (emp.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, emp.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!empRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Emp> result = empRepository
            .findById(emp.getId())
            .map(existingEmp -> {
                if (emp.getName() != null) {
                    existingEmp.setName(emp.getName());
                }
                if (emp.getOrgId() != null) {
                    existingEmp.setOrgId(emp.getOrgId());
                }
                if (emp.getNum() != null) {
                    existingEmp.setNum(emp.getNum());
                }
                if (emp.getIdNum() != null) {
                    existingEmp.setIdNum(emp.getIdNum());
                }
                if (emp.getGender() != null) {
                    existingEmp.setGender(emp.getGender());
                }
                if (emp.getDob() != null) {
                    existingEmp.setDob(emp.getDob());
                }
                if (emp.getTenantId() != null) {
                    existingEmp.setTenantId(emp.getTenantId());
                }
                if (emp.getCreatedAt() != null) {
                    existingEmp.setCreatedAt(emp.getCreatedAt());
                }
                if (emp.getCreatedBy() != null) {
                    existingEmp.setCreatedBy(emp.getCreatedBy());
                }
                if (emp.getLastUpdatedAt() != null) {
                    existingEmp.setLastUpdatedAt(emp.getLastUpdatedAt());
                }
                if (emp.getLastUpdateBy() != null) {
                    existingEmp.setLastUpdateBy(emp.getLastUpdateBy());
                }

                return existingEmp;
            })
            .map(empRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, emp.getId().toString())
        );
    }

    /**
     * {@code GET  /emps} : get all the emps.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of emps in body.
     */
    @GetMapping("/emps")
    public List<Emp> getAllEmps() {
        log.debug("REST request to get all Emps");
        return empRepository.findAll();
    }

    /**
     * {@code GET  /emps/:id} : get the "id" emp.
     *
     * @param id the id of the emp to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the emp, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/emps/{id}")
    public ResponseEntity<Emp> getEmp(@PathVariable Long id) {
        log.debug("REST request to get Emp : {}", id);
        Optional<Emp> emp = empRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(emp);
    }

    /**
     * {@code DELETE  /emps/:id} : delete the "id" emp.
     *
     * @param id the id of the emp to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/emps/{id}")
    public ResponseEntity<Void> deleteEmp(@PathVariable Long id) {
        log.debug("REST request to delete Emp : {}", id);
        empRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
