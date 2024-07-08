package com.codingtest.app.presentation.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

fun String.formatToDisplay(): String {
    return "Updated: " + LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
        .format(DateTimeFormatter.ofPattern("MMM dd, HH:mm"))
}