package com.cieslak.lab02.models

class CodeManager {
    var name: String = ""
        get() = field
        set(value) {
            field = value
        }
    init {
        this.name = name
        println("Call my name. $name")
    }

    var counter: Int = 0
        get() = field
        set(value) {
            field = value
        }

    fun increaseCounter() {
        this.counter += 1;
    }

    fun cleanCounter() {
        this.counter = 0;
    }

    fun showMotivationText(): String{
        return when(counter){
            in 1..10 -> "Gratuluje ${name} napisałeś już ${counter} linii kodu!"
            in 11..20 -> "Ogień! ${name} napisałeś już ${counter} linii kodu!"
            else -> "Dasz radę ${name}! już napisałeś ${counter}"
        }
    }
}