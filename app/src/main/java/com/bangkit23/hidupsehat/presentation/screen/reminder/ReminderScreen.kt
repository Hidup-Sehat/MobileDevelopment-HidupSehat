package com.bangkit23.hidupsehat.presentation.screen.reminder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit23.hidupsehat.R
import com.bangkit23.hidupsehat.domain.model.reminder.Reminder
import com.bangkit23.hidupsehat.infrastructure.reminder.ReminderAlarm
import com.bangkit23.hidupsehat.presentation.components.TimePickerDialog
import com.bangkit23.hidupsehat.presentation.screen.reminder.components.ItemReminder
import com.bangkit23.hidupsehat.presentation.ui.theme.HidupSehatTheme
import com.bangkit23.hidupsehat.util.DateHelper
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderScreen(
    viewModel: ReminderViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var showTimePicker by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState()
    val formatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    ReminderContent(
        eatReminders = state.eatReminders,
        activityReminders = state.activityReminders,
        onItemClick = {
            viewModel.onEvent(ReminderEvent.OnItemClick(it))
            showTimePicker = true
        },
        onSwitchChange = { isActive, reminder ->
            ReminderAlarm.setAlarmActiveStatus(context, reminder, isActive)
            viewModel.onEvent(ReminderEvent.OnSwitchCheckedChanged(isActive, reminder))
        }
    )
    SnackbarHost(
        hostState = snackState,
    )

    if (showTimePicker) {
        TimePickerDialog(
            onCancel = { showTimePicker = false },
            onConfirm = {
                val cal = Calendar.getInstance()
                cal.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                cal.set(Calendar.MINUTE, timePickerState.minute)
                cal.isLenient = false
                snackScope.launch {
                    state.editedReminder?.let {
                        ReminderAlarm.setAlarmActiveStatus(context, it, it.isActive)
                        viewModel.onEvent(ReminderEvent.UpdateReminder(it.copy(time = cal.timeInMillis)))
                    }
                    snackState.showSnackbar("Reminder akan diaktifkan pada ${formatter.format(cal.time)}")
                }
                showTimePicker = false
            },
        ) {
            TimePicker(state = timePickerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderContent(
    eatReminders: List<Reminder>,
    activityReminders: List<Reminder>,
    onItemClick: (Reminder) -> Unit,
    onSwitchChange: (Boolean, Reminder) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reminder") },
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add Reminder"
                )
            }
        }
    ) { contentPadding ->
        Column(
            modifier = modifier.padding(contentPadding)
        ) {
            CardReminder(
                title = "Makan",
                reminders = eatReminders,
                cardColor = colorResource(R.color.mental_health),
                onItemClick = onItemClick,
                onSwitchChange = onSwitchChange,
                modifier = Modifier
                    .padding(16.dp)
            )
            CardReminder(
                title = "Olahraga",
                reminders = activityReminders,
                cardColor = colorResource(R.color.yoga),
                onItemClick = onItemClick,
                onSwitchChange = onSwitchChange,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun CardReminder(
    title: String,
    cardColor: Color,
    onItemClick: (Reminder) -> Unit,
    onSwitchChange: (Boolean, Reminder) -> Unit,
    reminders: List<Reminder>,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(
            containerColor = cardColor
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 16.dp)
        ) {
            item {
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                    Divider(color = MaterialTheme.colorScheme.outline)
                }
            }
            items(items = reminders, key = { it.id } ) {
                ItemReminder(
                    title = it.title,
                    time = DateHelper.convertMillisToString(it.time),
                    isActive = it.isActive,
                    onItemClicked = {
                        onItemClick(it)
                    },
                    onSwitchChange = { isActive ->
                        onSwitchChange(isActive, it)
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReminderContentPreview() {
    HidupSehatTheme {
        ReminderContent(
            eatReminders = listOf(
                Reminder(0, "Sarapan", 1685750451183, 0, true),
                Reminder(1, "Makan Siang", 1685768423114, 0, false),
                Reminder(2, "Makan Malam", 1685793657078, 0, true)
            ),
            activityReminders = listOf(
                Reminder(0, "Harian", 1685754048103, 0, false),
                Reminder(1, "Mingguan", 1685750434107, 0, true)
            ),
            onItemClick = {},
            onSwitchChange = {_, _ ->},
        )
    }
}