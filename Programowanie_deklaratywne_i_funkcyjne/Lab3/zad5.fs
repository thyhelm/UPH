let list1 = [1;1;1;-2;2;2;3;-34;2;1;34;1;2;2;100]

let zad5 sr tym x =
    if x > sr then
        tym
    else
        x::tym

let zad5v2 sr x =
    if x > sr then
        false
    else
        true

List.filter (fun x -> zad5v2 (List.sum list1/list1.Length) x) list1