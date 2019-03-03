package com.sample.sonarsample.model.type;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
public enum ErrorType {
    //@formatter:off
    EC_INVALID_ARGUMENT(400000, "Wrong Parameter for request"),
    EC_INVALID_JSON_FORMAT(400001, "Invalid Json format for request"),
    EC_MISSING_REQUEST_PARAMETER(400002, "Missing request parameter"),
    EC_INVALID_CHARACTER(400003, "Invalid character"),
    EC_NOT_FOUND_CHARACTER(400003, "Not found character"),
    EC_INVALID_CHARACTER_NOT_MATCH_MEMBER_NO(400003, "Invalid character, member information is wrong"),

    EC_UNAUTHORIZED(400004, "Unauthorized"),
    EC_UNAUTHORIZED_HEADER_NOT_FOUND(400004, "Authentication Header Not Found"),
    EC_UNAUTHORIZED_INVALID_ONLINE_TOKEN(400004, "Failed to validate online access token"),
    EC_UNAUTHORIZED_FAILED_TO_PARSE_ONLINE_TOKEN(400004, "Failed to parse online access token"),
    EC_UNAUTHORIZED_INVALID_MOBILE_TOKEN(400004, "Failed to validate mobile token"),
    EC_UNAUTHORIZED_FAILED_TO_PARSE_MOBILE_TOKEN(400004, "Failed to parse mobile token"),
    EC_UNAUTHORIZED_EXPIRED_MOBILE_TOKEN(400004, "Expired mobile token"),
    EC_UNAUTHORIZED_INVALID_INFRA_TOKEN(400004, "Failed to validate infra access token"),       //Internal
    EC_UNAUTHORIZED_OAUTH_FAIL_NOT_MATCH_ELEMENT(400004, "Unauthorized, Element count of Ticket is wrong"),
    EC_UNAUTHORIZED_OAUTH_FAIL_DECRYPT(400004, "Unauthorized, Failed to decrypt"),
    EC_INVALID_AUTH_TOKEN(400005, "Invalid authorization token"),
    EC_RESOURCE_NOT_FOUND(400006, "Resource Not found"),


    //EXAMPLE
    EC_NOT_FOUND_TEMPLATE(40100, "Not found Sonar"),


    //ETC
    EC_UNAVAILABLE(503000, "HealthCheck failed"),
    EC_INTERACTION(504000, "Occurred error when interacting with external service"),
    EC_OCCURRED_NOT_ALLOWED(999997, "Occurred not allowed error"),
    EC_UNEXPECTED(999998, "Unexpected error"),
    EC_UNKNOWN(999999, "Unknown error");
    //@formatter:on

    @Getter
    private Integer resultCode;
    @Getter @Setter
    private String resultMessage;

    public static final Map<Integer, ErrorType> findMap = Maps.newConcurrentMap();
    static {
        for (ErrorType errorType: ErrorType.values()) {
            findMap.put(errorType.getResultCode(), errorType);
        }
    }
    public static ErrorType findByResultCode(String code) {
        return findMap.get(code);
    }

}
