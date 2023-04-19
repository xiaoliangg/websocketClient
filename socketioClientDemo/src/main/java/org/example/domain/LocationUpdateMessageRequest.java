package org.example.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocationUpdateMessageRequest implements Serializable {
    private String deviceId;
    private String requestId;
    private Double lng;
    private Double lat;
}
