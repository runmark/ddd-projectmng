package com.mark.projectmng.web.rest;

import com.mark.projectmng.domain.OrgType;
import com.mark.projectmng.repository.OrgTypeRepository;
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
 * REST controller for managing {@link com.mark.projectmng.domain.OrgType}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OrgTypeResource {

    private final Logger log = LoggerFactory.getLogger(OrgTypeResource.class);

    private static final String ENTITY_NAME = "orgType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrgTypeRepository orgTypeRepository;

    public OrgTypeResource(OrgTypeRepository orgTypeRepository) {
        this.orgTypeRepository = orgTypeRepository;
    }

    /**
     * {@code POST  /org-types} : Create a new orgType.
     *
     * @param orgType the orgType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orgType, or with status {@code 400 (Bad Request)} if the orgType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/org-types")
    public ResponseEntity<OrgType> createOrgType(@Valid @RequestBody OrgType orgType) throws URISyntaxException {
        log.debug("REST request to save OrgType : {}", orgType);
        if (orgType.getId() != null) {
            throw new BadRequestAlertException("A new orgType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrgType result = orgTypeRepository.save(orgType);
        return ResponseEntity
            .created(new URI("/api/org-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /org-types/:id} : Updates an existing orgType.
     *
     * @param id the id of the orgType to save.
     * @param orgType the orgType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orgType,
     * or with status {@code 400 (Bad Request)} if the orgType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orgType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/org-types/{id}")
    public ResponseEntity<OrgType> updateOrgType(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody OrgType orgType
    ) throws URISyntaxException {
        log.debug("REST request to update OrgType : {}, {}", id, orgType);
        if (orgType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orgType.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orgTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OrgType result = orgTypeRepository.save(orgType);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orgType.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /org-types/:id} : Partial updates given fields of an existing orgType, field will ignore if it is null
     *
     * @param id the id of the orgType to save.
     * @param orgType the orgType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orgType,
     * or with status {@code 400 (Bad Request)} if the orgType is not valid,
     * or with status {@code 404 (Not Found)} if the orgType is not found,
     * or with status {@code 500 (Internal Server Error)} if the orgType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/org-types/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OrgType> partialUpdateOrgType(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody OrgType orgType
    ) throws URISyntaxException {
        log.debug("REST request to partial update OrgType partially : {}, {}", id, orgType);
        if (orgType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orgType.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orgTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OrgType> result = orgTypeRepository
            .findById(orgType.getId())
            .map(existingOrgType -> {
                if (orgType.getName() != null) {
                    existingOrgType.setName(orgType.getName());
                }
                if (orgType.getTenantId() != null) {
                    existingOrgType.setTenantId(orgType.getTenantId());
                }
                if (orgType.getCreatedAt() != null) {
                    existingOrgType.setCreatedAt(orgType.getCreatedAt());
                }
                if (orgType.getCreatedBy() != null) {
                    existingOrgType.setCreatedBy(orgType.getCreatedBy());
                }
                if (orgType.getLastUpdatedAt() != null) {
                    existingOrgType.setLastUpdatedAt(orgType.getLastUpdatedAt());
                }
                if (orgType.getLastUpdateBy() != null) {
                    existingOrgType.setLastUpdateBy(orgType.getLastUpdateBy());
                }

                return existingOrgType;
            })
            .map(orgTypeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orgType.getId().toString())
        );
    }

    /**
     * {@code GET  /org-types} : get all the orgTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orgTypes in body.
     */
    @GetMapping("/org-types")
    public List<OrgType> getAllOrgTypes() {
        log.debug("REST request to get all OrgTypes");
        return orgTypeRepository.findAll();
    }

    /**
     * {@code GET  /org-types/:id} : get the "id" orgType.
     *
     * @param id the id of the orgType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orgType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/org-types/{id}")
    public ResponseEntity<OrgType> getOrgType(@PathVariable Long id) {
        log.debug("REST request to get OrgType : {}", id);
        Optional<OrgType> orgType = orgTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(orgType);
    }

    /**
     * {@code DELETE  /org-types/:id} : delete the "id" orgType.
     *
     * @param id the id of the orgType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/org-types/{id}")
    public ResponseEntity<Void> deleteOrgType(@PathVariable Long id) {
        log.debug("REST request to delete OrgType : {}", id);
        orgTypeRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
