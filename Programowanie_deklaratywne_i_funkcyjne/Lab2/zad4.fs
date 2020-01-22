let lista = [1;2;3;4;5;6]

let rec func lista =
    match lista with
    | [] -> (0, 0)
    | x::xs ->
        let tuple = func xs
        if x % 2 = 0 && x % 3 = 0 then (fst tuple + 1, snd tuple + 1)
        elif x % 2 = 0 && not(x % 3 = 0) then (fst tuple + 1, snd tuple)
        elif not(x % 2 = 0) && x % 3 = 0 then (fst tuple, snd tuple + 1)
        else (fst tuple, snd tuple)

func lista