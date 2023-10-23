package ru.urfu.SecondLabTask.model;

import lombok.*;
import jakarta.validation.constraints.*;
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

    private String systemName;

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
}

