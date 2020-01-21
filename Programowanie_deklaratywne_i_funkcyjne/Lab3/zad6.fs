let list1 = [1;1;1;-2;2;2;3;-34;2;1;34;1;2;2;100]


let zad6 li =
    (
    List.fold (fun acc x -> if x % 2 = 0 then acc + 1 else acc) 0 li
    ,
    List.fold (fun acc x -> if x % 3 = 0 then acc + 1 else acc) 0 li
    )

zad6 list1