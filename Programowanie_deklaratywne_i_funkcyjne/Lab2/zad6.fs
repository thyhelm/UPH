let list06 = -1::-4::4::8::11::5::3::4::-6::-6::-7::-5::-9::-3::-5::[]
//let list06 = 1::9::3::[]

let rec longestAP list06 resList tmpList =
    match list06 with
    | [] -> let res = if snd tmpList > snd resList then fst tmpList
                      else fst resList
            let rec rewrite res res2 =
                match res with
                | [] -> res2 
                | x::xs -> rewrite xs (x::res2)     
            rewrite res []
    | x::xs -> 
           if x < 0 then longestAP xs resList (x::fst tmpList, snd tmpList + 1)             // dodawanie kolejnych liczb ujemnych do listy tymczasowej
           elif snd tmpList > snd resList then longestAP xs tmpList ([], 0)                 // gdy liczba dodatnia, porownanie ilosci liczb ujemnych w obecnym najdluzszym ciagu z ciagiem w liscie tymczasowej,
                                                                                            // w przypadku gdy lista tymczasowa posiada dluzszy ciag, zastepienie obecnego najdluzszego ciagu i wyzerowanie listy tymczasowej
           else longestAP xs resList tmpList


longestAP list06 ([] , 0) ([] , 0) 