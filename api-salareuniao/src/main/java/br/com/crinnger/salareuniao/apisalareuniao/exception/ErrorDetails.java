package br.com.crinnger.salareuniao.apisalareuniao.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String detail;
}
