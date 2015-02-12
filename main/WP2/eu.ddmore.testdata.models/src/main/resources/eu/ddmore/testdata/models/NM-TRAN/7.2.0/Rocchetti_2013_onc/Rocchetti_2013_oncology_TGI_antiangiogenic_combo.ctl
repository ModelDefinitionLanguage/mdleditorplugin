; Script generated by the pharmML2Nmtran Converter v.1.0.0
; Source	: PharmML 0.3.1
; Target	: NMTRAN 7.2.0
; Model 	: Rocchetti_2013_oncology_TGI_antiangiogenic_combo
; Dated 	: Wed Feb 11 14:01:41 GMT 2015

$PROBLEM "Rocchetti_2013_oncology_TGI_antiangiogenic_combo - generated by MDL2PharmML v.6.0"

$INPUT ID TIME DV AMT CMT EVID
$DATA rocchetti2013_data.csv IGNORE=@
$SUBS ADVAN13 TOL=9

$MODEL
COMP (Q0_A)
COMP (Q1_A)
COMP (Q0_B)
COMP (Q1_B)
COMP (Q2_B)
COMP (Z0)
COMP (Z1)
COMP (Z2)
COMP (Z3)

$PK
IC50COMBO = THETA(1)
CV = THETA(2)
K2 = THETA(3)
LAMBDA0 = THETA(4)
LAMBDA1 = THETA(5)
K1 = THETA(6)
IC50 = THETA(7)
W0 = THETA(8)



KA_A =  ((24*LOG(2))/24) ;


KE_A =  (LOG(2)/6.05) ;


FV1_A =  (1/0.119) ;


KA_B =  18.8 ;


KE_B =  49.2 ;


K21 =  10.4 ;


K12 =  141.1 ;


FV1_B =  (1/2.13) ;


EMAX =  1 ;


PSI =  20 ;

A_0(1) = 0
A_0(2) = 0
A_0(3) = 0
A_0(4) = 0
A_0(5) = 0
A_0(6) = W0
A_0(7) = 0
A_0(8) = 0
A_0(9) = 0

$DES
Q0_A = A(1)
Q1_A = A(2)
Q0_B = A(3)
Q1_B = A(4)
Q2_B = A(5)
Z0 = A(6)
Z1 = A(7)
Z2 = A(8)
Z3 = A(9)

C1_A =  (Q1_A*FV1_A)
C1_B =  (Q1_B*FV1_B)
K2INH =  (K2*(1-(C1_A/(C1_A+IC50COMBO))))
WTOT_DES =  (((Z0+Z1)+Z2)+Z3)
DADT(1) = (-(KA_A)*Q0_A)
DADT(2) = ((KA_A*Q0_A)-(KE_A*Q1_A))
DADT(3) = (-(KA_B)*Q0_B)
DADT(4) = (((KA_B*Q0_B)-((K12+KE_B)*Q1_B))+(K21*Q2_B))
DADT(5) = ((K12*Q1_B)-(K21*Q2_B))
DADT(6) = ((((LAMBDA0*Z0)/((1+(((WTOT_DES*LAMBDA0)/LAMBDA1)**PSI))**(1/PSI)))*(1-((EMAX*C1_A)/(C1_A+IC50))))-((K2INH*C1_B)*Z0))
DADT(7) = (((K2INH*C1_B)*Z0)-(K1*Z1))
DADT(8) = ((K1*Z1)-(K1*Z2))
DADT(9) = ((K1*Z2)-(K1*Z3))

$ERROR
WTOT =  (((A(6)+A(7))+A(8))+A(9))

IPRED = WTOT
W = CV*IPRED
Y = IPRED+W*EPS(1)
IRES = DV - IPRED
IWRES = IRES/W

$THETA
( 0.0 , 7.0 )	;IC50COMBO
( 0.0 , 0.1 )	;CV
( 0.221  FIX )	;K2
( 0.14  FIX )	;LAMBDA0
( 0.129  FIX )	;LAMBDA1
( 3.54  FIX )	;K1
( 2.02  FIX )	;IC50
( 0.062  FIX )	;W0

$OMEGA 0 FIX

$SIGMA
1.0 FIX	;SIGMA_RES_W


$EST METHOD=ZERO MAXEVALS=9999 PRINT=10 NOABORT
$COV

$TABLE ID TIME AMT CMT EVID PRED IPRED RES IRES WRES IWRES Y DV NOAPPEND NOPRINT FILE=sdtab

$TABLE ID KA_A KE_A FV1_A KA_B KE_B K21 K12 FV1_B EMAX PSI NOAPPEND NOPRINT FILE=patab

