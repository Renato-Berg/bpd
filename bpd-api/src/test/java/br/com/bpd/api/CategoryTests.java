package br.com.bpd.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.bpd.common.bean.Category;
import br.com.bpd.common.constants.PathsApiServices;

class CategoryTests extends AbstractTests {

	private static Category category;

	@BeforeEach
	public void onSetUp() {
		super.setUp();
	}

	@Test
	@Order(1)
	public void create() throws Exception {
		category = new Category();
		category.setCategory("Category Test");
		
		String inputJson = super.mapToJson(category);
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.put(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CATEGORY)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		category = super.mapFromJson(content, Category.class);
		assertTrue(category != null && category.getIdCategoria() > 0);
	}

	@Test
	@Order(2)
	public void read() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CATEGORY + PathsApiServices.ROOT + category.getIdCategoria())
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		category = super.mapFromJson(content, Category.class);
		assertTrue(category != null && category.getIdCategoria() > 0);
	}

	@Test
	@Order(3)
	public void readList() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CATEGORIES)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Category[] categorylist = super.mapFromJson(content, Category[].class);
		assertTrue(categorylist.length > 0);
	}

	@Test
	@Order(4)
	public void readListWithPagination() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CATEGORIES)
						.param("qtyItens", "10")
						.param("indexPagination", "1")
						.param("orderField", "idCategoria")
						.param("order", "ASC")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Category[] categorylist = super.mapFromJson(content, Category[].class);
		assertTrue(categorylist.length > 0 && categorylist.length < 11);
	}

	@Test
	@Order(5)
	public void update() throws Exception {
		category.setCategory("Category Test Updated");
		
		String inputJson = super.mapToJson(category);
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.post(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CATEGORY)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		category = super.mapFromJson(content, Category.class);
		assertTrue(category != null && category.getIdCategoria() > 0);
	}

	@Test
	@Order(6)
	public void delete() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.delete(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CATEGORY + PathsApiServices.ROOT + category.getIdCategoria())
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		boolean deleted = super.mapFromJson(content, Boolean.class);
		assertTrue(deleted);
	}

}
