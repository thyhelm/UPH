let lista = [23;4;22;12;6;4;8;5]

let rec minmax min max lista = 
    match lista with
    | [] -> (min, max)
    | x::xs ->
        if min = 0 && max = 0 then minmax x x xs
        elif x > max then minmax min x xs
        elif x < min then minmax x max xs
        else minmax min max xs

minmax 0 0 lista