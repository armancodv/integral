package com.armanco.integral.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(val id: String, @DrawableRes val drawableRes: Int, @StringRes val stringRes: Int)