package com.omsai.spring_7_rest_mvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerDTO {

    private String name;
    private UUID id;
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}