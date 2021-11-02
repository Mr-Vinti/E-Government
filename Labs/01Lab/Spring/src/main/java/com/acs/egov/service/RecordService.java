package com.acs.egov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.acs.egov.domain.Record;
import com.acs.egov.dto.RecordDto;
import com.acs.egov.dto.StringResponse;
import com.acs.egov.repository.RecordRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecordService {

	private final RecordRepository recordRepository;
	
	public List<RecordDto> getRecords() {
		return recordRepository.findAll().stream().map(record -> Record.toDto(record)).collect(Collectors.toList());
	}

	public StringResponse addRecord(RecordDto record) {
		recordRepository.save(RecordDto.toEntity(record));
		
		return new StringResponse("OK");
	}

}
