;IR code
;LABEL main
;LINK
;STOREI 1 $T1
;STOREI $T1 a
;STOREI 1 $T2
;STOREI $T2 b
;LABEL label1
;LINK
;STOREI 80 $T3
;GTI a $T3 label2
;STOREI 1 $T4
;NEI b $T4 label3
;STOREI 1 $T5
;MULTI $T5 a $T6
;STOREI $T6 g
;WRITEI g
;WRITES newline
;LABEL label3
;LINK
;STOREI 2 $T7
;NEI b $T7 label4
;STOREI 2 $T8
;MULTI $T8 a $T9
;STOREI $T9 p
;WRITEI p
;WRITES newline
;LABEL label4
;LINK
;STOREI 3 $T10
;NEI b $T10 label5
;STOREI 3 $T11
;MULTI $T11 a $T12
;STOREI $T12 k
;WRITEI k
;WRITES newline
;JUMP label6
;LABEL label5
;LINK
;STOREI 4 $T13
;MULTI $T13 a $T14
;STOREI $T14 u
;WRITEI u
;WRITES newline
;LABEL label6
;LINK
;STOREI 1 $T15
;ADDI b $T15 $T16
;STOREI $T16 b
;STOREI 20 $T17
;ADDI a $T17 $T18
;STOREI $T18 a
;JUMP label1
;LABEL label2
;LINK
var a
var b
var k
var g
var p
var u
str newline "\n"
label main
link 0
move 1 r0
move r0 a
move 1 r1
move r1 b
label label1
link 0
move 80 r0
cmpi a r0
jgt label2
move 1 r1
cmpi b r1
jne label3
move 1 r2
move r2 r3
muli a r3
move r3 g
sys writei g
sys writes newline
label label3
link 0
move 2 r0
cmpi b r0
jne label4
move 2 r1
move r1 r2
muli a r2
move r2 p
sys writei p
sys writes newline
label label4
link 0
move 3 r0
cmpi b r0
jne label5
move 3 r1
move r1 r2
muli a r2
move r2 k
sys writei k
sys writes newline
jmp label6
label label5
link 0
move 4 r0
move r0 r1
muli a r1
move r1 u
sys writei u
sys writes newline
label label6
link 0
move 1 r0
move b r1
addi r0 r1
move r1 b
move 20 r2
move a r3
addi r2 r3
move r3 a
jmp label1
label label2
link 0

