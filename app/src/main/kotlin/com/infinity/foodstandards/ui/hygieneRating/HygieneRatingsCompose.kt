package com.infinity.foodstandards.ui.hygieneRating

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infinity.foodstandards.R
import com.infinity.foodstandards.model.Establishment

@Composable
fun AuthorityDetailDescription() {
    AuthorityName()
}

@Preview
@Composable
fun AuthorityNamePreview() {
    AuthorityName()
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}

@Composable
private fun AuthorityName() {
    val personList = listOf(
        Establishment(stringResource(R.string.star5)),
        Establishment(stringResource(R.string.star4)),
        Establishment(stringResource(R.string.star3)),
        Establishment(stringResource(R.string.star2)),
        Establishment(stringResource(R.string.star1)),
        Establishment(stringResource(R.string.exempt)),
    )
    val tableData = personList.mapIndexed { index, item ->
        "${item.rating}" to "16.6%"
    }
    // Each cell of a column must have the same weight.
    val column1Weight = .5f // 50%
    val column2Weight = .5f // 50%
    // The LazyColumn will be our table. Notice the use of the weights below
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        // Here is the header
        item {
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = stringResource(R.string.rating), weight = column1Weight)
                TableCell(text = stringResource(R.string.percentage), weight = column2Weight)
            }
        }
        // Here are all the lines of your table.
        items(tableData) {
            val (id, text) = it
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = id.toString(), weight = column1Weight)
                TableCell(text = text, weight = column2Weight)
            }
        }
    }
}
