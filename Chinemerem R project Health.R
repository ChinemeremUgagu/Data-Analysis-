setwd("C:/Users/cugagu/Document")
getwd()
HospitalCosts<-read.csv("Hospital.csv")
 summary(HospitalCosts)
hist(HospitalCosts$AGE)
 table(as.factor(HospitalCosts$AGE))
 summary(as.factor(HospitalCosts$AGE))
 #Finding the age of people who frequent the hospital,and has the max expenditure.
 aggregate(TOTCHG ~AGE,FUN = sum,data = HospitalCosts)
 which.max(summary(as.factor(HospitalCosts$APRDRG)))
  # determining the diagnosis related groups with max hospitalization & treatment
 diagnosiscost <- aggregate(TOTCHG ~APRDRG,FUN = sum,data = HospitalCosts)
 diagnosiscost
 diagnosiscost[which.max(diagnosiscost$TOTCHG),]
 # Comparing the race of patients by expenditure
 sapply(HospitalCosts, function(x) sum(is.na(x)))
 HospitalCosts <- na.omit(HospitalCosts)
 summary(as.factor(HospitalCosts$RACE))
 HospitalCosts$RACE <- as.factor(HospitalCosts$RACE)
 modl <- aov(TOTCHG ~ RACE,data = HospitalCosts)
 modl
 # summary(modl)
 summary(HospitalCosts$RACE)
 # what is the relationship btw hospitals cost by age and gender?
 mod2 <- lm(TOTCHG ~ AGE+FEMALE,data= HospitalCosts)
 summary(mod2)
 # predicting if the length of stay on age, gender and race
 mod3 <- lm(LOS ~ AGE+FEMALE+RACE,data = HospitalCosts)
 summary(mod3)
 # What are the main variables that affect the hospital cost
 mod4 <- lm(TOTCHG ~.,data = HospitalCosts)
 summary(mod4)




