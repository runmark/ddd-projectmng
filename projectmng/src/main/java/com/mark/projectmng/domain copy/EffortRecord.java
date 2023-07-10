package com.mark.projectmng.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EffortRecord.
 */
@Entity
@Table(name = "effort_record")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EffortRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "99.9")
    @Column(name = "effort", nullable = false)
    private Float effort;

    @Column(name = "work_date")
    private Instant workDate;

    @Size(max = 255)
    @Column(name = "notes", length = 255)
    private String notes;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "tenant_id")
    private Integer tenantId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "last_updated_at")
    private Instant lastUpdatedAt;

    @Column(name = "last_update_by")
    private Integer lastUpdateBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EffortRecord id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getEffort() {
        return this.effort;
    }

    public EffortRecord effort(Float effort) {
        this.setEffort(effort);
        return this;
    }

    public void setEffort(Float effort) {
        this.effort = effort;
    }

    public Instant getWorkDate() {
        return this.workDate;
    }

    public EffortRecord workDate(Instant workDate) {
        this.setWorkDate(workDate);
        return this;
    }

    public void setWorkDate(Instant workDate) {
        this.workDate = workDate;
    }

    public String getNotes() {
        return this.notes;
    }

    public EffortRecord notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getProjectId() {
        return this.projectId;
    }

    public EffortRecord projectId(Integer projectId) {
        this.setProjectId(projectId);
        return this;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getEmpId() {
        return this.empId;
    }

    public EffortRecord empId(Integer empId) {
        this.setEmpId(empId);
        return this;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getTenantId() {
        return this.tenantId;
    }

    public EffortRecord tenantId(Integer tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public EffortRecord createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public EffortRecord createdBy(Integer createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public EffortRecord lastUpdatedAt(Instant lastUpdatedAt) {
        this.setLastUpdatedAt(lastUpdatedAt);
        return this;
    }

    public void setLastUpdatedAt(Instant lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Integer getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    public EffortRecord lastUpdateBy(Integer lastUpdateBy) {
        this.setLastUpdateBy(lastUpdateBy);
        return this;
    }

    public void setLastUpdateBy(Integer lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EffortRecord)) {
            return false;
        }
        return id != null && id.equals(((EffortRecord) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EffortRecord{" +
            "id=" + getId() +
            ", effort=" + getEffort() +
            ", workDate='" + getWorkDate() + "'" +
            ", notes='" + getNotes() + "'" +
            ", projectId=" + getProjectId() +
            ", empId=" + getEmpId() +
            ", tenantId=" + getTenantId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", lastUpdatedAt='" + getLastUpdatedAt() + "'" +
            ", lastUpdateBy=" + getLastUpdateBy() +
            "}";
    }
}
