package com.compassouol.city.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class Audited {
	
	@Column(name = "dt_save")
	private LocalDateTime dateSave;
	
	@Column(name = "dt_update")
	private LocalDateTime dateUpdate;
	
	@PrePersist
    public void prePersist() {
		dateSave = LocalDateTime.now();
    }
	
	@PreUpdate
    public void preUpdate() {
		dateUpdate = LocalDateTime.now();
    }

}
