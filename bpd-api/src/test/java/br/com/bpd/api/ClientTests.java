package br.com.bpd.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.bpd.common.bean.Client;
import br.com.bpd.common.constants.PathsApiServices;

class ClientTests extends AbstractTests {

	private static Client client;

	@BeforeEach
	public void onSetUp() {
		super.setUp();
	}

	@Test
	@Order(1)
	public void create() throws Exception {
		client = new Client();
		client.setCity("São Paulo");
		client.setEmail("bpd@gmail.com");
		client.setName("Client Test");
		client.setNeighborhood("Santo Amaro");
		client.setPassword("myPassword");
		client.setPostcode("04757-050");
		client.setState("São Paulo");
		client.setStreet("Rua Adele");
		
		String inputJson = super.mapToJson(client);
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.put(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CLIENT)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		client = super.mapFromJson(content, Client.class);
		assertTrue(client != null && client.getIdCliente() > 0);
	}

	@Test
	@Order(2)
	public void read() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CLIENT + PathsApiServices.ROOT + client.getIdCliente())
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		client = super.mapFromJson(content, Client.class);
		assertTrue(client != null && client.getIdCliente() > 0);
	}

	@Test
	@Order(3)
	public void readList() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CLIENTS)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Client[] clientlist = super.mapFromJson(content, Client[].class);
		assertTrue(clientlist.length > 0);
	}

	@Test
	@Order(4)
	public void readListWithPagination() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CLIENTS)
						.param("qtyItens", "10")
						.param("indexPagination", "1")
						.param("orderField", "idCliente")
						.param("order", "ASC")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Client[] clientlist = super.mapFromJson(content, Client[].class);
		assertTrue(clientlist.length > 0 && clientlist.length < 11);
	}

	@Test
	@Order(5)
	public void update() throws Exception {
		client.setName("Client Test Updated");
		
		String inputJson = super.mapToJson(client);
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.post(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CLIENT)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		client = super.mapFromJson(content, Client.class);
		assertTrue(client != null && client.getIdCliente() > 0);
	}

	@Test
	@Order(6)
	public void delete() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.delete(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CLIENT + PathsApiServices.ROOT + client.getIdCliente())
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		boolean deleted = super.mapFromJson(content, Boolean.class);
		assertTrue(deleted);
	}

}
