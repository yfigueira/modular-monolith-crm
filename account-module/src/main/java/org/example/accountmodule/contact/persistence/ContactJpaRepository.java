package org.example.accountmodule.contact.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

interface ContactJpaRepository extends JpaRepository<ContactEntity, UUID> {

    @Query("""
            SELECT c FROM ContactEntity c
            WHERE c.company = :companyId
            """)
    List<ContactEntity> findByCompany(UUID companyId);

    @Query("""
            SELECT CASE WHEN EXISTS
                (SELECT 1 FROM ContactEntity c WHERE c.email = :email)
            THEN true ELSE false END
            """)
    Boolean existsByEmail(String email);
}
