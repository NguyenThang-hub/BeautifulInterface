package com.example.bill


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroList()
                }
            }
        }
    }
}

data class Hero(
    val name: String,
    val description: String
)

val heroes = listOf(
    Hero("Superman", "Man of Steel with super strength."),
    Hero("Batman", "Dark Knight of Gotham."),
    Hero("Wonder Woman", "Amazon warrior princess."),
    Hero("Flash", "Fastest man alive."),
    Hero("Aquaman", "King of Atlantis.")
)

@Composable
fun HeroList() {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(heroes) { hero ->
            HeroCard(hero)
        }
    }
}

@Composable
fun HeroCard(hero: Hero) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .animateContentSize(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = hero.name,
                fontSize = 22.sp,
                style = MaterialTheme.typography.titleLarge
            )

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = hero.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}