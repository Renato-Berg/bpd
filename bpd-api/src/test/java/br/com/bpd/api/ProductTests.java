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
import br.com.bpd.common.bean.Product;
import br.com.bpd.common.constants.PathsApiServices;

class ProductTests extends AbstractTests {

	private static Product product;

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
		
		product = new Product();
		product.setCategory(category);
		product.setDescription("Product Test");
		product.setPhoto("...");
		product.setPrice(1.0);
		product.setProduct("Product A");
		product.setQty(10);
		
		String inputJson = super.mapToJson(product);
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.put(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.PRODUCT)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		product = super.mapFromJson(content, Product.class);
		assertTrue(product != null && product.getIdProduto() > 0);
	}

	@Test
	@Order(2)
	public void read() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.PRODUCT + PathsApiServices.ROOT + product.getIdProduto())
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		product = super.mapFromJson(content, Product.class);
		assertTrue(product != null && product.getIdProduto() > 0);
	}

	@Test
	@Order(3)
	public void readList() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.PRODUCTS)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Product[] productlist = super.mapFromJson(content, Product[].class);
		assertTrue(productlist.length > 0);
	}

	@Test
	@Order(4)
	public void readListWithPagination() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.get(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.PRODUCTS)
						.param("qtyItens", "10")
						.param("indexPagination", "1")
						.param("orderField", "idProduto")
						.param("order", "ASC")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Product[] productlist = super.mapFromJson(content, Product[].class);
		assertTrue(productlist.length > 0 && productlist.length < 11);
	}

	@Test
	@Order(5)
	public void update() throws Exception {
		// product.setCategory(category);
		product.setDescription("Product Test Updated");
		product.setPhoto("...");
		product.setPrice(1.0);
		product.setProduct("Product A");
		product.setQty(10);
		
		String inputJson = super.mapToJson(product);
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.post(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.PRODUCT)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		product = super.mapFromJson(content, Product.class);
		assertTrue(product != null && product.getIdProduto() > 0);
	}

	@Test
	@Order(6)
	public void delete() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						MockMvcRequestBuilders
						.delete(PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.PRODUCT + PathsApiServices.ROOT + product.getIdProduto())
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		boolean deleted = super.mapFromJson(content, Boolean.class);
		assertTrue(deleted);
	}

}
