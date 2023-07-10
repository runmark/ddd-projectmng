package com.mark.projectmng.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProjectMember.
 */
@Entity
@Table(name = "project_member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProjectMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_at")
    private Instant startAt;

    @Column(name = "end_at")
    private Instant endAt;

    @NotNull
    @Size(max = 2)
    @Column(name = "status", length = 2, nullable = false)
    private String status;

    @Column(name = "estimate_invest_ratio")
    private Integer estimateInvestRatio;

    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "project_id")
    private Integer projectId;

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

    public ProjectMember id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartAt() {
        return this.startAt;
    }

    public ProjectMember startAt(Instant startAt) {
        this.setStartAt(startAt);
        return this;
    }

    public void setStartAt(Instant startAt) {
        this.startAt = startAt;
    }

    public Instant getEndAt() {
        return this.endAt;
    }

    public ProjectMember endAt(Instant endAt) {
        this.setEndAt(endAt);
        return this;
    }

    public void setEndAt(Instant endAt) {
        this.endAt = endAt;
    }

    public String getStatus() {
        return this.status;
    }

    public ProjectMember status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEstimateInvestRatio() {
        return this.estimateInvestRatio;
    }

    public ProjectMember estimateInvestRatio(Integer estimateInvestRatio) {
        this.setEstimateInvestRatio(estimateInvestRatio);
        return this;
    }

    public void setEstimateInvestRatio(Integer estimateInvestRatio) {
        this.estimateInvestRatio = estimateInvestRatio;
    }

    public Integer getEmpId() {
        return this.empId;
    }

    public ProjectMember empId(Integer empId) {
        this.setEmpId(empId);
        return this;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getProjectId() {
        return this.projectId;
    }

    public ProjectMember projectId(Integer projectId) {
        this.setProjectId(projectId);
        return this;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTenantId() {
        return this.tenantId;
    }

    public ProjectMember tenantId(Integer tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public ProjectMember createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public ProjectMember createdBy(Integer createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public ProjectMember lastUpdatedAt(Instant lastUpdatedAt) {
        this.setLastUpdatedAt(lastUpdatedAt);
        return this;
    }

    public void setLastUpdatedAt(Instant lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Integer getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    public ProjectMember lastUpdateBy(Integer lastUpdateBy) {
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
        if (!(o instanceof ProjectMember)) {
            return false;
        }
        return id != null && id.equals(((ProjectMember) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectMember{" +
            "id=" + getId() +
            ", startAt='" + getStartAt() + "'" +
            ", endAt='" + getEndAt() + "'" +
            ", status='" + getStatus() + "'" +
            ", estimateInvestRatio=" + getEstimateInvestRatio() +
            ", empId=" + getEmpId() +
            ", projectId=" + getProjectId() +
            ", tenantId=" + getTenantId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", lastUpdatedAt='" + getLastUpdatedAt() + "'" +
            ", lastUpdateBy=" + getLastUpdateBy() +
            "}";
    }
}
