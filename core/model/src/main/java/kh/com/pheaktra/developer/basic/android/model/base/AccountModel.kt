package kh.com.pheaktra.developer.basic.android.model.base

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

data class AccountModel(
    // Basic info
    val accountNo: String,
    val accountName: String,
    val accountNickName: String,
    val accountType: String,           // SAVING, CURRENT, LOAN
    val currencyCode: String,          // USD, KHR
    val balance: Double,
    val availableBalance: Double,

    // Status
    val isPrimary: Boolean,
    val accountStatus: String,         // ACTIVE, DORMANT, CLOSED
    val isBlocked: Boolean,

    // Dates
    val createDate: String,            // 20261006 -> Jan 6, 2026
    val lastTransactionDate: String?,  // nullable
    val lastTransactionTime: String?,

    // Limits
    val dailyTransferLimit: Double,
    val monthlyTransferLimit: Double,

    // Branch & Bank
    val branchCode: String,
    val branchName: String,
    val bankCode: String,
    val bankName: String,

    // Interest & fees
    val interestRate: Double,          // %
    val maintenanceFee: Double,

    // Flags
    val allowOnlineTransfer: Boolean,
    val allowInternationalTransfer: Boolean,
) {
    val title = "${accountNo} | ${accountName.uppercase()}"

//    fun formatBankDate(): String {
//        val inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")
//        val outputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH)
//
//        val date = LocalDate.parse(createDate, inputFormatter)
//        return date.format(outputFormatter)
//    }
}
