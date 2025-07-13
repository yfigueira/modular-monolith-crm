package org.example.accountmodule.account.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {

    @Query("""
            SELECT
            CASE WHEN EXISTS (
                   SELECT 1 FROM AccountEntity a WHERE a.tin = :tin 
            ) THEN true ELSE false END
            """)
    Boolean existsByTin(String tin);
}
