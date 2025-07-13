package org.example.activitymodule.user.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record User(
        UUID id
) {
}
