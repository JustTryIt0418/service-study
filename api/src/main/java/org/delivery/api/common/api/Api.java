package org.delivery.api.common.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.common.error.ErrorCodeIfs;

/*@Data
@AllArgsConstructor
@NoArgsConstructor
public class Api<T> {

    private Result result;

    @Valid
    private T body;

    public static <T> Api ok(T data) {
        Api<T> api = new Api<>();
        api.result = Result.ok();
        api.body = data;
        return api;
    }

    public static Api<Object> error(Result result) {
        Api<Object> api = new Api<>();
        api.result = result;
        return api;
    }

    public static Api<Object> error(ErrorCodeIfs errorCodeIfs) {
        Api<Object> api = new Api<>();
        api.result = Result.error(errorCodeIfs);
        return api;
    }

    public static Api<Object> error(ErrorCodeIfs errorCodeIfs, Throwable ex) {
        Api<Object> api = new Api<>();
        api.result = Result.error(errorCodeIfs, ex);
        return api;
    }

    public static Api<Object> error(ErrorCodeIfs errorCodeIfs, String message) {
        Api<Object> api = new Api<>();
        api.result = Result.error(errorCodeIfs, message);
        return api;
    }
}*/
