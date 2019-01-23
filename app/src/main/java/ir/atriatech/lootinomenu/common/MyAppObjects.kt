package com.example.beautyshopapplication.common

import javax.inject.Singleton

@Singleton
object MyAppObjects {
	var density = 2

	object Login {
		val login = "login"
		val verifyCode = "verifyCode"
		val resendVerifyCode = "verifyCode.resend"
		val register = "register"
	}

	object Home {
		val index = "home.index"
		val relatedProduct = "home.related.product"
		val search = "home.search"

		object Category {
			val index = "category.detail.index"
			val gallery = "category.detail.gallery"
		}
	}

	object ShopCart {
		val index = "shop.cart.index"
		val address = "shop.cart.address"
		val payment = "shop.cart.payment"
		val checkout = "shop.cart.checkout"
	}

	object Product {
		val detail = "product.detail"
		val comment = "product.comment"
	}

	object Maker {
		val index = "maker.index"
		val step1 = "maker.step1"
	}

	object Search {
		val index = "search.index"
		val relatedProduct = "search.related.product"
	}

	object Survey {
		val index = "survey.index"
	}

	/*=========================Order============================*/
	object Order {
		val index = "orders.index"
		val detail = "orders.detail"
		val detailHome = "orders.detail.home"
		val survey = "orders.survey"
	}

	/*=========================Profile============================*/
	object Profile {
		val index = "profile.index"
		val editInfo = "profile.edit.info"

		object ChangeMobile {
			val index = "change.number.index"
			val verify = "change.number.verify"
			val resendCode = "change.number.resend.code"
		}
	}

	object Address {
		val index = "address.index"
		val addressPosition = "address.position"
		val addEdit = "address.add.edit"
		val search = "address.add.search"
		val json = "address.json"
	}

	object Transaction {
		val index = "transactions.index"
		val add = "transactions.add"
		val payment = "transactions.payment.confirmation"
	}

	object Messages {
		val index = "messages.index"
		val detail = "messages.detail"
	}

	object Support {
		val index = "support.index"
		val detail = "support.detail"
		val id = "support.id"
		val add = "support.add"
		val reply = "support.reply"
		val supportPosition = "support.position"
		val supportStatus = "support.status"
		val supportStatusText = "support.status.text"
	}

	object MyFoods {
		val index = "credit.card.index"
		val position = "credit.edit.position"
		val add = "credit.card.add"
		val edit = "credit.card.edit"
		val delete = "credit.card.delete"
	}

	object ChangeNumber {
		val index = "change.number.index"
		val verify = "cange.number.verify"
	}

	/*=========================More============================*/

	object More {
		val about = "more.about"
		val contact = "more.contact"
		val share = "more.share"
		val law = "more.law"
		val conditions = "more.conditions"
		val faq = "more.faq"
	}
}