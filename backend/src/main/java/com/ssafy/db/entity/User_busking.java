package com.ssafy.db.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User_busking extends BaseEntity {

	long u_id;
	long b_id;
}
