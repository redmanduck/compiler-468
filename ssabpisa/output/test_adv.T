;IR code
;LABEL main
;LINK
;STOREF 1.0E-4 $T1
;STOREF $T1 tolerance
;STOREF 7.0 $T2
;STOREF $T2 num
;STOREF num approx
;STOREI 0 $T3
;STOREI $T3 count
;STOREF 0.0 $T4
;STOREF $T4 diff
;STOREI 0 $T5
;STOREI $T5 enough
;LABEL label1
;LINK
;STOREI 1 $T6
;EQI enough $T6 label2
;STOREI 1 $T7
;ADDI count $T7 $T8
;STOREI $T8 count
;STOREF 0.5 $T9
;DIVF num approx $T10
;ADDF approx $T10 $T11
;MULTF $T9 $T11 $T12
;STOREF $T12 newapprox
;SUBF approx newapprox $T13
;STOREF $T13 diff
;STOREF 0.0 $T14
;LEF diff $T14 label3
;GEF diff tolerance label4
;STOREI 1 $T15
;STOREI $T15 enough
;LABEL label4
;LINK
;JUMP label5
;LABEL label3
;LINK
;STOREF 0.0 $T16
;SUBF $T16 tolerance $T17
;LEF diff $T17 label6
;STOREI 1 $T18
;STOREI $T18 enough
;LABEL label6
;LINK
;LABEL label5
;LINK
;STOREF newapprox approx
;JUMP label1
;LABEL label2
;LINK
;WRITEF approx
;WRITEI count
var count
var enough
var newapprox
var approx
var num
var tolerance
var diff
label main
link 0
move 1.0E-4 r0
move r0 tolerance
move 7.0 r1
move r1 num
move num r2
move r2 approx
move 0 r3
move r3 count
move 0.0 r4
move r4 diff
move 0 r5
move r5 enough
label label1
link 0
move 1 r0
cmpi enough r0
jeq label2
move 1 r1
move count r2
addi r1 r2
move r2 count
move 0.5 r3
move num r4
divr approx r4
move approx r5
addr r4 r5
move r3 r6
mulr r5 r6
move r6 newapprox
move approx r7
subr newapprox r7
move r7 diff
move 0.0 r8
cmpr diff r8
jle label3
move tolerance r9
cmpr diff r9
jge label4
move 1 r10
move r10 enough
label label4
link 0
jmp label5
label label3
link 0
move 0.0 r0
move r0 r1
subr tolerance r1
cmpr diff r1
jle label6
move 1 r2
move r2 enough
label label6
link 0
label label5
link 0
move newapprox r0
move r0 approx
jmp label1
label label2
link 0
sys writer approx
sys writei count

