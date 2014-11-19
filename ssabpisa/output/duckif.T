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
;JUMP label2
;LABEL label1
;LINK
;SUBI num a $T6
;STOREI $T6 num
;LABEL label2
;LINK
;WRITEI num
var i
var a
var num
push r0
push r1
push r2
push r3
push r4
push r5
push r6
push r7
push r8
push r9
push r10
push r11
push r12
push r13
push r14
jsr main
sys halt
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
jmp label2

label label1
link 0
move num r0
subi a r0
move r0 num
label label2
link 0
sys writei num

