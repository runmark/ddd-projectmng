package com.mark.projectmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A EmpPost.
 */
@Entity
@Table(name = "emp_post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EmpPost implements Serializable, Persistable<EmpPostId> {

    private static final long serialVersionUID = 1L;

    @Id
    private EmpPostId id;

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

    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getTenantId() {
        return this.tenantId;
    }

    public EmpPost tenantId(Integer tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public EmpPost createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public EmpPost createdBy(Integer createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public EmpPost lastUpdatedAt(Instant lastUpdatedAt) {
        this.setLastUpdatedAt(lastUpdatedAt);
        return this;
    }

    public void setLastUpdatedAt(Instant lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Integer getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    public EmpPost lastUpdateBy(Integer lastUpdateBy) {
        this.setLastUpdateBy(lastUpdateBy);
        return this;
    }

    public void setLastUpdateBy(Integer lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public EmpPost setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmpPost)) {
            return false;
        }
        return id != null && id.equals(((EmpPost) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmpPost{" +
            "id=" + getId() +
            ", tenantId=" + getTenantId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", lastUpdatedAt='" + getLastUpdatedAt() + "'" +
            ", lastUpdateBy=" + getLastUpdateBy() +
            "}";
    }
}
