; Script generated by the pharmML2Nmtran Converter v.1.0.0
; Source	: PharmML 0.3.1
; Target	: NMTRAN 7.2.0
; Model 	: Hansson_2013_oncology_Sunitib_sVEGFR2_combError3
; Dated 	: Wed Feb 11 14:01:24 GMT 2015

$PROBLEM "Hansson_2013_oncology_Sunitib_sVEGFR2_combError3 - generated by MDL2PharmML v.6.0"

$INPUT  ID CYCL TIME DAYS FLAG DVX DV DOS PLA CL EVID
$DATA BIOMARKER_exDATA_sVEGFR2.csv IGNORE=#
$SUBS ADVAN13 TOL=9

$MODEL
COMP (SVEGFR2)

$PK
POP_IC50 = THETA(1)
POP_BM02 = THETA(2)
POP_MRT2 = THETA(3)
POP_HILL2 = THETA(4)
POP_RES_sVEGFR2_PROP = THETA(5)
POP_RES_sVEGFR2_ADD = THETA(6)
POP_IMAX = THETA(7)

eta_BM02 = ETA(1)
eta_IC502 = ETA(2)
eta_MRT_VEGFs = ETA(3)

MU_1 = LOG(POP_BM02);
BM02 = EXP(MU_1 + ETA(1));


IMAX2 =  POP_IMAX ;

MU_2 = LOG(POP_IC50);
IC502 = EXP(MU_2 + ETA(2));


HILL2 =  POP_HILL2 ;

MU_3 = LOG(POP_MRT2);
MRT2 = EXP(MU_3 + ETA(3));


KOUT2 =  (1/MRT2) ;


KIN2 =  (BM02*KOUT2) ;

A_0(1) = BM02

$DES
SVEGFR2 = A(1)

AUC =  (DOS/CL)
EFF2 =  ((IMAX2*(AUC**HILL2))/((IC502**HILL2)+(AUC**HILL2)))
DADT(1) = ((KIN2*(1-EFF2))-(KOUT2*SVEGFR2))

$ERROR
IPRED = LOG(A(1))
W = SQRT(POP_RES_SVEGFR2_PROP**2 + (POP_RES_SVEGFR2_ADD/A(1))**2)
Y = IPRED+W*EPS(1)
IRES = DV - IPRED
IWRES = IRES/W

$THETA
( 0.0 , 1.0 )	;POP_IC50
( 0.0 , 8670.0 )	;POP_BM02
( 0.0 , 554.0 )	;POP_MRT2
( 0.0 , 1.54 )	;POP_HILL2
( 0.0 , 0.12 )	;POP_RES_sVEGFR2_PROP
( 0.0 , 583.0 )	;POP_RES_sVEGFR2_ADD
( 1.0  FIX )	;POP_IMAX

$OMEGA
( 0.0369 )	;OMEGA_BM02
( 0.06 )	;OMEGA_MRT_VEGFs
( 0.189 )	;OMEGA_IC502

$SIGMA
1.0 FIX	;SIGMA_RES_W


$EST METHOD=COND INTER MAXEVALS=9999 PRINT=10 NOABORT
$COV

$TABLE  ID TIME CYCL DAYS FLAG DVX DOS PLA CL EVID PRED IPRED RES IRES WRES IWRES Y DV NOAPPEND NOPRINT FILE=sdtab

$TABLE  ID BM02 IMAX2 IC502 HILL2 MRT2 KOUT2 KIN2 ETA_BM02 ETA_IC502 ETA_MRT_VEGFS NOAPPEND NOPRINT FILE=patab

$TABLE  ID CL NOAPPEND NOPRINT FILE=cotab


