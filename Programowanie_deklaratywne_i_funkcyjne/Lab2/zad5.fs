let lista = [1;2;3;4;5;6]

let rec func lista =
    match lista with
    | [] -> ([], [])                          
    | x::xs ->
        let lists = func xs
        if x % 2 = 0 && x % 3 = 0 then  (x::fst lists, x::snd lists)
        elif x % 2 = 0 && not(x % 3 = 0) then (x::fst lists, snd lists)
        elif not(x % 2 = 0) && x % 3 = 0 then (fst lists, x::snd lists)
        else (fst lists, snd lists)


func lista