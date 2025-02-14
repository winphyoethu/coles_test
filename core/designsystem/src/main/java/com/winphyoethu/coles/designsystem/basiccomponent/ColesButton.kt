package com.winphyoethu.coles.designsystem.basiccomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.winphyoethu.coles.designsystem.ui.theme.ColesTheme
import com.winphyoethu.coles.designsystem.ui.theme.mediumDp
import com.winphyoethu.coles.designsystem.ui.theme.smallDp

enum class IconPosition {
    LEADING,
    TRAILING
}

/**
 * Button designed for Coles app
 */
@Composable
fun ColesButton(
    modifier: Modifier = Modifier,
    text: String,
    iconPosition: IconPosition = IconPosition.LEADING,
    icon: ImageVector? = null,
    iconDescription: String? = null,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(onClick = onClick, enabled = isEnabled, modifier = modifier) {
        ColesButtonContent(text, iconPosition, icon, iconDescription)
    }
}

/**
 * Outlined Button designed for Coles app
 */
@Composable
fun ColesOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    iconPosition: IconPosition = IconPosition.LEADING,
    icon: ImageVector? = null,
    iconDescription: String? = null,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(onClick = onClick, enabled = isEnabled, modifier = modifier) {
        ColesButtonContent(text, iconPosition, icon, iconDescription)
    }
}

/**
 * Button content for primary button and outlined button
 */
@Composable
private fun ColesButtonContent(
    text: String,
    iconPosition: IconPosition = IconPosition.LEADING,
    icon: ImageVector? = null,
    iconDescription: String? = null,
) {
    if (icon == null) {
        ColesBody(
            body = text,
        )
    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(smallDp)
        ) {
            if (iconPosition == IconPosition.LEADING) {
                Icon(
                    imageVector = icon,
                    contentDescription = iconDescription,
                    modifier = Modifier.size(20.dp)
                )
                ColesBody(body = text)
            } else {
                ColesBody(body = text)
                Icon(imageVector = icon, contentDescription = iconDescription)
            }
        }
    }
}

@Preview
@Composable
private fun ColesButtonPreview() {
    ColesTheme(dynamicColor = false) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.spacedBy(mediumDp)) {
                Text(text = "Enabled Buttons")

                ColesButton(text = "Primary Button") { }
                ColesButton(text = "Primary Button", icon = Icons.Default.Add) { }
                ColesOutlinedButton(text = "Outlined Button") { }
                ColesOutlinedButton(
                    text = "Outlined Button",
                    icon = Icons.Default.Add,
                    iconPosition = IconPosition.TRAILING
                ) { }

                Text(text = "Disabled Buttons")

                ColesButton(text = "Primary Button", isEnabled = false) { }
                ColesButton(
                    text = "Primary Button",
                    icon = Icons.Default.Add,
                    isEnabled = false
                ) { }
                ColesOutlinedButton(text = "Outlined Button", isEnabled = false) { }
                ColesOutlinedButton(
                    text = "Outlined Button",
                    icon = Icons.Default.Add,
                    iconPosition = IconPosition.TRAILING
                    , isEnabled = false
                ) { }
            }
        }
    }
}