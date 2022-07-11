package com.ledokol.thebestprojectever.ui.components.screens

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.os.Process
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.ledokol.thebestprojectever.R
import com.ledokol.thebestprojectever.presentation.GamesViewModel
import com.ledokol.thebestprojectever.services.GamesStatistic
import com.ledokol.thebestprojectever.ui.components.atoms.Body1
import com.ledokol.thebestprojectever.ui.components.atoms.HeadlineH4
import com.ledokol.thebestprojectever.ui.components.atoms.HeadlineH5
import com.ledokol.thebestprojectever.ui.components.atoms.buttons.ButtonPrimaryFull

@Composable
fun RequestPermission(
    navController: NavController,
    gamesViewModel: GamesViewModel,
) {
    val context: Context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val checkPermission = remember{ mutableStateOf(false) }

    DisposableEffect(lifecycleOwner, checkPermission) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START || event == Lifecycle.Event.ON_RESUME) {

                if(checkForPermission(context)){
                    Log.d("INSTALLEDAPPS", "UPDATE CHECK")
                    gamesViewModel.clearGames()
                    gamesViewModel.insertGames(
                        GamesStatistic.convertListApplicationToListGame(
                            context,
                            context.packageManager,
                            GamesStatistic.getInstalledAppGamesList(context.packageManager)
                        )
                    )
                    gamesViewModel.getGames()
                    gamesViewModel.shareGames()

                    navController.navigate("quick_game"){
                        popUpTo("quick_game")
                        launchSingleTop = true
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = 100.dp, bottom = 100.dp,start = 30.dp, end = 30.dp)
    ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally,

    ){

        Column(
            modifier = Modifier.weight(3f)
        ){
            HeadlineH4(text = stringResource(id = R.string.hello_permission),
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                ,
                fontWeight = FontWeight.W500,
            )
            HeadlineH5(text = stringResource(id = R.string.need_permission),
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                ,
                fontWeight = FontWeight.W500,
            )

            HeadlineH4(text = stringResource(id = R.string.reason_permission),
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                ,
                fontWeight = FontWeight.W500,
            )
        }

        Column(
            modifier = Modifier.weight(3f),
            verticalArrangement = Arrangement.Bottom

        ){
            Body1(text = stringResource(id = R.string.explain_permission),
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                ,
                fontWeight = FontWeight.W500,
            )

            ButtonPrimaryFull(
                text = stringResource(id = R.string.allow_permission),
                onClick = {
                    context.startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
                }
            )
        }

    }
}

fun checkForPermission(context: Context): Boolean {
    val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
    val mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(), context.packageName)
    return mode == AppOpsManager.MODE_ALLOWED
}

