let list1 = [1;1;1;-2;2;2;3;-34;2;1;34;1;2;2;100]

let zad5 srednia tym x =
    if x > srednia then
        tym
    else
        x::tym

let zad5v2 srednia x =
    if x > srednia then
        false
    else
        true

List.filter (fun x -> zad5v2 (List.sum list1/list1.Length) x) list1