package kh.com.pheaktra.developer.basic.android.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenLazyColumn() {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        /**
         * 1. item
         * 2. items
         * 3. itemIndex
         */
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            item {
                for (account in accountList) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(color = MaterialTheme.colorScheme.primary),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = "Item ${account.accountNo}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            modifier = Modifier.padding(end = 8.dp),
                            text = "${account.availableBalance}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.padding(end = 16.dp),
                            text = account.currencyCode.code,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal
                        )

                    }
                }
            }

            items(accountList.size) { index ->
                val account = accountList[index]
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(color = MaterialTheme.colorScheme.secondary),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = account.accountNo,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = "${account.availableBalance}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.padding(end = 16.dp),
                        text = account.currencyCode.code,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )

                }
            }

            itemsIndexed(
                items = accountList,
                key = {_, item -> item.accountNo }
            ) { _, account ->
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = account.accountNo,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = "${account.availableBalance}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.padding(end = 16.dp),
                        text = account.currencyCode.code,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )

                }
            }

        }
    }
}

@Preview(showBackground = false)
@Composable
fun ScreenLazyColumnPreview() {
    ScreenLazyColumn()
}

enum class AccountTypeCode(val code: String) {
    LOAN_ACCOUNT("20000"),

    DEPOSIT("30000"),

    GOLD_SAVING("40000")
}

enum class CurrencyCode(val code: String) {
    KHR("KHR"),
    USD("USD")
}

enum class FreezeYN {
    Y,
    N
}

data class AccountModel(
    val accountNo: String,
    val accountType: AccountTypeCode,
    val currencyCode: CurrencyCode,
    val availableBalance: Double,
    val freezeYN: FreezeYN
)

val accountList = listOf(
    AccountModel("100000001", AccountTypeCode.LOAN_ACCOUNT, CurrencyCode.USD, 1500.00, FreezeYN.N),
    AccountModel("100000002", AccountTypeCode.DEPOSIT, CurrencyCode.KHR, 6_000_000.00, FreezeYN.N),
    AccountModel("100000003", AccountTypeCode.GOLD_SAVING, CurrencyCode.USD, 250.75, FreezeYN.Y),
    AccountModel("100000004", AccountTypeCode.LOAN_ACCOUNT, CurrencyCode.KHR, 12_500_000.00, FreezeYN.N),
    AccountModel("100000005", AccountTypeCode.DEPOSIT, CurrencyCode.USD, 9_999.99, FreezeYN.N),

    AccountModel("100000006", AccountTypeCode.LOAN_ACCOUNT, CurrencyCode.KHR, 800_000.00, FreezeYN.Y),
    AccountModel("100000007", AccountTypeCode.LOAN_ACCOUNT, CurrencyCode.USD, 120.00, FreezeYN.N),
    AccountModel("100000008", AccountTypeCode.LOAN_ACCOUNT, CurrencyCode.KHR, 3_450_000.00, FreezeYN.N),
    AccountModel("100000009", AccountTypeCode.LOAN_ACCOUNT, CurrencyCode.USD, 75_000.00, FreezeYN.N),
    AccountModel("100000010", AccountTypeCode.LOAN_ACCOUNT, CurrencyCode.KHR, 1_200_000.00, FreezeYN.Y),

    AccountModel("100000011", AccountTypeCode.GOLD_SAVING, CurrencyCode.USD, 560.40, FreezeYN.N),
    AccountModel("100000012", AccountTypeCode.GOLD_SAVING, CurrencyCode.KHR, 980_000.00, FreezeYN.N),
    AccountModel("100000013", AccountTypeCode.GOLD_SAVING, CurrencyCode.USD, 18_500.00, FreezeYN.N),
    AccountModel("100000014", AccountTypeCode.GOLD_SAVING, CurrencyCode.KHR, 22_000_000.00, FreezeYN.N),
    AccountModel("100000015", AccountTypeCode.GOLD_SAVING, CurrencyCode.USD, 0.00, FreezeYN.Y),

    AccountModel("100000016", AccountTypeCode.DEPOSIT, CurrencyCode.KHR, 450_000.00, FreezeYN.N),
    AccountModel("100000017", AccountTypeCode.DEPOSIT, CurrencyCode.USD, 3_200.00, FreezeYN.N),
    AccountModel("100000018", AccountTypeCode.DEPOSIT, CurrencyCode.KHR, 7_700_000.00, FreezeYN.Y),
    AccountModel("100000019", AccountTypeCode.DEPOSIT, CurrencyCode.USD, 88.88, FreezeYN.N),
    AccountModel("100000020", AccountTypeCode.DEPOSIT, CurrencyCode.KHR, 15_000_000.00, FreezeYN.N)
)

/**
 * - Homework
 * 1. Use LazyColumn to create a scroll view
 * 2. Design Card view for Account
 * 3. Apply in item, items and itemIndexed of LazyColumn
 * 4. Record the result as videos and send to teacher
 */









