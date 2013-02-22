;Model Desc: MK-0634 PopPK Base Model, 2-compartment, with exponential IIV on CL and V2
;Project Name: MK-0634 Population PK Analysis
;Project ID: NO PROJECT DESCRIPTION
$PROBLEM    RUN# base_run1
$INPUT      ID PERD PROT TIME AMT DV EVID MDV ADDL II FED AGE SEXF WT
            HT=DROP RACE=DROP BMI BSA CRCL DOSE
$DATA      
            /net/navtst-nonmem/usr/global/mrogalski/other/scm-linear/scm-linear/linear/forward_scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1/m1/CLWT2_data_1_copy.dta
            IGNORE=@
$SUBROUTINE ADVAN4 TRANS4
$OMEGA  DIAGONAL(5)
 0.0309816  ; [P] INTERIND VAR IN CL
 1.3396E-08  ; [P] INTERIND VAR IN V2
 0.0321136  ; [P] INTERIND VAR IN KA
 0  FIX  ; [P] INTERIND VAR IN Q
 0  FIX  ; [P] INTERIND VAR IN V3
$PK

;;; CLWT-DEFINITION START
CLWT = ( 1 + THETA(16)*(WT - 75.39))
;;; CLWT-DEFINITION END


;;; V2WT-DEFINITION START
V2WT = ( 1 + THETA(15)*(WT - 75.39))
;;; V2WT-DEFINITION END


;;; V2SEXF-DEFINITION START
IF(SEXF.EQ.1) V2SEXF = 1  ; Most common
IF(SEXF.EQ.0) V2SEXF = ( 1 + THETA(14))
;;; V2SEXF-DEFINITION END


;;; V2CRCL-DEFINITION START
V2CRCL = ( 1 + THETA(13)*(CRCL - 60.23))
;;; V2CRCL-DEFINITION END


;;; V2BMI-DEFINITION START
V2BMI = ( 1 + THETA(12)*(BMI - 26.73))
;;; V2BMI-DEFINITION END


;;; V2AGE-DEFINITION START
V2AGE = ( 1 + THETA(11)*(AGE - 54.00))
;;; V2AGE-DEFINITION END

;;; V2-RELATION START
V2COV=V2AGE*V2BMI*V2CRCL*V2SEXF*V2WT
;;; V2-RELATION END


;;; CLSEXF-DEFINITION START
IF(SEXF.EQ.1) CLSEXF = 1  ; Most common
IF(SEXF.EQ.0) CLSEXF = ( 1 + THETA(10))
;;; CLSEXF-DEFINITION END


;;; CLCRCL-DEFINITION START
CLCRCL = ( 1 + THETA(9)*(CRCL - 60.23))
;;; CLCRCL-DEFINITION END


;;; CLBSA-DEFINITION START
CLBSA = ( 1 + THETA(8)*(BSA - 1.85))
;;; CLBSA-DEFINITION END


;;; CLAGE-DEFINITION START
CLAGE = ( 1 + THETA(7)*(AGE - 54.00))
;;; CLAGE-DEFINITION END

;;; CL-RELATION START
CLCOV=CLAGE*CLBSA*CLCRCL*CLSEXF*CLWT
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

$THETA  5.93683 ; 1 [CL]
 7.058 ; 2 [V2]
 (0,0.222177) ; 3 [KA]
 1 FIX ; 4 [F1]
 5.86046 ; 5[Q]
 9.33218 ; 6 [V3]
$THETA  (-0.055,0.00516327,0.029) ; CLAGE1
$THETA  (-2.941,0.720304,4.166) ; CLBSA1
$THETA  (-0.012,-0.00324595,0.028) ; CLCRCL1
$THETA  (-1,0.465757,5) ; CLSEXF1
$THETA  (-0.055,0.00719356,0.029) ; V2AGE1
$THETA  (-0.166,0.0374992,0.185) ; V2BMI1
$THETA  (-0.012,0.00268668,0.028) ; V2CRCL1
$THETA  (-1,1.26679,5) ; V2SEXF1
$THETA  (-0.041,-0.0295446,0.074) ; V2WT1
$THETA  (-0.041,-4.1E-05,0.074) ; CLWT1
$SIGMA  0.121168  ; [P] Variance for proportional error
$ESTIMATION METHOD=1 INTERACTION PRINT=1 NOABORT MAXEVAL=9999
;$COV PRINT=E

;Tables for Xpose Diagnostic Plots

;Standard Table file containing ID, IDV, DV, PRED, IPRED,

; STRT, WRES, IWRES, RES, IRES, etc.
$TABLE      ID TIME IPRED EVID MDV CWRES IWRES STRT NOPRINT ONEHEADER
            FILE=/net/navtst-nonmem/usr/global/mrogalski/other/scm-linear/scm-linear/linear/forward_scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1//m1/CLWT2.mod.sdtab1
;Parameter table containing model parameters - THETAS, ETAS, EPS
$TABLE      ID CL V2 KA F1 Q V3 ETA1 ETA2 ETA3 ETA4 NOPRINT ONEHEADER
            FILE=/net/navtst-nonmem/usr/global/mrogalski/other/scm-linear/scm-linear/linear/forward_scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1/scm_dir1//m1/CLWT2.mod.patab1
;Continuous Covariate table - WT, AGE,...

;$TABLE ID WT HT AGE BMI BSA CRCL NOPRINT ONEHEADER FILE=cotab1

;Categorical covariate table - SEX, RACE,.....

;$TABLE ID PROT SEXF RACE FED NOPRINT ONEHEADER FILE=catab1

