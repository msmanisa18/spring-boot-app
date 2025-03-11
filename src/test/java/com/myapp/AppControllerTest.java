package com.myapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

class AppControllerTest {

	@Mock
	AppController controller;

    @BeforeEach
    void init() {
    	controller = new AppController();
    }

	@DisplayName("welcome method - NullPointer Test")
	@Test
	void welcomeTest() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode body = mapper.createObjectNode();
		body.set("uname", mapper.convertValue("test", JsonNode.class));
		Assertions.assertThrows(NullPointerException.class, () -> controller.getUser(body));
	}
}