package br.com.bpd.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.bpd.common.bean.Client;
import br.com.bpd.common.bean.Request;
import br.com.bpd.common.constants.PathsApiServices;

class RequestTests extends AbstractTests {

	private static Request request;

	@BeforeEach
	public void onSetUp() {
		super.setUp();
	}

	@Test
	@Order(1)
	public void create() throws Exception {
		Client client = new Client();
		client.setCity("São Paulo");
		client.setEmail("bpd@gmail.com");
		client.setName("Request Client Test");
		client.setNeighborhood("Santo Amaro");
		client.setPassword("myPassword");
		client.setPostcode("04757-050");
		client.setState("São Paulo");
		client.setStreet("Rua Adele");
		
		request = new Request();
		request.setClient(client);
		request.setDate(LocalDateTime.now());
		request.setSession("Session Test");
		request.setStatus("OK");
		
		String inputJson = super.mapToJson(request);
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.put(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.REQUEST)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		request = super.mapFromJson(content, Request.class);
		assertTrue(request != null && request.getIdRequest() > 0);
	}

	@Test
	@Order(2)
	public void read() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.REQUEST + PathsApiServices.ROOT + request.getIdRequest())
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		request = super.mapFromJson(content, Request.class);
		assertTrue(request != null && request.getIdRequest() > 0);
	}

	@Test
	@Order(3)
	public void readList() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.REQUESTS)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Request[] requestlist = super.mapFromJson(content, Request[].class);
		assertTrue(requestlist.length > 0);
	}

	@Test
	@Order(4)
	public void readListWithPagination() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.REQUESTS)
						.param("qtyItens", "10")
						.param("indexPagination", "1")
						.param("orderField", "idRequest")
						.param("order", "ASC")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Request[] requestlist = super.mapFromJson(content, Request[].class);
		assertTrue(requestlist.length > 0 && requestlist.length < 11);
	}

	@Test
	@Order(5)
	public void update() throws Exception {
		request.setSession("Session Test Updated");
		
		String inputJson = super.mapToJson(request);
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.post(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.REQUEST)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		request = super.mapFromJson(content, Request.class);
		assertTrue(request != null && request.getIdRequest() > 0);
	}

	@Test
	@Order(6)
	public void delete() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.delete(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.REQUEST + PathsApiServices.ROOT + request.getIdRequest())
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		boolean deleted = super.mapFromJson(content, Boolean.class);
		assertTrue(deleted);
	}

}
