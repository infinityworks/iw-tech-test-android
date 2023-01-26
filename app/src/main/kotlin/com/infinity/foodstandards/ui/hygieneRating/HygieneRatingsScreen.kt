package com.infinity.foodstandards.ui.hygieneRating

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infinity.foodstandards.R
import com.infinity.foodstandards.model.Establishment

private val defaultRatings = listOf("16.6%", "16.6%", "16.6%", "16.6%", "16.6%", "16.6%")

@Composable
fun HygieneRatingsScreen(
    viewModel: HygieneRatingsViewModel,
    localAuthorityId: String?
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AuthorityIdTitle(localAuthorityId = localAuthorityId)
        RatingTable(ratings = defaultRatings)
    }
}

@Composable
fun AuthorityIdTitle(localAuthorityId: String?) {
    Text(
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp
        ),
        style = MaterialTheme.typography.subtitle2,
        text = buildString {
            append(stringResource(R.string.local_authority_id, localAuthorityId ?: ""))
        }
    )
}

@Composable
fun RatingTable(ratings: List<String>) {
    val personList = listOf(
        Establishment(stringResource(R.string.star5)),
        Establishment(stringResource(R.string.star4)),
        Establishment(stringResource(R.string.star3)),
        Establishment(stringResource(R.string.star2)),
        Establishment(stringResource(R.string.star1)),
        Establishment(stringResource(R.string.exempt))
    )
    val tableData = personList.mapIndexed { index, item ->
        item.rating to ratings[index]
    }
    // Each cell of a column must have the same weight.
    val column1Weight = .5f // 50%
    val column2Weight = .5f // 50%
    // The LazyColumn will be our table. Notice the use of the weights below
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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

@Composable
private fun RowScope.TableCell(
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

@Preview
@Composable
private fun RatingTablePreview() {
    RatingTable(defaultRatings)
}
