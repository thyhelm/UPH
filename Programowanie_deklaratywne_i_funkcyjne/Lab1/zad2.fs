//silnia
let n = 5
let rec factorial n = if n < 2 then 1 
                      else n * factorial (n - 1)
printfn "Wartość %i! = : %i" n (factorial n)

//Fibonacii
let m = 8
let rec fib n = 
    if n < 2 then 1
    else fib (n - 2) + fib(n - 1)
printfn "%i wyraz ciagu Fibonacciego to: %i" m (fib m)

//nwd odejmowanie
let a, b = 230, 430
let rec nwdOdejmowanie a b = 
    if a > b then nwdOdejmowanie (a-b) b
    elif b > a then nwdOdejmowanie (b-a) a
    else a
printfn "NWD liczb a= %i i b= %i to %i" a b (nwdOdejmowanie a b)

//nwd euklides
let c, d = 610, 375
let rec nwdEuklides a b = 
    if b = 0 then a
    else nwdEuklides b (a % b)
printfn "NWD liczb a= %i i b= %i to %i" c d (nwdEuklides c d)

//suma cyfr w liczbie
let number = 1234567
let rec sumOfDigits number = 
    if number < 10 then number % 10
    else number % 10 + sumOfDigits(number/10)
printfn "Suma cyfr %i wynosi %i" number (sumOfDigits number)