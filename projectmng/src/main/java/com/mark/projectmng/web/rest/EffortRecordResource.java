package com.mark.projectmng.web.rest;

import com.mark.projectmng.domain.EffortRecord;
import com.mark.projectmng.repository.EffortRecordRepository;
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
 * REST controller for managing {@link com.mark.projectmng.domain.EffortRecord}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EffortRecordResource {

    private final Logger log = LoggerFactory.getLogger(EffortRecordResource.class);

    private static final String ENTITY_NAME = "effortRecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EffortRecordRepository effortRecordRepository;

    public EffortRecordResource(EffortRecordRepository effortRecordRepository) {
        this.effortRecordRepository = effortRecordRepository;
    }

    /**
     * {@code POST  /effort-records} : Create a new effortRecord.
     *
     * @param effortRecord the effortRecord to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new effortRecord, or with status {@code 400 (Bad Request)} if the effortRecord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/effort-records")
    public ResponseEntity<EffortRecord> createEffortRecord(@Valid @RequestBody EffortRecord effortRecord) throws URISyntaxException {
        log.debug("REST request to save EffortRecord : {}", effortRecord);
        if (effortRecord.getId() != null) {
            throw new BadRequestAlertException("A new effortRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EffortRecord result = effortRecordRepository.save(effortRecord);
        return ResponseEntity
            .created(new URI("/api/effort-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /effort-records/:id} : Updates an existing effortRecord.
     *
     * @param id the id of the effortRecord to save.
     * @param effortRecord the effortRecord to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated effortRecord,
     * or with status {@code 400 (Bad Request)} if the effortRecord is not valid,
     * or with status {@code 500 (Internal Server Error)} if the effortRecord couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/effort-records/{id}")
    public ResponseEntity<EffortRecord> updateEffortRecord(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody EffortRecord effortRecord
    ) throws URISyntaxException {
        log.debug("REST request to update EffortRecord : {}, {}", id, effortRecord);
        if (effortRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, effortRecord.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!effortRecordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EffortRecord result = effortRecordRepository.save(effortRecord);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, effortRecord.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /effort-records/:id} : Partial updates given fields of an existing effortRecord, field will ignore if it is null
     *
     * @param id the id of the effortRecord to save.
     * @param effortRecord the effortRecord to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated effortRecord,
     * or with status {@code 400 (Bad Request)} if the effortRecord is not valid,
     * or with status {@code 404 (Not Found)} if the effortRecord is not found,
     * or with status {@code 500 (Internal Server Error)} if the effortRecord couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/effort-records/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EffortRecord> partialUpdateEffortRecord(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody EffortRecord effortRecord
    ) throws URISyntaxException {
        log.debug("REST request to partial update EffortRecord partially : {}, {}", id, effortRecord);
        if (effortRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, effortRecord.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!effortRecordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EffortRecord> result = effortRecordRepository
            .findById(effortRecord.getId())
            .map(existingEffortRecord -> {
                if (effortRecord.getEffort() != null) {
                    existingEffortRecord.setEffort(effortRecord.getEffort());
                }
                if (effortRecord.getWorkDate() != null) {
                    existingEffortRecord.setWorkDate(effortRecord.getWorkDate());
                }
                if (effortRecord.getNotes() != null) {
                    existingEffortRecord.setNotes(effortRecord.getNotes());
                }
                if (effortRecord.getProjectId() != null) {
                    existingEffortRecord.setProjectId(effortRecord.getProjectId());
                }
                if (effortRecord.getEmpId() != null) {
                    existingEffortRecord.setEmpId(effortRecord.getEmpId());
                }
                if (effortRecord.getTenantId() != null) {
                    existingEffortRecord.setTenantId(effortRecord.getTenantId());
                }
                if (effortRecord.getCreatedAt() != null) {
                    existingEffortRecord.setCreatedAt(effortRecord.getCreatedAt());
                }
                if (effortRecord.getCreatedBy() != null) {
                    existingEffortRecord.setCreatedBy(effortRecord.getCreatedBy());
                }
                if (effortRecord.getLastUpdatedAt() != null) {
                    existingEffortRecord.setLastUpdatedAt(effortRecord.getLastUpdatedAt());
                }
                if (effortRecord.getLastUpdateBy() != null) {
                    existingEffortRecord.setLastUpdateBy(effortRecord.getLastUpdateBy());
                }

                return existingEffortRecord;
            })
            .map(effortRecordRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, effortRecord.getId().toString())
        );
    }

    /**
     * {@code GET  /effort-records} : get all the effortRecords.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of effortRecords in body.
     */
    @GetMapping("/effort-records")
    public List<EffortRecord> getAllEffortRecords() {
        log.debug("REST request to get all EffortRecords");
        return effortRecordRepository.findAll();
    }

    /**
     * {@code GET  /effort-records/:id} : get the "id" effortRecord.
     *
     * @param id the id of the effortRecord to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the effortRecord, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/effort-records/{id}")
    public ResponseEntity<EffortRecord> getEffortRecord(@PathVariable Long id) {
        log.debug("REST request to get EffortRecord : {}", id);
        Optional<EffortRecord> effortRecord = effortRecordRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(effortRecord);
    }

    /**
     * {@code DELETE  /effort-records/:id} : delete the "id" effortRecord.
     *
     * @param id the id of the effortRecord to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/effort-records/{id}")
    public ResponseEntity<Void> deleteEffortRecord(@PathVariable Long id) {
        log.debug("REST request to delete EffortRecord : {}", id);
        effortRecordRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
