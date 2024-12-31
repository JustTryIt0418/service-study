package org.delivery.common.api

import org.delivery.common.error.ErrorCode
import org.delivery.common.error.ErrorCodeIfs

data class Result (
    val resultCode: Int ?= null,
    val resultMessage: String ?= null,
    val resultDescription: String ?= null,
) {

    companion object {
        // 자바로 컴파일 시 블록 내의 코드는 static 으로 인식
        @JvmStatic
        fun ok(): Result {
            return Result(
                resultCode = ErrorCode.OK.getErrorCode(),
                resultMessage = ErrorCode.OK.getDescription(),
                resultDescription = "성공"
            )
        }

        @JvmStatic
        fun error(
            errorCodeIfs: ErrorCodeIfs
        ): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = "에러"
            )
        }

        @JvmStatic
        fun error(
            errorCodeIfs: ErrorCodeIfs,
            throwable: Throwable
        ): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = throwable.localizedMessage
            )
        }

        @JvmStatic
        fun error(
            errorCodeIfs: ErrorCodeIfs,
            errorDescription: String
        ): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorDescription
            )
        }

    }
}