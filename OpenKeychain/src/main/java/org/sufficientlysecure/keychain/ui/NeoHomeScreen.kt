package org.sufficientlysecure.keychain.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.sufficientlysecure.keychain.R

private val Ink = Color(0xFF142522)
private val Pine = Color(0xFF006B5F)
private val Canvas = Color(0xFFF3F5F0)
private val Mist = Color(0xFFE0EAE5)
private val Coral = Color(0xFFA5422D)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun NeoHomeScreen(onAction: (NeoHomeFragment.HomeAction) -> Unit) {
    val colors = lightColorScheme(
        primary = Pine,
        onPrimary = Color.White,
        secondary = Coral,
        onSecondary = Color.White,
        background = Canvas,
        onBackground = Ink,
        surface = Color.White,
        onSurface = Ink,
        surfaceVariant = Mist,
        onSurfaceVariant = Color(0xFF465650)
    )

    MaterialTheme(colorScheme = colors, motionScheme = MotionScheme.expressive()) {
        Surface(modifier = Modifier.fillMaxSize(), color = colors.background) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 22.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(22.dp)
            ) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            text = stringResource(R.string.home_eyebrow),
                            style = MaterialTheme.typography.labelLarge,
                            color = colors.primary,
                            fontWeight = FontWeight.Bold
                        )
                        ActiveDevelopmentLabel()
                        Text(
                            text = stringResource(R.string.home_headline),
                            style = MaterialTheme.typography.headlineLarge,
                            color = colors.onBackground,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(30.dp),
                        colors = CardDefaults.cardColors(containerColor = Ink)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.home_feature_tag),
                                style = MaterialTheme.typography.labelLarge,
                                color = Color(0xFF9FE0CD),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(R.string.home_summary),
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Button(
                                onClick = { onAction(NeoHomeFragment.HomeAction.ENCRYPT_DECRYPT) },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(18.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF9FE0CD),
                                    contentColor = Ink
                                )
                            ) {
                                Text(
                                    text = stringResource(R.string.nav_encrypt_decrypt),
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }

                item {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            text = stringResource(R.string.home_actions),
                            style = MaterialTheme.typography.titleMedium,
                            color = colors.onBackground,
                            fontWeight = FontWeight.SemiBold
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            ActionTile(
                                modifier = Modifier.weight(1f),
                                title = stringResource(R.string.nav_keys),
                                detail = stringResource(R.string.home_keys_detail),
                                action = NeoHomeFragment.HomeAction.KEYS,
                                onAction = onAction
                            )
                            ActionTile(
                                modifier = Modifier.weight(1f),
                                title = stringResource(R.string.nav_backup),
                                detail = stringResource(R.string.home_backup_detail),
                                action = NeoHomeFragment.HomeAction.BACKUP,
                                onAction = onAction
                            )
                        }
                        ActionTile(
                            modifier = Modifier.fillMaxWidth(),
                            title = stringResource(R.string.title_api_registered_apps),
                            detail = stringResource(R.string.home_apps_detail),
                            action = NeoHomeFragment.HomeAction.APPS,
                            onAction = onAction
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ActiveDevelopmentLabel() {
    Surface(
        shape = CircleShape,
        color = Color(0xFFDCECE4)
    ) {
        Text(
            text = stringResource(R.string.home_active_development),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelSmall,
            color = Pine,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ActionTile(
    title: String,
    detail: String,
    action: NeoHomeFragment.HomeAction,
    onAction: (NeoHomeFragment.HomeAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onAction(action) },
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Ink,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = detail,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF52625C)
            )
        }
    }
}