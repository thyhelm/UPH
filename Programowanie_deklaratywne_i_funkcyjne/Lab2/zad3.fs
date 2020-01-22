let lista1 = [34;23;12;54;76;87;54;43;2;4;5;78;7]

let rec srednia total i lista1 lista2 =  
  match lista1 with
  | [] -> 
    let sr = total / i
    let rec usun sr lista2 =
        match lista2 with
        | [] -> []
        | x::xs ->
            let l = usun sr xs
            if x > sr then l else x::l
    usun sr lista2
  | x::xs -> 
     srednia (x + total) (i + 1) xs lista2
             
srednia 0 0 lista1 lista1