package org.example.dealmodule.deal.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deal", schema = "deal")
public class DealEntity {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "title")
    private String title;

    @Column(name = "stage")
    private Integer stage;

    @Column(name = "expected_revenue")
    private String expectedRevenue;

    @Column(name = "expected_close_date")
    private LocalDateTime expectedCloseDate;

    @Column(name = "contact")
    private UUID contact;

    @Column(name = "owner")
    private UUID owner;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
