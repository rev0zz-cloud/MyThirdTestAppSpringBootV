package ru.arkhipov.MyThirdTestAppSpringBoot.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Request {
    @Override
    public String toString(){
        return "{" +
                "uid=`" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemNam" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source=`" + source + '\'' +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                "}";
    }

    @NotBlank
    @Size(max = 32)
    private String uid;

    @NotBlank
    @Size(max = 32)
    private String operationUid;

    private Systems systemName;

    @NotBlank
    private String systemTime;

    private long requestTime;  // Новое поле для хранения метки времени

    private String source;

    @Min(1)
    @Max(100000)
    private int communicationId;

    private int templateId;
    private int productCode;
    private int smsCode;
}
