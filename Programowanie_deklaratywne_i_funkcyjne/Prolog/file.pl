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


wysylac:-
	write('Czasownik wysyłać'),nl,
	write('Jakim sposobem powinienem wysylac wiadomosc').

czy_istnieje(X) :-(mezczyzna(X);kobieta(X)),
write('wiadomosc ma byc wyslana przez internet, siec, analogowo czy radio'),nl,
read(Typ_wiadomosci),poziomI(Typ_wiadomosci,X).

poziomI(internet,X):-
write('sluzbowo czy prywatnie?'),nl,
read(S_or_priv),sluzbpriwint(S_or_priv,X).

sluzbpriwint(prywatnie,X):-
write('wiadomosc ma byc tekstowa?'),
read(Txt), w5(Txt,X).

sluzbpriwint(sluzbowo,X):-
write('wiadomosc ma byc tekstowa?'),nl,
read(Txts), skype(Txts,X).

skype(nie,X):-
w1(skype,X).

skype(tak,X):-
write('czy wiadomosc jest dluga?'),
read(Dluga), dlugaw(Dluga,X).

dlugaw(nie,X):-
w2(hangouts,X).

dlugaw(tak,X):-
write('Czy musi byc papierze?'),
read(Paper), w3(Paper,X).

poziomI(siec,X):-
write('wiadomosc graficzna czy tekstowa?'),nl,
read(G_or_text), w13(G_or_text,X).

poziomI(analogowo,X):-
write('sluzbowo czy prywatnie'),nl,
read(S_or_oriv),sluzbpriw(S_or_oriv,X).

sluzbpriw(prywatnie, X):-
write('Poczta?'),nl,
read(Pocz),pocz(Pocz,X).

pocz(tak,X):-
write('wiadomosc jest pilna?'),
read(Pil),pil(Pil,X).

pil(tak,X):-
w11(list_polecony,X).

pil(nie,X):-
write('wiadomosc jest jest tekstowa?'),
read(Tes),w9(Tes,X).

pocz(nie,X):-
write('wiadomosc jest pilna?'),
read(Pill),w7(Pill,X).

sluzbpriw(sluzbowo, X):-
w12(telegram,X).

poziomI(radio,X):-
w15(radio,X).

w1(skype,X):-write(X),write("powinien wyslac wiadomosc wizualna skype").
w2(hangouts,X):-write(X),write("powinien wyslac wiadomosc na komunikatorze hangouts").
w3(nie,X):-write(X),write("powinien wyslac wiadomosc email").
w3(tak,X):-write(X),write("powinien wyslac fax").
w5(nie,X):-write(X),write("powinien wyslac wiadomosc na snapchacie").
w5(tak,X):-write(X),write("powinien wyslac wiadomosc na messengerze").
w7(nie,X):-write(X),write("powinien wyslac golebia").
w7(tak,X):-write(X),write("powinien wyslac wiadomosc kodem morsa").
w9(nie,X):-write(X),write("powinien wyslac pocztowke").
w10(tak,X):-write(X),write("powinien wyslac list").
w11(list_polecony,X):-write(X),write("powinien wyslac list polecony").
w12(telegram,X):-write(X),write("powinien wyslac telegram").
w13(tekstowa,X):-write(X),write("powinien wyslac wiadomosc sms").
w13(graficzna,X):-write(X),write("powinien wyslac wiadomosc mms").
w15(radio,X):-write(X),write("powinien wyslac wiadomosc glosowa radiem").