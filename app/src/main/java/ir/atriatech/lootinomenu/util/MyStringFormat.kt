package com.example.kotlintest.util

import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class MyStringFormat {

	companion object {
		private val numberFormat = NumberFormat.getNumberInstance(Locale.US)
	}

	fun getPrice(price: Int): String {
		val mPrice: String
		mPrice = numberFormat.format(price.toLong()) + " " + "تومان"

		return mPrice
	}

	fun getPriceWithSuffix(price: Int): String {
		val mPrice: String
		mPrice = "قیمت : " + numberFormat.format(price.toLong()) + " " + "تومان"

		return mPrice
	}

	fun getFormatedNumber(price: Int): String {
		return numberFormat.format(price.toLong())
	}

	fun getFormatedNumber(price: Long): String {
		return numberFormat.format(price)
	}

	fun getFormatCreditCard(creditCard: String): String {
		if (creditCard.isEmpty())
			return ""

		val stringBuilder = StringBuilder()
		stringBuilder.append(creditCard)
		val positions: MutableList<Int> = ArrayList()
		for (i in 1 until creditCard.length) {
			if (i % 5 == 0 && i < creditCard.length)
				positions.add(i - 1)
		}

		if (positions.size > 0)
			positions.forEach {
				stringBuilder.insert(it, "-")
			}

		return stringBuilder.toString()
	}

	fun getFormatCreditCardR(creditCard: String): String {
		if (creditCard.isEmpty())
			return ""

		val stringBuilder = StringBuilder()
		stringBuilder.append(creditCard)
		val positions: MutableList<Int> = ArrayList()
		for (i in 1 until creditCard.length) {
			if (i % 5 == 0 && i < creditCard.length)
				positions.add(i - 1)
		}

		if (positions.size > 0)
			positions.forEach {
				stringBuilder.insert(it, "-")
			}

		val cardNumber = stringBuilder.split("-")
		val stringBuilderR = StringBuilder()

		for (i in cardNumber.size - 1 downTo 0) {
			cardNumber[i].forEach {
				stringBuilderR.append(it)
			}

			if (i > 0)
				stringBuilderR.append("-")
		}

		return stringBuilderR.toString()
	}


	fun getRemainTime(seconds: Int): String {
		if (seconds < 60)
			return "یک دقیقه پیش"

		val minute = seconds / 60

		return "$minute دقیقه پیش"
	}
}
