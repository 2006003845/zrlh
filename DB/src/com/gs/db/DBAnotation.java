package com.gs.db;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DBAnotation {
	String type() default "";
	String tableName() default "";
	String _id() default "integer primary key autoincrement";
}	
