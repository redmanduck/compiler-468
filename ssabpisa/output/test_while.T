;IR code
;LABEL main
;LINK
;STOREF 7.0 $T1
;STOREF $T1 num
;STOREI 100 $T2
;STOREI $T2 i
;STOREI 1 $T3
;STOREI $T3 j
;STOREF num approx
;LABEL label1
;LINK
;STOREI 0 $T4
;EQI i $T4 label2
;STOREF 0.5 $T5
;DIVF num approx $T6
;ADDF approx $T6 $T7
;MULTF $T5 $T7 $T8
;STOREF $T8 newapprox
;STOREF newapprox approx
;STOREI 1 $T9
;SUBI i $T9 $T10
;STOREI $T10 i
;JUMP label1
;LABEL label2
;LINK
;WRITEF approx
var i
var j
var newapprox
var approx
var num
label main
link 0
move 7.0 r0
move r0 num
move 100 r1
move r1 i
move 1 r2
move r2 j
move num r3
move r3 approx
label label1
link 0
move 0 r0
cmpi i r0
jeq label2
move 0.5 r1
move num r2
divr approx r2
move approx r3
addr r2 r3
move r1 r4
mulr r3 r4
move r4 newapprox
move newapprox r5
move r5 approx
move 1 r6
move i r7
subi r6 r7
move r7 i
jmp label1
label label2
link 0
sys writer approx

