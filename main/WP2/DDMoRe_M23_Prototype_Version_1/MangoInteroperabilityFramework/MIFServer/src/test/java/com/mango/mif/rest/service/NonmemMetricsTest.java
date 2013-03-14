/* File			:  NonmemMetricsTest.java
 * Project		:  MIFServer
 * Created on	:  27 Sep 2012
 */
package com.mango.mif.rest.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.io.File;
import java.io.FileFilter;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.mango.mif.client.api.rest.NmMetrics;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.managers.JobManagementService;
import com.mango.pharma.nonmem.ControlFile;
import com.mango.pharma.nonmem.ExtFileParser;
import com.mango.pharma.nonmem.FileFinder;
import com.mango.pharma.nonmem.NonmemBlockElementRO;
import com.mango.pharma.nonmem.NonmemException;
import com.mango.pharma.nonmem.NonmemMatrixManager;
import com.mango.pharma.nonmem.NonmemProblem;
import com.mango.pharma.nonmem.NonmemThetaManager;
import com.mango.pharma.nonmem.NonmemThetaRO;
import com.mango.pharma.nonmem.OutputFile;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({JobServiceImpl.class, NONMEMMetricsCollector.class})
public class NonmemMetricsTest {

    private static final File FILE = new File(".");

    private static final String CONTROL_FILE = "$PROBLEM 2ABC ACZ885A2101 PK->IL-1b\r\n" +
            "\r\n" +
            "$INPUT  ID STDY DOSE AMT RATE CMT EVID TIME TIM2 LIDV DV=LNDV MDV \r\n" +
            "        IL10 WGT SEX=DROP AGE PBO CTR SUB DIS ALB RACE=DROP ETHN=DROP\r\n" +
            "$DATA   nm_pool_all_studies_lesscmt4_header.txt IGNORE='I'\r\n" +
            "$SUBROUTINE ADVAN6 TOL=5\r\n" +
            "\r\n" +
            "$MODEL\r\n" +
            "  COMP=(SC)        ;CMT1 is depot site (unused)\r\n" +
            "  COMP=(TD,DEFOBS) ;CMT2 is total ACZ885 (free + complex)\r\n" +
            "  COMP=(TL)        ;CMT3 is total IL-1beta\r\n" +
            "  COMP=(PL)        ;CMT4 is free IL-1beta in input code (unused), peripheral ligand as a compartment\r\n" +
            "  COMP=(PD)        ;CMT5 is TOTAL peripheral drug (free + complex)\r\n" +
            "\r\n" +
            "$PK\r\n" +
            "\r\n" +
            "CEL=1\r\n" +
            "IF (STDY.EQ.42304) CEL=2\r\n" +
            "\r\n" +
            "\r\n" +
            " TVCLD = THETA(1)\r\n" +
            " CLD = TVCLD*EXP(ETA(1))  ;CL of drug (L/d)\r\n" +
            " CLC = CLD                                  ;assume clearance of complex same as free monoclonal (tested in R28 & 28, little difference)\r\n" +
            " TVVD = THETA(2)\r\n" +
            " VD = TVVD*EXP(ETA(2))   ;Volume (L) same for drug, ligand & complex\r\n" +
            " TVCLL = THETA(3)\r\n" +
            " CLL = TVCLL*EXP(ETA(3))  ;CL of ligand (L/d)\r\n" +
            " TVRLI = THETA(4)\r\n" +
            " RLI = TVRLI*EXP(ETA(4))  ;Rate ligand initially (ng/day)\r\n" +
            " TVVP = THETA(5)\r\n" +
            " VP  = TVVP*EXP(ETA(5))  ;peripheral volume (L)\r\n" +
            " TVPSD = THETA(6)\r\n" +
            " PSD = TVPSD*EXP(ETA(6)) ;permeability*surface area for drug & complex exchange initially (L/d)\r\n" +
            " TVPSL = THETA(7)\r\n" +
            " PSL = TVPSL*EXP(ETA(6)) ;permeability*surface area for ligand exchange (L/d)\r\n" +
            " TVKD = THETA(8)\r\n" +
            " KD  = TVKD*EXP(ETA(7))                 ;equilibrium binding constant (nM)\r\n" +
            " TVKA = THETA(9)\r\n" +
            " KA  = TVKA*EXP(ETA(8))                 ;absn rate constant through skin\r\n" +
            " TVF1 = THETA(10)\r\n" +
            " F1  = (EXP(TVF1)/(1+EXP(TVF1)))*EXP(ETA(9))\r\n" +
            "\r\n" +
            " S2  = VD\r\n" +
            " S3  = VD                                   ;V for ligand same as drug\r\n" +
            " F3  = VD*RLI/CLL/17000                     ;Initialise central IL-1beta nmole AMOUNT including conversion RLI ng/d to nmoles\r\n" +
            " F4  = VP*RLI*(1/PSL+1/CLL)/17000           ;Initialise peripheral IL-1beta nmole AMOUNT including conversion RLI ng/d to nmoles\r\n" +
            " CLP0= RLI*(1/PSL+1/CLL)                    ;concentration free ligand peripheral at time zero (pg/mL)\r\n" +
            "\r\n" +
            " \r\n" +
            "\r\n" +
            "$DES  \r\n" +
            ";Drug-Ligand COMPlex AMOUNT as quadratic solution to hyperbolic binding equation.\r\n" +
            ";Model assumes rapid achievement of equilibrium between drug, ligand and complex.\r\n" +
            ";Also assumes that permeation rate of ligand to/from periphery will be governed by complex when drug is present.\r\n" +
            ";When drug is absent this will be faster, but we have no information on this situation therefore cannot determine\r\n" +
            ";what the PS coefficient for the ligand alone will be.\r\n" +
            " TD  = A(2)\r\n" +
            " TL  = A(3)\r\n" +
            " TLP = A(4)\r\n" +
            " TDP = A(5)\r\n" +
            " COMP=((KD*VD+TD+TL)-((KD*VD+TD+TL)**2-4*TD*TL)**0.5)/2\r\n" +
            " COPP=((KD*VP+TDP+TLP)-((KD*VP+TDP+TLP)**2-4*TDP*TLP)**0.5)/2\r\n" +
            " FL  = TL-COMP      ;free ligand central is total minus complex\r\n" +
            " FLP = TLP-COPP     ;free ligand peripheral is total minus complex\r\n" +
            " CLP = 17000*FLP/VP ;concentration free ligand peripheral (pg/mL)\r\n" +
            ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;80\r\n" +
            " DADT(1)=-KA*A(1)                                                               ;Depot injection site AMOUNT nmoles\r\n" +
            " DADT(2)= KA*A(1)-COMP*CLC/VD-(TD-COMP)*CLD/VD+(TDP/VP-TD/VD)*PSD               ;Total Drug central AMOUNT nmoles\r\n" +
            " DADT(3)=-COMP*CLC/VD-FL*CLL/VD+(FLP/VP-FL/VD)*PSL+(COPP/VP-COMP/VD)*PSD        ;Total Ligand central AMOUNT nmoles\r\n" +
            " DADT(4)= RLI/17000            +(FL/VD-FLP/VP)*PSL+(COMP/VD-COPP/VP)*PSD        ;Total (free + complex) Peripheral ligand AMOUNT\r\n" +
            " DADT(5)=                       (TD/VD-TDP/VP)*PSD                              ;Total (free + complex) drug peripheral AMOUNT\r\n" +
            "\r\n" +
            "$ERROR\r\n" +
            " TTD = A(2)\r\n" +
            " TTL = A(3)\r\n" +
            " TTLP= A(4)\r\n" +
            " TTDP= A(5)\r\n" +
            ";Drug-Ligand Complex concentration as quadratic solution to hyperbolic binding equation\r\n" +
            " COP = ((KD*VD+TTD+TTL)-((KD*VD+TTD+TTL)**2-4*TTD*TTL)**0.5)/2\r\n" +
            " PCOP =((KD*VP+TTDP+TTLP)-((KD*VP+TTDP+TTLP)**2-4*TTDP*TTLP)**0.5)/2\r\n" +
            " CTD = 0.150*TTD/VD                 ;Total Drug ��g/mL (free+complex) \r\n" +
            " CTL = 17000*TTL/VD                 ;Total Ligand pg/mL (free + complex)\r\n" +
            " CFL = 17000*(TTL-COP)/VD           ;Free Ligand pg/mL as total-complex\r\n" +
            " CTLP= 17000*TTLP/VP                ;Total Ligand peripheral pg/mL (free + complex)\r\n" +
            " CFLP= 17000*(TTLP-PCOP)/VP         ;Free Ligand peripheral pg/mL as total-complex\r\n" +
            " LLIM= 0.01                          ;Lower limit (��g/mL or pg/mL) when LOG transform turns off.\r\n" +
            " Y = -4.6051                        ;then log function is set to -2.3026 (=0.1 ng/mL).\r\n" +
            "                                    ;All data is above this (lowest datapoint is 0.12)\r\n" +
            "                                    ;Should only happen at extreme values e.g. at terminal elimination of PK\r\n" +
            " IPRE=0\r\n" +
            " IF(CMT.EQ.2.AND.CTD.GE.LLIM) IPRE=LOG(CTD)\r\n" +
            " IF(CMT.EQ.3.AND.CTL.GE.LLIM) IPRE=LOG(CTL)\r\n" +
            " IF(CMT.EQ.4) IPRE=CFLP\r\n" +
            " IRES=LNDV-IPRE\r\n" +
            " IF(CMT.EQ.2) Y=IPRE+EPS(1)         ;CTD Additive error model in log domain.\r\n" +
            " IF(CMT.EQ.3) Y=IPRE+EPS(2)         ;CTL Additive error model in log domain.\r\n" +
            " IF(CMT.EQ.4) Y=IPRE ;+EPS(3)                ;CFLP predicted output\r\n" +
            "\r\n" +
            "$THETA\r\n" +
            "       (0.001,0.161,1000)          ;1 CLD L/d\r\n" +
            "        (0.001,3.27,1000)           ;2 VD, VL & VC Litres\r\n" +
            "        (0.001,21.6,1000)           ;3 CLL L/d\r\n" +
            "        (0.001,6.77,1000)           ;4 RLI ng/d\r\n" +
            "        (0.001,2.65,1000)           ;5 VP Litres\r\n" +
            "        (0.001,0.402,1000)          ;6 PSD L/d for drug\r\n" +
            "        (0.001,0.382,1000)          ;7 PSL  L/d for ligand\r\n" +
            "        (0.001,0.499,1000)          ;8 KD nM\r\n" +
            "        (0.001,.258,1000)              ;9 KA nM\r\n" +
            "        (0.001,.458,1000)              ;10 XX (for F1)\r\n" +
            "\r\n" +
            "$OMEGA BLOCK(2)\r\n" +
            "         0.0965             ;1 CLD\r\n" +
            "         0.0261 0.0422            ;2 VD (also VL & VC)\r\n" +
            "$OMEGA BLOCK(2)\r\n" +
            "         0.654             ;3 CLL\r\n" +
            "         0.23 0.346       ;4 RLI\r\n" +
            "$OMEGA BLOCK(2)\r\n" +
            "         0.0774            ;5 VP\r\n" +
            "         0.0359 0.133     ;6 PSD and PSL combined as RA study data insufficient information to estimate separately\r\n" +
            "$OMEGA\r\n" +
            "         0.797            ;7 KD\r\n" +
            "         0.236               ;8 KA\r\n" +
            "         0.131              ;9 XX (for F1)\r\n" +
            "         \r\n" +
            "$SIGMA   0.0501            ;Drug\r\n" +
            "         0.0818            ;Total Ligand\r\n" +
            "         ;1               ; Free ligand\r\n" +
            "         \r\n" +
            "$EST METHOD=ZERO NOABORT MAXEVAL=9999 SIGDIGITS=3 POSTHOC\r\n" +
            "     PRINT=10 MSF=m000_new.nmmsf\r\n" +
            ";$COV\r\n" +
            "\r\n" +
            "$TABLE  NOPRINT ONEHEADER \r\n" +
            "        ID STDY DOSE AMT RATE CMT EVID TIME TIM2 LIDV LNDV MDV \r\n" +
            "        IL10 WGT AGE PBO CTR SUB DIS ALB \r\n" +
            "        CLD VD CLL RLI VP PSD PSL KD KA F1 TVF1 CFLP\r\n" +
            "        CTD CTL ETA1 ETA2 ETA3 ETA4 ETA5 ETA6 ETA7 ETA8 ETA9 \r\n" +
            "  FILE=M1.nmtbl\r\n" +
            "\r\n" +
            ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;80\r\n" +
            "";

    private static final String EXT_FILE = "TABLE NO.  1: First Order: Goal Function=MINIMUM VALUE OF OBJECTIVE FUNCTION\r\n" +
            " ITERATION    THETA1       THETA2       THETA3       THETA4       THETA5       THETA6       THETA7       THETA8       THETA9       THETA10      SIGMA(1,1)   SIGMA(2,1)   SIGMA(2,2)   OMEGA(1,1)   OMEGA(2,1)   OMEGA(2,2)   OMEGA(3,1)   OMEGA(3,2)   OMEGA(3,3)   OMEGA(4,1)   OMEGA(4,2)   OMEGA(4,3)   OMEGA(4,4)   OMEGA(5,1)   OMEGA(5,2)   OMEGA(5,3)   OMEGA(5,4)   OMEGA(5,5)   OMEGA(6,1)   OMEGA(6,2)   OMEGA(6,3)   OMEGA(6,4)   OMEGA(6,5)   OMEGA(6,6)   OMEGA(7,1)   OMEGA(7,2)   OMEGA(7,3)   OMEGA(7,4)   OMEGA(7,5)   OMEGA(7,6)   OMEGA(7,7)   OMEGA(8,1)   OMEGA(8,2)   OMEGA(8,3)   OMEGA(8,4)   OMEGA(8,5)   OMEGA(8,6)   OMEGA(8,7)   OMEGA(8,8)   OMEGA(9,1)   OMEGA(9,2)   OMEGA(9,3)   OMEGA(9,4)   OMEGA(9,5)   OMEGA(9,6)   OMEGA(9,7)   OMEGA(9,8)   OMEGA(9,9)   OBJ\r\n" +
            "            0  1.61000E-01  3.27000E+00  2.16000E+01  6.77000E+00  2.65000E+00  4.02000E-01  3.82000E-01  4.99000E-01  2.58000E-01  4.58000E-01  5.01000E-02  0.00000E+00  8.18164E-02  9.65000E-02  2.61000E-02  4.22000E-02  0.00000E+00  0.00000E+00  6.54000E-01  0.00000E+00  0.00000E+00  2.30000E-01  3.46000E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  7.74000E-02  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  3.59000E-02  1.33000E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  7.97000E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  2.36000E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  1.31000E-01   -9645.3457530458272\r\n" +
            "           10  1.65926E-01  3.19686E+00  1.77266E+01  7.17820E+00  2.63006E+00  4.37950E-01  4.09446E-01  6.46282E-01  3.31735E-01  4.89176E-01  4.99502E-02  0.00000E+00  8.66707E-02  1.07320E-01  3.88182E-02  5.74308E-02  0.00000E+00  0.00000E+00  3.96682E-01  0.00000E+00  0.00000E+00  1.85593E-01  3.49801E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  6.63019E-02  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  6.24102E-02  1.64788E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  4.55499E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  5.03502E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  1.39551E-01   -9744.6999319045881\r\n" +
            "           20  1.68873E-01  3.17150E+00  1.82901E+01  7.13182E+00  2.69317E+00  4.24051E-01  4.02937E-01  6.22575E-01  3.30970E-01  5.45696E-01  5.07499E-02  0.00000E+00  8.74924E-02  1.01454E-01  3.93515E-02  5.83860E-02  0.00000E+00  0.00000E+00  4.04194E-01  0.00000E+00  0.00000E+00  1.87402E-01  3.45587E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  6.34819E-02  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  3.62643E-02  4.88595E-02  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  4.68532E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  4.96887E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  1.39000E-01   -9749.8009153376624\r\n" +
            "           30  1.69227E-01  3.12875E+00  1.84513E+01  7.08918E+00  2.69500E+00  4.33699E-01  4.07653E-01  6.34507E-01  3.14274E-01  6.05496E-01  5.15672E-02  0.00000E+00  8.81108E-02  1.00541E-01  4.50566E-02  5.99117E-02  0.00000E+00  0.00000E+00  4.35038E-01  0.00000E+00  0.00000E+00  2.10033E-01  3.60756E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  6.62871E-02  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  4.80065E-02  1.14209E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  4.71652E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  4.48094E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  1.35122E-01   -9755.3823923960954\r\n" +
            "           35  1.69278E-01  3.12936E+00  1.84549E+01  7.09167E+00  2.69610E+00  4.34025E-01  4.08041E-01  6.34634E-01  3.14338E-01  6.06181E-01  5.15704E-02  0.00000E+00  8.80751E-02  1.00521E-01  4.50462E-02  5.99238E-02  0.00000E+00  0.00000E+00  4.34928E-01  0.00000E+00  0.00000E+00  2.09973E-01  3.60707E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  6.62816E-02  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  4.79644E-02  1.14262E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  4.71603E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  4.48222E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  1.35145E-01   -9755.3827804299417\r\n" +
            "  -1000000000  1.69278E-01  3.12936E+00  1.84549E+01  7.09167E+00  2.69610E+00  4.34025E-01  4.08041E-01  6.34634E-01  3.14338E-01  6.06181E-01  5.15704E-02  0.00000E+00  8.80993E-02  1.00521E-01  4.50462E-02  5.99238E-02  0.00000E+00  0.00000E+00  4.34928E-01  0.00000E+00  0.00000E+00  2.09973E-01  3.60707E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  6.62816E-02  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  4.79644E-02  1.14262E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  4.71603E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  4.48222E-01  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  0.00000E+00  1.35145E-01   -9755.3827804299417\r\n" +
            "";

    private static final String LST_FILE = "Fri Sep 28 13:29:43 BST 2012\r\n" +
            "gfortran\r\n" +
            "$PROBLEM 2ABC ACZ885A2101 PK->IL-1b\r\n" +
            "\r\n" +
            "$INPUT  ID STDY DOSE AMT RATE CMT EVID TIME TIM2 LIDV DV=LNDV MDV \r\n" +
            "        IL10 WGT SEX=DROP AGE PBO CTR SUB DIS ALB RACE=DROP ETHN=DROP\r\n" +
            "$DATA   nm_pool_all_studies_lesscmt4_header.txt IGNORE='I'\r\n" +
            "$SUBROUTINE ADVAN6 TOL=5\r\n" +
            "\r\n" +
            "$MODEL\r\n" +
            "  COMP=(SC)        ;CMT1 is depot site (unused)\r\n" +
            "  COMP=(TD,DEFOBS) ;CMT2 is total ACZ885 (free + complex)\r\n" +
            "  COMP=(TL)        ;CMT3 is total IL-1beta\r\n" +
            "  COMP=(PL)        ;CMT4 is free IL-1beta in input code (unused), peripheral ligand as a compartment\r\n" +
            "  COMP=(PD)        ;CMT5 is TOTAL peripheral drug (free + complex)\r\n" +
            "\r\n" +
            "$PK\r\n" +
            "\r\n" +
            "CEL=1\r\n" +
            "IF (STDY.EQ.42304) CEL=2\r\n" +
            "\r\n" +
            "\r\n" +
            " TVCLD = THETA(1)\r\n" +
            " CLD = TVCLD*EXP(ETA(1))  ;CL of drug (L/d)\r\n" +
            " CLC = CLD                                  ;assume clearance of complex same as free monoclonal (tested in R28 & 28, little difference)\r\n" +
            " TVVD = THETA(2)\r\n" +
            " VD = TVVD*EXP(ETA(2))   ;Volume (L) same for drug, ligand & complex\r\n" +
            " TVCLL = THETA(3)\r\n" +
            " CLL = TVCLL*EXP(ETA(3))  ;CL of ligand (L/d)\r\n" +
            " TVRLI = THETA(4)\r\n" +
            " RLI = TVRLI*EXP(ETA(4))  ;Rate ligand initially (ng/day)\r\n" +
            " TVVP = THETA(5)\r\n" +
            " VP  = TVVP*EXP(ETA(5))  ;peripheral volume (L)\r\n" +
            " TVPSD = THETA(6)\r\n" +
            " PSD = TVPSD*EXP(ETA(6)) ;permeability*surface area for drug & complex exchange initially (L/d)\r\n" +
            " TVPSL = THETA(7)\r\n" +
            " PSL = TVPSL*EXP(ETA(6)) ;permeability*surface area for ligand exchange (L/d)\r\n" +
            " TVKD = THETA(8)\r\n" +
            " KD  = TVKD*EXP(ETA(7))                 ;equilibrium binding constant (nM)\r\n" +
            " TVKA = THETA(9)\r\n" +
            " KA  = TVKA*EXP(ETA(8))                 ;absn rate constant through skin\r\n" +
            " TVF1 = THETA(10)\r\n" +
            " F1  = (EXP(TVF1)/(1+EXP(TVF1)))*EXP(ETA(9))\r\n" +
            "\r\n" +
            " S2  = VD\r\n" +
            " S3  = VD                                   ;V for ligand same as drug\r\n" +
            " F3  = VD*RLI/CLL/17000                     ;Initialise central IL-1beta nmole AMOUNT including conversion RLI ng/d to nmoles\r\n" +
            " F4  = VP*RLI*(1/PSL+1/CLL)/17000           ;Initialise peripheral IL-1beta nmole AMOUNT including conversion RLI ng/d to nmoles\r\n" +
            " CLP0= RLI*(1/PSL+1/CLL)                    ;concentration free ligand peripheral at time zero (pg/mL)\r\n" +
            "\r\n" +
            " \r\n" +
            "\r\n" +
            "$DES  \r\n" +
            ";Drug-Ligand COMPlex AMOUNT as quadratic solution to hyperbolic binding equation.\r\n" +
            ";Model assumes rapid achievement of equilibrium between drug, ligand and complex.\r\n" +
            ";Also assumes that permeation rate of ligand to/from periphery will be governed by complex when drug is present.\r\n" +
            ";When drug is absent this will be faster, but we have no information on this situation therefore cannot determine\r\n" +
            ";what the PS coefficient for the ligand alone will be.\r\n" +
            " TD  = A(2)\r\n" +
            " TL  = A(3)\r\n" +
            " TLP = A(4)\r\n" +
            " TDP = A(5)\r\n" +
            " COMP=((KD*VD+TD+TL)-((KD*VD+TD+TL)**2-4*TD*TL)**0.5)/2\r\n" +
            " COPP=((KD*VP+TDP+TLP)-((KD*VP+TDP+TLP)**2-4*TDP*TLP)**0.5)/2\r\n" +
            " FL  = TL-COMP      ;free ligand central is total minus complex\r\n" +
            " FLP = TLP-COPP     ;free ligand peripheral is total minus complex\r\n" +
            " CLP = 17000*FLP/VP ;concentration free ligand peripheral (pg/mL)\r\n" +
            ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;80\r\n" +
            " DADT(1)=-KA*A(1)                                                               ;Depot injection site AMOUNT nmoles\r\n" +
            " DADT(2)= KA*A(1)-COMP*CLC/VD-(TD-COMP)*CLD/VD+(TDP/VP-TD/VD)*PSD               ;Total Drug central AMOUNT nmoles\r\n" +
            " DADT(3)=-COMP*CLC/VD-FL*CLL/VD+(FLP/VP-FL/VD)*PSL+(COPP/VP-COMP/VD)*PSD        ;Total Ligand central AMOUNT nmoles\r\n" +
            " DADT(4)= RLI/17000            +(FL/VD-FLP/VP)*PSL+(COMP/VD-COPP/VP)*PSD        ;Total (free + complex) Peripheral ligand AMOUNT\r\n" +
            " DADT(5)=                       (TD/VD-TDP/VP)*PSD                              ;Total (free + complex) drug peripheral AMOUNT\r\n" +
            "\r\n" +
            "$ERROR\r\n" +
            " TTD = A(2)\r\n" +
            " TTL = A(3)\r\n" +
            " TTLP= A(4)\r\n" +
            " TTDP= A(5)\r\n" +
            ";Drug-Ligand Complex concentration as quadratic solution to hyperbolic binding equation\r\n" +
            " COP = ((KD*VD+TTD+TTL)-((KD*VD+TTD+TTL)**2-4*TTD*TTL)**0.5)/2\r\n" +
            " PCOP =((KD*VP+TTDP+TTLP)-((KD*VP+TTDP+TTLP)**2-4*TTDP*TTLP)**0.5)/2\r\n" +
            " CTD = 0.150*TTD/VD                 ;Total Drug ��g/mL (free+complex) \r\n" +
            " CTL = 17000*TTL/VD                 ;Total Ligand pg/mL (free + complex)\r\n" +
            " CFL = 17000*(TTL-COP)/VD           ;Free Ligand pg/mL as total-complex\r\n" +
            " CTLP= 17000*TTLP/VP                ;Total Ligand peripheral pg/mL (free + complex)\r\n" +
            " CFLP= 17000*(TTLP-PCOP)/VP         ;Free Ligand peripheral pg/mL as total-complex\r\n" +
            " LLIM= 0.01                          ;Lower limit (��g/mL or pg/mL) when LOG transform turns off.\r\n" +
            " Y = -4.6051                        ;then log function is set to -2.3026 (=0.1 ng/mL).\r\n" +
            "                                    ;All data is above this (lowest datapoint is 0.12)\r\n" +
            "                                    ;Should only happen at extreme values e.g. at terminal elimination of PK\r\n" +
            " IPRE=0\r\n" +
            " IF(CMT.EQ.2.AND.CTD.GE.LLIM) IPRE=LOG(CTD)\r\n" +
            " IF(CMT.EQ.3.AND.CTL.GE.LLIM) IPRE=LOG(CTL)\r\n" +
            " IF(CMT.EQ.4) IPRE=CFLP\r\n" +
            " IRES=LNDV-IPRE\r\n" +
            " IF(CMT.EQ.2) Y=IPRE+EPS(1)         ;CTD Additive error model in log domain.\r\n" +
            " IF(CMT.EQ.3) Y=IPRE+EPS(2)         ;CTL Additive error model in log domain.\r\n" +
            " IF(CMT.EQ.4) Y=IPRE ;+EPS(3)                ;CFLP predicted output\r\n" +
            "\r\n" +
            "$THETA\r\n" +
            "       (0.001,0.161,1000)          ;1 CLD L/d\r\n" +
            "        (0.001,3.27,1000)           ;2 VD, VL & VC Litres\r\n" +
            "        (0.001,21.6,1000)           ;3 CLL L/d\r\n" +
            "        (0.001,6.77,1000)           ;4 RLI ng/d\r\n" +
            "        (0.001,2.65,1000)           ;5 VP Litres\r\n" +
            "        (0.001,0.402,1000)          ;6 PSD L/d for drug\r\n" +
            "        (0.001,0.382,1000)          ;7 PSL  L/d for ligand\r\n" +
            "        (0.001,0.499,1000)          ;8 KD nM\r\n" +
            "        (0.001,.258,1000)              ;9 KA nM\r\n" +
            "        (0.001,.458,1000)              ;10 XX (for F1)\r\n" +
            "\r\n" +
            "$OMEGA BLOCK(2)\r\n" +
            "         0.0965             ;1 CLD\r\n" +
            "         0.0261 0.0422            ;2 VD (also VL & VC)\r\n" +
            "$OMEGA BLOCK(2)\r\n" +
            "         0.654             ;3 CLL\r\n" +
            "         0.23 0.346       ;4 RLI\r\n" +
            "$OMEGA BLOCK(2)\r\n" +
            "         0.0774            ;5 VP\r\n" +
            "         0.0359 0.133     ;6 PSD and PSL combined as RA study data insufficient information to estimate separately\r\n" +
            "$OMEGA\r\n" +
            "         0.797            ;7 KD\r\n" +
            "         0.236               ;8 KA\r\n" +
            "         0.131              ;9 XX (for F1)\r\n" +
            "         \r\n" +
            "$SIGMA   0.0501            ;Drug\r\n" +
            "         0.0818            ;Total Ligand\r\n" +
            "         ;1               ; Free ligand\r\n" +
            "         \r\n" +
            "$EST METHOD=ZERO NOABORT MAXEVAL=9999 SIGDIGITS=3 POSTHOC\r\n" +
            "     PRINT=10 MSF=m000_new.nmmsf\r\n" +
            ";$COV\r\n" +
            "\r\n" +
            "$TABLE  NOPRINT ONEHEADER \r\n" +
            "        ID STDY DOSE AMT RATE CMT EVID TIME TIM2 LIDV LNDV MDV \r\n" +
            "        IL10 WGT AGE PBO CTR SUB DIS ALB \r\n" +
            "        CLD VD CLL RLI VP PSD PSL KD KA F1 TVF1 CFLP\r\n" +
            "        CTD CTL ETA1 ETA2 ETA3 ETA4 ETA5 ETA6 ETA7 ETA8 ETA9 \r\n" +
            "  FILE=M1.nmtbl\r\n" +
            "\r\n" +
            ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;80\r\n" +
            " \r\n" +
            " \r\n" +
            "  \r\n" +
            " WARNINGS AND ERRORS (IF ANY) FOR PROBLEM    1\r\n" +
            "             \r\n" +
            " (WARNING  2) NM-TRAN INFERS THAT THE DATA ARE POPULATION.\r\n" +
            "             \r\n" +
            " (WARNING  97) A RANDOM QUANTITY IS RAISED TO A POWER. IF THE RESULT AFFECTS\r\n" +
            " THE VALUE OF THE OBJECTIVE FUNCTION, THE USER SHOULD ENSURE THAT THE\r\n" +
            " RANDOM QUANTITY IS NEVER 0 WHEN THE POWER IS < 1.\r\n" +
            " CREATING MUMODEL ROUTINE...\r\n" +
            " \r\n" +
            " \r\n" +
            "License Registered to: Mango Business Solutions Ltd.\r\n" +
            "Expiration Date:    14 OCT 2012\r\n" +
            "Current Date:       28 SEP 2012\r\n" +
            "  ****WARNING!!! Days until program expires :  16\r\n" +
            "1NONLINEAR MIXED EFFECTS MODEL PROGRAM (NONMEM) VERSION 7.1.0\r\n" +
            " ORIGINALLY DEVELOPED BY STUART BEAL, LEWIS SHEINER, AND ALISON BOECKMANN\r\n" +
            " CURRENT DEVELOPERS ARE ROBERT BAUER, ICON DEVELOPMENT SOLUTIONS,\r\n" +
            " AND ALISON BOECKMANN. IMPLEMENTATION, EFFICIENCY, AND STANDARDIZATION\r\n" +
            " PERFORMED BY NOUS INFOSYSTEMS.\r\n" +
            " \r\n" +
            " PROBLEM NO.:         1\r\n" +
            " 2ABC ACZ885A2101 PK->IL-1b                                              \r\n" +
            "0DATA CHECKOUT RUN:              NO \r\n" +
            " DATA SET LOCATED ON UNIT NO.:    2\r\n" +
            " THIS UNIT TO BE REWOUND:        NO \r\n" +
            " NO. OF DATA RECS IN DATA SET:     9092\r\n" +
            " NO. OF DATA ITEMS IN DATA SET:  20\r\n" +
            " ID DATA ITEM IS DATA ITEM NO.:   1\r\n" +
            " DEP VARIABLE IS DATA ITEM NO.:  11\r\n" +
            " MDV DATA ITEM IS DATA ITEM NO.: 12\r\n" +
            "0INDICES PASSED TO SUBROUTINE PRED:\r\n" +
            "  7  8  4  5  0  0  6  0  0\r\n" +
            "  0  0\r\n" +
            " 0LABELS FOR DATA ITEMS:\r\n" +
            "  ID STDY DOSE AMT RATE CMT EVID TIME TIM2 LIDV LNDV MDV IL10 WGT AGE PBO CTR SUB DIS ALB\r\n" +
            "0(NONBLANK) LABELS FOR PRED-DEFINED ITEMS:\r\n" +
            " CLD     VD      CLL     RLI     VP      PSD     PSL     KD      KA  \r\n" +
            " TVF1    F1      CTD     CTL     CFLP\r\n" +
            "0FORMAT FOR DATA:\r\n" +
            " (2E5.0,E7.0,E9.0,E11.0,2E1.0,2E9.0,E8.0,E7.0,E1.0/E5.0,7E6.0)                   \r\n" +
            " \r\n" +
            " TOT. NO. OF OBS RECS:     7241\r\n" +
            " TOT. NO. OF INDIVIDUALS:    233\r\n" +
            "0LENGTH OF THETA: 10\r\n" +
            "0DEFAULT THETA BOUNDARY TEST OMITTED:    NO \r\n" +
            "0OMEGA HAS BLOCK FORM:\r\n" +
            "  1\r\n" +
            "  1  1\r\n" +
            "  0  0  2\r\n" +
            "  0  0  2  2\r\n" +
            "  0  0  0  0  3\r\n" +
            "  0  0  0  0  3  3\r\n" +
            "  0  0  0  0  0  0  4\r\n" +
            "  0  0  0  0  0  0  0  5\r\n" +
            "  0  0  0  0  0  0  0  0  6\r\n" +
            "0DEFAULT OMEGA BOUNDARY TEST OMITTED:    NO \r\n" +
            "0SIGMA HAS SIMPLE DIAGONAL FORM WITH DIMENSION:  2\r\n" +
            "0DEFAULT SIGMA BOUNDARY TEST OMITTED:    NO \r\n" +
            "0INITIAL ESTIMATE OF THETA:\r\n" +
            " LOWER BOUND    INITIAL EST    UPPER BOUND\r\n" +
            "  0.1000E-02     0.1610E+00     0.1000E+04\r\n" +
            "  0.1000E-02     0.3270E+01     0.1000E+04\r\n" +
            "  0.1000E-02     0.2160E+02     0.1000E+04\r\n" +
            "  0.1000E-02     0.6770E+01     0.1000E+04\r\n" +
            "  0.1000E-02     0.2650E+01     0.1000E+04\r\n" +
            "  0.1000E-02     0.4020E+00     0.1000E+04\r\n" +
            "  0.1000E-02     0.3820E+00     0.1000E+04\r\n" +
            "  0.1000E-02     0.4990E+00     0.1000E+04\r\n" +
            "  0.1000E-02     0.2580E+00     0.1000E+04\r\n" +
            "  0.1000E-02     0.4580E+00     0.1000E+04\r\n" +
            "0INITIAL ESTIMATE OF OMEGA:\r\n" +
            " BLOCK SET NO.   BLOCK                                                                    FIXED\r\n" +
            "        1                                                                                   NO \r\n" +
            "                  0.9650E-01\r\n" +
            "                  0.2610E-01   0.4220E-01\r\n" +
            "        2                                                                                   NO \r\n" +
            "                  0.6540E+00\r\n" +
            "                  0.2300E+00   0.3460E+00\r\n" +
            "        3                                                                                   NO \r\n" +
            "                  0.7740E-01\r\n" +
            "                  0.3590E-01   0.1330E+00\r\n" +
            "        4                                                                                   NO \r\n" +
            "                  0.7970E+00\r\n" +
            "        5                                                                                   NO \r\n" +
            "                  0.2360E+00\r\n" +
            "        6                                                                                   NO \r\n" +
            "                  0.1310E+00\r\n" +
            "0INITIAL ESTIMATE OF SIGMA:\r\n" +
            " 0.5010E-01\r\n" +
            " 0.0000E+00   0.8180E-01\r\n" +
            "0ESTIMATION STEP OMITTED:           NO \r\n" +
            " EPS-ETA INTERACTION:               NO \r\n" +
            " POP. ETAS OBTAINED POST HOC:      YES \r\n" +
            " NO. OF FUNCT. EVALS. ALLOWED:       9999\r\n" +
            " NO. OF SIG. FIGURES REQUIRED:       3\r\n" +
            " INTERMEDIATE PRINTOUT:            YES \r\n" +
            " ESTIMATE OUTPUT TO MSF:           YES \r\n" +
            " ABORT WITH PRED EXIT CODE 1:       NO \r\n" +
            " IND. OBJ. FUNC. VALUES SORTED:     NO \r\n" +
            "0TABLES STEP OMITTED:    NO \r\n" +
            " NO. OF TABLES:           1\r\n" +
            "0-- TABLE  1 --\r\n" +
            " PRINTED:                NO \r\n" +
            " HEADERS:               ONE\r\n" +
            " FILE TO BE FORWARDED:   NO \r\n" +
            " 0USER-CHOSEN ITEMS:\r\n" +
            "  ID STDY DOSE AMT RATE CMT EVID TIME TIM2 LIDV LNDV MDV IL10 WGT AGE PBO CTR SUB DIS ALB CLD VD CLL RLI VP PSD PSL KD KA F1 TVF1\r\n" +
            "  CFLP CTD CTL ETA1 ETA2 ETA3 ETA4 ETA5 ETA6 ETA7 ETA8 ETA9\r\n" +
            "\r\n" +
            " THE FOLLOWING LABELS ARE EQUIVALENT\r\n" +
            " PRED=NPRED\r\n" +
            " RES=NRES\r\n" +
            " WRES=NWRES\r\n" +
            "1DOUBLE PRECISION PREDPP VERSION 7.1.0\r\n" +
            " \r\n" +
            " GENERAL NONLINEAR KINETICS MODEL (ADVAN6)\r\n" +
            "0MODEL SUBROUTINE USER-SUPPLIED - ID NO. 9999\r\n" +
            "0MAXIMUM NO. OF BASIC PK PARAMETERS:  10\r\n" +
            "0COMPARTMENT ATTRIBUTES \r\n" +
            " COMPT. NO.   FUNCTION   INITIAL    ON/OFF      DOSE      DEFAULT    DEFAULT\r\n" +
            "                         STATUS     ALLOWED    ALLOWED    FOR DOSE   FOR OBS.\r\n" +
            "    1         SC           ON         YES        YES        YES        NO \r\n" +
            "    2         TD           ON         YES        YES        NO         YES\r\n" +
            "    3         TL           ON         YES        YES        NO         NO \r\n" +
            "    4         PL           ON         YES        YES        NO         NO \r\n" +
            "    5         PD           ON         YES        YES        NO         NO \r\n" +
            "    6         OUTPUT       OFF        YES        NO         NO         NO \r\n" +
            "0NRD VALUE FROM SUBROUTINE TOL:   5\r\n" +
            "1\r\n" +
            " ADDITIONAL PK PARAMETERS - ASSIGNMENT OF ROWS IN GG\r\n" +
            " COMPT. NO.                             INDICES\r\n" +
            "              SCALE      BIOAVAIL.   ZERO-ORDER  ZERO-ORDER  ABSORB\r\n" +
            "                         FRACTION    RATE        DURATION    LAG  \r\n" +
            "    1           *          11           *           *           *\r\n" +
            "    2          12           *           *           *           *\r\n" +
            "    3          13          14           *           *           *\r\n" +
            "    4           *          15           *           *           *\r\n" +
            "    5           *           *           *           *           *\r\n" +
            "    6           *           -           -           -           -\r\n" +
            "             - PARAMETER IS NOT ALLOWED FOR THIS MODEL\r\n" +
            "             * PARAMETER IS NOT SUPPLIED BY PK SUBROUTINE;\r\n" +
            "               WILL DEFAULT TO ONE IF APPLICABLE\r\n" +
            "0DATA ITEM INDICES USED BY PRED ARE:\r\n" +
            "   EVENT ID DATA ITEM IS DATA ITEM NO.:      7\r\n" +
            "   TIME DATA ITEM IS DATA ITEM NO.:          8\r\n" +
            "   DOSE AMOUNT DATA ITEM IS DATA ITEM NO.:   4\r\n" +
            "   DOSE RATE DATA ITEM IS DATA ITEM NO.:     5\r\n" +
            "   COMPT. NO. DATA ITEM IS DATA ITEM NO.:    6\r\n" +
            " \r\n" +
            "0PK SUBROUTINE CALLED WITH EVERY EVENT RECORD.\r\n" +
            " PK SUBROUTINE NOT CALLED AT NONEVENT (ADDITIONAL OR LAGGED) DOSE TIMES.\r\n" +
            "0ERROR SUBROUTINE CALLED WITH EVERY EVENT RECORD.\r\n" +
            "0ERROR SUBROUTINE INDICATES THAT DERIVATIVES OF COMPARTMENT AMOUNTS ARE USED.\r\n" +
            "0DES SUBROUTINE USES COMPACT STORAGE MODE.\r\n" +
            "1\r\n" +
            "\r\n" +
            "\r\n" +
            " #METH: First Order\r\n" +
            " MONITORING OF SEARCH:\r\n" +
            "\r\n" +
            "0ITERATION NO.:    0    OBJECTIVE VALUE:  -9645.34575304583        NO. OF FUNC. EVALS.:14\r\n" +
            " CUMULATIVE NO. OF FUNC. EVALS.:       14\r\n" +
            " PARAMETER:  1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01\r\n" +
            "             1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01\r\n" +
            "             1.0000E-01  1.0000E-01  1.0000E-01  1.0000E-01\r\n" +
            " GRADIENT:  -6.5544E+02  6.7827E+02 -1.6757E+00 -1.0093E+02  1.1941E+02 -6.8109E+01  7.2452E+01 -1.1828E+02 -2.2060E+02 -3.0238E+00\r\n" +
            "            -3.2152E+01 -1.4991E+02 -5.2005E+01  6.8322E+01 -7.2802E+01 -2.9951E+01  1.2955E+01 -5.5698E+01 -4.5577E-02  4.2766E+01\r\n" +
            "            -8.1206E+01 -1.9380E+01 -3.4997E+02 -2.8227E+02\r\n" +
            "0ITERATION NO.:   10    OBJECTIVE VALUE:  -9744.69993190459        NO. OF FUNC. EVALS.:16\r\n" +
            " CUMULATIVE NO. OF FUNC. EVALS.:      172\r\n" +
            " PARAMETER:  1.3033E-01  7.7300E-02 -1.0159E-01  1.5897E-01  9.2423E-02  1.8589E-01  1.6959E-01  3.5923E-01  3.5231E-01  1.6602E-01\r\n" +
            "             1.5314E-01  1.4103E-01  2.0543E-01 -1.4999E-01  1.0361E-01  9.5939E-02  2.2616E-02  1.8783E-01  5.3619E-02 -1.7973E-01\r\n" +
            "             4.7888E-01  1.3162E-01  9.8503E-02  1.2879E-01\r\n" +
            " GRADIENT:  -1.1305E+02  1.4722E+02 -5.4799E+01  3.7891E+01 -1.6697E+01 -8.2532E-01  2.2580E+01 -2.7439E+01  2.2765E+00 -4.8269E+00\r\n" +
            "             2.8007E+01 -9.9726E+01  7.5601E+00 -2.0768E+01 -2.6886E+01  2.4616E+00 -1.3244E+01  2.9392E+01  2.1008E-02 -7.0818E+00\r\n" +
            "             1.2029E+01  1.2914E+00 -2.1220E+02 -1.0073E+02\r\n" +
            "0ITERATION NO.:   20    OBJECTIVE VALUE:  -9749.80091533766        NO. OF FUNC. EVALS.:15\r\n" +
            " CUMULATIVE NO. OF FUNC. EVALS.:      325\r\n" +
            " PARAMETER:  1.4804E-01  6.9307E-02 -6.9717E-02  1.5244E-01  1.1621E-01  1.5355E-01  1.5352E-01  3.2178E-01  3.5000E-01  2.7563E-01\r\n" +
            "             1.2503E-01  1.4704E-01  2.0234E-01 -1.4061E-01  1.0364E-01  8.7754E-02  8.8409E-04  1.1154E-01 -6.0964E-01 -1.6562E-01\r\n" +
            "             4.7227E-01  1.2964E-01  1.0644E-01  1.3350E-01\r\n" +
            " GRADIENT:  -4.8152E+01  7.1114E+01 -3.4338E+01  1.4159E+01  3.3151E+01 -2.2332E+01  1.7925E+01 -2.8765E+01  1.7359E+01 -3.7816E+00\r\n" +
            "             1.1750E+01 -7.1309E+01  6.3085E+00 -1.1713E+01 -2.8495E+01 -1.4803E+00 -4.9235E+00 -4.4033E-01 -3.7923E+00 -3.5718E+00\r\n" +
            "             7.8067E+00 -2.3837E+00 -1.3899E+02 -5.0956E+01\r\n" +
            "0ITERATION NO.:   30    OBJECTIVE VALUE:  -9755.38239239610        NO. OF FUNC. EVALS.:15\r\n" +
            " CUMULATIVE NO. OF FUNC. EVALS.:      480\r\n" +
            " PARAMETER:  1.5015E-01  5.5689E-02 -6.0777E-02  1.4640E-01  1.1689E-01  1.7611E-01  1.6519E-01  3.4081E-01  2.9806E-01  3.7986E-01\r\n" +
            "             1.2051E-01  1.6913E-01  1.6125E-01 -1.0384E-01  1.1197E-01  8.9018E-02  2.2504E-02  1.4450E-01 -9.0782E-02 -1.6231E-01\r\n" +
            "             4.2059E-01  1.1549E-01  1.1443E-01  1.3702E-01\r\n" +
            " GRADIENT:  -1.3251E-03  5.8796E-02  6.5142E-02 -3.8646E-02 -2.4337E-01  1.1282E-01 -2.1632E-02  4.1684E-02 -6.0872E-02  2.5539E-02\r\n" +
            "            -1.2199E-02  2.8085E-02 -4.1884E-02  2.4272E-02  6.2790E-02  1.0786E-02 -4.8549E-03  2.2408E-02 -2.7162E-02 -1.6967E-02\r\n" +
            "            -1.2200E-02 -2.2481E-02  1.9996E-01 -1.4574E-02\r\n" +
            "0ITERATION NO.:   35    OBJECTIVE VALUE:  -9755.38278042994        NO. OF FUNC. EVALS.: 0\r\n" +
            " CUMULATIVE NO. OF FUNC. EVALS.:      577\r\n" +
            " PARAMETER:  1.5045E-01  5.5882E-02 -6.0583E-02  1.4675E-01  1.1730E-01  1.7687E-01  1.6614E-01  3.4101E-01  2.9826E-01  3.8099E-01\r\n" +
            "             1.2041E-01  1.6910E-01  1.6146E-01 -1.0396E-01  1.1195E-01  8.8986E-02  2.2462E-02  1.4438E-01 -9.0088E-02 -1.6236E-01\r\n" +
            "             4.2073E-01  1.1557E-01  1.1446E-01  1.3709E-01\r\n" +
            " GRADIENT:   3.2295E-04  5.9114E-04  4.2394E-03 -1.6574E-04 -1.9042E-03  3.0556E-03 -1.2015E-03 -3.9833E-04  8.0469E-04 -4.4189E-04\r\n" +
            "             9.1755E-04 -3.5417E-03  8.8738E-04  2.6387E-04  8.7995E-04  1.2960E-04  8.0352E-05 -5.5175E-05 -2.6600E-05  3.8012E-04\r\n" +
            "             1.0120E-04  1.2010E-04  2.1839E-03  6.3075E-04\r\n" +
            " Elapsed estimation time in seconds:  1465.27\r\n" +
            "\r\n" +
            " #TERM:\r\n" +
            "0MINIMIZATION SUCCESSFUL\r\n" +
            " NO. OF FUNCTION EVALUATIONS USED:      577\r\n" +
            " NO. OF SIG. DIGITS IN FINAL EST.:  4.0\r\n" +
            " #TERE:\r\n" +
            "1\r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " \r\n" +
            " ************************************************************************************************************************\r\n" +
            " ********************                                                                                ********************\r\n" +
            " ********************                                   FIRST ORDER                                  ********************\r\n" +
            " #OBJT:**************                       MINIMUM VALUE OF OBJECTIVE FUNCTION                      ********************\r\n" +
            " ********************                                                                                ********************\r\n" +
            " ************************************************************************************************************************\r\n" +
            " \r\n" +
            "\r\n" +
            "\r\n" +
            "\r\n" +
            "\r\n" +
            "\r\n" +
            " #OBJV:********************************************    -9755.383       **************************************************\r\n" +
            "1\r\n" +
            " ************************************************************************************************************************\r\n" +
            " ********************                                                                                ********************\r\n" +
            " ********************                                   FIRST ORDER                                  ********************\r\n" +
            " ********************                             FINAL PARAMETER ESTIMATE                           ********************\r\n" +
            " ********************                                                                                ********************\r\n" +
            " ************************************************************************************************************************\r\n" +
            " \r\n" +
            "\r\n" +
            "\r\n" +
            " THETA - VECTOR OF FIXED EFFECTS PARAMETERS   *********\r\n" +
            "\r\n" +
            "\r\n" +
            "         TH 1      TH 2      TH 3      TH 4      TH 5      TH 6      TH 7      TH 8      TH 9      TH10     \r\n" +
            " \r\n" +
            "         1.69E-01  3.13E+00  1.85E+01  7.09E+00  2.70E+00  4.34E-01  4.08E-01  6.35E-01  3.14E-01  6.06E-01\r\n" +
            " \r\n" +
            "\r\n" +
            "\r\n" +
            " OMEGA - COV MATRIX FOR RANDOM EFFECTS - ETAS  ********\r\n" +
            "\r\n" +
            "\r\n" +
            "            ETA1      ETA2      ETA3      ETA4      ETA5      ETA6      ETA7      ETA8      ETA9\r\n" +
            " \r\n" +
            " ETA1\r\n" +
            "+        1.01E-01\r\n" +
            " \r\n" +
            " ETA2\r\n" +
            "+        4.50E-02  5.99E-02\r\n" +
            " \r\n" +
            " ETA3\r\n" +
            "+        0.00E+00  0.00E+00  4.35E-01\r\n" +
            " \r\n" +
            " ETA4\r\n" +
            "+        0.00E+00  0.00E+00  2.10E-01  3.61E-01\r\n" +
            " \r\n" +
            " ETA5\r\n" +
            "+        0.00E+00  0.00E+00  0.00E+00  0.00E+00  6.63E-02\r\n" +
            " \r\n" +
            " ETA6\r\n" +
            "+        0.00E+00  0.00E+00  0.00E+00  0.00E+00  4.80E-02  1.14E-01\r\n" +
            " \r\n" +
            " ETA7\r\n" +
            "+        0.00E+00  0.00E+00  0.00E+00  0.00E+00  0.00E+00  0.00E+00  4.72E-01\r\n" +
            " \r\n" +
            " ETA8\r\n" +
            "+        0.00E+00  0.00E+00  0.00E+00  0.00E+00  0.00E+00  0.00E+00  0.00E+00  4.48E-01\r\n" +
            " \r\n" +
            " ETA9\r\n" +
            "+        0.00E+00  0.00E+00  0.00E+00  0.00E+00  0.00E+00  0.00E+00  0.00E+00  0.00E+00  1.35E-01\r\n" +
            " \r\n" +
            "\r\n" +
            "\r\n" +
            " SIGMA - COV MATRIX FOR RANDOM EFFECTS - EPSILONS  ****\r\n" +
            "\r\n" +
            "\r\n" +
            "            EPS1      EPS2\r\n" +
            " \r\n" +
            " EPS1\r\n" +
            "+        5.16E-02\r\n" +
            " \r\n" +
            " EPS2\r\n" +
            "+        0.00E+00  8.81E-02\r\n" +
            " \r\n" +
            "Fri Sep 28 13:55:41 BST 2012\r\n" +
            "";

    @Mock
    JobManagementService jobManagementService;

    @Mock
    Job job;

    @Mock
    Invoker invoker;

    @Mock
    DetailedStatus status;

    @Mock
    FileFinder fileFinder;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Ignore
    @Test
    public void shouldGetRightMetrix() throws Exception {
        JobServiceImpl jobService = new JobServiceImpl();
        jobService.setJobManagementService(jobManagementService);
        when(jobManagementService.getJob(anyString())).thenReturn(job);
        when(job.getInvoker()).thenReturn(invoker);
        when(job.getGridJobCurrentWorkingDirectory()).thenReturn(FILE);
        when(job.getGridJobExecutionFile()).thenReturn(FILE);
        when(job.getDetailedStatus()).thenReturn(status);
        when(status.getRequestId()).thenReturn("1234567");
        when(status.getSummary()).thenReturn("this is the summary");
        ControlFile ctrl = new ControlFile(new StringReader(CONTROL_FILE), FILE.getAbsolutePath());
        ctrl.setFileFinder(fileFinder);
        when(fileFinder.findFiles(any(File.class), any(FileFilter.class))).thenReturn(new File[] { FILE });
        OutputFile output = new OutputFile(new StringReader(LST_FILE), FILE.getAbsolutePath());
        whenNew(OutputFile.class).withArguments(anyObject(), anyString()).thenReturn(output);
        whenNew(ControlFile.class).withArguments(anyObject(), anyString()).thenReturn(ctrl);
        when(invoker.execute(anyString(), anyString())).thenAnswer(new Answer<InvokerResult>() {
            private int count = 0;

            public InvokerResult answer(InvocationOnMock invocation) {
                if (count == 0) {
                    count++;
                    return new InvokerResult("", CONTROL_FILE, "", 0);
                }
                else if (count == 1) {
                    count++;
                    return new InvokerResult("", LST_FILE, "", 0);
                }
                else
                    return new InvokerResult("", EXT_FILE, "", 0);
            }
        });

        NmMetrics metrics = jobService.getNONMEMMetrics("1234567");
        assertTrue(metrics.getMetrics() != null);
        
        Iterator<String> i = metrics.getMetrics().keySet().iterator();
        while (i.hasNext()) {
        	String lable = i.next();
        	StringBuffer sb = new StringBuffer(lable + " : ");
        	Double[] d = metrics.getMetrics().get(lable);
        	for (Double dbl : d) {
        		sb.append(dbl).append(" ,");
        	}
        	System.out.println(sb.toString());
        }
        assertEquals((Double)0.0965, (Double)metrics.getMetrics().get("1 CLD")[0]);
        assertEquals((Double)0.0422, (Double)metrics.getMetrics().get("2 VD (also VL & VC)")[0]);
        assertEquals((Double)0.654, (Double)metrics.getMetrics().get("3 CLL")[0]);
        assertEquals((Double)0.346, (Double)metrics.getMetrics().get("4 RLI")[0]);
        assertEquals((Double)0.0774, (Double)metrics.getMetrics().get("5 VP")[0]);
        assertEquals((Double)0.133,(Double)metrics.getMetrics().get("6 PSD and PSL combined as RA study data insufficient information to estimate separately")[0]);
        assertEquals((Double)0.797, (Double)metrics.getMetrics().get("7 KD")[0]);
        assertEquals((Double)0.236, (Double)metrics.getMetrics().get("8 KA")[0]);
        assertEquals((Double)0.131, (Double)metrics.getMetrics().get("9 XX (for F1)")[0]);
        assertTrue(metrics.getMetrics().get("7 KD").length == 6);
        assertTrue(metrics.getMetrics().get("Total Ligand").length == 6);
        assertTrue(metrics.getSummary().equals("this is the summary"));
    }

    @Test
    @Ignore("Purely investigative method to print and inspect some stuff")
    public void shouldGetNonmemMetrics() {
        LinkedHashMap<String, List<Double>> columns = ExtFileParser.parseAsMap(Arrays.asList(EXT_FILE.split("\\r?\\n")));

        ControlFile ctrl = null;
        try {
            ctrl = new ControlFile(new StringReader(CONTROL_FILE));
        } catch (NonmemException e) {
        }
        List<String> parms = ctrl.getProblems().get(0).getAllThetaSigmaOmegaVariableParameters();

        NonmemProblem prob = ctrl.getProblems().get(0);

        System.out.println("--------- THETAS --------------------------------");
        NonmemThetaManager tm = prob.getThetaManager();
        NonmemThetaRO[] thetas = tm.getThetas();
        for (NonmemThetaRO t : thetas) {
            System.out.println(t.getLabel() + ":" + t.isFixed());
        }

        System.out.println("--------- SIMGAS --------------------------------");
        NonmemMatrixManager sm = prob.getSigmaManager();
        NonmemBlockElementRO[] sigmas = sm.getBlocks();
        for (NonmemBlockElementRO sigma : sigmas) {
            System.out.println(printMatrixParam(sigma));
        }

        System.out.println("--------- OMEGAS --------------------------------");
        NonmemMatrixManager om = prob.getOmegaManager();
        NonmemBlockElementRO[] omegas = om.getBlocks();
        for (NonmemBlockElementRO omega : omegas) {
            System.out.println(printMatrixParam(omega));
        }

        System.out.println("------------ lables ----");
        for (String label : om.getLabels()) {
            System.out.println(label);
        }

//        System.out.println("--------- values set in matrix");
//        for (boolean set : om.getValuesSetInMatrix()) {
//            System.out.println(set);
//        }

        System.out.println("--------- COLUMNS KEY SET (twice) --------------------------------");
        System.out.println(columns.keySet());
        System.out.println(columns.keySet());

        System.out.println("--------- COLUMNS KEY SET --------------------------------");
        System.out.println(columns);

        System.out.println("--------- CTL FILE TSO --------------------------------");
        System.out.println(parms);
    }

	private String printMatrixParam(NonmemBlockElementRO param) {
		return param.getLabel() + ":" + param.isFixed() + "(fixed) :" + param.isDiagonal() + "(Diagonal) :" + param.getX() + "(X) :" + param.getY() + "(Y) :" + param.isOnDiagonal() + "(onDiagonal)";
	}
}
