let list = [1;1;1;2;2;3;2;2;1;1;1;2;2]

let rec compress' acc res list =
    match list with
    | [] -> res
    | head::tail -> if head=acc then compress' head res tail else compress' head (res @ [head]) tail

let compress list = compress' (List.head list) [(List.head list)] list

compress list