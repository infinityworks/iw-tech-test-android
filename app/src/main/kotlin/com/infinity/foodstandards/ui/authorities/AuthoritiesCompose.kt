package com.infinity.foodstandards.ui.authorities

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.AnnotatedString
import com.infinity.foodstandards.R
import com.infinity.foodstandards.model.LocalAuthority

@Composable
fun AuthorityList(authority: List<LocalAuthority>, onItemSelected: () -> Unit) {
    LazyColumn {
        items(authority) { authority ->
            AuthorityName(authority.name, { onItemSelected() })
            Divider(color = Color.Gray)
        }
    }
}

@Composable
private fun AuthorityName(name: String, onItemSelected: () -> Unit) {
    ClickableText(
        text = AnnotatedString(name),
        onClick = { onItemSelected() }, // Apply simple UI logic
        style = MaterialTheme.typography.h5,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.standard_margin))
    )
}
