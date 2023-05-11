package com.cybersoft.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDTO {
    private int numberOrder;
    private int pageIndex = 1;
    private int pageSize = 10;
}
