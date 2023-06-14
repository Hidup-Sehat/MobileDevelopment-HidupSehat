package com.bangkit23.hidupsehat.util

object ListConverter {
    fun convertListToString(list : List<String>) : String {
        return list.joinToString(",")
    }
}