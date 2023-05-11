package com.cybersoft.common;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AppUtils {
    public static <T extends BaseDTO> void createOrderNumber (Collection<T> list, int pageIndex , int pageSize) {
        int offset = (pageIndex - 1 ) * pageSize + 1;

        for(T data : list) {
         data.setNumberOrder(offset);
         offset++;
       }
    }

    public static <T extends BaseDTO> void createOrderNumberWithoutPaging (Collection<T> list) {
        int offset = 1;

        for(T data : list) {
            data.setNumberOrder(offset);
            offset++;
        }
    }
}
