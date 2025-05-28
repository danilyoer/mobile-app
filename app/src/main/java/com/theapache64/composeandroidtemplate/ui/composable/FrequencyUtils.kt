package com.theapache64.composeandroidtemplate.ui.composable


fun getMaxDaysForFrequency(frequency: String): Int {
    return when (frequency) {
        "Ежедневно"      -> 365
        "Через день"     -> 180
        "Еженедельно"    -> 54
        "Раз в месяц"    -> 12
        else              -> 365
    }
}
