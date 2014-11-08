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
;STOREI 1 $T6
;ADDI count $T6 $T7
;STOREI $T7 count
;STOREF 0.5 $T8
;DIVF num approx $T9
;ADDF approx $T9 $T10
;MULTF $T8 $T10 $T11
;STOREF $T11 newapprox
;SUBF approx newapprox $T12
;STOREF $T12 diff
;STOREF 0.0 $T13
;GEI diff $T13 gelse_1331474958
;GEI diff tolerance gelse_1320017366
;STOREI 1 $T14
;STOREI $T14 enough
;LABEL gelse_1320017366
;JUMP g_leave2025796852
;LABEL gelse_1331474958
;STOREF 0.0 $T15
;SUBF $T15 tolerance $T16
;GEI diff $T16 gelse_1361952026
;STOREI 1 $T17
;STOREI $T17 enough
;LABEL gelse_1361952026
;LABEL g_leave2025796852
;STOREF newapprox approx
;WRITEF approx
;WRITEI count
;RET
;tiny code
var tolerance
var num
var approx
var count
var diff
var enough
var newapprox
label main
move 1.0E-4 r0
move r0 tolerance
move 7.0 r1
move r1 num
move num approx
move 0 r2
move r2 count
move 0.0 r3
move r3 diff
move 0 r4
move r4 enough
move 1 r5
move count r6
addi r5 r6
move r6 count
move 0.5 r7
move num r8
divr approx r8
move approx r9
addr r8 r9
move r7 r10
mulr r9 r10
move r10 newapprox
move approx r11
subr newapprox r11
move r11 diff
move 0.0 r12
cmpi diff r12
jge gelse_1331474958
cmpi diff tolerance
jge gelse_1320017366
move 1 r13
move r13 enough
label gelse_1320017366
jmp g_leave2025796852
label gelse_1331474958
move 0.0 r14
move r14 r15
subr tolerance r15
cmpi diff r15
jge gelse_1361952026
move 1 r16
move r16 enough
label gelse_1361952026
label g_leave2025796852
move newapprox approx
sys writer approx
sys writei count
sys halt
