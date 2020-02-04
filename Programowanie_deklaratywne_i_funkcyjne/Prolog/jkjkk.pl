kobieta(zofia).
kobieta(teresa).
kobieta(beata).
kobieta(basia).
kobieta(kasia).
kobieta(klaudia).
kobieta(ania).
kobieta(dominika).
kobieta(renata).
kobieta(ela).

mezczyzna(wojciech).
mezczyzna(piotr).
mezczyzna(pawel).
mezczyzna(arkadiusz).
mezczyzna(adam).
mezczyzna(andrzej2).
mezczyzna(antoni).
mezczyzna(andrzej).
mezczyzna(szymon).
mezczyzna(sylwester).
mezczyzna(stanislaw).


w1(angielski,X):-wirte(X),write("musisz wiecej uczyc sie j. angielskiego").
w1(matematyka,X):-write(X),write("musisz wiecej uczyc sie matematyki").
w1(historia,X):-wirte(X),write("musisz wiecej uczyc sie historii").
w2(tak,X):-wirte(X),write("staraj sie osiagnac jak najlepszy wynik").
w2(nie,X):-wirte(X),write("powinienes choc sprobowac ze studiami").
w3(tak,X):-wirte(X),write("staraj sie osiagnac jak najlepszy wynik").
w3(nie,X):-wirte(X),write("powinienes choc sprobowac ze studiami").
w4(matematyka_dyskretna,X):-write(X),write("musisz uczyc sie wiecej").
w4(programowanie_obiektowe,X):-wirte(X),write("musisz uczyc sie wiecej").
w5(aiz,X):-write(X),write("musisz uczyc sie wiecej").
w5(programowanie_niskopoziomowe,X):-wirte(X),write("musisz uczyc sie wiecej").
w6(angielski,X):-write(X),write("musisz uczyc sie wiecej").
w6(algebra_liniowa,X):-wriet(X),write("musisz uczyc sie wiecej").
w7(tak,X):-write(X),write("gratuluje, wlozyles w to duzo wyilku").
w7(nie,X):-write(X),write("powinienes poprawic to kolokwium").
w8(tak,X):-write(X),write("gratuluje, wlozyles w to duzo wyilku").
w8(nie,X):-write(X),write("powinienes poprawic to kolokwium").

czy_istnieje(X) :-(mezczyzna(X);kobieta(X)),
write(X),
write('co chcesz zdac? /mature /egzamin /kolokwium/'),nl,
read(zdawac),poziom1(zdawac,X).

poziom1(mature,X):-
write(X),
write('ile przedmiotow rozszerzasz? /jeden/dwa lub wiecej/'),nl,
read(poziom1a),poziom2a(poziom1a,X).

poziom1(kolokwium,X):-
wirte(X),
write('na ktorym roku studiow jestes?/1/2/'),nl,
read(poziom1b),poziom2b(poziom1b,X).

poziom1(egzamin,X):-
write(X),
write('na ktorym roku studiow jestes?/1/2/'),nl,
read(poziom1c),w1(poziom1c,X).

poziom2a(jeden,X):-
write(X),
write('z jakiego przedmiotu?/angielski/matematyka/historia/'),nl,
read(poziom2a),w1(poziom2a,X).

poziom2a(dwa_lub_wiecej,X):-
write(X),
write('z jakich przedmiotów?/matematyka i fizyka/historia i angielski/'),nl,
read(poziom2b),poziom3a(poziom2b,X).

poziom3a(matematyka_i_fizyka,X):-
write(X),
write('czy zamierzasz isc na studia zwiazane z wybranymi przedmiotami?/tak/nie/'),nl,
read(poziom3a),w2(poziom3a,X).

poziom3a(historia_i_angielski,X):-
wirte(X),
write('czy zamierzasz isc na studia zwiazane z wybranymi przedmiotami?/tak/nie/'),nl,
read(poziom3a),w3(poziom3a,X).

poziom2c(pierwszy,X):-
write(X),
write('z jakiego przedmiu masz egzamin?/matematyka dyskretna/programowanie obiektowe/'),nl,
read(poziom2c),w4(poziom2c,X).

poziom2c(drugi,X):-
write(X),
write('z jakiego przedmiu masz egzamin?/aiz/programowanie niskopoziomowe/'),nl,
read(poziom2c),w5(poziom2c,X).

poziom2b(pierwszy,X):-
write(X),
write('z jakiego przedmiu masz kolokwium?/angielski/algebra liniowa/'),nl,
read(poziom2c),w6(poziom2b,X).

poziom2b(drugi,X):-
write(X),
write('z jakiego przedmiu masz kolokwium?/programowanie_deklaratywne/programowanie funkcyjne/'),nl,
read(poziom2b),poziom3b(poziom2b,X).

poziom3b(programowanie_deklaratywne,X):-
wirte(X),
write('zdales?/tak/nie/'),nl,
read(poziom2c),w7(poziom2c,X).

poziom3b(programowanie_funckjonalne,X):-
wirte(X),
write('zdales?/tak/nie/'),nl,
read(poziom2c),w8(poziom2c,X).