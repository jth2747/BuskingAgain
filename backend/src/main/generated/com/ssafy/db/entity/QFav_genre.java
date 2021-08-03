package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QFav_genre extends EntityPathBase<Fav_genre> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9080789870324772458L;

	public static final QFav_genre fav_genre = new QFav_genre("fav_genre");
    public final QBaseEntity _super = new QBaseEntity(this);
	public final NumberPath<Long> id = _super.id;
	public final NumberPath<Long> u_id = createNumber("u_id", Long.class);
	public final NumberPath<Long> g_id = createNumber("g_id", Long.class);
	
	public QFav_genre(String variable) {
		super(Fav_genre.class, forVariable(variable));
	}
	
	public QFav_genre(Path<? extends Fav_genre> path) {
		super(path.getType(), path.getMetadata());
	}
	
	public QFav_genre(PathMetadata metadata) {
		super(Fav_genre.class, metadata);
	}
}
