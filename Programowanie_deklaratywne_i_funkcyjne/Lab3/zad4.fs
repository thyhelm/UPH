let list1 = [1;1;1;-2;2;2;3;-34;2;1;34;1;2;2;100]

let zad4 (min, max) x =
    if x < min then (x, max)
    elif x > max then (min, x)
    else (min, max)

List.fold zad4 (list1.Head, list1.Head) list1