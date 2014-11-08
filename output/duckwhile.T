;IR code
;LABEL main
;LINK
;STOREI 10 $T1
;STOREI $T1 i
;LABEL w_enter1464250712
;STOREI 0 $T2
;EQI i $T2 w_exit1464250712
;STOREI 1 $T3
;SUBI i $T3 $T4
;STOREI $T4 i
;JUMP w_enter1464250712
;LABEL w_exit1464250712
;WRITEI i
;RET
;tiny code
var i
label main
move 10 r0
move r0 i
label w_enter1464250712
move 0 r1
cmpi i r1
jeq w_exit1464250712
move 1 r2
move i r3
subi r2 r3
move r3 i
jmp w_enter1464250712
label w_exit1464250712
sys writei i
sys halt
