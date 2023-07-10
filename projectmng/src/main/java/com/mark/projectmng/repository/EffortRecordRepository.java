package com.mark.projectmng.repository;

import com.mark.projectmng.domain.EffortRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the EffortRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EffortRecordRepository extends JpaRepository<EffortRecord, Long> {}
