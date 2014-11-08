;IR code
;LABEL main
;LINK
;STOREI 7 $T1
;STOREI $T1 num
;STOREI 2 $T2
;STOREI $T2 i
;STOREI 42 $T3
;STOREI $T3 a
;STOREI 5 $T4
;GEI i $T4 gelse_1395443311
;ADDI num a $T5
;STOREI $T5 num
;JUMP g_leave546819090
;LABEL gelse_1395443311
;SUBI num a $T6
;STOREI $T6 num
;LABEL g_leave546819090
;WRITEI num
;RET
;tiny code
var num
var i
var a
label main
move 7 r0
move r0 num
move 2 r1
move r1 i
move 42 r2
move r2 a
move 5 r3
cmpi i r3
jge gelse_1395443311
move num r4
addi a r4
move r4 num
jmp g_leave546819090
label gelse_1395443311
move num r5
subi a r5
move r5 num
label g_leave546819090
sys writei num
sys halt
