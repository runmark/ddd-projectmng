package com.mark.projectmng.web.rest;

import com.mark.projectmng.domain.ProjectMember;
import com.mark.projectmng.repository.ProjectMemberRepository;
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
 * REST controller for managing {@link com.mark.projectmng.domain.ProjectMember}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProjectMemberResource {

    private final Logger log = LoggerFactory.getLogger(ProjectMemberResource.class);

    private static final String ENTITY_NAME = "projectMember";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectMemberRepository projectMemberRepository;

    public ProjectMemberResource(ProjectMemberRepository projectMemberRepository) {
        this.projectMemberRepository = projectMemberRepository;
    }

    /**
     * {@code POST  /project-members} : Create a new projectMember.
     *
     * @param projectMember the projectMember to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectMember, or with status {@code 400 (Bad Request)} if the projectMember has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-members")
    public ResponseEntity<ProjectMember> createProjectMember(@Valid @RequestBody ProjectMember projectMember) throws URISyntaxException {
        log.debug("REST request to save ProjectMember : {}", projectMember);
        if (projectMember.getId() != null) {
            throw new BadRequestAlertException("A new projectMember cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectMember result = projectMemberRepository.save(projectMember);
        return ResponseEntity
            .created(new URI("/api/project-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-members/:id} : Updates an existing projectMember.
     *
     * @param id the id of the projectMember to save.
     * @param projectMember the projectMember to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectMember,
     * or with status {@code 400 (Bad Request)} if the projectMember is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectMember couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-members/{id}")
    public ResponseEntity<ProjectMember> updateProjectMember(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProjectMember projectMember
    ) throws URISyntaxException {
        log.debug("REST request to update ProjectMember : {}, {}", id, projectMember);
        if (projectMember.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectMember.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectMemberRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProjectMember result = projectMemberRepository.save(projectMember);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectMember.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /project-members/:id} : Partial updates given fields of an existing projectMember, field will ignore if it is null
     *
     * @param id the id of the projectMember to save.
     * @param projectMember the projectMember to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectMember,
     * or with status {@code 400 (Bad Request)} if the projectMember is not valid,
     * or with status {@code 404 (Not Found)} if the projectMember is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectMember couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/project-members/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProjectMember> partialUpdateProjectMember(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProjectMember projectMember
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProjectMember partially : {}, {}", id, projectMember);
        if (projectMember.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectMember.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectMemberRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProjectMember> result = projectMemberRepository
            .findById(projectMember.getId())
            .map(existingProjectMember -> {
                if (projectMember.getStartAt() != null) {
                    existingProjectMember.setStartAt(projectMember.getStartAt());
                }
                if (projectMember.getEndAt() != null) {
                    existingProjectMember.setEndAt(projectMember.getEndAt());
                }
                if (projectMember.getStatus() != null) {
                    existingProjectMember.setStatus(projectMember.getStatus());
                }
                if (projectMember.getEstimateInvestRatio() != null) {
                    existingProjectMember.setEstimateInvestRatio(projectMember.getEstimateInvestRatio());
                }
                if (projectMember.getEmpId() != null) {
                    existingProjectMember.setEmpId(projectMember.getEmpId());
                }
                if (projectMember.getProjectId() != null) {
                    existingProjectMember.setProjectId(projectMember.getProjectId());
                }
                if (projectMember.getTenantId() != null) {
                    existingProjectMember.setTenantId(projectMember.getTenantId());
                }
                if (projectMember.getCreatedAt() != null) {
                    existingProjectMember.setCreatedAt(projectMember.getCreatedAt());
                }
                if (projectMember.getCreatedBy() != null) {
                    existingProjectMember.setCreatedBy(projectMember.getCreatedBy());
                }
                if (projectMember.getLastUpdatedAt() != null) {
                    existingProjectMember.setLastUpdatedAt(projectMember.getLastUpdatedAt());
                }
                if (projectMember.getLastUpdateBy() != null) {
                    existingProjectMember.setLastUpdateBy(projectMember.getLastUpdateBy());
                }

                return existingProjectMember;
            })
            .map(projectMemberRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectMember.getId().toString())
        );
    }

    /**
     * {@code GET  /project-members} : get all the projectMembers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectMembers in body.
     */
    @GetMapping("/project-members")
    public List<ProjectMember> getAllProjectMembers() {
        log.debug("REST request to get all ProjectMembers");
        return projectMemberRepository.findAll();
    }

    /**
     * {@code GET  /project-members/:id} : get the "id" projectMember.
     *
     * @param id the id of the projectMember to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectMember, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-members/{id}")
    public ResponseEntity<ProjectMember> getProjectMember(@PathVariable Long id) {
        log.debug("REST request to get ProjectMember : {}", id);
        Optional<ProjectMember> projectMember = projectMemberRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(projectMember);
    }

    /**
     * {@code DELETE  /project-members/:id} : delete the "id" projectMember.
     *
     * @param id the id of the projectMember to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-members/{id}")
    public ResponseEntity<Void> deleteProjectMember(@PathVariable Long id) {
        log.debug("REST request to delete ProjectMember : {}", id);
        projectMemberRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
