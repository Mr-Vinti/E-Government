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
public class PaapDetailDto {
	Integer id;
    Integer planId;
    String code;
    String name;
    LookupValue sysProcedureType;
    LookupValue sysAquisitionContractType;
    LookupValue cpvCode;
    Double estimatedValueRonVat;
    Double estimatedValueRon;
    Double estimatedValueEur;
    Double consumedValueRon;
    Date startEstimatedDateTime;
    Date endEstimatedDateTime;
    String personResponsable;
    LookupValue sysFinancingType;
    String responsibleUnit;
    String uniqueComplexCode;
}
