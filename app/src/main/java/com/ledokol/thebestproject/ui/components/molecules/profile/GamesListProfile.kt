package com.ledokol.thebestproject.ui.components.molecules.profile

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ledokol.thebestproject.R
import com.ledokol.thebestproject.presentation.GamesViewModel
import com.ledokol.thebestproject.ui.components.atoms.alertdialogs.AlertDialogShow
import com.ledokol.thebestproject.ui.components.atoms.texts.HeadlineH4
import com.ledokol.thebestproject.ui.components.molecules.BackToolbar
import com.ledokol.thebestproject.ui.components.molecules.GameInList
import java.util.*

@Composable
fun GamesListProfile(
    gamesViewModel: GamesViewModel,
    navController: NavController,
) {
    val context = LocalContext.current
    val games = gamesViewModel.state.games
    var openDialog by remember{ mutableStateOf(false) }
    var selectedGame by remember {
        mutableStateOf("")
    }

    val usageStatsManager =
        context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

    val calendar: Calendar = Calendar.getInstance()
    calendar.add(Calendar.WEEK_OF_YEAR, -1)
    val start: Long = calendar.getTimeInMillis()
    val end = System.currentTimeMillis()
    val stats: Map<String, UsageStats> = usageStatsManager.queryAndAggregateUsageStats(start, end)


    BackToolbar(
        buttonBackClick = {
            navController.popBackStack()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp, start = 20.dp, end = 20.dp)
    ){

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth()
    ) {


        item(span = { GridItemSpan(2) }) {
            HeadlineH4(
                text = "Игры",
                modifier = Modifier.padding(start = 20.dp),
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.W700,
            )
        }
        if(games != null) {

            items(games) { game ->
                GameInList(
                    packageName = game.android_package_name,
                    name = game.name,
                    icon = game.icon_preview!!,
                    openGame = true,
                    onClick = {
                        openDialog = true
                        selectedGame = game.android_package_name
                    },
                    usageTime = if (game.android_package_name in stats.keys)
//                            (stats.get(game.android_package_name)!!.totalTimeInForeground.milliseconds).toString()
                        (stats.get(game.android_package_name)!!.totalTimeInForeground.toInt() / 60000).toString()
//                            null
                    else null
                )
            }
        }
    }

        AlertDialogShow(
            openDialog = openDialog,
            label = stringResource(id = R.string.profile_open_game_title),
            description = stringResource(id = R.string.profile_open_game_description),
            buttonTextYes = stringResource(id = R.string.yes),
            buttonTextNo = stringResource(id = R.string.cancel),
            onActionPrimary = { openGame(selectedGame, context);openDialog = false; selectedGame = "" },
            onActionSecondary = { openDialog = false; selectedGame = "" }
        )
    }


}

fun openGame(packageName: String, context: Context){
    val launchIntent = context.packageManager.getLaunchIntentForPackage(packageName)
    launchIntent?.let { context.startActivity(it) }
}