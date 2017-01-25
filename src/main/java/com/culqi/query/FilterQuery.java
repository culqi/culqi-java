package com.culqi.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by culqi on 1/25/17.
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class FilterQuery {

    private int limit = 50;

    private String before;

    private String after;

}
