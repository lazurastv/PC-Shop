package pw.pcshop.controllers;

public class ApiResult<T> {
    boolean result;
    String message;
    T value;

    public ApiResult<T> OK() {
        ApiResult<T> result = new ApiResult<>();
        result.result = true;
        return result;
    }

    public ApiResult<T> OK(T item) {
        ApiResult<T> result = new ApiResult<>();
        result.result = true;
        result.value = item;
        return result;
    }

    public ApiResult<T> Error(String message) {
        ApiResult<T> result = new ApiResult<>();
        result.result = false;
        result.message = message;
        return result;
    }
}
