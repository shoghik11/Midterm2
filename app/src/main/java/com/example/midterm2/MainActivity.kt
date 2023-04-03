package com.example.midterm2

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.midterm2.ui.theme.Midterm2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val randomUsersService = (RandomUsersService::class.java)

        setContent {
            val usersState = remember { mutableStateOf<List<User>>(emptyList()) }

            LaunchedEffect(Unit) {
                val response = RandomUsersService.getUsers("nat,name,email", 10)
                if (response.isSuccessful) {
                    val usersResponse = response.body()
                    val users = usersResponse?.results?.map { result ->
                        User("${result.name.first} ${result.name.last}", result.email)
                    } ?: emptyList()
                    usersState.value = users
                }
            }

            UserList(users = usersState.value)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Users",
        style = MaterialTheme.typography.h4,
        //color = Color.WHITE,
        )
}

@Composable
fun UserList(users: List<User>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(Color.WHITE)
    ) {
        Text(
            text = "Users",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(users) { user ->
                UserList(user = user)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Midterm2Theme {
        Greeting("Midterm2")
    }
}