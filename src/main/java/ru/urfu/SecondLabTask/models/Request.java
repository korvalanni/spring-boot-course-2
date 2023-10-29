package ru.urfu.SecondLabTask.models;

import lombok.*;
import jakarta.validation.constraints.*;
import ru.urfu.SecondLabTask.enums.Systems;
import ru.urfu.SecondLabTask.validation.IsValidUidAnnotation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Request {

    @NotBlank
    @Digits(integer = 32, fraction = 0)
    @IsValidUidAnnotation
    private String uid;

    @NotBlank
    @Digits(integer = 32, fraction = 0)
    private String operationUid;

    private Systems systemName;

    @NotNull
    private String systemTime;

    private String source;

    @NotNull
    @Min(1)
    @Digits(integer = 6, fraction = 0)
    private int communicationId;

    private int templateId;

    private int productCode;

    private int smsCode;

    @Override
    public String toString(){
        return "Request{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
}

