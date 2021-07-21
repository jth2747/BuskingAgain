package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QConference_genre extends EntityPathBase<Conference_genre>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -300934380741964546L;
	
	public static final QConference_genre conference_genre = new QConference_genre("conference_genre");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");


    public QConference_genre(String variable) {
        super(Conference_genre.class, forVariable(variable));
    }

    public QConference_genre(Path<? extends Conference_genre> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConference_genre(PathMetadata metadata) {
        super(Conference_genre.class, metadata);
    }

}
