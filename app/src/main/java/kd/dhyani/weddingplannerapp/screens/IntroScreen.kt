package kd.dhyani.weddingplannerapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kd.dhyani.weddingplannerapp.content.getList
import kotlinx.coroutines.launch

@Composable
fun IntroScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val list = getList() // Get list of intro pages
    val pagerState = rememberPagerState(pageCount = { list.size }) // Keep track of current page
    val scope = rememberCoroutineScope() // Needed for animating page scroll

    // 🔹 Horizontal Pager to swipe between intro pages
    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
        val item = list[page]

        Box(modifier = Modifier.fillMaxSize()) {
            // Background image for each page
            Image(
                painter = painterResource(id = item.res),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient overlay at bottom for better text visibility
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.65f)
                            ),
                            startY = 600f
                        )
                    )
            )

            // Text & buttons column aligned at bottom
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                // Page title
                if (item.title.isNotEmpty()) {
                    Text(
                        text = item.title,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Page description
                if (item.des.isNotEmpty()) {
                    Text(
                        text = item.des,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // 🔹 Buttons logic changes based on page number
                when (page) {
                    0 -> {
                        // First page: only Next button with page indicator
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.weight(2.5f))
                            PageIndicator(size = list.size, currentPage = page)
                            Spacer(modifier = Modifier.weight(1f))
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFddc57c)
                                ),
                                onClick = {
                                    scope.launch { pagerState.animateScrollToPage(page + 1) }
                                },
                                modifier = Modifier.padding(start = 16.dp)
                            ) {
                                Text("Next", color = Color(0xFF5e5840))
                            }
                        }
                    }

                    1, 2 -> {
                        // Middle pages: Prev, PageIndicator, Next buttons
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = {
                                    scope.launch { pagerState.animateScrollToPage(page - 1) }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Gray
                                )
                            ) { Text("Prev", color = Color(0xFF5e5840)) }

                            PageIndicator(size = list.size, currentPage = page)

                            Button(
                                onClick = {
                                    scope.launch { pagerState.animateScrollToPage(page + 1) }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFddc57c)
                                )
                            ) { Text("Next", color = Color(0xFF5e5840)) }
                        }
                    }

                    3 -> {
                        // Last page: "Get Started" & "Log in"
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                onClick = { onSignUpClick() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFddc57c)
                                ),
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                    .height(50.dp)
                            ) {
                                Text("Get Started", color = Color(0xFF5e5840))
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Log in",
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .clickable { onLoginClick() }
                            )
                        }
                    }
                }
            }
        }
    }
}

// 🔹 Row of small circles to indicate current page
@Composable
fun PageIndicator(size: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(size) { index ->
            val color =
                if (index == currentPage) Color(0xFFddc57c) // active page
                else Color.LightGray.copy(alpha = 0.5f)    // inactive
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(10.dp)
                    .background(color, shape = CircleShape)
            )
        }
    }
}
