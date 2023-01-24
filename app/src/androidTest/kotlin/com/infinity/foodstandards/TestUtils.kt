package com.infinity.foodstandards

import org.mockito.Mockito

internal inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
