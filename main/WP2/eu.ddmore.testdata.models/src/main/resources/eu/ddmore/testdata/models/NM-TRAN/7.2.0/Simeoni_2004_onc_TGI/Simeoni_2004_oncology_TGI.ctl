; Script generated by the pharmML2Nmtran Converter v.1.0.0
; Source	: PharmML 0.3.1
; Target	: NMTRAN 7.2.0
; Model 	: Simeoni_2004_oncology_TGI
; Dated 	: Wed Feb 11 15:32:06 GMT 2015

$PROBLEM "Simeoni_2004_oncology_TGI - generated by MDL2PharmML v.6.0"

$INPUT ID TIME DV AMT EVID CMT
$DATA simeoni2004_data.csv IGNORE=@
$SUBS ADVAN13 TOL=9

$MODEL
COMP (Q1)
COMP (Q2)
COMP (X1)
COMP (X2)
COMP (X3)
COMP (X4)

$PK
LAMBDA0 = THETA(1)
LAMBDA1 = THETA(2)
K1 = THETA(3)
K2 = THETA(4)
W0 = THETA(5)
CV = THETA(6)



K10 =  (0.868*24) ;


K12 =  (0.0060*24) ;


K21 =  (0.0838*24) ;


V1 =  0.81 ;


PSI =  20 ;

A_0(1) = 0
A_0(2) = 0
A_0(3) = W0
A_0(4) = 0
A_0(5) = 0
A_0(6) = 0

$DES
Q1 = A(1)
Q2 = A(2)
X1 = A(3)
X2 = A(4)
X3 = A(5)
X4 = A(6)

C =  (Q1/V1)
WTOT_DES =  (((X1+X2)+X3)+X4)
DADT(1) = ((K21*Q2)-((K10+K12)*Q1))
DADT(2) = ((K12*Q1)-(K21*Q2))
DADT(3) = (((LAMBDA0*X1)/((1+(((WTOT_DES*LAMBDA0)/LAMBDA1)**PSI))**(1/PSI)))-((K2*C)*X1))
DADT(4) = (((K2*C)*X1)-(K1*X2))
DADT(5) = ((K1*X2)-(K1*X3))
DADT(6) = ((K1*X3)-(K1*X4))

$ERROR
WTOT =  (((A(3)+A(4))+A(5))+A(6))

IPRED = WTOT
W = CV*IPRED
Y = IPRED+W*EPS(1)
IRES = DV - IPRED
IWRES = IRES/W

$THETA
( 0.0 , 0.3 )	;LAMBDA0
( 0.0 , 0.3 )	;LAMBDA1
( 0.0 , 0.3 )	;K1
( 0.0 , 0.5 )	;K2
( 0.0 , 0.02 )	;W0
( 0.0 , 0.1 )	;CV

$OMEGA 0 FIX

$SIGMA
1.0 FIX	;SIGMA_RES_W


$EST METHOD=COND MAXEVALS=9999 PRINT=10 NOABORT
$COV

$TABLE ID TIME AMT EVID CMT PRED IPRED RES IRES WRES IWRES Y DV NOAPPEND NOPRINT FILE=sdtab

$TABLE ID K10 K12 K21 V1 PSI NOAPPEND NOPRINT FILE=patab


