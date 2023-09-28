package com.xh.lib.util

import java.util.regex.Pattern

internal const val REGEX_MOBILE = "^[1]\\d{10}$"
internal const val REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"

internal fun isMobile(input: CharSequence): Boolean {
    return isMatch(REGEX_MOBILE, input)
}

internal fun isEmail(input: CharSequence): Boolean {
    return isMatch(REGEX_EMAIL, input)
}

private fun isMatch(regex: String, input: CharSequence): Boolean {
    return input.isNotEmpty() && Pattern.matches(regex, input)
}

