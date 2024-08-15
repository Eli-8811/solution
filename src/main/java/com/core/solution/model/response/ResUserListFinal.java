package com.core.solution.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResUserListFinal {

	@JsonProperty(index=0, value = "users")
	private List<ResUserData> listResponseUserData;

}