let list = [34;2;42;23]

let rec srednia total i list =  
  match list with
  | [] -> total / i
  | x::xs -> 
     srednia (x + total) (i + 1) xs
             
srednia 0 0 list