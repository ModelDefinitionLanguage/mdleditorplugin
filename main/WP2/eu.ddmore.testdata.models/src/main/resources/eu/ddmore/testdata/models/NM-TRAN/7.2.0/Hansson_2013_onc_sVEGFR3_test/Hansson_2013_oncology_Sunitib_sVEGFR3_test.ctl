; Script generated by the pharmML2Nmtran Converter v.1.0.0
; Source	: PharmML 0.3.1
; Target	: NMTRAN 7.2.0
; Model 	: Hansson_2013_oncology_Sunitib_sVEGFR3_test
; Dated 	: Wed Feb 11 14:01:31 GMT 2015

$PROBLEM "Hansson_2013_oncology_Sunitib_sVEGFR3_test - generated by MDL2PharmML v.6.0"

$INPUT ID CYCL TIME DAYS FLAG DVX DV DOS PLA CL EVID
$DATA BIOMARKER_exDATA_sVEGFR3.csv IGNORE=#
$SUBS ADVAN13 TOL=9

$MODEL
COMP (SVEGFR3)

$PK
POP_IC50 = THETA(1)
POP_BM03 = THETA(2)
POP_MRT3 = THETA(3)
POP_RES_sVEGFR3_ADD = THETA(4)
POP_IMAX = THETA(5)

eta_BM03 = ETA(1)
eta_IC503 = ETA(2)
eta_MRT_VEGFs = ETA(3)

MU_1 = LOG(POP_BM03);
BM03 = EXP(MU_1 + ETA(1));


IMAX3 =  POP_IMAX ;


IMAXS =  POP_IMAX ;

MU_2 = LOG(POP_IC50);
IC503 = EXP(MU_2 + ETA(2));

MU_3 = LOG(POP_MRT3);
MRT3 = EXP(MU_3 + ETA(3));


KOUT3 =  (1/MRT3) ;


KIN3 =  (BM03*KOUT3) ;

A_0(1) = BM03

$DES
SVEGFR3 = A(1)

AUC =  (DOS/CL)
EFF3 =  ((IMAX3*AUC)/(IC503+AUC))
DADT(1) = ((KIN3*(1-EFF3))-(KOUT3*SVEGFR3))

$ERROR
IPRED = LOGSVEGFR3
W = POP_RES_SVEGFR3_ADD
Y = IPRED+W*EPS(1)
IRES = DV - IPRED
IWRES = IRES/W

$THETA
( 0.0 , 1.0 )	;POP_IC50
( 0.0 , 63900.0 )	;POP_BM03
( 0.0 , 401.0 )	;POP_MRT3
( 0.0 , 0.22 )	;POP_RES_sVEGFR3_ADD
( 1.0  FIX )	;POP_IMAX

$OMEGA
( 0.186 )	;OMEGA_BM03
( 0.06 )	;OMEGA_MRT_VEGFs
( 0.398 )	;OMEGA_IC503

$SIGMA
1.0 FIX	;SIGMA_RES_W


$EST METHOD=COND MAXEVALS=9999 PRINT=10 NOABORT
$COV

$TABLE ID TIME CYCL DAYS FLAG DVX DOS PLA CL EVID PRED IPRED RES IRES WRES IWRES Y DV NOAPPEND NOPRINT FILE=sdtab

$TABLE ID BM03 IMAX3 IMAXS IC503 MRT3 KOUT3 KIN3 ETA_BM03 ETA_IC503 ETA_MRT_VEGFS NOAPPEND NOPRINT FILE=patab

$TABLE ID CL NOAPPEND NOPRINT FILE=cotab


