package com.acs.egov.rest;

import com.acs.egov.dto.RecordDto;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Api(tags = "Users Controller")
@RequestMapping("/api/records")
@AllArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
public class RecordController {

	private RecordService recordService;

	@ApiOperation("Get All Records Method")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 400, message = "Malformed request"),
			@ApiResponse(code = 500, message = "Internal error") })
	@GetMapping
	public ResponseEntity<StringResponse> getRecords() {
		log.info("Retrieving records");

		List<RecordDto> records = recordService.getRecords();
		Records recordsList = new Records(records);

		XmlMapper xmlMapper = new XmlMapper();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try {
			xmlMapper.writeValue(byteArrayOutputStream, recordsList);

			return ResponseEntity.ok(new StringResponse(byteArrayOutputStream.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't convert records to XML!");
		}

	}

	@ApiOperation("Add Records Method")
	@ApiResponses({ @ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 400, message = "Malformed request"),
			@ApiResponse(code = 500, message = "Internal error") })
	@PostMapping
	public ResponseEntity<StringResponse> addRecord(@RequestBody RecordDto record) {
		log.info("Adding record {}", record);

		return ResponseEntity.ok(recordService.addRecord(record));
	}

}
