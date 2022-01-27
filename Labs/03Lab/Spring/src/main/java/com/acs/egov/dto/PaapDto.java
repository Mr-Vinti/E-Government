package com.acs.egov.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaapDto {
	Integer paapId;
    String paapCode;
    String paapName;
    Integer paapYear;
    Integer paapVersion;
    String caCIF;
    String caName;
    Date publicationDate;
    Boolean isPublished;
    String paapStateName;
}
