package com.example.basiccodelab.common.base

interface Mapper<F, T> {
    fun fromMap(from: F): T
}