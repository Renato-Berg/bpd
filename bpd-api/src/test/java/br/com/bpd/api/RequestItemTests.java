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

import br.com.bpd.common.bean.Category;
import br.com.bpd.common.bean.Client;
import br.com.bpd.common.bean.Product;
import br.com.bpd.common.bean.Request;
import br.com.bpd.common.bean.RequestItem;
import br.com.bpd.common.constants.PathsApiServices;

class RequestItemTests extends AbstractTests {

	private static RequestItem requestItem;

	@BeforeEach
	public void onSetUp() {
		super.setUp();
	}

	@Test
	@Order(1)
	public void create() throws Exception {
		Category category = new Category();
		category.setCategory("Product Category Test");
		// category.setIdCategoria(idCategoria);
		
		Product product = new Product();
		product.setCategory(category);
		product.setDescription("Product Test");
		product.setPhoto("...");
		product.setPrice(1.0);
		product.setProduct("Product A");
		product.setQty(10);
		
		Client client = new Client();
		client.setCity("São Paulo");
		client.setEmail("bpd@gmail.com");
		client.setName("Request Client Test");
		client.setNeighborhood("Santo Amaro");
		client.setPassword("myPassword");
		client.setPostcode("04757-050");
		client.setState("São Paulo");
		client.setStreet("Rua Adele");
		
		Request request = new Request();
		request.setClient(client);
		request.setDate(LocalDateTime.now());
		request.setSession("Session Test");
		request.setStatus("OK");

		requestItem = new RequestItem();
		requestItem.setPrice(1.0);
		requestItem.setProduct(product);
		requestItem.setProductDesc("Request Item Test");
		requestItem.setQty(5);
		requestItem.setRequest(request);
		requestItem.setValue(1.0);
		
		String inputJson = super.mapToJson(requestItem);
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.put(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.REQUEST_ITEM)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		requestItem = super.mapFromJson(content, RequestItem.class);
		assertTrue(requestItem != null && requestItem.getIdItem() > 0);
	}

	@Test
	@Order(2)
	public void read() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.REQUEST_ITEM + PathsApiServices.ROOT + requestItem.getIdItem())
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		requestItem = super.mapFromJson(content, RequestItem.class);
		assertTrue(requestItem != null && requestItem.getIdItem() > 0);
	}

	@Test
	@Order(3)
	public void readList() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.REQUEST_ITENS)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		RequestItem[] requestItemlist = super.mapFromJson(content, RequestItem[].class);
		assertTrue(requestItemlist.length > 0);
	}

	@Test
	@Order(4)
	public void readListWithPagination() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.REQUEST_ITENS)
						.param("qtyItens", "10")
						.param("indexPagination", "1")
						.param("orderField", "idItem")
						.param("order", "ASC")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		RequestItem[] requestItemlist = super.mapFromJson(content, RequestItem[].class);
		assertTrue(requestItemlist.length > 0 && requestItemlist.length < 11);
	}

	@Test
	@Order(5)
	public void update() throws Exception {
		requestItem.setProductDesc("Request Item Test Updated");
		
		String inputJson = super.mapToJson(requestItem);
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.post(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.REQUEST_ITEM)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		requestItem = super.mapFromJson(content, RequestItem.class);
		assertTrue(requestItem != null && requestItem.getIdItem() > 0);
	}

	@Test
	@Order(6)
	public void delete() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.delete(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.REQUEST_ITEM + PathsApiServices.ROOT + requestItem.getIdItem())
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		boolean deleted = super.mapFromJson(content, Boolean.class);
		assertTrue(deleted);
	}

}
