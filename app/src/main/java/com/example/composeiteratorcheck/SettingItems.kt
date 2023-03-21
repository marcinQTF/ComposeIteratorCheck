package com.example.composeiteratorcheck

import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.composeiteratorcheck.ui.theme.Grey500


data class SettingsItem(
    val leadingIcon: Int,
    val settingName: Int,
    val optionalElements: Array<(@Composable () -> Unit)>
)

val languageSettingItem = SettingsItem(
    R.drawable.ic_language,
    R.string.settings_language,
    optionalElements = arrayOf({
        Text(
            text = stringResource(id = R.string.settings_english_uk),
            style = TextStyle(fontWeight = FontWeight.Light)
        )
    },
        {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_forward),
                contentDescription = ""
            )
        })

)

val darkModeSettingItem = SettingsItem(
    R.drawable.ic_dark_mode,
    R.string.settings_dark_mode,
    optionalElements = arrayOf({ Switch() })
)


val notificationsSettingItem = SettingsItem(
    R.drawable.ic_notifications,
    R.string.settings_notifications,
    optionalElements = arrayOf({
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            contentDescription = ""
        )
    })
)

@Composable
private fun Switch() {
    var checkedState by remember { mutableStateOf(true) }
    Switch(
        checked = checkedState,
        onCheckedChange = { checkedState = !checkedState },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            uncheckedThumbColor = Color.White,
            checkedTrackColor = Color.Black,
            uncheckedTrackColor = Grey500,
            uncheckedBorderColor = Grey500,
            checkedBorderColor = Color.Black
        )
    )
}

object SettingsItems {
    val items = listOf(
        languageSettingItem,
        darkModeSettingItem,
        notificationsSettingItem,
    )

}