package com.mark.projectmng.web.rest;

import com.mark.projectmng.domain.EmpPost;
import com.mark.projectmng.repository.EmpPostRepository;
import com.mark.projectmng.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link com.mark.projectmng.domain.EmpPost}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EmpPostResource {

    private final Logger log = LoggerFactory.getLogger(EmpPostResource.class);

    private static final String ENTITY_NAME = "empPost";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmpPostRepository empPostRepository;

    public EmpPostResource(EmpPostRepository empPostRepository) {
        this.empPostRepository = empPostRepository;
    }

    /**
     * {@code POST  /emp-posts} : Create a new empPost.
     *
     * @param empPost the empPost to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new empPost, or with status {@code 400 (Bad Request)} if the empPost has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/emp-posts")
    public ResponseEntity<EmpPost> createEmpPost(@RequestBody EmpPost empPost) throws URISyntaxException {
        log.debug("REST request to save EmpPost : {}", empPost);
        if (empPost.getId() != null) {
            throw new BadRequestAlertException("A new empPost cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmpPost result = empPostRepository.save(empPost);
        return ResponseEntity
            .created(new URI("/api/emp-posts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /emp-posts/:id} : Updates an existing empPost.
     *
     * @param id the id of the empPost to save.
     * @param empPost the empPost to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated empPost,
     * or with status {@code 400 (Bad Request)} if the empPost is not valid,
     * or with status {@code 500 (Internal Server Error)} if the empPost couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/emp-posts/{id}")
    public ResponseEntity<EmpPost> updateEmpPost(
        @PathVariable(value = "id", required = false) final EmpPostId id,
        @RequestBody EmpPost empPost
    ) throws URISyntaxException {
        log.debug("REST request to update EmpPost : {}, {}", id, empPost);
        if (empPost.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, empPost.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!empPostRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        empPost.setIsPersisted();
        EmpPost result = empPostRepository.save(empPost);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, empPost.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /emp-posts/:id} : Partial updates given fields of an existing empPost, field will ignore if it is null
     *
     * @param id the id of the empPost to save.
     * @param empPost the empPost to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated empPost,
     * or with status {@code 400 (Bad Request)} if the empPost is not valid,
     * or with status {@code 404 (Not Found)} if the empPost is not found,
     * or with status {@code 500 (Internal Server Error)} if the empPost couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/emp-posts/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EmpPost> partialUpdateEmpPost(
        @PathVariable(value = "id", required = false) final EmpPostId id,
        @RequestBody EmpPost empPost
    ) throws URISyntaxException {
        log.debug("REST request to partial update EmpPost partially : {}, {}", id, empPost);
        if (empPost.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, empPost.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!empPostRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EmpPost> result = empPostRepository
            .findById(empPost.getId())
            .map(existingEmpPost -> {
                if (empPost.getTenantId() != null) {
                    existingEmpPost.setTenantId(empPost.getTenantId());
                }
                if (empPost.getCreatedAt() != null) {
                    existingEmpPost.setCreatedAt(empPost.getCreatedAt());
                }
                if (empPost.getCreatedBy() != null) {
                    existingEmpPost.setCreatedBy(empPost.getCreatedBy());
                }
                if (empPost.getLastUpdatedAt() != null) {
                    existingEmpPost.setLastUpdatedAt(empPost.getLastUpdatedAt());
                }
                if (empPost.getLastUpdateBy() != null) {
                    existingEmpPost.setLastUpdateBy(empPost.getLastUpdateBy());
                }

                return existingEmpPost;
            })
            .map(empPostRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, empPost.getId().toString())
        );
    }

    /**
     * {@code GET  /emp-posts} : get all the empPosts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of empPosts in body.
     */
    @GetMapping("/emp-posts")
    public List<EmpPost> getAllEmpPosts() {
        log.debug("REST request to get all EmpPosts");
        return empPostRepository.findAll();
    }

    /**
     * {@code GET  /emp-posts/:id} : get the "id" empPost.
     *
     * @param id the id of the empPost to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the empPost, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/emp-posts/{id}")
    public ResponseEntity<EmpPost> getEmpPost(@PathVariable EmpPostId id) {
        log.debug("REST request to get EmpPost : {}", id);
        Optional<EmpPost> empPost = empPostRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(empPost);
    }

    /**
     * {@code DELETE  /emp-posts/:id} : delete the "id" empPost.
     *
     * @param id the id of the empPost to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/emp-posts/{id}")
    public ResponseEntity<Void> deleteEmpPost(@PathVariable EmpPostId id) {
        log.debug("REST request to delete EmpPost : {}", id);
        empPostRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
