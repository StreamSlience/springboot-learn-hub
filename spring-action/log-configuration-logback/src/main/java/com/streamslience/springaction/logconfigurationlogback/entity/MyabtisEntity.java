package com.streamslience.springaction.logconfigurationlogback.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author StreamSlience
 * @date 2020-06-28 15:45
 */
@Data
@Accessors(chain = true)
public class MyabtisEntity {

    private String id;

    private String info;

}
