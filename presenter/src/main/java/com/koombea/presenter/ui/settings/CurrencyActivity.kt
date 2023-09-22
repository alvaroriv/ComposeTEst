package com.koombea.presenter.ui.settings

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.koombea.androidtemplate.ui.theme.AndroidtemplateTheme
import com.koombea.data.character.base.SharedPrefUtils

class CurrencyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            AndroidtemplateTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    CurrencyScreen()
                }
            }
        }
    }
}

data class ItemData(
    val id : Int,
    val display: String,
    val isSelected: Boolean = false
)

class ItemDataState {

    val itemDataList = mutableStateListOf(
        ItemData(1, "United States (USD)"),
        ItemData(2, "Japan (JPY)"),
        ItemData(3, "Germany (EUR)"),
        ItemData(4, "Colombia(COP)")
    )

    // were updating the entire list in a single pass using its iterator
    fun onItemSelected(selectedItemData: ItemData) {
        val iterator = itemDataList.listIterator()

        while (iterator.hasNext()) {
            val listItem = iterator.next()

            iterator.set(
                if (listItem.id == selectedItemData.id) {
                    selectedItemData
                } else {
                    listItem.copy(isSelected = false)
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CurrencyScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 32.dp, 16.dp, 0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val context = LocalContext.current as Activity

        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.clickable {
                    context.finish()
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Currency",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))
        ItemList()
    }
}

@Composable
fun ItemDisplay(
    itemData: ItemData,
    onCheckChanged: (ItemData) -> Unit
) {
    val context =LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(text = itemData.display,
            style = TextStyle(fontSize = 14.sp))
        //Text(text = if (itemData.isSelected) "I'm selected!" else itemData.display)

        Checkbox(
            checked = itemData.isSelected,
            onCheckedChange = {
                SharedPrefUtils.saveData(context,"currency",itemData.display)
                onCheckChanged(itemData.copy(isSelected = !itemData.isSelected))
            },
            colors = CheckboxDefaults.colors(checkedColor = Color(0xff5233FF)))
    }
}

@Composable
fun ItemList() {

    val itemDataState = remember { ItemDataState() }

    LazyColumn {
        items(itemDataState.itemDataList, key = { it.id } ) { item ->
            ItemDisplay(
                itemData = item,
                onCheckChanged = itemDataState::onItemSelected
            )
        }
    }
}


