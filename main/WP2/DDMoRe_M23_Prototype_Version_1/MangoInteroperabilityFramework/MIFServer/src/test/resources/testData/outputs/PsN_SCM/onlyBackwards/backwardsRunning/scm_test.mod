;Model Desc: MK-0634 PopPK Base Model, 2-compartment, with exponential IIV on CL and V2
;Project Name: MK-0634 Population PK Analysis
;Project ID: NO PROJECT DESCRIPTION

$PROB  RUN# base_run1
$INPUT ID PERD PROT TIME AMT DV EVID MDV ADDL II FED AGE SEXF WT HT RACE BMI BSA CRCL DOSE
$DATA simu_rich_2.csv IGNORE=@ 

$SUBROUTINES ADVAN4 TRANS4
$PK
; Insert stratification variable (STRT) variable for VPC
; Typically stratify on dose, dosing regimen, protocol, ....
; For no stratification, set STRT=Constant
  STRT=PROT*1000+DOSE

  TVCL=EXP(THETA(1))  
  MU_1=LOG(TVCL)
  CL=EXP(MU_1+ETA(1))
  
  TVV2=EXP(THETA(2))
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

$THETA
  (6.5) ;1 [CL]
  (9) ;2 [V2]
  (0,0.5)  ;3 [KA]
  1 FIXED ;4 [F1]
  6.5  ;5[Q]
  10   ;6 [V3]
$OMEGA DIAGONAL(5)
 0.06  ; [P] INTERIND VAR IN CL
 0.06  ; [P] INTERIND VAR IN V2
 0.06 ; [P] INTERIND VAR IN KA 
 0 FIXED  ; [P] INTERIND VAR IN Q
 0 FIXED  ; [P] INTERIND VAR IN V3
$SIGMA
   0.04 ;[P] Variance for proportional error


$ESTIMATION METHOD=1 INTERACTION PRINT=1 NOABORT MAXEVAL=9999

;$COV PRINT=E
;Tables for Xpose Diagnostic Plots
;Standard Table file containing ID, IDV, DV, PRED, IPRED,
; STRT, WRES, IWRES, RES, IRES, etc.
$TABLE ID TIME IPRED EVID MDV CWRES IWRES STRT NOPRINT ONEHEADER FILE=sdtab1
;Parameter table containing model parameters - THETAS, ETAS, EPS
$TABLE ID CL V2 KA F1 Q V3 ETA1 ETA2 ETA3 ETA4 NOPRINT ONEHEADER FILE=patab1
;Continuous Covariate table - WT, AGE,...
;$TABLE ID WT HT AGE BMI BSA CRCL NOPRINT ONEHEADER FILE=cotab1
;Categorical covariate table - SEX, RACE,.....
;$TABLE ID PROT SEXF RACE FED NOPRINT ONEHEADER FILE=catab1
