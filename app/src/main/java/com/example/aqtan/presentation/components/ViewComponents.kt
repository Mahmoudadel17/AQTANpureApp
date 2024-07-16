package com.example.aqtan.presentation.components

import android.os.Parcelable
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.aqtan.presentation.navigation.Screens
import com.example.aqtan.ui.theme.RedComponentColor3
import com.example.aqtan.ui.theme.brush2
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id:Int,
    val images:List<String>,
    val productName:String,
    val description:String,
    val price:Double,
    val oldPrice:Double = 167.5,
    val sealNumber:Int = 15,
    val isSale:Boolean = false,
    val isNew:Boolean = true,

): Parcelable



val saleProducts = listOf(
    Product(
        id = 35,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "T-shirts",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5,
        isSale = true
    ),
    Product(
        id = 34,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "T-shirts",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5,
        isSale = true
    ),
    Product(
        id = 33,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "T-shirts",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5,
        isSale = true
    ),
    Product(
        id = 32,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "T-shirts",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5,
        isSale = true
    ),
    Product(
        id = 31,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "T-shirts",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5,
        isSale = true
    ),
    Product(
        id = 30,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "T-shirts",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5,
        isSale = true
    ),
)


























val products = listOf(
    Product(
        id = 0,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "T-shirts",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 1,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "T-shirts",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 2,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "T-shirts",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 3,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 4,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 5,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "T-shirts",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 6,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 7,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 8,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 9,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 10,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 11,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 12,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 13,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 14,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 15,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 16,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 17,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 18,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 19,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 20,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 21,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 22,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
    Product(
        id = 23,
        images = listOf(
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg",
            "https://m.media-amazon.com/images/I/81D1XEyh0mL._AC_SX679_.jpg",
            "https://m.media-amazon.com/images/I/71WcPwIvIYL._AC_SY879_.jpg"

        ),
        productName = "تيشرت البرق والرعد كاجوال جديد للشباب والرجال من ميزة",
        description = "معلومات عن السلعة\n" +
                "تصميم كاجوال عصري: استمتع بأناقة فريدة وراحة لا مثيل لها مع تيشيرت الرجال هذا، المصمم بقصات كاجوال تناسب أي مناسبة.\n" +
                "قماش عالي الجودة: مصنوع من مواد عالية الجودة، هذا التيشيرت يوفر لك الراحة طوال اليوم مع تحمل فترات الاستخدام الطويلة.\n" +
                "قصة متناسقة: يتميز بقصة مريحة تتناسب مع جسمك بشكل مثالي، مما يضيف لك إطلالة أنيقة وعصرية.\n" +
                "سهولة الصيانة: يمكن غسله بسهولة في الغسالة دون التأثير على جودته أو شكله.\n" +
                "تنوع في الاستخدام: مثالي للاستخدام اليومي، سواء كنت في العمل، المدرسة، التسوق، أو حتى في اللقاءات الاجتماعية.",
        price = 199.5
    ),
)


@Composable
fun ProductCardView(
    product: Product,
    modifier: Modifier = Modifier,
    cardHeight:Int = 200,
    alpha: Float,
    scale: Float,
    onProductClick: () -> Unit
    ) {
    Column (
        modifier = Modifier
            .padding(6.dp)
    ){
        Card(
            modifier = modifier
                .height(cardHeight.dp)
                .fillMaxWidth()
                .graphicsLayer(alpha = alpha, scaleX = scale, scaleY = scale)
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .clickable {
                    // go to details screen
                    // on user click on post to show its details
                    // navController.currentBackStackEntry?.arguments?.putParcelable("user", user) // old
                    onProductClick()
                }

        ) {
            Box{
                AsyncImage(
                    model = product.images[0],
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = brush2),
                    contentScale = ContentScale.Crop
                )
                if (product.isSale){
                    Column (
                        modifier = Modifier
                            .align(Alignment.TopStart)
                    ){
                        TextWithBackgroundColorAsCard(
                            text = "-${product.sealNumber}%",
                            modifier = Modifier,
                            textColor = Color.White,
                            textFont = 14,
                            backgroundColor = RedComponentColor3
                        )

                    }
                }else if( product.isNew){
                    Column (
                        modifier = Modifier
                            .align(Alignment.TopStart)
                        ,
                    ){
                        TextWithBackgroundColorAsCard(
                            text = "NEW",
                            textColor = Color.White,
                            textFont = 14,
                            backgroundColor = Color.Black
                        )

                    }
                }


            }
        }
        TextTitle(text = product.productName.split(" ").take(3).joinToString(" "))
        if (product.isSale){
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp)
                ,
            ) {
                TextLabel(
                    text = product.oldPrice.toString(),
                    textFont = 14,
                    textColor = MaterialTheme.colorScheme.secondary,
                    textDecoration = TextDecoration.LineThrough,
                    textFontWight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(2.dp))
                TextLabel(
                    text = product.price.toString(),
                    textFont = 14,
                    textColor = RedComponentColor3,
                    textFontWight = FontWeight.Bold
                )
            }
        }
        else{
            TextLabel(
                text = product.price.toString(),
                textFont = 14,
                modifier = Modifier
                    .padding(start = 6.dp)
                ,
                textColor = RedComponentColor3,
                textFontWight = FontWeight.Bold
            )
        }
    }

}




@Composable
fun ProductsGridList(
    products: List<Product>,
    navController: NavHostController
) {

    val state = rememberLazyGridState()

    LazyVerticalGrid(
        // Set the number of columns in the grid
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(2.dp)
    ) {
        itemsIndexed(products){ index,product ->
            val (delay, easing) = state.calculateDelayAndEasing(index, 1)
            val animation = tween<Float>(durationMillis = 500, delayMillis = delay, easing = easing)
            val args = ScaleAndAlphaArgs(fromScale = 2f, toScale = 1f, fromAlpha = 0f, toAlpha = 1f)
            val (scale, alpha) = scaleAndAlpha(args = args, animation = animation)

            ProductCardView(
                product = product,
                alpha = alpha,
                scale = scale
            ) {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "product",
                    product
                ) // new
                navController.navigate(Screens.ProductDetails.route)
            }



        }
    }
}



@Composable
private fun LazyGridState.calculateDelayAndEasing(index: Int, columnCount: Int): Pair<Int, Easing> {
    val row = index / columnCount
    val column = index % columnCount
    val firstVisibleRow by remember { derivedStateOf { firstVisibleItemIndex } }
    val visibleRows = layoutInfo.visibleItemsInfo.count()
    val scrollingToBottom = firstVisibleRow < row
    val isFirstLoad = visibleRows == 0
    val rowDelay = 20 * when {
        isFirstLoad -> row // initial load
        scrollingToBottom -> visibleRows + firstVisibleRow - row // scrolling to bottom
        else -> 1 // scrolling to top
    }
    val scrollDirectionMultiplier = if (scrollingToBottom || isFirstLoad) 1 else -1
    val columnDelay = column * 60 * scrollDirectionMultiplier
    val easing = if (scrollingToBottom || isFirstLoad) LinearOutSlowInEasing else FastOutSlowInEasing
    return rowDelay + columnDelay to easing
}

@Composable
fun LazyListState.calculateDelayAndEasing(index: Int, columnCount: Int): Pair<Int, Easing> {
    val row = index / columnCount
    val column = index % columnCount
    val firstVisibleRow by remember { derivedStateOf { firstVisibleItemIndex } }
    val visibleRows = layoutInfo.visibleItemsInfo.count()
    val scrollingToBottom = firstVisibleRow < row
    val isFirstLoad = visibleRows == 0
    val rowDelay = 20 * when {
        isFirstLoad -> row // initial load
        scrollingToBottom -> visibleRows + firstVisibleRow - row // scrolling to bottom
        else -> 1 // scrolling to top
    }
    val scrollDirectionMultiplier = if (scrollingToBottom || isFirstLoad) 1 else -1
    val columnDelay = column * 60 * scrollDirectionMultiplier
    val easing = if (scrollingToBottom || isFirstLoad) LinearOutSlowInEasing else FastOutSlowInEasing
    return rowDelay + columnDelay to easing
}


private enum class State { PLACING, PLACED }
data class ScaleAndAlphaArgs(
    val fromScale: Float,
    val toScale: Float,
    val fromAlpha: Float,
    val toAlpha: Float
)

@Composable
fun scaleAndAlpha(
    args: ScaleAndAlphaArgs,
    animation: FiniteAnimationSpec<Float>
): Pair<Float, Float> {
    val transitionState =
        remember { MutableTransitionState(State.PLACING).apply { targetState = State.PLACED } }
    val transition = updateTransition(transitionState, label = "")
    val alpha by transition.animateFloat(transitionSpec = { animation }, label = "") { state ->
        when (state) {
            State.PLACING -> args.fromAlpha
            State.PLACED -> args.toAlpha
        }
    }
    val scale by transition.animateFloat(transitionSpec = { animation }, label = "") { state ->
        when (state) {
            State.PLACING -> args.fromScale
            State.PLACED -> args.toScale
        }
    }
    return alpha to scale
}
