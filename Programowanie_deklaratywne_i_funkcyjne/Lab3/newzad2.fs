let list02 = [1; 1; 1; 2; 2; 1; 1; 3; 1; 2; 2]

let pack list =
    let rec loop list prev tmpList resList =
        match list with
        | [] -> let resList = tmpList::resList
                List.rev resList
        | x::xs -> if prev = x then loop xs x (x::tmpList) resList
                   else loop xs x (x::[]) (tmpList::resList)
    loop list (list.Item(0)) [] []

pack list02