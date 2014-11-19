;IR code
;LABEL factorial
;LINK
;STOREI 1 $T1
;NEI $P1 $T1 label1
;STOREI 1 $T2
;STOREI $T2 $R
;RET
;JUMP label2
;LABEL label1
;LINK
;PUSH
