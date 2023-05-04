package sg.edu.ntu.m3project.m3project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import sg.edu.ntu.m3project.m3project.controller.ConcertController;
import sg.edu.ntu.m3project.m3project.interceptor.Interceptor;
import sg.edu.ntu.m3project.m3project.service.ConcertService;

@WebMvcTest(controllers = ConcertController.class)
class M3projectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ConcertService concertService;

	@MockBean
	Interceptor interceptor;

	// to bypass interceptor when testing
	@BeforeEach
	void initTest() throws Exception{
		when(interceptor.preHandle(any(), any(), any())).thenReturn(true);
	}

	@Test
	void whenGetConcertsThenReturnStatusOK() throws Exception {
		mockMvc.perform(get("/concerts").contentType("application/json"))
				// .andExpect(status().isNotFound());
				.andExpect(status().isOk());
	}

}
