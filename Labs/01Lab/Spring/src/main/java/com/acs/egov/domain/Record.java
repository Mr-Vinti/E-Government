package com.acs.egov.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.acs.egov.dto.RecordDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@SuperBuilder
@Entity
@Table(name = "RECORD")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;
    
    @Column(name = "PAYMENT")
    private Double payment;
    
    @Column(name = "PAYMENT_WITH_VAT")
    private Double paymentWithVat;

    public static RecordDto toDto(Record entity) {
        RecordDto recordDto = RecordDto.builder()
                .id(entity.id)
                .firstName(entity.firstName)
                .lastName(entity.lastName)
                .email(entity.email)
                .phoneNumber(entity.phoneNumber)
                .address(entity.address)
                .payment(entity.payment)
                .paymentWithVat(entity.paymentWithVat)
                .build();

        return recordDto;
    }
}
