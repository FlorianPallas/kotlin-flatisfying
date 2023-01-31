package com.fpallas.flatisfying.controller

import com.fpallas.flatisfying.model.ReceiptItem
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.SdkBytes
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.textract.TextractClient
import software.amazon.awssdk.services.textract.model.AnalyzeExpenseRequest
import software.amazon.awssdk.services.textract.model.Document

@RestController
@RequestMapping("/receipts")
class ReceiptController {

    private fun cleanup(input: String): String {
        return input.replace(Regex("[^0-9.,]"), "").replace(",", ".")
    }

    @PostMapping(consumes = ["multipart/form-data"])
    @ResponseStatus(HttpStatus.OK)
    fun scanReceipt(@RequestPart("file") file: MultipartFile): List<ReceiptItem> {
        val textractClient = TextractClient.builder().region(Region.EU_CENTRAL_1).build()

        val response = textractClient.analyzeExpense(
            AnalyzeExpenseRequest.builder()
                .document(Document.builder().bytes(SdkBytes.fromByteArray(file.bytes)).build()).build()
        )

        val items = ArrayList<ReceiptItem>()

        response.expenseDocuments().forEach { expenseDocument ->
            expenseDocument.lineItemGroups().forEach { lineItemGroup ->
                lineItemGroup.lineItems().forEach { lineItemFields ->

                    var name: String? = null
                    var price: String? = null
                    var unitPrice: String? = null
                    var quantity: String? = null

                    lineItemFields.lineItemExpenseFields().forEach { expenseField ->
                        when (expenseField.type().text()) {
                            "ITEM" -> name = expenseField.valueDetection().text()
                            "PRICE" -> price = expenseField.valueDetection().text()
                            "UNIT_PRICE" -> unitPrice = expenseField.valueDetection().text()
                            "QUANTITY" -> quantity = expenseField.valueDetection().text()
                        }
                    }

                    if (name != null && price != null) {
                        val item = ReceiptItem(
                            name!!,
                            cleanup(price!!).toDoubleOrNull(),
                            if (quantity == null) null else cleanup(quantity!!).toIntOrNull(),
                            if (unitPrice == null) null else cleanup(unitPrice!!).toDoubleOrNull(),
                        )
                        items.add(item)
                    }
                }
            }
        }

        return items
    }

}