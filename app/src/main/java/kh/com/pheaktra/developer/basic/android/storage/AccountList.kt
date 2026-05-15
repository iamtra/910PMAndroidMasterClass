package kh.com.pheaktra.developer.basic.android.storage

import kh.com.pheaktra.developer.basic.android.common.AccountType
import kh.com.pheaktra.developer.basic.android.model.AccountModel

val accountList = arrayOf(
    // 1️⃣ Primary Saving Account
    AccountModel(
        accountNo = "12020200344",
        accountName = "Buon Pheaktra",
        accountNickName = "Teacher Pheaktra",
        accountType = "Saving Account",
        currencyCode = "USD",
        balance = 10000.00,
        availableBalance = 9990.00,
        isPrimary = true,
        accountStatus = "Active",
        isBlocked = false,
        createDate = "20260106",
        lastTransactionDate = "20260106",
        lastTransactionTime = "9:00 PM",
        dailyTransferLimit = 5000.00,
        monthlyTransferLimit = 150000.00,
        branchCode = "TK00001",
        branchName = "Tuol Kork",
        bankCode = "CHNOKHPP",
        bankName = "Chip Mong Bank",
        interestRate = 4.0,
        maintenanceFee = 5.0,
        allowOnlineTransfer = true,
        allowInternationalTransfer = true
    ),

    // 2️⃣ Deposit Account (Fixed Deposit)
    AccountModel(
        accountNo = "12020200991",
        accountName = "Buon Pheaktra",
        accountNickName = "Fixed Deposit 12M",
        accountType = AccountType.CONSTANT_ACCOUNT,
        currencyCode = "USD",
        balance = 20000.00,
        availableBalance = 0.00,
        isPrimary = false,
        accountStatus = "Active",
        isBlocked = false,
        createDate = "20250115",
        lastTransactionDate = null,
        lastTransactionTime = null,
        dailyTransferLimit = 0.00,
        monthlyTransferLimit = 0.00,
        branchCode = "TK00001",
        branchName = "Tuol Kork",
        bankCode = "CHNOKHPP",
        bankName = "Chip Mong Bank",
        interestRate = 6.5,
        maintenanceFee = 0.0,
        allowOnlineTransfer = false,
        allowInternationalTransfer = false,
    ),

    // 3️⃣ Deposit Account (Term Deposit)
    AccountModel(
        accountNo = "12020200992",
        accountName = "Buon Pheaktra",
        accountNickName = "Term Deposit 6M",
        accountType = AccountType.DEPOSIT,
        currencyCode = "KHR",
        balance = 80000000.00,
        availableBalance = 0.00,
        isPrimary = false,
        accountStatus = "Active",
        isBlocked = false,
        createDate = "20250410",
        lastTransactionDate = null,
        lastTransactionTime = null,
        dailyTransferLimit = 0.00,
        monthlyTransferLimit = 0.00,
        branchCode = "TK00002",
        branchName = "Russian Market",
        bankCode = "CHNOKHPP",
        bankName = "Chip Mong Bank",
        interestRate = 7.2,
        maintenanceFee = 0.0,
        allowOnlineTransfer = false,
        allowInternationalTransfer = false
    ),

    // 4️⃣ Secondary Saving Account
    AccountModel(
        accountNo = "12020200555",
        accountName = "Buon Pheaktra",
        accountNickName = "Travel Savings",
        accountType = AccountType.SAVING,
        currencyCode = "USD",
        balance = 3500.00,
        availableBalance = 3500.00,
        isPrimary = false,
        accountStatus = "Active",
        isBlocked = false,
        createDate = "20250920",
        lastTransactionDate = "20251230",
        lastTransactionTime = "3:45 PM",
        dailyTransferLimit = 3000.00,
        monthlyTransferLimit = 50000.00,
        branchCode = "TK00001",
        branchName = "Tuol Kork",
        bankCode = "CHNOKHPP",
        bankName = "Chip Mong Bank",
        interestRate = 3.5,
        maintenanceFee = 3.0,
        allowOnlineTransfer = true,
        allowInternationalTransfer = false
    )
)