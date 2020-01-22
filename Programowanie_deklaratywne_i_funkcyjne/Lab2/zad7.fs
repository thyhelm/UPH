let list07 = 1::2::3::4::5::6::-1::-2::-3::5::4::3::2::1::[]

let rec numbersSequences list07 tmpList prev res =
    match list07 with
    | [] -> let res2 = tmpList::res
            let rec reverse1 res2 res3 =                                                      // rewersja nadlisty
                match res2 with
                | [] -> res3 
                | x1::xs1 -> let rec reverse2 xs1 x1 res4 =                                   // rewersja list
                               match x1 with
                               | [] -> reverse1 xs1 (res4::res3)
                               | x2::xs2 -> reverse2 xs1 xs2 (x2::res4) 
                             reverse2 xs1 x1 []
            reverse1 res2 [] 
    | x::xs -> if x > 0 && prev >= 0 then numbersSequences xs (x::tmpList) x res              // dodawanie liczb dodatnich(i 0) do listy
               elif x > 0 && prev < 0 then numbersSequences xs (x::[]) x (tmpList::res)       // jesli poprzednia liczba jest ujemna, dodanie elementu do nowej listy, dodanie poprzedniej listy do nadlisty
               elif x < 0 && prev < 0 then numbersSequences xs (x::tmpList) x res             // dodawanie liczb ujemnych  do listy
               elif x < 0 && prev >= 0 then numbersSequences xs (x::[]) x (tmpList::res)      // jesli poprzednia liczba jest dodatnia, dodanie elementu do nowej listy, dodanie poprzedniej listy do nadlisty
               else numbersSequences xs tmpList x res
    
        

numbersSequences list07 [] 0 []