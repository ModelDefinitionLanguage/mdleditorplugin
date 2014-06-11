
$PROB WARFARIN PK
;O'Reilly RA, Aggeler PM, Leong LS. Studies of the coumarin anticoagulant
;drugs: The pharmacodynamics of warfarin in man.
;Journal of Clinical Investigation 1963;42(10):1542-1551

;O'Reilly RA, Aggeler PM. Studies on coumarin anticoagulant drugs
;Initiation of warfarin therapy without a loading dose.
;Circulation 1968;38:169-177

$INPUT ID TIME WT AGE SEX AMT DVID DV MDV
$DATA warfarin_conc_pca.csv IGNORE=#
IGNORE (DVID.EQ.2) ; ignore PCA observations

$EST METHOD=COND INTER 
MAX=9990 SIG=3 NOABORT ;PRINT=1
$COV

$THETA
(0.001,0.1) ; POP_CL L/h/70kg
(0.001,8)   ; POP_V  L/70kg
(0.001,2) ; POP_KA h-1
(0.001,1) ; POP_TLAG h

$OMEGA BLOCK(2)
0.1 ; PPV_CL
0.01 0.1 ; PPV_V
$OMEGA
0.1 ; PPV_KA
0.1 ; PPV_TLAG

$SIGMA 
0.01 ; RUV_PROP
0.05 ; RUV_ADD mg/L


$PRED
   IF (AMT.GT.0) DOSE=AMT
   ; Covariate model
   GRPCL=THETA(1)*(WT/70)**0.75
   GRPV=THETA(2)*WT/70
   GRPKA=THETA(3)
   GRPLG=THETA(4)

   ; Individual parameters
   CL=GRPCL*EXP(ETA(1))
   V=GRPV*EXP(ETA(2))
   KA=GRPKA*EXP(ETA(3))
   TLAG=GRPLG*EXP(ETA(4))

   IF (TIME.LE.TLAG) THEN
      CONC=0
   ELSE
      TNOW=TIME-TLAG
      K=CL/V
      CONC=DOSE*KA/(V*(KA-K))*(EXP(-K*TNOW) - EXP(-KA*TNOW))
   ENDIF
   Y=CONC*(1+ERR(1))+ERR(2)

$TABLE ID TIME WT SEX AGE ; covariates
CL V KA TLAG ; EBE estimates
DVID Y ; predictions
ONEHEADER NOPRINT FILE=warf.fit




