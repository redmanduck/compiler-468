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
;GEI i $T4 label1
;ADDI num a $T5
;STOREI $T5 num
;STOREI 3 $T6
;GEI i $T6 label2
;ADDI num a $T7
;STOREI $T7 num
;LABEL label2
;LINK
;JUMP label3
;LABEL label1
;LINK
;SUBI num a $T8
;STOREI $T8 num
;LABEL label3
;LINK
;WRITEI num
var i
var a
var num
label main
link 0
move 7 r0
move r0 num
move 2 r1
move r1 i
move 42 r2
move r2 a
move 5 r3
cmpi i r3
jge label1
move num r4
addi a r4
move r4 num
move 3 r5
cmpi i r5
jge label2
move num r6
addi a r6
move r6 num
label label2
link 0
jmp label3
label label1
link 0
move num r0
subi a r0
move r0 num
label label3
link 0
sys writei num

