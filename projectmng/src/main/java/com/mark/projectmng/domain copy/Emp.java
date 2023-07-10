package com.mark.projectmng.domain;

import com.mark.projectmng.domain.enumeration.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Emp.
 */
@Entity
@Table(name = "emp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Emp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "org_id")
    private Integer orgId;

    @NotNull
    @Size(max = 20)
    @Column(name = "num", length = 20, nullable = false)
    private String num;

    @NotNull
    @Size(max = 20)
    @Column(name = "id_num", length = 20, nullable = false)
    private String idNum;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "dob")
    private Instant dob;

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

    public Emp id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Emp name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrgId() {
        return this.orgId;
    }

    public Emp orgId(Integer orgId) {
        this.setOrgId(orgId);
        return this;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getNum() {
        return this.num;
    }

    public Emp num(String num) {
        this.setNum(num);
        return this;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getIdNum() {
        return this.idNum;
    }

    public Emp idNum(String idNum) {
        this.setIdNum(idNum);
        return this;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Gender getGender() {
        return this.gender;
    }

    public Emp gender(Gender gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Instant getDob() {
        return this.dob;
    }

    public Emp dob(Instant dob) {
        this.setDob(dob);
        return this;
    }

    public void setDob(Instant dob) {
        this.dob = dob;
    }

    public Integer getTenantId() {
        return this.tenantId;
    }

    public Emp tenantId(Integer tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Emp createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public Emp createdBy(Integer createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public Emp lastUpdatedAt(Instant lastUpdatedAt) {
        this.setLastUpdatedAt(lastUpdatedAt);
        return this;
    }

    public void setLastUpdatedAt(Instant lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Integer getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    public Emp lastUpdateBy(Integer lastUpdateBy) {
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
        if (!(o instanceof Emp)) {
            return false;
        }
        return id != null && id.equals(((Emp) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Emp{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", orgId=" + getOrgId() +
            ", num='" + getNum() + "'" +
            ", idNum='" + getIdNum() + "'" +
            ", gender='" + getGender() + "'" +
            ", dob='" + getDob() + "'" +
            ", tenantId=" + getTenantId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", lastUpdatedAt='" + getLastUpdatedAt() + "'" +
            ", lastUpdateBy=" + getLastUpdateBy() +
            "}";
    }
}
