package com.acs.egov.xml;

import java.util.List;

import com.acs.egov.dto.PaapDto;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Records {
	@JacksonXmlElementWrapper(useWrapping = false, localName = "record")
	List<PaapDto> record;
}
