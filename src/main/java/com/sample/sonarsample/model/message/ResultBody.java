package com.sample.sonarsample.model.message;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sample.sonarsample.model.type.ErrorType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultBody {
    int resultCode;
    String resultMessage;
    Object result;

    public ResultBody(ErrorType errorType) {
        this.resultCode = errorType.getResultCode();
        this.resultMessage = errorType.getResultMessage();
    }

    public ResultBody(@NotNull ErrorType errorType, String resultMessage)
    {
        this.resultCode = errorType.getResultCode();
        this.resultMessage = resultMessage;
    }

    public ResultBody(int resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public ResultBody(int resultCode, String resultMessage, Object result) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.result = result;
    }
}
