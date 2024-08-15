package com.core.solution.access;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.solution.exception.SolutionData;
import com.core.solution.exception.SolutionException;
import com.core.solution.mapper.AforeMapper;
import com.core.solution.model.entity.EntityConfigTable;
import com.core.solution.utils.MessagesAccess;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class AforeRepository extends GenericRepository {

	public List<EntityConfigTable> getConfigTables() throws SolutionException {		
		AforeMapper mapper = super.getSqlSession().getMapper(AforeMapper.class);
		List<EntityConfigTable> listConfigTable = null;
		try {			
			listConfigTable = mapper.getConfigTables();			
		} catch (Exception e) {			
			throw new SolutionException(
					MessagesAccess.MESSAGE_ERROR_DB_GET_ALL_CONFIG_AUTO,
					new SolutionData(MessagesAccess.SUCCESS,
									  MessagesAccess.TITLE_ERROR_DB_GET_ALL_CONFIG_AUTO,
							          e.getCause().toString(),
							          MessagesAccess.CODE_ERROR_DB_GET_ALL_CONFIG_AUTO));			
		}				
		return listConfigTable;
	}

}
