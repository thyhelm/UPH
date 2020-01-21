//Kwadrat
let a = 5
let poleKwadratu a = a * a
let P = poleKwadratu a
printf "Pole kwadratu wynosi: %i \n" P

let obwodKwadratu a = 4 * a
let O = obwodKwadratu a
printf "Obwod kwadratu wynosi: %i \n" O


//Prostokąt
let a = 3
let b = 4
let poleProstokata a b = a * b
let P = poleProstokata a b
printf "Pole prostokata wynosi: %i \n" P

let obwodProstokota a b = 2 * a + 2 * b
let O = obwodProstokota a b
printf "Obwod prostokata wynosi: %i \n" O


//Trójkąt
let a = 3
let h = 4
let poleTrojkata a h = a * h / 2
let P = poleTrojkata a b
printf "Pole Trojkata wynosi: %i \n" P

let obwodTrojkata a = 3 * a
let O = obwodTrojkata a
printf "Obwod Trojkata wynosi: %i \n" O


//Koło
let r = 4.0
let poleKola r : float = r * r * 3.14
let P = poleKola r
printf "Pole Trojkata wynosi: %f \n" P

let obwodKola r : float = r * 2.0 * 3.14
let O = obwodKola r
printf "Obwod Trojkata wynosi: %f \n" O