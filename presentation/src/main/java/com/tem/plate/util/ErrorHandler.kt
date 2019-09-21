package com.tem.plate.util

import com.tem.plate.util.viewmodels.Placeholder

class ErrorHandler {

    private fun getUnknownErrorPlaceholder(): Placeholder {
        return Placeholder.Message("Sorry, something went wrong")
    }
}
