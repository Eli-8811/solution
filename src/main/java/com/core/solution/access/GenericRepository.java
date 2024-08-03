package com.core.solution.access;

import org.apache.ibatis.session.SqlSession;

import jakarta.inject.Inject;
import lombok.Data;

@Data
public abstract class GenericRepository {
	
	@Inject
	SqlSession sqlSession;
	
}
