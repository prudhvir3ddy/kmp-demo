package com.prudhvir3ddy.todoapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform