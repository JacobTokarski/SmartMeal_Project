package org.example.smartmeal

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform