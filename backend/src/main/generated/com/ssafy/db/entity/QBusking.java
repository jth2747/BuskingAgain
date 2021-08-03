package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QBusking extends EntityPathBase<Busking> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4996892432334404501L;
	
	public static final QBusking busking = new QBusking("busking");
	public final QBaseEntity _super = new QBaseEntity(this);
	public final NumberPath<Long> id = _super.id;
	
	public final NumberPath<Long> owner_id = createNumber("owner_id", Long.class);
	public final NumberPath<Long> busking_genre = createNumber("owner_id", Long.class);
	public final NumberPath<Integer> is_active = createNumber("is_active", Integer.class);
	public final NumberPath<Integer> likes = createNumber("likes", Integer.class);
	public final NumberPath<Integer> viewers = createNumber("viewers", Integer.class);
	public final NumberPath<Integer> max_viewers = createNumber("max_viewers", Integer.class);
	public final StringPath thumbnail_url = createString("thumbnail_url");
	public final StringPath title = createString("title");
	public final StringPath description = createString("description");
	
	public QBusking(String variable) {
        super(Busking.class, forVariable(variable));
    }

    public QBusking(Path<? extends Busking> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBusking(PathMetadata metadata) {
        super(Busking.class, metadata);
    }

}
