package com.tem.domain.util

class ApiFieldsException constructor(
    private val classType: Any,
    private val error: String
): Throwable() {

    override val message: String?
        get() = classType.javaClass.name + " - " + error
}