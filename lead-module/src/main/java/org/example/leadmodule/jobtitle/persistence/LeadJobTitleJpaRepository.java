package org.example.leadmodule.jobtitle.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

interface LeadJobTitleJpaRepository extends JpaRepository<LeadJobTitleEntity, UUID> {

    @Query("""
            SELECT CASE WHEN EXISTS 
                (SELECT 1 FROM LeadJobTitleEntity j WHERE j.name = :name) 
            THEN true ELSE false END
            """)
    Boolean existsByName(String name);
}
