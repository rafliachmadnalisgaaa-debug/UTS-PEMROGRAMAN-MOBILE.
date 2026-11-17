package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var selectedPageIndex by remember { mutableIntStateOf(0) }
                val pageTitles = listOf("No. 2", "No. 3", "No. 4")

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            pageTitles.forEachIndexed { index, title ->
                                NavigationBarItem(
                                    selected = selectedPageIndex == index,
                                    onClick = { selectedPageIndex = index },
                                    icon = { Text("H${index + 1}") },
                                    label = { Text(title) }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (selectedPageIndex) {
                            0 -> ParentScreen()
                            1 -> DaftarTugasScreen()
                            2 -> MenuAkademikScreen()
                        }
                    }
                }
            }
        }
    }
}

// ==========================================
// JAWABAN SOAL NO. 2 UTS (Halaman 1)
// ==========================================
@Composable
fun ParentScreen() {
    var teks by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Halaman 1 - Soal No. 2",
                style = MaterialTheme.typography.headlineSmall
            )
            InputNama(
                value = teks,
                onValueChange = { teks = it }
            )
            if (teks.isNotEmpty()) {
                Text(
                    text = "Hasil Input: $teks",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun InputNama(
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Nama") }
    )
}

@Preview(showBackground = true)
@Composable
fun ParentScreenPreview() {
    MyApplicationTheme {
        ParentScreen()
    }
}

// ==========================================
// JAWABAN SOAL NO. 3 UTS (Halaman 2)
// ==========================================
data class Tugas(
    val judul: String,
    val deadline: String
)

@Composable
fun DaftarTugasScreen() {
    val listTugas = listOf(
        Tugas("Pemrograman Mobile", "15 November 2026"),
        Tugas("Metnum Lanjutan", "15 November 2026")
    )

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(listTugas) { tugas ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = tugas.judul, style = MaterialTheme.typography.titleMedium)
                        Text(text = "Deadline: ${tugas.deadline}", style = MaterialTheme.typography.bodyMedium)
                    }
                    Button(onClick = { /* Action detail */ }) {
                        Text("Detail")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DaftarTugasScreenPreview() {
    MyApplicationTheme {
        DaftarTugasScreen()
    }
}

// ==========================================
// JAWABAN SOAL NO. 4 UTS (Halaman 3)
// ==========================================
@Composable
fun MenuAkademikScreen(
    onMenuClick: (String) -> Unit = {}
) {
    val daftarMenu = listOf("Profil", "Jadwal", "Nilai", "Tagihan")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Menu Akademik",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(daftarMenu) { menu ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clickable { onMenuClick(menu) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = menu,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuAkademikScreenPreview() {
    MyApplicationTheme {
        MenuAkademikScreen()
    }
}
