package br.com.bpd.api;

import java.io.IOException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bpd.api.config.BrasilPrevDigitalApiApplication;

@WebAppConfiguration
@Transactional
@SpringBootTest(classes = BrasilPrevDigitalApiApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class AbstractTests {

	protected MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).dispatchOptions(true).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

}
