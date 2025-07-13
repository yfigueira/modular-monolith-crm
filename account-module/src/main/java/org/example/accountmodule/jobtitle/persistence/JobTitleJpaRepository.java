package org.example.accountmodule.jobtitle.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

interface JobTitleJpaRepository extends JpaRepository<JobTitleEntity, UUID> {

    @Query("""
        SELECT
        CASE WHEN EXISTS(
                SELECT 1 FROM JobTitleEntity j WHERE LOWER(j.name) = LOWER(:name)
        ) THEN true ELSE false END
        """)
    Boolean existsByName(String name);
}
