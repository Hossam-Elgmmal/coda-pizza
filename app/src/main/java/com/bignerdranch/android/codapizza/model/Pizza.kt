package com.bignerdranch.android.codapizza.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pizza(
    val toppings: Map<Topping, ToppingPlacement> = emptyMap()
) : Parcelable {
    val price: Double
        get() = 9.99 + toppings.asSequence().sumOf { (_, toppingPlacement) ->
            when (toppingPlacement) {
                ToppingPlacement.Right, ToppingPlacement.Left -> 0.50
                ToppingPlacement.All -> 1.0
            }
        }

    fun withTopping(topping: Topping, placement: ToppingPlacement?): Pizza {
        return copy(
            toppings = if (placement == null) {
                toppings - topping
            } else {
                toppings + (topping to placement)
            }
        )
    }
}
