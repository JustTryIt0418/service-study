package org.delivery.common.api

import jakarta.validation.Valid
import org.delivery.common.error.ErrorCodeIfs

data class Api<T> (
    var result: Result ?= null,

    @field:Valid
    var body: T ?= null
) {
    companion object {

        @JvmStatic
        fun <T> ok(body: T?): Api<T> {
            return Api(
                result = Result.ok(),
                body = body
            )
        }

        @JvmStatic
        fun <T> error(result: Result): Api<T> {
            return Api(
                result = result
            )
        }

        @JvmStatic
        fun <T> error(errorCodeIfs: ErrorCodeIfs): Api<T> {
            return Api(
                result = Result.error(errorCodeIfs)
            )
        }

        @JvmStatic
        fun <T> error(
            errorCodeIfs: ErrorCodeIfs,
            throwable: Throwable
        ): Api<T> {
            return Api(
                result = Result.error(errorCodeIfs, throwable)
            )
        }

        @JvmStatic
        fun <T> error(
            errorCodeIfs: ErrorCodeIfs,
            description: String
        ): Api<T> {
            return Api(
                result = Result.error(errorCodeIfs, description)
            )
        }

    }
}