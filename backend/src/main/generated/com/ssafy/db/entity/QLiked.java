package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QLiked extends EntityPathBase<Liked>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6841660716072693727L;
	public static final QLiked liked = new QLiked("liked");
	public final QBaseEntity _super = new QBaseEntity(this);
	public final NumberPath<Long> id = _super.id;
	public final NumberPath<Long> u_id = createNumber("u_id", Long.class);
	public final NumberPath<Long> b_id = createNumber("b_id", Long.class);
	
	public QLiked(String variable) {
		super(Liked.class, forVariable(variable));
	}
	
	public QLiked(Path<? extends Liked> path) {
		super(path.getType(), path.getMetadata());
	}
	
	public QLiked(PathMetadata metadata) {
		super(Liked.class, metadata);
	}
	
}
