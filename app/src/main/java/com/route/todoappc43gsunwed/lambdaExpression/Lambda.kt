package com.route.todoappc43gsunwed.lambdaExpression

import android.util.Log


//        Inputs of the function/lambda //  void
// Declare Lambda Expression

var addLambda: (num1: Int, num2: Int) -> Int = { num1, num2 ->
    num1 + num2
}

fun add(num1: Int, num2: Int, beforeExecutingCalculation: () -> Unit): Int {
    beforeExecutingCalculation()
    return num1 + num2
}


fun test() {
    val sum = add(10, 20) {
        Log.e("TAG", "test: Testing Calculation")
    }
    val sum1 = addLambda(10, 20)
}

fun main() {
    test()
}

