package org.delivery.common.exception

import org.delivery.common.error.ErrorCodeIfs

class ApiException : RuntimeException, ApiExceptionIfs {

    override val errorCodeIfs: ErrorCodeIfs
    override val errorDescription: String

    constructor(errorCodeIfs: ErrorCodeIfs): super(errorCodeIfs.getDescription()) {
        this.errorCodeIfs = errorCodeIfs
        this.errorDescription = errorCodeIfs.getDescription()
    }

    constructor(
        errorCodeIfs: ErrorCodeIfs,
        errorDescription: String
    ): super(errorDescription) {
        this.errorCodeIfs = errorCodeIfs
        this.errorDescription = errorDescription
    }

    constructor(
        errorCodeIfs: ErrorCodeIfs,
        throwable: Throwable
    ): super(throwable) {
        this.errorCodeIfs = errorCodeIfs
        this.errorDescription = errorCodeIfs.getDescription()
    }

    constructor(
        errorCodeIfs: ErrorCodeIfs,
        errorDescription: String,
        throwable: Throwable
    ): super(throwable) {
        this.errorCodeIfs = errorCodeIfs
        this.errorDescription = errorDescription
    }

}