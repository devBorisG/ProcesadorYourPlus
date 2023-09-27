package com.uco.yourplus.serviceyourplus.usecase;

public interface UseCase <D> {
    void execute(D domain);
}
