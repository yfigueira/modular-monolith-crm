package org.example.dealmodule.deal.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DealStage {
    NEW(1, "New"),
    DISCOVERY(2, "Discovery"),
    PROPOSAL(3, "Proposal"),
    NEGOTIATION(4, "Negotiation"),
    CLOSED_LOST(5, "Closed lost"),
    CLOSED_WON(6, "Closed won"),
    NOT_AVAILABLE(0, "N/A");

    private final Integer code;
    private final String label;
}
