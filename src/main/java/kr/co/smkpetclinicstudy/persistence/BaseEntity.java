package kr.co.smkpetclinicstudy.persistence;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@EqualsAndHashCode(callSuper = false)      // equals & hashCode 메서드를 자동 생성
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreatedDate
    @Column(name = "created_at")
    protected LocalDate createdAt;

    @LastModifiedDate
    @Column(name = "modified_at")
    protected LocalDate modifiedAt;
}
