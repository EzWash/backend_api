package com.ezwash.backend.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public abstract class AuditModel implements Serializable {
    @Temporal(TemporalType.TIMESTAMP) //Cataloga que el atributo no solo guarde fecha sino fecha y hora
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate //Guarda la fecha y hora en la que se creó el objeto
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false, updatable = false)
    @LastModifiedDate //Guarda la fecha y hora en la que se modificó el objeto
    private Date updatedAt;


}