package com.ssafy.db.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Kickout_info extends BaseEntity{
	long u_id;
	long b_id;
}
