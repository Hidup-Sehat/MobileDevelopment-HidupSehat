package com.bangkit23.hidupsehat.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

private val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
