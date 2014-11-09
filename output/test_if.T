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
;GEI i $T4 gelse_2026228835
;ADDI num a $T5
;STOREI $T5 num
;STOREI 3 $T6
;GEI i $T6 gelse_32355719
;ADDI num a $T7
;STOREI $T7 num
;LABEL gelse_2026228835
;JUMP g_leave489206542
;LABEL gelse_32355719
;SUBI num a $T8
;STOREI $T8 num
;LABEL g_leave489206542
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
jge gelse_2026228835
move num r4
addi a r4
move r4 num
move 3 r5
cmpi i r5
jge gelse_32355719
move num r6
addi a r6
move r6 num
label gelse_2026228835
jmp g_leave489206542
label gelse_32355719
move num r7
subi a r7
move r7 num
label g_leave489206542
sys writei num
sys halt
