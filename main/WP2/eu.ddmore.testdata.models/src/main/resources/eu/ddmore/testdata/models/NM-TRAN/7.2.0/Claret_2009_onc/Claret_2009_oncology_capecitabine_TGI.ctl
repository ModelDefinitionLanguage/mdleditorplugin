; Script generated by the pharmML2Nmtran Converter v.1.0.0
; Source	: PharmML 0.3.1
; Target	: NMTRAN 7.2.0
; Model 	: Claret_2009_oncology_capecitabine_TGI
; Dated 	: Wed Feb 11 14:01:11 GMT 2015

$PROBLEM "Claret_2009_oncology_capecitabine_TGI - generated by MDL2PharmML v.6.0"

$INPUT ID TIME AMT II ADDL CMT DV CYCLE MDV
$DATA claret_simuldata.csv IGNORE=@
$SUBS ADVAN13 TOL=9

$MODEL
COMP (CAPE)
COMP (TS)

$PK
POP_TS0 = THETA(1)
POP_KL = THETA(2)
POP_KD = THETA(3)
POP_KE = THETA(4)
POP_LAMBDA = THETA(5)
AERR = THETA(6)
PERR = THETA(7)

eta_TS0 = ETA(1)
eta_KL = ETA(2)
eta_KD = ETA(3)
eta_KE = ETA(4)
eta_LAMBDA = ETA(5)


POP_KE_h =  (POP_KE*168) ;

MU_1 = LOG(POP_TS0);
TS0 = EXP(MU_1 + ETA(1));

MU_2 = LOG(POP_KL);
KL = EXP(MU_2 + ETA(2));

MU_3 = LOG(POP_KD);
KD = EXP(MU_3 + ETA(3));

MU_4 = LOG(POP_KE_H);
KE = EXP(MU_4 + ETA(4));

MU_5 = LOG(POP_LAMBDA);
LAMBDA = EXP(MU_5 + ETA(5));

A_0(1) = 0
A_0(2) = TS0

$DES
CAPE = A(1)
TS = A(2)

RESISTANCE =  EXP((-(LAMBDA)*T))
DADT(1) = (-(KE)*CAPE)
DADT(2) = ((KL*TS)-(((KD*RESISTANCE)*CAPE)*TS))

$ERROR
IPRED = A(2)
W = SQRT((AERR*AERR)+ (PERR*PERR*IPRED*IPRED))
Y = IPRED+W*EPS(1)
IRES = DV - IPRED
IWRES = IRES/W

$THETA
( 0.0 , 71.0 )	;POP_TS0
( 0.0 , 0.021 )	;POP_KL
( 0.0 , 0.025 )	;POP_KD
( 0.0 , 0.5 )	;POP_KE
( 0.0 , 0.053 )	;POP_LAMBDA
( 0.0 , 12.0 )	;AERR
( 0.0  FIX )	;PERR

$OMEGA
( 0.4 )	;OMEGA_TS0
( 0.499 )	;OMEGA_KL
( 0.388 )	;OMEGA_KD
( 0.04 )	;OMEGA_KE
( 1.26 )	;OMEGA_LAMBDA

$SIGMA
1.0 FIX	;SIGMA_RES_TS


$EST METHOD=COND INTER MAXEVALS=9999 PRINT=10 NOABORT
$COV

$TABLE ID TIME AMT II ADDL CMT CYCLE MDV PRED IPRED RES IRES WRES IWRES Y DV NOAPPEND NOPRINT FILE=sdtab

$TABLE ID POP_KE_h TS0 KL KD KE LAMBDA ETA_TS0 ETA_KL ETA_KD ETA_KE ETA_LAMBDA NOAPPEND NOPRINT FILE=patab

