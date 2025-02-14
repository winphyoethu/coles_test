package com.winphyoethu.coles.designsystem.basiccomponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.winphyoethu.coles.designsystem.ui.theme.ColesIcons
import com.winphyoethu.coles.designsystem.ui.theme.ColesTheme
import com.winphyoethu.coles.designsystem.ui.theme.mediumDp
import com.winphyoethu.coles.designsystem.ui.theme.xxLargeDp

/**
 * Custom Appbar to be used in Coles app screens
 */
@Composable
fun ColesAppbar(
    icon: ImageVector? = null,
    iconDescription: String? = null,
    iconAction: (() -> Unit)? = null,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(mediumDp)
    ) {
        icon?.let {
            Icon(
                imageVector = icon,
                contentDescription = iconDescription,
                modifier = Modifier
                    .size(xxLargeDp)
                    .clickable { iconAction?.invoke() }
                    .semantics {
                        contentDescription = "Navigate Back"
                        onClick { true }
                    }
            )
        }
        ColesSubTitle(subtitle = title, modifier = Modifier.padding(start = mediumDp))
    }
}

@Preview
@Composable
private fun ColesAppbarPreview() {
    ColesTheme {
        Surface {
            ColesAppbar(
                icon = ColesIcons.Back,
                iconDescription = "Back Button",
                iconAction = { },
                title = "Dashboard"
            )
        }
    }
}