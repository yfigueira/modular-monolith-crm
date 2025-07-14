package org.example.leadmodule.company.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

interface CompanyJpaRepository extends JpaRepository<CompanyEntity, UUID> {

    @Query("""
            SELECT CASE WHEN EXISTS 
                (SELECT 1 FROM CompanyEntity c WHERE c.name = :name) 
            THEN true ELSE false END
            """)
    Boolean existsByName(String name);
}
