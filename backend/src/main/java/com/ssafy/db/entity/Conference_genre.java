package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "conference_genre")
public class Conference_genre extends BaseEntity{
	String name;
}
