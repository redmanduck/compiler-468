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
;WRITEF $L2
;WRITEF $L3
;STOREI 0 $T4
;STOREI $T4 $R
;RET
var gg
str elop "Please enter an integer number: "
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
label add
link 1
move $17 r0
addr $18 r0
move r0 $-1
move $-1 r1
move r1 $19
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
jsr add
pop r14
pop r13
pop r12
pop r11
pop r10
pop r9
pop r8
pop r7
pop r6
pop r5
pop r4
pop r3
pop r2
pop r1
pop r0
pop
pop
pop r2
move r2 $-3
sys writer $-3
sys writer $-2
move 0 r3
move r3 $17
unlnk
ret

