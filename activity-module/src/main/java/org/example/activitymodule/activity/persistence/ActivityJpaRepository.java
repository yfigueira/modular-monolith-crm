package org.example.activitymodule.activity.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ActivityJpaRepository extends JpaRepository<ActivityEntity, UUID> {
}
