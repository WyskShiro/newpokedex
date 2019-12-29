package com.tem.plate.util

import com.tem.data.util.request.RequestException
import com.tem.domain.entity.error.HttpError
import com.tem.domain.util.Logger
import com.tem.domain.util.StringsProvider
import com.tem.plate.util.viewmodels.DialogData
import com.tem.plate.util.viewmodels.Placeholder

class ErrorHandler constructor(
    private val strings: StringsProvider,
    private val logger: Logger
) {

    fun getPlaceholder(throwable: Throwable, retryAction: (() -> Unit)? = null): Placeholder {
        logger.e(throwable)
        return if (throwable is RequestException) {
            handleError(throwable, retryAction)
        } else {
            getUnknownErrorPlaceholder()
        }
    }

    fun getDialogData(
        throwable: Throwable,
        retryAction: (() -> Unit)?,
        onDismiss: (() -> Unit)? = null
    ): DialogData {
        val data = getPlaceholder(throwable, retryAction)
        return if (data.message == null) {
            DialogData.error(strings, getUnknownErrorMessage(), onDismiss = onDismiss)
        } else {
            DialogData.error(strings, data.message, data.buttonText, data.buttonAction)
        }
    }

    private fun handleError(throwable: Throwable, tryAgainAction: (() -> Unit)? = null): Placeholder {
        logger.e(throwable)
        return when (throwable) {
            is RequestException -> handleRequestException(throwable, tryAgainAction)
            else -> unexpectedErrorData(tryAgainAction)
        }
    }

    private fun handleRequestException(
        exception: RequestException,
        tryAgainAction: (() -> Unit)? = null
    ): Placeholder {
        return when {
            exception.isUnProcessableEntity() -> unProcessableEntityErrorData(exception.errorMessage
                ?: strings.errorUnknown)
            exception.isTimeOutException() -> timeoutErrorData(tryAgainAction)
            exception.isNetworkError() -> httpErrorData(strings.errorNetwork, tryAgainAction)
            exception.isUnauthorizedError() -> unauthorizedErrorData(exception.errorMessage
                ?: strings.errorUnknown)
            exception.isHttpError() -> resolveHttpError(exception, tryAgainAction)
            else -> unexpectedErrorData(tryAgainAction)
        }
    }

    private fun resolveHttpError(
        exception: RequestException,
        tryAgainAction: (() -> Unit)?
    ): Placeholder {
        return when (HttpError.getErrorForCode(exception.errorCode)) {
            HttpError.NOT_FOUND -> notFoundErrorData(exception.errorMessage
                ?: strings.errorNotFound)
            HttpError.TIMEOUT -> timeoutErrorData(tryAgainAction)
            HttpError.INTERNAL_SERVER_ERROR -> httpErrorData(strings.errorUnknown, tryAgainAction)
            else -> httpErrorData(
                exception.errorMessage
                    ?: exception.message
                    ?: strings.errorUnknown, null
            )
        }
    }

    private fun notFoundErrorData(errorMessage: String): Placeholder {
        return Placeholder.Message(errorMessage)
    }

    private fun unauthorizedErrorData(errorMessage: String): Placeholder {
        return Placeholder.Message(errorMessage)
    }

    private fun unProcessableEntityErrorData(errorMessage: String?): Placeholder {
        return Placeholder.Message(errorMessage ?: strings.errorUnknown)
    }

    private fun httpErrorData(message: String, tryAgainAction: (() -> Unit)? = null): Placeholder {
        return tryAgainPlaceholder(message, tryAgainAction)
    }

    private fun timeoutErrorData(tryAgainAction: (() -> Unit)? = null): Placeholder {
        return tryAgainPlaceholder(strings.errorSocketTimeout, tryAgainAction)
    }

    private fun unexpectedErrorData(tryAgainAction: (() -> Unit)? = null): Placeholder {
        return tryAgainPlaceholder(strings.errorUnknown, tryAgainAction)
    }

    private fun tryAgainPlaceholder(errorMessage: String, tryAgainAction: (() -> Unit)?): Placeholder {
        return Placeholder.Action(errorMessage, strings.globalTryAgain, tryAgainAction ?: {})
    }

    private fun getUnknownErrorPlaceholder(): Placeholder {
        return Placeholder.Message(getUnknownErrorMessage())
    }

    private fun getUnknownErrorMessage(): String {
        return strings.errorUnknown
    }
}