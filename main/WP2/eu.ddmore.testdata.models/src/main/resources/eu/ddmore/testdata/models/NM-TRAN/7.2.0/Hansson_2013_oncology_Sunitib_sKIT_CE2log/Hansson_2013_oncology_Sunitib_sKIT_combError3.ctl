; Script generated by the pharmML2Nmtran Converter v.1.0.0
; Source	: PharmML 0.3.1
; Target	: NMTRAN 7.2.0
; Model 	: Hansson_2013_oncology_Sunitib_sKIT_combError3
; Dated 	: Mon Feb 09 15:21:18 GMT 2015

$PROBLEM "Hansson_2013_oncology_Sunitib_sKIT_combError3 - generated by MDL2PharmML v.6.0"

$INPUT ID CYCL TIME DAYS FLAG DVX DV DOS PLA CL EVID
$DATA BIOMARKER_exDATA_sKIT.csv IGNORE=#
$SUBS ADVAN13 TOL=9

$MODEL
COMP (SKIT)

$PK
POP_IC50 = THETA(1)
POP_BM0S = THETA(2)
POP_MRTS = THETA(3)
POP_TVSLP = THETA(4)
POP_RES_sKIT_PROP = THETA(5)
POP_IMAX = THETA(6)
POP_RES_sKIT_ADD = THETA(7)

eta_BM0S = ETA(1)
eta_IC50S = ETA(2)
eta_MRT_sKIT = ETA(3)
eta_TVSLPS = ETA(4)

MU_1 = LOG(POP_BM0S);
BM0S = EXP(MU_1 + ETA(1));


IMAXS =  POP_IMAX ;

MU_2 = LOG(POP_IC50);
IC50S = EXP(MU_2 + ETA(2));

MU_3 = LOG(POP_MRTS);
MRTS = EXP(MU_3 + ETA(3));


TVSLPS =  (POP_TVSLP/1000) ;

MU_4 = LOG(TVSLPS);
DPSLPS = EXP(MU_4 + ETA(4));


KOUTS =  (1/MRTS) ;

A_0(1) = BM0S

$DES
SKIT = A(1)

AUC =  (DOS/CL)
DPS =  (BM0S*(1+(DPSLPS*TIME)))
KINS =  (DPS*KOUTS)
EFFS =  ((IMAXS*AUC)/(IC50S+AUC))
DADT(1) = ((KINS*(1-EFFS))-(KOUTS*SKIT))

$ERROR
IPRED = LOG(A(1))
W = SQRT(POP_RES_SKIT_PROP**2 + (POP_RES_SKIT_ADD/A(1))**2)
Y = IPRED+W*EPS(1)
IRES = DV - IPRED
IWRES = IRES/W

$THETA
( 0.0 , 1.0 )	;POP_IC50
( 0.0 , 39200.0 )	;POP_BM0S
( 0.0 , 2430.0 )	;POP_MRTS
( -0.06 , 0.035 )	;POP_TVSLP
( 0.0 , 0.224 )	;POP_RES_sKIT_PROP
( 1.0 )	;POP_IMAX
( 0.0 )	;POP_RES_sKIT_ADD

$OMEGA
( 0.254 )	;OMEGA_BM0S
( 0.0753 )	;OMEGA_MRT_sKIT
( 5.77 )	;OMEGA_IC50S
( 3.01 )	;OMEGA_TVSLPS

$SIGMA
1.0 FIX	;SIGMA_RES_W


$EST METHOD=COND INTER MAXEVALS=9999 PRINT=10 NOABORT
$COV

$TABLE ID TIME CYCL DAYS FLAG DVX DOS PLA CL EVID PRED IPRED RES IRES WRES IWRES Y DV NOAPPEND NOPRINT FILE=sdtab

$TABLE ID BM0S IMAXS IC50S MRTS TVSLPS DPSLPS KOUTS ETA_BM0S ETA_IC50S ETA_MRT_SKIT ETA_TVSLPS NOAPPEND NOPRINT FILE=patab

$TABLE ID CL NOAPPEND NOPRINT FILE=cotab


