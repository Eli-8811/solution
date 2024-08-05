package com.core.solution.resource;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.core.solution.SolutionApplication;
import com.core.solution.bussines.UserService;
import com.core.solution.exception.SolutionException;
import com.core.solution.model.entity.EntityRoleUser;
import com.core.solution.model.entity.EntityUser;
import com.core.solution.model.payload.UserRequest;
import com.core.solution.model.response.ResponseUserData;
import com.core.solution.model.response.ResponseUserFinal;
import com.core.solution.model.response.ResponseUserListFinal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SolutionApplication.class)
@AutoConfigureMockMvc
public class UserHandlerTests {

	@Autowired
	private MockMvc mockMcv;
	
	@SpyBean
	private UserService userServiceMockBean;
	/*
	@Test
	public void whenCreateUser() throws SolutionException, JsonProcessingException {		
		UserRequest userRequest = new UserRequest();		
		userRequest.setName("Demo");
		userRequest.setLastname("Test JUnit");
		userRequest.setUsername("demostenes");
		userRequest.setEmail("demo@springboot.com");
		userRequest.setPhone(5555555555L);
		userRequest.setPassword("secret");
		userRequest.setAge(35);
		when(userServiceMockBean.createUser(userRequest)).thenReturn(userRequest);
		ResultMatcher created = status().isCreated();		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/create")
				.content(asJsonString(userRequest))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE);			
		assertDoesNotThrow(() -> {			
			MvcResult result = mockMcv.perform(requestBuilder).andExpect(status().isCreated()).andDo(print()).andExpect(created).andReturn();		
			assertNotNull(result);	
	    });	
	}
	*/
	private static String asJsonString(final Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);		
	}
	
	@Test
	public void whenGetUsers() throws SolutionException {
		ResponseUserListFinal responseUserFinal = new ResponseUserListFinal();		
		List<ResponseUserData> listResponseUserData = new ArrayList<>();
		List<EntityUser> listEntityUser = new ArrayList<EntityUser>();
		EntityUser entityUser1 = new EntityUser(1, "Demos1", "Demostenes1", "demostenes", "demo@springboot.com", "secret", 35, 5555555555L, true, new Date(), new Date(), new HashSet<EntityRoleUser>());
		EntityUser entityUser2 = new EntityUser(2, "Demos1", "Demostenes1", "demostenes", "demo@springboot.com", "secret", 35, 5555555555L, true, new Date(), new Date(), new HashSet<EntityRoleUser>());
		EntityUser entityUser3 = new EntityUser(3, "Demos1", "Demostenes1", "demostenes", "demo@springboot.com", "secret", 35, 5555555555L, true, new Date(), new Date(), new HashSet<EntityRoleUser>());		
		listEntityUser.add(entityUser1);
		listEntityUser.add(entityUser2);
		listEntityUser.add(entityUser3);		
		when(userServiceMockBean.getUsers()).thenReturn(listEntityUser);
		for (EntityUser entityUser : this.userServiceMockBean.getUsers()) {
			ResponseUserData responseUserData = new ResponseUserData();
			responseUserData.setUserId(entityUser.getUserId());
			responseUserData.setName(entityUser.getName());
			responseUserData.setLastname(entityUser.getLastname());
			responseUserData.setAge(entityUser.getAge());
			responseUserData.setEnabled(entityUser.getEnabled());
			responseUserData.setCreationAt(entityUser.getCreationAt());
			responseUserData.setModificationAt(entityUser.getModificationAt());
			listResponseUserData.add(responseUserData);
		}		
		responseUserFinal.setListResponseUserData(listResponseUserData);			
		ResultMatcher ok = status().isOk();		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/all")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE);		
		assertDoesNotThrow(() -> {
			MvcResult result = mockMcv.perform(requestBuilder).andExpect(status().isOk()).andDo(print()).andExpect(ok).andReturn();		
			assertNotNull(result);
	    });	
	}
	
	@Test
	public void whenGetUser() throws SolutionException {
		ResponseUserFinal responseUserFinal = new ResponseUserFinal();
		ResponseUserData responseUserData = new ResponseUserData();		
		EntityUser entityUser = new EntityUser(1, "Demos1", "Demostenes1", "demostenes", "demo@springboot.com", "secret", 35, 5555555555L, true, new Date(), new Date(), new HashSet<EntityRoleUser>());
		responseUserData.setUserId(entityUser.getUserId());
		responseUserData.setName(entityUser.getName());
		responseUserData.setLastname(entityUser.getLastname());
		responseUserData.setAge(entityUser.getAge());
		responseUserData.setEnabled(entityUser.getEnabled());
		responseUserData.setCreationAt(entityUser.getCreationAt());
		responseUserData.setModificationAt(entityUser.getModificationAt());	
		responseUserFinal.setResponseUserData(responseUserData);
		when(userServiceMockBean.getUser(entityUser.getUsername())).thenReturn(entityUser);			
		ResultMatcher ok = status().isOk();			
		assertDoesNotThrow(() -> {
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/get?username=demostenes")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.accept(MediaType.APPLICATION_JSON_VALUE);	
			MvcResult result = mockMcv.perform(requestBuilder).andExpect(status().isOk()).andDo(print()).andExpect(ok).andReturn();		
			assertNotNull(result);
	    });	
	}
	
	@Test
	public void whenPatchUser() throws SolutionException {
		UserRequest userRequest = new UserRequest();		
		userRequest.setName(null);
		userRequest.setLastname(null);
		userRequest.setUsername("demostenes");
		userRequest.setEmail("demo_patch@springboot.com");
		userRequest.setPhone(null);
		userRequest.setPassword(null);
		userRequest.setAge(null);
		userServiceMockBean.patchUser(userRequest);			
		ResultMatcher ok = status().isOk();		
		assertDoesNotThrow(() -> {
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/user/patch")
					.content(asJsonString(userRequest))
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.accept(MediaType.APPLICATION_JSON_VALUE);
			MvcResult result = mockMcv.perform(requestBuilder).andExpect(status().isOk()).andDo(print()).andExpect(ok).andReturn();		
			assertNotNull(result);
	    });	
	}
	
	@Test
	public void whenPutUser() throws SolutionException {
		UserRequest userRequest = new UserRequest();		
		userRequest.setName("Demo");
		userRequest.setLastname("Test JUnit");
		userRequest.setUsername("demostenes");
		userRequest.setEmail("demo@springboot.com");
		userRequest.setPhone(5555555555L);
		userRequest.setPassword("secret");
		userRequest.setAge(35);
		userServiceMockBean.putUser(userRequest);			
		ResultMatcher ok = status().isOk();		
		assertDoesNotThrow(() -> {
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/user/put")
					.content(asJsonString(userRequest))
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.accept(MediaType.APPLICATION_JSON_VALUE);
			MvcResult result = mockMcv.perform(requestBuilder).andExpect(status().isOk()).andDo(print()).andExpect(ok).andReturn();		
			assertNotNull(result);
	    });	
	}
	
}