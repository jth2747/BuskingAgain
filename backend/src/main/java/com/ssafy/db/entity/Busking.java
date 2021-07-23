package com.ssafy.db.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Busking extends BaseEntity{
	long owner_id;
	long busking_genre;
	String start_time;
	String end_time;
	String title;
	String description;
	String thumbnail_url;
	int is_active;
	int likes;
	int viewers;
}
