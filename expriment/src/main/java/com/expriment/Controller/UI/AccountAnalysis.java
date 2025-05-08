package com.expriment.Controller.UI;



import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountAnalysis {

    private MonthlyDetails monthlyDetails;

    @XmlElementWrapper(name = "EODBalances")
    @XmlElement(name = "EODBalance")
    private List<EODBalances> eODBalances;

    public static class MonthlyDetails {

        @XmlElementWrapper(name = "MonthlyDetail")
//        @XmlElement(name = "Detail")
        private List<MonthlyDetail> monthlyDetailList;

        public static class MonthlyDetail {
            private String monthName;
            private String avgUtilization;
            private String bal10;
            private String bal15;
            private String bal17;
            private String bal2;
            private String bal20;
            private String bal25;
            private String bal30;
            private String bal4;
            private String bal5;
            private String balAvg;
            private String balLast;
            private String balMax;
            private String balMin;
            private String balTopNAvg;
            private String cashDeposits;
            private String cashWithdrawals;
            private String chqDeposits;
            private String chqIssues;
            private String credits;
            private String creditsSC;
            private String creditsSelf;
            private String debits;
            private String debitsSC;
            private String debitsSelf;
            private String dpLimit;
            private String emiOrLoans;
            private String highestSalaryDate;
            private String intPayDelay;
            private String inwBounceNonTechnical;
            private String inwBounceTechnical;
            private String inwBounces;
            private String inwChqBounceNonTechnical;
            private String inwEMIBounces;
            private String loanDisbursals;
            private String outwBounces;
            private String overdrawnAmount;
            private String overdrawnDays;
            private String overdrawnDaysPeak;
            private String overdrawnInstances;
            private String salaries;
            private String snLimit;
            private String startDate;
            private String totalBankCharge;
            private String totalCashDeposit;
            private String totalCashWithdrawal;
            private String totalChqDeposit;
            private String totalChqIssue;
            private String totalCredit;
            private String totalCreditCardPayment;
            private String totalCreditSC;
            private String totalCreditSelf;
            private String totalDebit;
            private String totalDebitSC;
            private String totalDebitSelf;
            private String totalEmiIssue;
            private String totalEmiOrLoan;
            private String totalInterestCharged;
            private String totalInterestIncome;
            private String totalInvExpense;
            private String totalInvIncome;
            private String totalLoanDisbursal;
            private String totalOtherExpense;
            private String totalOtherIncome;
            private String totalPaidSalary;
            private String totalPension;
            private String totalSalary;
            private String totalUtilityExpense;

            public String getMonthName() {
                return monthName;
            }

            public void setMonthName(String monthName) {
                this.monthName = monthName;
            }

            public String getAvgUtilization() {
                return avgUtilization;
            }

            public void setAvgUtilization(String avgUtilization) {
                this.avgUtilization = avgUtilization;
            }

            public String getBal10() {
                return bal10;
            }

            public void setBal10(String bal10) {
                this.bal10 = bal10;
            }

            public String getBal15() {
                return bal15;
            }

            public void setBal15(String bal15) {
                this.bal15 = bal15;
            }

            public String getBal17() {
                return bal17;
            }

            public void setBal17(String bal17) {
                this.bal17 = bal17;
            }

            public String getBal2() {
                return bal2;
            }

            public void setBal2(String bal2) {
                this.bal2 = bal2;
            }

            public String getBal20() {
                return bal20;
            }

            public void setBal20(String bal20) {
                this.bal20 = bal20;
            }

            public String getBal25() {
                return bal25;
            }

            public void setBal25(String bal25) {
                this.bal25 = bal25;
            }

            public String getBal30() {
                return bal30;
            }

            public void setBal30(String bal30) {
                this.bal30 = bal30;
            }

            public String getBal4() {
                return bal4;
            }

            public void setBal4(String bal4) {
                this.bal4 = bal4;
            }

            public String getBal5() {
                return bal5;
            }

            public void setBal5(String bal5) {
                this.bal5 = bal5;
            }

            public String getBalAvg() {
                return balAvg;
            }

            public void setBalAvg(String balAvg) {
                this.balAvg = balAvg;
            }

            public String getBalLast() {
                return balLast;
            }

            public void setBalLast(String balLast) {
                this.balLast = balLast;
            }

            public String getBalMax() {
                return balMax;
            }

            public void setBalMax(String balMax) {
                this.balMax = balMax;
            }

            public String getBalMin() {
                return balMin;
            }

            public void setBalMin(String balMin) {
                this.balMin = balMin;
            }

            public String getBalTopNAvg() {
                return balTopNAvg;
            }

            public void setBalTopNAvg(String balTopNAvg) {
                this.balTopNAvg = balTopNAvg;
            }

            public String getCashDeposits() {
                return cashDeposits;
            }

            public void setCashDeposits(String cashDeposits) {
                this.cashDeposits = cashDeposits;
            }

            public String getCashWithdrawals() {
                return cashWithdrawals;
            }

            public void setCashWithdrawals(String cashWithdrawals) {
                this.cashWithdrawals = cashWithdrawals;
            }

            public String getChqDeposits() {
                return chqDeposits;
            }

            public void setChqDeposits(String chqDeposits) {
                this.chqDeposits = chqDeposits;
            }

            public String getChqIssues() {
                return chqIssues;
            }

            public void setChqIssues(String chqIssues) {
                this.chqIssues = chqIssues;
            }

            public String getCredits() {
                return credits;
            }

            public void setCredits(String credits) {
                this.credits = credits;
            }

            public String getCreditsSC() {
                return creditsSC;
            }

            public void setCreditsSC(String creditsSC) {
                this.creditsSC = creditsSC;
            }

            public String getCreditsSelf() {
                return creditsSelf;
            }

            public void setCreditsSelf(String creditsSelf) {
                this.creditsSelf = creditsSelf;
            }

            public String getDebits() {
                return debits;
            }

            public void setDebits(String debits) {
                this.debits = debits;
            }

            public String getDebitsSC() {
                return debitsSC;
            }

            public void setDebitsSC(String debitsSC) {
                this.debitsSC = debitsSC;
            }

            public String getDebitsSelf() {
                return debitsSelf;
            }

            public void setDebitsSelf(String debitsSelf) {
                this.debitsSelf = debitsSelf;
            }

            public String getDpLimit() {
                return dpLimit;
            }

            public void setDpLimit(String dpLimit) {
                this.dpLimit = dpLimit;
            }

            public String getEmiOrLoans() {
                return emiOrLoans;
            }

            public void setEmiOrLoans(String emiOrLoans) {
                this.emiOrLoans = emiOrLoans;
            }

            public String getHighestSalaryDate() {
                return highestSalaryDate;
            }

            public void setHighestSalaryDate(String highestSalaryDate) {
                this.highestSalaryDate = highestSalaryDate;
            }

            public String getIntPayDelay() {
                return intPayDelay;
            }

            public void setIntPayDelay(String intPayDelay) {
                this.intPayDelay = intPayDelay;
            }

            public String getInwBounceNonTechnical() {
                return inwBounceNonTechnical;
            }

            public void setInwBounceNonTechnical(String inwBounceNonTechnical) {
                this.inwBounceNonTechnical = inwBounceNonTechnical;
            }

            public String getInwBounceTechnical() {
                return inwBounceTechnical;
            }

            public void setInwBounceTechnical(String inwBounceTechnical) {
                this.inwBounceTechnical = inwBounceTechnical;
            }

            public String getInwBounces() {
                return inwBounces;
            }

            public void setInwBounces(String inwBounces) {
                this.inwBounces = inwBounces;
            }

            public String getInwChqBounceNonTechnical() {
                return inwChqBounceNonTechnical;
            }

            public void setInwChqBounceNonTechnical(String inwChqBounceNonTechnical) {
                this.inwChqBounceNonTechnical = inwChqBounceNonTechnical;
            }

            public String getInwEMIBounces() {
                return inwEMIBounces;
            }

            public void setInwEMIBounces(String inwEMIBounces) {
                this.inwEMIBounces = inwEMIBounces;
            }

            public String getLoanDisbursals() {
                return loanDisbursals;
            }

            public void setLoanDisbursals(String loanDisbursals) {
                this.loanDisbursals = loanDisbursals;
            }

            public String getOutwBounces() {
                return outwBounces;
            }

            public void setOutwBounces(String outwBounces) {
                this.outwBounces = outwBounces;
            }

            public String getOverdrawnAmount() {
                return overdrawnAmount;
            }

            public void setOverdrawnAmount(String overdrawnAmount) {
                this.overdrawnAmount = overdrawnAmount;
            }

            public String getOverdrawnDays() {
                return overdrawnDays;
            }

            public void setOverdrawnDays(String overdrawnDays) {
                this.overdrawnDays = overdrawnDays;
            }

            public String getOverdrawnDaysPeak() {
                return overdrawnDaysPeak;
            }

            public void setOverdrawnDaysPeak(String overdrawnDaysPeak) {
                this.overdrawnDaysPeak = overdrawnDaysPeak;
            }

            public String getOverdrawnInstances() {
                return overdrawnInstances;
            }

            public void setOverdrawnInstances(String overdrawnInstances) {
                this.overdrawnInstances = overdrawnInstances;
            }

            public String getSalaries() {
                return salaries;
            }

            public void setSalaries(String salaries) {
                this.salaries = salaries;
            }

            public String getSnLimit() {
                return snLimit;
            }

            public void setSnLimit(String snLimit) {
                this.snLimit = snLimit;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getTotalBankCharge() {
                return totalBankCharge;
            }

            public void setTotalBankCharge(String totalBankCharge) {
                this.totalBankCharge = totalBankCharge;
            }

            public String getTotalCashDeposit() {
                return totalCashDeposit;
            }

            public void setTotalCashDeposit(String totalCashDeposit) {
                this.totalCashDeposit = totalCashDeposit;
            }

            public String getTotalCashWithdrawal() {
                return totalCashWithdrawal;
            }

            public void setTotalCashWithdrawal(String totalCashWithdrawal) {
                this.totalCashWithdrawal = totalCashWithdrawal;
            }

            public String getTotalChqDeposit() {
                return totalChqDeposit;
            }

            public void setTotalChqDeposit(String totalChqDeposit) {
                this.totalChqDeposit = totalChqDeposit;
            }

            public String getTotalChqIssue() {
                return totalChqIssue;
            }

            public void setTotalChqIssue(String totalChqIssue) {
                this.totalChqIssue = totalChqIssue;
            }

            public String getTotalCredit() {
                return totalCredit;
            }

            public void setTotalCredit(String totalCredit) {
                this.totalCredit = totalCredit;
            }

            public String getTotalCreditCardPayment() {
                return totalCreditCardPayment;
            }

            public void setTotalCreditCardPayment(String totalCreditCardPayment) {
                this.totalCreditCardPayment = totalCreditCardPayment;
            }

            public String getTotalCreditSC() {
                return totalCreditSC;
            }

            public void setTotalCreditSC(String totalCreditSC) {
                this.totalCreditSC = totalCreditSC;
            }

            public String getTotalCreditSelf() {
                return totalCreditSelf;
            }

            public void setTotalCreditSelf(String totalCreditSelf) {
                this.totalCreditSelf = totalCreditSelf;
            }

            public String getTotalDebit() {
                return totalDebit;
            }

            public void setTotalDebit(String totalDebit) {
                this.totalDebit = totalDebit;
            }

            public String getTotalDebitSC() {
                return totalDebitSC;
            }

            public void setTotalDebitSC(String totalDebitSC) {
                this.totalDebitSC = totalDebitSC;
            }

            public String getTotalDebitSelf() {
                return totalDebitSelf;
            }

            public void setTotalDebitSelf(String totalDebitSelf) {
                this.totalDebitSelf = totalDebitSelf;
            }

            public String getTotalEmiIssue() {
                return totalEmiIssue;
            }

            public void setTotalEmiIssue(String totalEmiIssue) {
                this.totalEmiIssue = totalEmiIssue;
            }

            public String getTotalEmiOrLoan() {
                return totalEmiOrLoan;
            }

            public void setTotalEmiOrLoan(String totalEmiOrLoan) {
                this.totalEmiOrLoan = totalEmiOrLoan;
            }

            public String getTotalInterestCharged() {
                return totalInterestCharged;
            }

            public void setTotalInterestCharged(String totalInterestCharged) {
                this.totalInterestCharged = totalInterestCharged;
            }

            public String getTotalInterestIncome() {
                return totalInterestIncome;
            }

            public void setTotalInterestIncome(String totalInterestIncome) {
                this.totalInterestIncome = totalInterestIncome;
            }

            public String getTotalInvExpense() {
                return totalInvExpense;
            }

            public void setTotalInvExpense(String totalInvExpense) {
                this.totalInvExpense = totalInvExpense;
            }

            public String getTotalInvIncome() {
                return totalInvIncome;
            }

            public void setTotalInvIncome(String totalInvIncome) {
                this.totalInvIncome = totalInvIncome;
            }

            public String getTotalLoanDisbursal() {
                return totalLoanDisbursal;
            }

            public void setTotalLoanDisbursal(String totalLoanDisbursal) {
                this.totalLoanDisbursal = totalLoanDisbursal;
            }

            public String getTotalOtherExpense() {
                return totalOtherExpense;
            }

            public void setTotalOtherExpense(String totalOtherExpense) {
                this.totalOtherExpense = totalOtherExpense;
            }

            public String getTotalOtherIncome() {
                return totalOtherIncome;
            }

            public void setTotalOtherIncome(String totalOtherIncome) {
                this.totalOtherIncome = totalOtherIncome;
            }

            public String getTotalPaidSalary() {
                return totalPaidSalary;
            }

            public void setTotalPaidSalary(String totalPaidSalary) {
                this.totalPaidSalary = totalPaidSalary;
            }

            public String getTotalPension() {
                return totalPension;
            }

            public void setTotalPension(String totalPension) {
                this.totalPension = totalPension;
            }

            public String getTotalSalary() {
                return totalSalary;
            }

            public void setTotalSalary(String totalSalary) {
                this.totalSalary = totalSalary;
            }

            public String getTotalUtilityExpense() {
                return totalUtilityExpense;
            }

            public void setTotalUtilityExpense(String totalUtilityExpense) {
                this.totalUtilityExpense = totalUtilityExpense;
            }
        }

        public List<MonthlyDetail> getMonthlyDetailList() {
            return monthlyDetailList;
        }

        public void setMonthlyDetailList(List<MonthlyDetail> monthlyDetailList) {
            this.monthlyDetailList = monthlyDetailList;
        }
    }

    public static class EODBalances {
        private String date;
        private String balance;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
    }

    public MonthlyDetails getMonthlyDetails() {
        return monthlyDetails;
    }

    public void setMonthlyDetails(MonthlyDetails monthlyDetails) {
        this.monthlyDetails = monthlyDetails;
    }

    public List<EODBalances> geteODBalances() {
        return eODBalances;
    }

    public void seteODBalances(List<EODBalances> eODBalances) {
        this.eODBalances = eODBalances;
    }
}





/*
package com.expriment.Controller.UI;

import com.expriment.Controller.ResponseData;

import java.util.List;

public class AccountAnalysis {

    private MonthlyDetails monthlyDetails;


    private List<EODBalances> eODBalances;

    public static class MonthlyDetails {

        private List<MonthlyDetail> monthlyDetail;


        public static class MonthlyDetail {
            private String monthName;
            private String avgUtilization;
            private String bal10;
            private String bal15;
            private String bal17;
            private String bal2;
            private String bal20;
            private String bal25;
            private String bal30;
            private String bal4;
            private String bal5;
            private String balAvg;
            private String balLast;
            private String balMax;
            private String balMin;
            private String balTopNAvg;
            private String cashDeposits;
            private String cashWithdrawals;
            private String chqDeposits;
            private String chqIssues;
            private String credits;
            private String creditsSC;
            private String creditsSelf;
            private String debits;
            private String debitsSC;
            private String debitsSelf;
            private String dpLimit;
            private String emiOrLoans;
            private String highestSalaryDate;
            private String intPayDelay;
            private String inwBounceNonTechnical;
            private String inwBounceTechnical;
            private String inwBounces;
            private String inwChqBounceNonTechnical;
            private String inwEMIBounces;
            private String loanDisbursals;
            private String outwBounces;
            private String overdrawnAmount;
            private String overdrawnDays;
            private String overdrawnDaysPeak;
            private String overdrawnInstances;
            private String salaries;
            private String snLimit;
            private String startDate;
            private String totalBankCharge;
            private String totalCashDeposit;
            private String totalCashWithdrawal;
            private String totalChqDeposit;
            private String totalChqIssue;
            private String totalCredit;
            private String totalCreditCardPayment;
            private String totalCreditSC;
            private String totalCreditSelf;
            private String totalDebit;
            private String totalDebitSC;
            private String totalDebitSelf;
            private String totalEmiIssue;
            private String totalEmiOrLoan;
            private String totalInterestCharged;
            private String totalInterestIncome;
            private String totalInvExpense;
            private String totalInvIncome;
            private String totalLoanDisbursal;
            private String totalOtherExpense;
            private String totalOtherIncome;
            private String totalPaidSalary;
            private String totalPension;
            private String totalSalary;
            private String totalUtilityExpense;

        }
    }


    public static class EODBalances {

        private String date;
        private String balance;
    }
}*/
