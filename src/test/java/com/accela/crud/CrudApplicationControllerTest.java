package com.accela.crud;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.accela.crud.controller.CrudController;
import com.accela.crud.models.PersonForm;
import com.accela.crud.service.CrudServiceInterface;

@WebMvcTest(CrudController.class)
class CrudApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CrudServiceInterface crudService;

	@Test
	public void appRootEndoint_thenCorrectResponseTest() throws Exception {
		MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8"));
		mockMvc.perform(MockMvcRequestBuilders.post("/").contentType(MediaType.TEXT_HTML))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(textPlainUtf8));
	}

	@Test
	public void addNewPersonEndpoint_thenCorrectResponseTest() throws Exception {
		MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8"));
		mockMvc.perform(MockMvcRequestBuilders.post("/person/new").contentType(MediaType.TEXT_HTML))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(textPlainUtf8));
	}

	@Test
	public void saveNewValidUser_thenCorrectResponseTest() throws Exception {
		MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8"));
		PersonForm personForm = new PersonForm();
		mockMvc.perform(
				MockMvcRequestBuilders.post("/person").content(personForm.toString()).contentType(MediaType.TEXT_HTML))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(textPlainUtf8));
	}

	@Test
	public void personNotFoundTest() throws Exception {
		mockMvc.perform(get("/person/show/").param("id", "1")).andExpect(status().isNotFound());
	}

	@Test
	public void getAllEmployeesAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/person/list").accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk());
	}

	@Test
	public void deletePersonTest() throws Exception {
		mockMvc.perform(get("/person/delete/").param("id", "1")).andExpect(status().isNotFound());
	}
}
