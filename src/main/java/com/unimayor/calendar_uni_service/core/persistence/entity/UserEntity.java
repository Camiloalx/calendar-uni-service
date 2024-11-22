package com.unimayor.calendar_uni_service.core.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios", schema = "calendar")
public class UserEntity implements Serializable {
    @Id
    @Column(unique = true, nullable = false, length = 100)
    private String id;

    @Column(name = "correo_electronico", nullable = false, length = 100)
    private String username;

    @Column(name = "contrasena", nullable = false, length = 100)
    private String password;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime
            creationDate;

    @Column(name = "activo", nullable = false)
    private boolean active;
}
