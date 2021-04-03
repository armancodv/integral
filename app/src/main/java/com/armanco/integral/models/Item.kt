package com.armanco.integral.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Item(val id: String, val categoryId: String, @DrawableRes val drawableRes: Int, @StringRes val stringRes: Int)