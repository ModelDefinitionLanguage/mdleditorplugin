;Model Desc: MK-0634 PopPK Base Model, 2-compartment, with exponential IIV on CL and V2
;Project Name: MK-0634 Population PK Analysis
;Project ID: NO PROJECT DESCRIPTION
$PROBLEM    RUN# base_run1
$INPUT      ID PERD PROT TIME AMT DV EVID MDV ADDL II FED AGE=DROP
            SEXF=DROP WT=DROP HT=DROP RACE BMI BSA CRCL=DROP DOSE
$DATA      
            /net/navtst-nonmem/usr/global/mrogalski/other/scm-non-linear/scm-non-linear/non_linear/forward_scm_dir1/scm_dir1/scm_dir1/scm_dir1/m1/V2BSA2_data_1_copy.dta
            IGNORE=@
$SUBROUTINE ADVAN4 TRANS4
$OMEGA  DIAGONAL(5)
 0.0267598  ; [P] INTERIND VAR IN CL
 1.81823E-08  ; [P] INTERIND VAR IN V2
 0.0596963  ; [P] INTERIND VAR IN KA
 0  FIX  ; [P] INTERIND VAR IN Q
 0  FIX  ; [P] INTERIND VAR IN V3
$PK

;;; V2BSA-DEFINITION START
V2BSA = ( 1 + THETA(13)*(BSA - 1.85))
;;; V2BSA-DEFINITION END


;;; V2RACE-DEFINITION START
IF(RACE.EQ.0) V2RACE = 1  ; Most common
IF(RACE.EQ.3) V2RACE = ( 1 + THETA(11))
IF(RACE.EQ.1) V2RACE = ( 1 + THETA(12))
;;; V2RACE-DEFINITION END


;;; V2BMI-DEFINITION START
V2BMI = ( 1 + THETA(10)*(BMI - 26.73))
;;; V2BMI-DEFINITION END

;;; V2-RELATION START
V2COV=V2BMI*V2RACE*V2BSA
;;; V2-RELATION END


;;; CLRACE-DEFINITION START
IF(RACE.EQ.0) CLRACE = 1  ; Most common
IF(RACE.EQ.3) CLRACE = ( 1 + THETA(8))
IF(RACE.EQ.1) CLRACE = ( 1 + THETA(9))
;;; CLRACE-DEFINITION END


;;; CLBMI-DEFINITION START
CLBMI = ( 1 + THETA(7)*(BMI - 26.73))
;;; CLBMI-DEFINITION END

;;; CL-RELATION START
CLCOV=CLBMI*CLRACE
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

$THETA  6.15616 ; 1 [CL]
 7.37197 ; 2 [V2]
 (0,0.211312) ; 3 [KA]
 1 FIX ; 4 [F1]
 5.85384 ; 5[Q]
 9.34124 ; 6 [V3]
$THETA  (-0.166,0.0229657,0.185) ; CLBMI1
$THETA  (-1,-0.247488,5) ; CLRACE1
 (-1,-0.149629,5) ; CLRACE2
$THETA  (-0.166,-0.0559833,0.185) ; V2BMI1
$THETA  (-1,0.0183141,5) ; V2RACE1
 (-1,-0.475032,5) ; V2RACE2
$THETA  (-2.941,-0.002941,4.166) ; V2BSA1
$SIGMA  0.12054  ; [P] Variance for proportional error
$ESTIMATION METHOD=1 INTERACTION PRINT=1 NOABORT MAXEVAL=9999
;$COV PRINT=E

;Tables for Xpose Diagnostic Plots

;Standard Table file containing ID, IDV, DV, PRED, IPRED,

; STRT, WRES, IWRES, RES, IRES, etc.
$TABLE      ID TIME IPRED EVID MDV CWRES IWRES STRT NOPRINT ONEHEADER
            FILE=/net/navtst-nonmem/usr/global/mrogalski/other/scm-non-linear/scm-non-linear/non_linear/forward_scm_dir1/scm_dir1/scm_dir1/scm_dir1//m1/V2BSA2.mod.sdtab1
;Parameter table containing model parameters - THETAS, ETAS, EPS
$TABLE      ID CL V2 KA F1 Q V3 ETA1 ETA2 ETA3 ETA4 NOPRINT ONEHEADER
            FILE=/net/navtst-nonmem/usr/global/mrogalski/other/scm-non-linear/scm-non-linear/non_linear/forward_scm_dir1/scm_dir1/scm_dir1/scm_dir1//m1/V2BSA2.mod.patab1
;Continuous Covariate table - WT, AGE,...

;$TABLE ID WT HT AGE BMI BSA CRCL NOPRINT ONEHEADER FILE=cotab1

;Categorical covariate table - SEX, RACE,.....

;$TABLE ID PROT SEXF RACE FED NOPRINT ONEHEADER FILE=catab1

