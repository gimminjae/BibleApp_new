package com.example.bo.base.util;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class ObjectUtil {
    public static <T> T isNullExceptionElseReturnObJect(Optional<T> optionalT) {
        T t = optionalT.orElse(null);

        if(t == null) {
            throw new NullPointerException("%s 데이터가 존재하지 않습니다.".formatted(t.getClass()));
        }
        return t;
    }
}
