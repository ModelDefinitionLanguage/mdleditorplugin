; Script generated by the pharmML2Nmtran Converter v.1.0.0
; Source	: PharmML 0.3.1
; Target	: NMTRAN 7.2.0
; Model 	: DelBene_2009_oncology_in_vitro_ETA
; Dated 	: Wed Feb 11 14:01:16 GMT 2015

$PROBLEM "DelBene_2009_oncology_in_vitro_ETA - generated by MDL2PharmML v.6.0"

$INPUT ID TIME DV CONC EVID
$DATA delbene2009_data.csv IGNORE=@
$SUBS ADVAN13 TOL=9

$MODEL
COMP (NP)
COMP (N1)
COMP (N2)
COMP (N3)

$PK
POP_LAMBDA0 = THETA(1)
K1 = THETA(2)
K2 = THETA(3)
N0 = THETA(4)
CV = THETA(5)

eta_OMEGA_LAMBDA0 = ETA(1)

MU_1 = LOG(POP_LAMBDA0);
LAMBDA0 = EXP(MU_1 + ETA(1));

A_0(1) = N0
A_0(2) = 0
A_0(3) = 0
A_0(4) = 0

$DES
NP = A(1)
N1 = A(2)
N2 = A(3)
N3 = A(4)

NT_DES =  (((NP+N1)+N2)+N3)
DADT(1) = ((LAMBDA0*NP)-((K2*CONC)*NP))
DADT(2) = (((K2*CONC)*NP)-(K1*N1))
DADT(3) = ((K1*N1)-(K1*N2))
DADT(4) = ((K1*N2)-(K1*N3))

$ERROR
NT =  (((A(1)+A(2))+A(3))+A(4))

IPRED = NT
W = CV*IPRED
Y = IPRED+W*EPS(1)
IRES = DV - IPRED
IWRES = IRES/W

$THETA
( 0.0 , 0.1 )	;POP_LAMBDA0
( 0.0 , 0.1 )	;K1
( 0.0 , 0.1 )	;K2
( 0.0 , 1000.0 )	;N0
( 0.0 , 0.1 )	;CV

$OMEGA
( 0.0  FIX )	;OMEGA_LAMBDA0
( 1.0  FIX )	;SIGMA_RES_W

$EST METHOD=COND MAXEVALS=9999 PRINT=10 NOABORT
$COV

$TABLE ID TIME CONC EVID PRED IPRED RES IRES WRES IWRES Y DV NOAPPEND NOPRINT FILE=sdtab

$TABLE ID LAMBDA0 ETA_OMEGA_LAMBDA0 NOAPPEND NOPRINT FILE=patab

$TABLE ID CONC NOAPPEND NOPRINT FILE=cotab


