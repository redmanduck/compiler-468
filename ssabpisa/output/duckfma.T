;IR code
;LABEL add
;LINK
;ADDF $P1 $P2 $T1
;STOREF $T1 $L1
;STOREF $L1 $T2
;STOREF $T2 $R
;RET
;LABEL main
;LINK
;STOREF 1.0 $T1
;STOREF $T1 $L1
;STOREF 2.0 $T2
;STOREF $T2 $L3
;PUSH
;PUSH $L3
;PUSH $L1
;JSR add
;POP
;POP
;POP $T3
;STOREF $T3 $L2
;STOREI 0 $T4
;STOREI $T4 $R
;RET
var gg
str elop "Please enter an integer number: "
label add
link 1
move $17 r0
addr $18 r0
move r0 $-1
move $-1 r1
move $r1 R$19
unlnk
ret
label main
link 3
move 1.0 r0
move r0 $-1
move 2.0 r1
move r1 $-2
push
push $-2
push $-1
jsr add
pop
pop
pop r2
move r2 $-3
move 0 r3
move $r3 R$17
unlnk
ret

