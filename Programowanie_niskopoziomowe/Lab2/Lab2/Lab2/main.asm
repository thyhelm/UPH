.386
.model flat, stdcall

stala equ 10
STD_OUTPUT_HANDLE equ -11

GetStdHandle PROTO :DWORD
WriteConsoleA PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
ExitProcess PROTO :DWORD

.data
	zmiennaA dd 7
	zmiennaB dd 4
	zmiennaC dd 6
	wynik dd 0

	cyfra dd 0
	ilosc dd $ - cyfra
	rout dd 0
	hout dd 0

.code
main proc
	mov eax, 5
	mov ebx, zmiennaA
	mul ebx
	mov ecx, eax
	mov eax, 4
	mov ebx, zmiennaB
	mul ebx
	add ecx, eax
	mov eax, zmiennaC
	sub ecx, eax
	mov eax, ecx
	mov wynik, eax
	mov esi, OFFSET wynik

	push STD_OUTPUT_HANDLE
	call GetStdHandle
	mov hout, eax
	push 0
	push OFFSET rout
	push ilosc
	push OFFSET cyfra
	push hout
	call WriteConsoleA
	cmp cyfra, 0
	call ExitProcess
main endp

END