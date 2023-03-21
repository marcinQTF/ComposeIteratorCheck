package com.example.composeiteratorcheck

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeiteratorcheck.ui.theme.Blue200
import com.example.composeiteratorcheck.ui.theme.ComposeIteratorCheckTheme
import com.example.composeiteratorcheck.ui.theme.Grey200


@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.space_l))
                .fillMaxSize()
        ) {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_m)),
                modifier = Modifier.weight(1f)
            ) {

                item {
                    Text(
                        modifier = Modifier.padding(
                            start = dimensionResource(id = R.dimen.space_xxs),
                            bottom = dimensionResource(id = R.dimen.space_m)
                        ),
                        text = stringResource(id = R.string.settings_title),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }

                items(SettingsItems.items) {
                    Tile(
                        leadingIcon = it.leadingIcon,
                        settingName = it.settingName,
                        optionalElement = it.optionalElements
                    )
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = dimensionResource(id = R.dimen.space_xl)
                    ),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.settings_sign_out),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tile(
    modifier: Modifier = Modifier,
    leadingIcon: Int,
    settingName: Int,
    vararg optionalElement: @Composable () -> Unit
) {

    Card(
        modifier = modifier.height(dimensionResource(id = R.dimen.settings_tile_height)),
        colors = CardDefaults.cardColors(
            containerColor = (if (isSystemInDarkTheme()) Grey200 else Blue200),
            contentColor = Color.Black,
        )
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,

            ) {
            Row(
                modifier = Modifier
                    .padding(
                        dimensionResource(id = R.dimen.space_l),
                        end = dimensionResource(id = R.dimen.space_xl)
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.space_m)))

                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = settingName),
                    style = MaterialTheme.typography.labelLarge,
                )

                optionalElement.iterator()
                    .apply {
                        while (hasNext()) {
                            next().invoke()

                            if (hasNext()) {
                                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.space_m)))
                            }
                        }
                    }
            }
        }

    }
}

@Composable
fun ActionButton() {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            painter = painterResource(R.drawable.ic_settings),
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    ComposeIteratorCheckTheme(content = { SettingsScreen() })
}