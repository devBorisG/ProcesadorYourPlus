package com.uco.yourplus.crosscuttingyourplus.helper.json;

import java.util.Optional;

public interface MapperJsonObject {

    Optional<String> execute(Object object);

    <T> Optional<T> execute(String json, Class<T> targetClass);

    Optional<String> executeGson(Object object);

}
