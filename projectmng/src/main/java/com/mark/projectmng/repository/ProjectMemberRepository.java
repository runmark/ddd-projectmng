package com.mark.projectmng.repository;

import com.mark.projectmng.domain.ProjectMember;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProjectMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {}
