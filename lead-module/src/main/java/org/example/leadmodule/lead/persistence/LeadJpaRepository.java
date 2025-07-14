package org.example.leadmodule.lead.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

interface LeadJpaRepository extends JpaRepository<LeadEntity, UUID> {

    @Query("""
            SELECT CASE WHEN EXISTS 
                (SELECT 1 FROM LeadEntity l WHERE l.email = :email) 
            THEN true ELSE false END       
            """)
    Boolean existsByEmail(String email);
}
