package com.infinity.foodstandards.ui.authorities

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infinity.foodstandards.R
import com.infinity.foodstandards.model.LocalAuthority

@Composable
fun AuthorityList(authority: LocalAuthority) {
    AuthorityName(authority.name)
}

@Preview
@Composable
fun AuthorityNamePreview() {
    AuthorityName("Leeds")
}

@Composable
private fun AuthorityName(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.h5,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.standard_margin))
            .drawBehind {
                val strokeWidth = 1 * density
                val y = size.height - strokeWidth / 2 + 8
                drawLine(
                    Color.Gray,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            }
    )
}
