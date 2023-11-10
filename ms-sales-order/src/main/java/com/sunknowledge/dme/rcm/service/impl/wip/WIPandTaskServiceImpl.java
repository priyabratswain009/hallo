package com.sunknowledge.dme.rcm.service.impl.wip;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.wip.WIPandTaskService;

import reactor.core.publisher.Mono;

@Service
public class WIPandTaskServiceImpl implements WIPandTaskService {

	@Override
	public Mono<Boolean> validateWIP() throws ParseException {
		// TODO Auto-generated method stub

		String accessToken = InternalAccessTokenUtilities.getAccessToken();
		JSONParser parser = new JSONParser();
		JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
		String token = accessTokenJson.get("access_token").toString();

		RestTemplate restTemplateData = new RestTemplate();

		System.out.println("Token========== " + token);

		HttpHeaders headersData = new HttpHeaders();
		headersData.add("Authorization", "Bearer " + token);
		HttpEntity entityData = new HttpEntity<>(headersData);

		ResponseEntity<ServiceOutcome> responseData = restTemplateData.exchange(
				"http://localhost:8080/services/utilityapis/api/wip/release/getAllObjectTypeMaster", HttpMethod.GET,
				entityData, ServiceOutcome.class);

		System.out.println("OUTPUT========= " + responseData.getBody().getData().getClass());

		ArrayList accessTokenJson1 = (ArrayList) responseData.getBody().getData();

		System.out.println("JSON================ " + accessTokenJson1);

		System.out.println("Entire Object================ " + accessTokenJson1.get(0));

		return null;
	}

	@Override
	public Mono<Boolean> createSoTasks(String taskName, String objectName) throws ParseException {
		// TODO Auto-generated method stub
		String accessToken = InternalAccessTokenUtilities.getAccessToken();
		JSONParser parser = new JSONParser();
		JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
		String token = accessTokenJson.get("access_token").toString();

		RestTemplate restTemplateData = new RestTemplate();

		System.out.println("Token========== " + token);

		HttpHeaders headersData = new HttpHeaders();
		headersData.add("Authorization", "Bearer " + token);
		HttpEntity entityData = new HttpEntity<>(headersData);

		ResponseEntity<ServiceOutcome> responseData = restTemplateData.exchange(
				"http://localhost:8080/services/utilityapis/api/wip/release/saveTaskTypeMaster?Task Name={taskName}&Object Name={objectName}",
				HttpMethod.POST, entityData, ServiceOutcome.class, taskName, objectName);

		System.out.println("OUTPUT========= " + responseData.getBody().getData().getClass());

		return null;
	}

	@Override
	public Mono<Boolean> createWIP(Long objectInstanceId, String objectName, Long taskId, String wipStatusName,
			Long assignedToId, String assignedToName) throws ParseException {
		// TODO Auto-generated method stub
		String accessToken = InternalAccessTokenUtilities.getAccessToken();
		JSONParser parser = new JSONParser();
		JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
		String token = accessTokenJson.get("access_token").toString();

		RestTemplate restTemplateData = new RestTemplate();

		System.out.println("Token========== " + token);

		HttpHeaders headersData = new HttpHeaders();
		headersData.add("Authorization", "Bearer " + token);
		HttpEntity entityData = new HttpEntity<>(headersData);

		ResponseEntity<ServiceOutcome> responseData = restTemplateData.exchange(
				"http://localhost:8080/services/utilityapis/api/wip/release/createWIPStatusandWipDetailsasperUI?objectInstanceId={objectInstanceId}&Object Name={objectName}&taskId={taskId}&WIP Status Name={wipStatusName}&assignedToId={assignedToId}&assignedToName={assignedToName}",
				HttpMethod.POST, entityData, ServiceOutcome.class, objectInstanceId, objectName, taskId, wipStatusName,
				assignedToId, assignedToName);

		System.out.println("OUTPUT========= " + responseData.getBody().getData().getClass());

		return null;
	}

}
