package com.core.solution.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.core.solution.exception.SolutionException;
import com.core.solution.model.entity.EntityConfigTable;

@Mapper
public interface AforeMapper {

	List<EntityConfigTable> getConfigTables() throws SolutionException;
	
}
