package com.ssafy.db.entity;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "conference")
public class Conference extends BaseEntity{
	long owner_id;
	long conference_genre;
	String call_start_time;
	String call_end_time;
	String thumbnail_url;
	String title;
	String description;
	int is_active;
}
