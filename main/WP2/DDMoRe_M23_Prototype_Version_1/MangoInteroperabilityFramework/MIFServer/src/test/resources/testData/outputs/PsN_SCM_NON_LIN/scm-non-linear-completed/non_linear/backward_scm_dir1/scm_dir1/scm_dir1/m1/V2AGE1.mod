;Model Desc: MK-0634 PopPK Base Model, 2-compartment, with exponential IIV on CL and V2
;Project Name: MK-0634 Population PK Analysis
;Project ID: NO PROJECT DESCRIPTION
$PROBLEM    RUN# base_run1
$INPUT      ID PERD PROT TIME AMT DV EVID MDV ADDL II FED AGE SEXF WT
            HT RACE BMI BSA=DROP CRCL DOSE
$DATA      
            /net/navtst-nonmem/usr/global/mrogalski/other/scm-non-linear/scm-non-linear/non_linear/backward_scm_dir1/scm_dir1/scm_dir1/m1/V2AGE1_data_1_copy.dta
            IGNORE=@
$SUBROUTINE ADVAN4 TRANS4
$OMEGA  DIAGONAL(5)
 0.0235418  ; [P] INTERIND VAR IN CL
 2.67626E-08  ; [P] INTERIND VAR IN V2
 0.0299828  ; [P] INTERIND VAR IN KA
 0  FIX  ; [P] INTERIND VAR IN Q
 0  FIX  ; [P] INTERIND VAR IN V3
$PK

;;; V2WT-DEFINITION START
V2WT = ( 1 + THETA(19)*(WT - 75.39))
;;; V2WT-DEFINITION END


;;; V2SEXF-DEFINITION START
IF(SEXF.EQ.1) V2SEXF = 1  ; Most common
IF(SEXF.EQ.0) V2SEXF = ( 1 + THETA(18))
;;; V2SEXF-DEFINITION END


;;; V2RACE-DEFINITION START
IF(RACE.EQ.0) V2RACE = 1  ; Most common
IF(RACE.EQ.3) V2RACE = ( 1 + THETA(16))
IF(RACE.EQ.1) V2RACE = ( 1 + THETA(17))
;;; V2RACE-DEFINITION END


;;; V2HT-DEFINITION START
V2HT = ( 1 + THETA(15)*(HT - 165.10))
;;; V2HT-DEFINITION END


;;; V2CRCL-DEFINITION START
V2CRCL = ( 1 + THETA(14)*(CRCL - 60.23))
;;; V2CRCL-DEFINITION END

;;; V2-RELATION START
V2COV=V2CRCL*V2HT*V2RACE*V2SEXF*V2WT
;;; V2-RELATION END


;;; CLSEXF-DEFINITION START
IF(SEXF.EQ.1) CLSEXF = 1  ; Most common
IF(SEXF.EQ.0) CLSEXF = ( 1 + THETA(13))
;;; CLSEXF-DEFINITION END


;;; CLRACE-DEFINITION START
IF(RACE.EQ.0) CLRACE = 1  ; Most common
IF(RACE.EQ.3) CLRACE = ( 1 + THETA(11))
IF(RACE.EQ.1) CLRACE = ( 1 + THETA(12))
;;; CLRACE-DEFINITION END


;;; CLHT-DEFINITION START
CLHT = ( 1 + THETA(10)*(HT - 165.10))
;;; CLHT-DEFINITION END


;;; CLCRCL-DEFINITION START
CLCRCL = ( 1 + THETA(9)*(CRCL - 60.23))
;;; CLCRCL-DEFINITION END


;;; CLBMI-DEFINITION START
CLBMI = ( 1 + THETA(8)*(BMI - 26.73))
;;; CLBMI-DEFINITION END


;;; CLAGE-DEFINITION START
CLAGE = ( 1 + THETA(7)*(AGE - 54.00))
;;; CLAGE-DEFINITION END

;;; CL-RELATION START
CLCOV=CLAGE*CLBMI*CLCRCL*CLHT*CLRACE*CLSEXF
;;; CL-RELATION END


; Insert stratification variable (STRT) variable for VPC
; Typically stratify on dose, dosing regimen, protocol, ....
; For no stratification, set STRT=Constant
  STRT=PROT*1000+DOSE

  TVCL=EXP(THETA(1))  

TVCL = CLCOV*TVCL
  MU_1=LOG(TVCL)
  CL=EXP(MU_1+ETA(1))
  
  TVV2=EXP(THETA(2))

TVV2 = V2COV*TVV2
  MU_2=LOG(TVV2)
  V2=EXP(MU_2+ETA(2))

  TVKA=THETA(3)
  MU_3=LOG(TVKA)
  KA=EXP(MU_3+ETA(3))
  
  TVF1=THETA(4)
  F1=TVF1

  TVQ=EXP(THETA(5))
  MU_4=LOG(TVQ)
  Q=EXP(MU_4+ETA(4))
  
  TVV3=EXP(THETA(6))
  MU_5=LOG(TVV3)
  V3=EXP(MU_5+ETA(5))
  
  S2=V2*624.709/1E6

$ERROR

  IPRED=F
  IRES=DV-IPRED

  DEL=0
  IF (F.EQ.0) DEL=10
  W=F
  IWRES=IRES/(W+DEL)
  Y=F*(1+ERR(1)) 

$THETA  6.14071 ; 1 [CL]
 6.73051 ; 2 [V2]
 (0,0.223064) ; 3 [KA]
 1 FIX ; 4 [F1]
 5.85691 ; 5[Q]
 9.32994 ; 6 [V3]
$THETA  (-0.055,0.00528167,0.029) ; CLAGE1
$THETA  (-0.166,0.0266404,0.185) ; CLBMI1
$THETA  (-0.012,-0.00268573,0.028) ; CLCRCL1
$THETA  (-0.046,0.0163526,0.078) ; CLHT1
$THETA  (-1,-0.229397,5) ; CLRACE1
 (-1,-0.184813,5) ; CLRACE2
$THETA  (-1,0.105573,5) ; CLSEXF1
$THETA  (-0.012,0.00206848,0.028) ; V2CRCL1
$THETA  (-0.046,0.00725872,0.078) ; V2HT1
$THETA  (-1,0.660258,5) ; V2RACE1
 (-1,-0.461226,5) ; V2RACE2
$THETA  (-1,2.00608,5) ; V2SEXF1
$THETA  (-0.041,-0.0269235,0.074) ; V2WT1
$SIGMA  0.120538  ; [P] Variance for proportional error
$ESTIMATION METHOD=1 INTERACTION PRINT=1 NOABORT MAXEVAL=9999
;$COV PRINT=E

;Tables for Xpose Diagnostic Plots

;Standard Table file containing ID, IDV, DV, PRED, IPRED,

; STRT, WRES, IWRES, RES, IRES, etc.
$TABLE      ID TIME IPRED EVID MDV CWRES IWRES STRT NOPRINT ONEHEADER
            FILE=/net/navtst-nonmem/usr/global/mrogalski/other/scm-non-linear/scm-non-linear/non_linear/backward_scm_dir1/scm_dir1/scm_dir1//m1/V2AGE1.mod.sdtab1
;Parameter table containing model parameters - THETAS, ETAS, EPS
$TABLE      ID CL V2 KA F1 Q V3 ETA1 ETA2 ETA3 ETA4 NOPRINT ONEHEADER
            FILE=/net/navtst-nonmem/usr/global/mrogalski/other/scm-non-linear/scm-non-linear/non_linear/backward_scm_dir1/scm_dir1/scm_dir1//m1/V2AGE1.mod.patab1
;Continuous Covariate table - WT, AGE,...

;$TABLE ID WT HT AGE BMI BSA CRCL NOPRINT ONEHEADER FILE=cotab1

;Categorical covariate table - SEX, RACE,.....

;$TABLE ID PROT SEXF RACE FED NOPRINT ONEHEADER FILE=catab1

