package org.example.activitymodule.activity.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

interface ActivityJpaRepository extends JpaRepository<ActivityEntity, UUID> {

    @Query("""
            SELECT activity
            FROM ActivityEntity activity
            WHERE activity.entity = :entityId
            """)
    List<ActivityEntity> findByEntity(UUID entityId);
}
