package com.ssafy.db.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Fav_genre extends BaseEntity{
	long u_id;
	long g_id;
}
