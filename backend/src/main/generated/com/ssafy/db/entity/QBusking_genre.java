package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


@Generated("com.querydsl.codegen.EntitySerializer")
public class QBusking_genre extends EntityPathBase<Busking_genre> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4300172399265676123L;
	
	public static final QBusking_genre busking_genre = new QBusking_genre("busking_genre");
	
    public final QBaseEntity _super = new QBaseEntity(this);
	
	public final NumberPath<Long> id = _super.id;
	
	public final StringPath name = createString("name");
	
	public QBusking_genre(String variable) {
		super(Busking_genre.class, forVariable(variable));
	}
	
	public QBusking_genre(Path<? extends Busking_genre> path) {
		super(path.getType(), path.getMetadata());
	}
	
	public QBusking_genre(PathMetadata metadata) {
		super(Busking_genre.class, metadata);
	}
}
