/*package com.cognizant.test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cognizant.config.DemoAppConfig;
import com.cognizant.entity.TransactionDetail;
import com.cognizant.entity.User;
import com.cognizant.service.BankService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

*//**
 * 
 * @author ManiSX
 *
 *         Hamcrest 1.3 (hamcrest-all). We use Hamcrest matchers when we are
 *         writing assertions for the responses. Junit 4.11. We need to exclude
 *         the hamcrest-core dependency because we already added the
 *         hamcrest-all dependency. Mockito 1.9.5 (mockito-core). We use Mockito
 *         as our mocking library. Spring Test 3.2.3.RELEASE JsonPath 0.8.1
 *         (json-path and json-path-assert). We use JsonPath when we are writing
 *         assertions for JSON documents returned by our REST API.
 *//*

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { DemoAppConfig.class })
public class RestControllerTest {

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mvc;

	@Autowired
	private BankService bankService;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void verifyBeans() {
		assertNotNull(bankService);
	}

	@Test
	public void performTransaction() throws Exception {

		String accNo = "1234567890123457";

		TransactionDetail transDetail = new TransactionDetail("Test", "Withdrawal", 6000.00);
		transDetail.setTransactionId(0L);

		MvcResult result = (MvcResult) mvc.perform(post("/api/performtransaction/" + accNo).contentType(MediaType.APPLICATION_JSON)
				.content(toJson(transDetail))).andExpect(status().isOk());
		
		System.out.println("");result.getResponse().getContentAsString();
		
	}

	@Test
	public void addUser() throws Exception {

		// Long accountNumber, String accountType, String accountHolderName,
		// Double accountBalance
		User theUser = new User(1234567790129499L, "Salary", "Simon", 10000.00);
		mvc.perform(post("/api/users/").contentType(MediaType.APPLICATION_JSON).content(toJson(theUser)))
				.andExpect(status().isOk());
	}

	private byte[] toJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsBytes(obj);
	}

	@Test
	public void getUsers() throws Exception {
		mvc.perform(get("/api/users"));
	}

	// Testing HTTP Status code

	@Test

	public void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived()
			throws ClientProtocolException, IOException {
		// Given
		HttpUriRequest request = new HttpGet("http://localhost:8080/bankmanagement/api/users");

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
	}

	@Test

	public void checkGivenUserExists() throws ClientProtocolException, IOException {

		String accNo = "1234567890123456";
		// Given
		HttpUriRequest request = new HttpGet("http://localhost:8080/bankmanagement/api/users/" + accNo);

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}

	// Test Mime Types

	public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson()
			throws ClientProtocolException, IOException {
		// Given
		String jsonMimeType = "application/json";
		HttpUriRequest request = new HttpGet("http://localhost:8080/bankmanagement/api/users");

		// When
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		// Then
		String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
		assertEquals(jsonMimeType, mimeType);
	}

	@Test
	public void getUser() throws Exception {
	}

}
*/