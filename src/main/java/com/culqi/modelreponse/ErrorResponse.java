package com.culqi.modelreponse;

import lombok.Data;

/**
 * Created by culqi on 12/21/16.
 */

@Data
public class ErrorResponse {

    private String code;

    private String type;

    private String message;

    private String user_message;

    private String object;
}