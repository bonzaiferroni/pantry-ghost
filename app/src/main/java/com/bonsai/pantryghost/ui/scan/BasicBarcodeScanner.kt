package com.bonsai.pantryghost.ui.scan

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import com.bonsai.pantryghost.utils.Paddings
import com.google.mlkit.vision.barcode.common.Barcode

@Composable
fun BasicBarcodeScanner() {
    val barcodes = remember { emptyList<Barcode>().toMutableStateList() }
    BarcodeScannerView { newBarcodes ->
        newBarcodes.forEach { barcode ->
            if (!barcodes.any { it.rawValue == barcode.rawValue })
                barcodes.add(barcode)
        }
    }
    LazyColumn(modifier = Modifier.padding(Paddings.small())) {
        items(barcodes) { barcode ->
            Text(barcode.displayValue ?: "Unknown barcode")
        }
    }
}
