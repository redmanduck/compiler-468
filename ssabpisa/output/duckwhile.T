;[log] func enter main
;[log] Total IR Lists: 1
;IR code
;LABEL main
;LINK
;STOREI 10 $T1
;STOREI $T1 i
;LABEL label1
;STOREI 0 $T2
;EQI i $T2 label2
;STOREI 1 $T3
;SUBI i $T3 $T4
;STOREI $T4 i
;WRITEI i
;JUMP label1
;LABEL label2
;WRITEI i
;RET
;tiny code
var i
label main
move 10 r0
move r0 i
label label1
move 0 r1
cmpi i r1
jeq label2
move 1 r2
move i r3
subi r2 r3
move r3 i
sys writei i
jmp label1
label label2
sys writei i
sys halt
