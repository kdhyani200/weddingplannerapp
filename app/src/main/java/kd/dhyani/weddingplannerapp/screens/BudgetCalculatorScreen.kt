package kd.dhyani.weddingplannerapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BudgetCalculatorScreen() {
    var budgetInput by remember { mutableStateOf("") }
    val budget = budgetInput.toIntOrNull() ?: 0

    val venueBudget = (budget * 0.4).toInt()
    val cateringBudget = (budget * 0.35).toInt()
    val decorBudget = (budget * 0.25).toInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Wedding Budget Calculator", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = budgetInput,
            onValueChange = { budgetInput = it },
            label = { Text("Enter Total Budget (₹)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )

        if (budget > 0) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Budget Breakdown", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Venue: ₹$venueBudget")
                    Text("Catering: ₹$cateringBudget")
                    Text("Décor: ₹$decorBudget")
                }
            }
        }
    }
}

@Preview
@Composable
fun BudgetCalculatorScreenPreview() {
    GuestListScreen()
}