package com.acs.egov.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.acs.egov.dto.PaapDetailDto;
import com.acs.egov.dto.PaapDetailRequestDto;
import com.acs.egov.dto.PaapDetailWrapperDto;
import com.acs.egov.dto.PaapDto;
import com.acs.egov.dto.PaapRequestDto;
import com.acs.egov.dto.PaapWrapperDto;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class RecordService {
	List<PaapDto> paapDtos;

	public List<PaapDto> getPaaps(List<String> dropdownItems) {
		if (!CollectionUtils.isEmpty(paapDtos)) {
			return paapDtos;
		}
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl(CacheControl.noCache());
		headers.set("Referer", "http://localhost:8080");
		
		List<PaapDto> allPaaps = new ArrayList<>();

		
		for (String text : dropdownItems) {
			log.info("Retrieving paaps for text: {}", text);
			PaapRequestDto dto = PaapRequestDto.builder().pageIndex(0).pageSize(18).sortProperties(new ArrayList<>()).acCifAndNameText(text).build();
	
			HttpEntity<PaapRequestDto> request = new HttpEntity<>(dto, headers);
	
			try {
				ResponseEntity<PaapWrapperDto> response = restTemplate.postForEntity(
						"http://e-licitatie.ro/api-pub/publicPaap/getAll", request, PaapWrapperDto.class);
	
				List<PaapDto> paaps = response.getBody().getItems();
				allPaaps.addAll(paaps);
			} catch (HttpClientErrorException | HttpServerErrorException ex) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Failed to send description to Python Web-service: " + ex.getMessage());
			} catch (ResourceAccessException ex) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Python api url: " + ex.getMessage());
			}
		}

		paapDtos = allPaaps;
		return allPaaps;
	}
	
	public List<PaapDetailDto> getPaapDetails(Integer paapId) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setCacheControl(CacheControl.noCache());
		headers.set("Referer", "http://localhost:8080");

		log.info("Retrieving paap details for paap id: {}", paapId);
		PaapDetailRequestDto dto = PaapDetailRequestDto.builder().pageIndex(0).pageSize(18).sortProperties(new ArrayList<>()).paapId(paapId).build();

		HttpEntity<PaapDetailRequestDto> request = new HttpEntity<>(dto, headers);

		try {
			ResponseEntity<PaapDetailWrapperDto> response = restTemplate.postForEntity(
					"http://e-licitatie.ro/api-pub/publicPaap/getForEditPlanDetails", request, PaapDetailWrapperDto.class);

			return response.getBody().getItems();
		} catch (HttpClientErrorException | HttpServerErrorException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Failed to send description to Python Web-service: " + ex.getMessage());
		} catch (ResourceAccessException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Python api url: " + ex.getMessage());
		}
	}

}
