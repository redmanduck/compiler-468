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
;STOREF 0.5 $T4
;DIVF num approx $T5
;ADDF approx $T5 $T6
;MULTF $T4 $T6 $T7
;STOREF $T7 newapprox
;STOREF newapprox approx
;STOREI 1 $T8
;SUBI i $T8 $T9
;STOREI $T9 i
;WRITEF approx
;RET
;tiny code
var num
var i
var j
var approx
var newapprox
label main
move 7.0 r0
move r0 num
move 100 r1
move r1 i
move 1 r2
move r2 j
move num approx
move 0.5 r3
move num r4
divr approx r4
move approx r5
addr r4 r5
move r3 r6
mulr r5 r6
move r6 newapprox
move newapprox approx
move 1 r7
move i r8
subi r7 r8
move r8 i
sys writer approx
sys halt
