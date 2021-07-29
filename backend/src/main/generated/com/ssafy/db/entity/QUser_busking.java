package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser_busking extends EntityPathBase<User_busking>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2514855839258580847L;

	public static final QUser_busking user_busking = new QUser_busking("user_busking");
	public final QBaseEntity _super = new QBaseEntity(this);
	public final NumberPath<Long> id = _super.id;
	public final NumberPath<Long> u_id = createNumber("u_id", Long.class);
	public final NumberPath<Long> b_id = createNumber("b_id", Long.class);
	
	public QUser_busking(String variable) {
		super(User_busking.class, forVariable(variable));
	}
	
	public QUser_busking(Path<? extends User_busking> path) {
		super(path.getType(), path.getMetadata());
	}
	
	public QUser_busking(PathMetadata metadata) {
		super(User_busking.class, metadata);
	}
	
	
}
