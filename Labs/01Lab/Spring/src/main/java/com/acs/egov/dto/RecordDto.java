package com.acs.egov.dto;

import com.acs.egov.domain.Record;
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
public class RecordDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private Double payment;
    private Double paymentWithVat;
    
    public static Record toEntity(RecordDto entity) {
        Record record = Record.builder()
                .firstName(entity.firstName)
                .lastName(entity.lastName)
                .email(entity.email)
                .phoneNumber(entity.phoneNumber)
                .address(entity.address)
                .payment(entity.payment)
                .paymentWithVat(entity.paymentWithVat)
                .build();

        return record;
    }
}
