package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QKickout_info extends EntityPathBase<Kickout_info>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1351800180060066131L;

	public static final QKickout_info kickout_info = new QKickout_info("kickout_info");
    public final QBaseEntity _super = new QBaseEntity(this);
	public final NumberPath<Long> id = _super.id;
	public final NumberPath<Long> u_id = createNumber("u_id", Long.class);
	public final NumberPath<Long> b_id = createNumber("b_id", Long.class);
	
	public QKickout_info(String variable) {
		super(Kickout_info.class, forVariable(variable));
	}
	
	public QKickout_info(Path<? extends Kickout_info> path) {
		super(path.getType(), path.getMetadata());
	}
	
	public QKickout_info(PathMetadata metadata) {
		super(Kickout_info.class, metadata);
	}
}
