package com.infinity.foodstandards.ui.authorities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.infinity.foodstandards.R
import com.infinity.foodstandards.model.LocalAuthority

@Composable
fun AuthoritiesScreen(
    viewModel: AuthoritiesViewModel,
    onNavigateToHygieneRatings: (authorityId: String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getLocalAuthorities()
    }
    val localAuthorities = viewModel.localAuthorities.observeAsState().value?.authorities
    val errorState = viewModel.errorLiveData.observeAsState().value

    when {
        localAuthorities != null -> AuthorityList(authority = localAuthorities) {
            onNavigateToHygieneRatings(it)
        }
        errorState == true -> TextPlaceholder(text = stringResource(R.string.default_error))
        else -> TextPlaceholder(text = stringResource(R.string.loading))
    }
}

@Composable
fun AuthorityList(
    authority: List<LocalAuthority>,
    onItemSelected: (authorityId: String) -> Unit
) {
    LazyColumn {
        items(authority) { authority ->
            AuthorityName(authority.name) { onItemSelected(authority.id.toString()) }
            Divider(color = Color.Gray)
        }
    }
}

@Composable
private fun AuthorityName(
    name: String,
    onItemSelected: () -> Unit
) {
    ClickableText(
        text = AnnotatedString(name),
        onClick = { onItemSelected() },
        style = MaterialTheme.typography.h5,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.standard_margin))
    )
}

@Composable
private fun TextPlaceholder(text: String) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text)
    }
}
