package com.acs.egov.rest;

import com.acs.egov.dto.PaapDetailDto;
import com.acs.egov.dto.PaapDto;
import com.acs.egov.dto.StringResponse;
import com.acs.egov.service.RecordService;
import com.acs.egov.xml.Records;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Api(tags = "Users Controller")
@RequestMapping("/api/paap")
@AllArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
public class RecordController {

	private RecordService recordService;

	@ApiOperation("Get Paaps Method")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 400, message = "Malformed request"),
			@ApiResponse(code = 500, message = "Internal error") })
	@PostMapping
	public ResponseEntity<List<PaapDto>> addRecord(@RequestBody List<String> dropdownItems) {

		return ResponseEntity.ok(recordService.getPaaps(dropdownItems));
	}
	
	@ApiOperation("Get Paaps Details Method")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 400, message = "Malformed request"),
			@ApiResponse(code = 500, message = "Internal error") })
	@GetMapping
	public ResponseEntity<List<PaapDetailDto>> addRecord(@RequestParam(value = "paapId") Integer paapId) {
		return ResponseEntity.ok(recordService.getPaapDetails(paapId));
	}


}
