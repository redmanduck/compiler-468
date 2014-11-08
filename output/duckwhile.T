;IR code
;LABEL main
;LINK
;STOREI 10 $T1
;STOREI $T1 i
;LABEL w_enter32355719
;STOREI 0 $T2
;EQI i $T2 w_exit32355719
;STOREI 1 $T3
;SUBI i $T3 $T4
;STOREI $T4 i
;JUMP w_enter32355719
;LABEL w_exit32355719
;WRITEI i
;RET
;tiny code
var i
label main
move 10 r0
move r0 i
label w_enter32355719
move 0 r1
cmpi i r1
jeq w_exit32355719
move 1 r2
move i r3
subi r2 r3
move r3 i
jmp w_enter32355719
label w_exit32355719
sys writei i
sys halt
