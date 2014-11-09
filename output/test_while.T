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
;LABEL w_enter1331474958
;STOREI 0 $T4
;EQI i $T4 w_exit1331474958
;STOREF 0.5 $T5
;DIVF num approx $T6
;ADDF approx $T6 $T7
;MULTF $T5 $T7 $T8
;STOREF $T8 newapprox
;STOREF newapprox approx
;STOREI 1 $T9
;SUBI i $T9 $T10
;STOREI $T10 i
;JUMP w_enter1331474958
;LABEL w_exit1331474958
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
move num r3
move r3 approx
label w_enter1331474958
move 0 r4
cmpi i r4
jeq w_exit1331474958
move 0.5 r5
move num r6
divr approx r6
move approx r7
addr r6 r7
move r5 r8
mulr r7 r8
move r8 newapprox
move newapprox r9
move r9 approx
move 1 r10
move i r11
subi r10 r11
move r11 i
jmp w_enter1331474958
label w_exit1331474958
sys writer approx
sys halt
