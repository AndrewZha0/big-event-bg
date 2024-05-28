package com.kenmi.bigevent.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleUser {
    private Long userId;
    private String username;
}
