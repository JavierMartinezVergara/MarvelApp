package com.javiermtz.composehome.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.javiermtz.composehome.ui.theme.ABOUT_PLACEHOLDER_HEIGHT
import com.javiermtz.composehome.ui.theme.EXTRA_SMALL_PADDING
import com.javiermtz.composehome.ui.theme.HERO_ITEM_HEIGHT
import com.javiermtz.composehome.ui.theme.ITEM_HEIGHT
import com.javiermtz.composehome.ui.theme.ITEM_WIDTH
import com.javiermtz.composehome.ui.theme.LARGE_PADDING
import com.javiermtz.composehome.ui.theme.MEDIUM_PADDING
import com.javiermtz.composehome.ui.theme.NAME_PLACEHOLDER_HEIGHT
import com.javiermtz.composehome.ui.theme.RATING_PLACEHOLDER_HEIGHT
import com.javiermtz.composehome.ui.theme.SMALL_PADDING
import com.javiermtz.composehome.ui.theme.ShimmerDarkGray
import com.javiermtz.composehome.ui.theme.ShimmerLightGray
import com.javiermtz.composehome.ui.theme.ShimmerMediumGray

@Composable
fun ShimmerEffect() {
    LazyRow(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ){
        items(count = 3){
            AnimatedShimmerItem()
        }
    }
}

@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ))
    ShimmerItem(alpha = alphaAnim)
}

@Composable
fun ShimmerItem(alpha: Float) {
    Surface(
        modifier = Modifier
            .width(ITEM_WIDTH)
            .height(ITEM_HEIGHT),
        color = if (isSystemInDarkTheme())
            Color.Black else ShimmerLightGray,
        shape = RoundedCornerShape(size = LARGE_PADDING)
    ) {
        Column(
            modifier = Modifier
                .padding(all = MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                modifier = Modifier
                    .alpha(alpha = alpha)
                    .fillMaxWidth()
                    .height(120.dp),
                color = if (isSystemInDarkTheme())
                    ShimmerDarkGray else ShimmerMediumGray,
                shape = RoundedCornerShape(size = SMALL_PADDING)
            ) {}
            Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
            repeat(1) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(alpha = alpha)
                        .height(ABOUT_PLACEHOLDER_HEIGHT),
                    color = if (isSystemInDarkTheme())
                        ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(size = SMALL_PADDING)
                ) {}
                Spacer(modifier = Modifier.padding(all = EXTRA_SMALL_PADDING))
            }

        }
    }
}

@Composable
@Preview
fun ShimmerItemPreview() {
    AnimatedShimmerItem()
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun ShimmerItemDarkPreview() {
    AnimatedShimmerItem()
}
