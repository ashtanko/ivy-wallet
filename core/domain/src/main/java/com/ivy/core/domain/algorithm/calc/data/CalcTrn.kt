package com.ivy.core.domain.algorithm.calc.data

import androidx.room.ColumnInfo
import com.ivy.data.CurrencyCode
import com.ivy.data.transaction.TransactionType

data class CalcTrn(
    @ColumnInfo(name = "amount")
    val amount: Double,
    @ColumnInfo(name = "currency")
    val currency: CurrencyCode,
    @ColumnInfo(name = "type")
    val type: TransactionType
)