package com.conceptile.flowchart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.conceptile.flowchart.service.FlowchartService;

@SpringBootTest
class FlowChartApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private FlowchartService flowchartService;

	@Test
	public void testCreateFlowchart() throws Exception { // Implement test cases }
	}

}
