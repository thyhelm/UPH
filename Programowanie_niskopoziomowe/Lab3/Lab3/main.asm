.386
.MODEL flat, STDCALL

STD_OUTPUT_HANDLE                    equ -11
STD_INPUT_HANDLE                     equ -10

GetStdHandle PROTO :DWORD
WriteConsoleA PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
ReadConsoleA PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
CharToOemA PROTO :DWORD, :DWORD
ExitProcess PROTO :DWORD

.data
	tekst1 db "Wpisz 10 znaków", 0
	tekst2 db "Indeks rozniacego sie elementu: ", 0
	source db 10 dup (?)
	destination db 10 dup (?)
	destination2 db 10 dup (?)
	zrodlo db 10 dup (?)
	przeznaczenie db 10 dup (?)

	tablica1 db "xdxdxdxdxdxdxdxdxdxdxdxd"
	tablica2 db "xdxdxdxdcdxdxdxdxdxdxdxd"
	indeks dd 0

	rout dd 0
	hout dd 0
	hin dd 0

.code 
main proc
	push STD_OUTPUT_HANDLE
	call GetStdHandle
	mov hout, eax

	push STD_INPUT_HANDLE
	call GetStdHandle	
	mov	hin, eax

	push OFFSET tekst1
	push OFFSET tekst1
	call CharToOemA

	push 0
	push OFFSET rout
	push LENGTHOF tekst1
	push OFFSET tekst1
	push hout
	call WriteConsoleA

	push 0
	push OFFSET rout
	push 10
	push OFFSET source
	push hin
	call ReadConsoleA

	mov ebx, OFFSET source
	add ebx, rout
	mov [ebx-2], BYTE PTR 0
	mov [ebx-1], BYTE PTR 0

	mov EAX, OFFSET source
	mov EBX, OFFSET destination

copy:
	mov CL, [EAX]
	mov [EBX], CL
	inc EAX                     
	inc EBX
	mov CL, [EAX]
	cmp CL, 00
	jnz copy
	jz finish

finish:
	CLD
	mov ecx, LENGTHOF source
	mov esi, OFFSET source
	mov edi, OFFSET destination2
	rep movsb

	push 0		
	push OFFSET rout 	
	push LENGTHOF tekst1		
	push OFFSET tekst1
 	push hout		
	call WriteConsoleA

	push 0
	push OFFSET rout
	push 10
	push OFFSET zrodlo
	push hin
	call ReadConsoleA

	push 0
	push OFFSET rout
	push 10
	push OFFSET zrodlo
	push hin
	call ReadConsoleA

	mov EBX, OFFSET zrodlo
	add EBX, rout
	
	xor EDX, EDX                 
	add EDX, OFFSET zrodlo          
	add EDX, LENGTHOF zrodlo        ; ustawienie rejestru ESI na koncu tablicy "zrodlo"
	sub EDX, 1
	mov ESI, EDX
	
	xor EDX, EDX

	mov EDI, OFFSET przeznaczenie
	
	mov ECX, LENGTHOF zrodlo        ; licznik
	
	petla:
	push ECX
	
	STD                             ; zmniejszanie rejestru ESI 
	lodsb
	CLD                             ; zwiekszanie rejestru EDI
	stosb

	pop ECX
	loop petla

	push 0		  
	push OFFSET rout 	
	push LENGTHOF przeznaczenie		
	push OFFSET przeznaczenie
 	push hout		
	call WriteConsoleA 

	CLD
	mov ECX, LENGTHOF tablica1
	mov ESI, OFFSET tablica1
	mov EDI, OFFSET tablica2

	repe cmpsb                     ; powtorz dla kolejnych znakow, jesli obecne sa rowne sobie, jesli nie przejdz dalej

	mov EAX, LENGTHOF tablica1   
	sub EAX, ECX                   ; odjecie aktualnej pozycji licznika od dlugosci tablicy = uzyskanie indeksu elementu rozniacego sie
	sub EAX, 1
	add EAX, 30h
	mov indeks, EAX

	push 0
	push OFFSET rout 	
	push LENGTHOF tekst2		
	push OFFSET tekst2
 	push hout		
	call WriteConsoleA         ; wypisanie indeksu na konsole


	push 0
	push OFFSET rout 	
	push LENGTHOF indeks		
	push OFFSET indeks
 	push hout		
	call WriteConsoleA         ; wypisanie indeksu na konsole

	call ExitProcess
main endp
END