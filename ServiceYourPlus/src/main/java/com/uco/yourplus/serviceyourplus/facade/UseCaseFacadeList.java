package com.uco.yourplus.serviceyourplus.facade;

import java.util.List;
import java.util.Optional;

public interface UseCaseFacadeList<T> {
    List<T> execute(Optional<T> dto);
}
