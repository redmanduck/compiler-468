;IR code
;LABEL main
;LINK
;STOREI 10 $T1
;STOREI $T1 i
;LABEL label1
;LINK
;STOREI 0 $T2
;EQI i $T2 label2
;STOREI 1 $T3
;SUBI i $T3 $T4
;STOREI $T4 i
;WRITEI i
;JUMP label1
;LABEL label2
;LINK
;WRITEI i
var i
var j
label main
link 0
move 10 r0
move r0 i
label label1
link 0
move 0 r0
cmpi i r0
jeq label2
move 1 r1
move i r2
subi r1 r2
move r2 i
sys writei i
jmp label1
label label2
link 0
sys writei i

