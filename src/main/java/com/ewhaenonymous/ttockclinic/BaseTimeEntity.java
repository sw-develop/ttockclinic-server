package com.ewhaenonymous.ttockclinic;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate //Entity가 생성되어 저장될 때 시간 자동 저장
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @LastModifiedDate //조회한 Entity의 값을 변경할 때 시간 자동 저장
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    public LocalDateTime getDateCreated(){
        return dateCreated;
    }

    public LocalDateTime getLastUpdated(){
        return lastUpdated;
    }

}
