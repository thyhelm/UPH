//liczby z przedzialu <a,b>
let a = 5
let b = 15
let rec displayNumbers a b = 
    if a = b then printfn "%i" a  
    else printfn "%i" a; displayNumbers (a+1) b
displayNumbers a b

//konkatenacja
let string = "napis"
let i = 5
let rec displayNthConc string i = 
    if i = 1 then printfn "%s" string
    else displayNthConc (string+string) (i-1)
displayNthConc string i

//ciag
let an, j = 10, 11
let rec ciag an j = 
    if j = 1 then printfn "%i " an
    else printf "%i " an; ciag (an+3) (j-1)
ciag an j


let x, n = 2, 5
let rec suma x n =
    if n = 0 then x
    else x + suma (x+1) (n-1)
suma x n 