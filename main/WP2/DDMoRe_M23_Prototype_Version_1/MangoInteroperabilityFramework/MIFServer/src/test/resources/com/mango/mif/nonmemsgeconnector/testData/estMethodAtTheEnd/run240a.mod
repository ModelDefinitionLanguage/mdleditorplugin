$PROB  Dacogen-PK-model: 3-CMT-model-lin-dist second CL on V2

$INPUT C ID TSLD=TIME AMT RATE DURN CONC DV MDV EVID STUD
       SUBJ=DROP DAY CYCL TP=DROP SEX RACE=DROP AGE=DROP WT BSA BMI
       CRCL=DROP CRCN CREA=DROP BILI=DROP DOSE DIS=DROP OCC=DROP
       NTIM=DROP CODE=DROP TAFD


$DATA  DacogenData.csv IGNORE=C

$SUB   ADVAN6 TRANS1 TOL=4

$MODEL 
       COMP=(CENTRAL,DEFDOSE,DEFOBS)
       COMP=(PERIF)
       COMP=(PERIF2)

$PK 
;***PK PARAMETERS***

 TVCL  = THETA(1)
 CL    = TVCL * EXP(ETA(1))
 
 TV1   = THETA(2) 
 V1    = TV1 * EXP(ETA(2))
 
 TKM   = THETA(3)
 KM    = TKM * EXP(ETA(3))

 TK21  = THETA(4) 
 K21   = TK21 

 TK13  = THETA(5) 
 K13   = TK13 

 TK31  = THETA(6) 
 K31   = TK31

 TK20  = THETA(7)
 K20   = TK20

 DUR1=DURN
 IF(DURN.LT.0.AND.STUD.NE.18)DUR1=1
 IF(DURN.LT.0.AND.STUD.EQ.18)DUR1=3
 D1 = DUR1
 IF(DURN.LT.0) D1  = DUR1  * EXP(ETA(4)) 

 S1  = V1/1000
 K10 = CL/V1

$DES
DADT(1) = -K10*A(1) - (KM*A(1)) + K21*A(2) - K13*A(1) + K31*A(3)
DADT(2) =             (KM*A(1)) - K21*A(2) - K20*A(2)
DADT(3) =                                    K13*A(1) - K31*A(3)


$ERROR CALLFL=0
 IPRE = -5
 W1=A(1)
 W2=A(2)
 IF (F.GT.0) IPRE=LOG(F)   ;if you work in log domain
 IRES = DV - IPRE          ;calculate individual residuals
 Y    = IPRE + EPS(1)      ;if you work in log domain

$THETA
(0.01 125  1000)                    ;1 CL
(0.01 18    500)                    ;2 V1
(1 7.3       20)                    ;3 KM
0.0182 FIX; (0.01 0.19  500)        ;4 K21
(0.01 3.1  1000)                    ;5 K13
(0.01 3.1  1000)                    ;6 K31
10 FIX                              ;7 K20

$OMEGA
0.01    ;IIV.CL
0.01    ;IIV.V1
0.01    ;IIV.KM
;0.01    ;IIV.K21
;0.01    ;IIV.K13
0.01    ;IIV.RATE


$SIGMA 
0.1

$EST MAX=9990 POSTHOC NSIGDIG=3 PRINT=5 NOABORT  MSF=msf0 METHOD=1
$COV PRINT=E MATRIX=S

$TABLE STUD ID TIME TSLD DAY DOSE AMT IPRE W1 ONEHEADER
       NOPRINT FILE=sdtab240a.tab
$TABLE STUD ID TIME TSLD DAY DOSE AMT DV CL V1 KM K21 K10 K20 WT BSA W1 W2
       SEX CRCN ETA1 ETA2 ETA3 ETA4 ONEHEADER
       NOPRINT FILE=patab240ap.tab
