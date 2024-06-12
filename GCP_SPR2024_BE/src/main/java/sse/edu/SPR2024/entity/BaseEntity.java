package sse.edu.SPR2024.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @CreationTimestamp
    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date", nullable = true, insertable = false)
    private OffsetDateTime modifiedDate;
}