package com.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AppController {

	@Autowired
	private Environment environment;

	@PostMapping("/usr-dtls")
	public ResponseEntity<ObjectNode> getUser(@RequestBody ObjectNode body) {

		log.info("getUser() :: /usr-dtls :: request :: {}", body.toString());
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		if (null != body 
			&& !body.isEmpty() 
			&& body.has("uname") && 
			"test".equals(body.get("uname").asText())) {
			objectNode.put("userName", environment.getProperty("test.user.name", "Not Found"));
		} else {
			objectNode.put("userName", "Not Found");
		}
		objectNode.put("userEmail", environment.getProperty("test.user.email", "Not Found"));
		System.out.println("Email : " + environment.getProperty("test.user.email", "Not Found"));
		log.info("Response sent : {}", objectNode);
		return ResponseEntity.ok(objectNode);
	}
}
