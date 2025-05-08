package com.expriment.Controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseData {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("CustomerInfo")
    private CustomerInfo customerInfo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("statementdetails")
    private StatementDetails statementdetails;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("accountAnalysis")
    private AccountAnalysis accountAnalysis;

    @JsonProperty("combinedMonthlyDetails")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CombinedMonthlyDetails> combinedMonthlyDetails;

    @JsonProperty("additionalABBSummaryDetails")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AdditionalABBSummaryDetails additionalABBSummaryDetails;

    @JsonProperty("additionalMonthlyFinOneDetails")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AdditionalMonthlyFinOneDetails additionalMonthlyFinOneDetails;

    @JsonProperty("additionalOverallDetails")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AdditionalOverallDetails additionalOverallDetails;

    @JsonProperty("additionalPartialMonthDetails")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AdditionalPartialMonthDetails additionalPartialMonthDetails;

    @JsonProperty("additionalMonthlyDetails")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AdditionalMonthlyDetails additionalMonthlyDetails;

    @JsonProperty("Xns")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Xns xns;

    // Getters and Setters


    public Xns getXns() {
        return xns;
    }

    public void setXns(Xns xns) {
        this.xns = xns;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public StatementDetails getStatementdetails() {
        return statementdetails;
    }

    public void setStatementdetails(StatementDetails statementdetails) {
        this.statementdetails = statementdetails;
    }

    public AccountAnalysis getAccountAnalysis() {
        return accountAnalysis;
    }

    public void setAccountAnalysis(AccountAnalysis accountAnalysis) {
        this.accountAnalysis = accountAnalysis;
    }

    public List<CombinedMonthlyDetails> getCombinedMonthlyDetails() {
        return combinedMonthlyDetails;
    }

    public void setCombinedMonthlyDetails(List<CombinedMonthlyDetails> combinedMonthlyDetails) {
        this.combinedMonthlyDetails = combinedMonthlyDetails;
    }

    public AdditionalABBSummaryDetails getAdditionalABBSummaryDetails() {
        return additionalABBSummaryDetails;
    }

    public void setAdditionalABBSummaryDetails(AdditionalABBSummaryDetails additionalABBSummaryDetails) {
        this.additionalABBSummaryDetails = additionalABBSummaryDetails;
    }

    public AdditionalMonthlyFinOneDetails getAdditionalMonthlyFinOneDetails() {
        return additionalMonthlyFinOneDetails;
    }

    public void setAdditionalMonthlyFinOneDetails(AdditionalMonthlyFinOneDetails additionalMonthlyFinOneDetails) {
        this.additionalMonthlyFinOneDetails = additionalMonthlyFinOneDetails;
    }

    public AdditionalOverallDetails getAdditionalOverallDetails() {
        return additionalOverallDetails;
    }

    public void setAdditionalOverallDetails(AdditionalOverallDetails additionalOverallDetails) {
        this.additionalOverallDetails = additionalOverallDetails;
    }

    public AdditionalPartialMonthDetails getAdditionalPartialMonthDetails() {
        return additionalPartialMonthDetails;
    }

    public void setAdditionalPartialMonthDetails(AdditionalPartialMonthDetails additionalPartialMonthDetails) {
        this.additionalPartialMonthDetails = additionalPartialMonthDetails;
    }

    public AdditionalMonthlyDetails getAdditionalMonthlyDetails() {
        return additionalMonthlyDetails;
    }

    public void setAdditionalMonthlyDetails(AdditionalMonthlyDetails additionalMonthlyDetails) {
        this.additionalMonthlyDetails = additionalMonthlyDetails;
    }

    public static class CustomerInfo {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String session_id;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String address;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String bank;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String name;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String email;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String pan;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String instId;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String landline;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String mobile;

        // Getters and Setters

        public String getSession_id() {
            return session_id;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPan() {
            return pan;
        }

        public void setPan(String pan) {
            this.pan = pan;
        }

        public String getInstId() {
            return instId;
        }

        public void setInstId(String instId) {
            this.instId = instId;
        }

        public String getLandline() {
            return landline;
        }

        public void setLandline(String landline) {
            this.landline = landline;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }

    public static class StatementDetails {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private StatementRemarks statementRemarks;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Statement statement;

        // Getters and Setters


        public StatementRemarks getStatementRemarks() {
            return statementRemarks;
        }

        public void setStatementRemarks(StatementRemarks statementRemarks) {
            this.statementRemarks = statementRemarks;
        }

        public Statement getStatement() {
            return statement;
        }

        public void setStatement(Statement statement) {
            this.statement = statement;
        }

        public static class StatementRemarks {
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private String remarks;

            // Getters and Setters

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }
        }

        public static class Statement {
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private String fileName;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private String statement_id;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private String statementStatus;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private CustomerInfo customerInfo;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private StatementAccounts statementAccounts;

            // Getters and Setters


            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public String getStatement_id() {
                return statement_id;
            }

            public void setStatement_id(String statement_id) {
                this.statement_id = statement_id;
            }

            public String getStatementStatus() {
                return statementStatus;
            }

            public void setStatementStatus(String statementStatus) {
                this.statementStatus = statementStatus;
            }

            public CustomerInfo getCustomerInfo() {
                return customerInfo;
            }

            public void setCustomerInfo(CustomerInfo customerInfo) {
                this.customerInfo = customerInfo;
            }

            public StatementAccounts getStatementAccounts() {
                return statementAccounts;
            }

            public void setStatementAccounts(StatementAccounts statementAccounts) {
                this.statementAccounts = statementAccounts;
            }

            public static class StatementAccounts {
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private StatementAccount statementAccount;

                // Getters and Setters


                public StatementAccount getStatementAccount() {
                    return statementAccount;
                }

                public void setStatementAccount(StatementAccount statementAccount) {
                    this.statementAccount = statementAccount;
                }

                public static class StatementAccount {
                    @JsonInclude(JsonInclude.Include.NON_NULL)
                    private String accountID;
                    @JsonInclude(JsonInclude.Include.NON_NULL)
                    private String accountNo;
                    @JsonInclude(JsonInclude.Include.NON_NULL)
                    private String accountType;
                    @JsonInclude(JsonInclude.Include.NON_NULL)
                    private String xnsStartDate;
                    @JsonInclude(JsonInclude.Include.NON_NULL)
                    private String xnsEndDate;

                    // Getters and Setters


                    public String getAccountID() {
                        return accountID;
                    }

                    public void setAccountID(String accountID) {
                        this.accountID = accountID;
                    }

                    public String getAccountNo() {
                        return accountNo;
                    }

                    public void setAccountNo(String accountNo) {
                        this.accountNo = accountNo;
                    }

                    public String getAccountType() {
                        return accountType;
                    }

                    public void setAccountType(String accountType) {
                        this.accountType = accountType;
                    }

                    public String getXnsStartDate() {
                        return xnsStartDate;
                    }

                    public void setXnsStartDate(String xnsStartDate) {
                        this.xnsStartDate = xnsStartDate;
                    }

                    public String getXnsEndDate() {
                        return xnsEndDate;
                    }

                    public void setXnsEndDate(String xnsEndDate) {
                        this.xnsEndDate = xnsEndDate;
                    }
                }
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccountAnalysis {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String accountNo;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String accountID;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String accountType;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String loanTrackDetails;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("SummaryInfo")
        private SummaryInfo summaryInfo;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private MonthlyDetails monthlyDetails;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<EODBalances> eODBalances;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Top10FundsReceived top10FundsReceived;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Top10PaymentsReceived top10PaymentsReceived;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String EMILOANXns;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Top10FundsTransferred top10FundsTransferred;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String BouncedOrPenalXns;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private RegularCredits regularCredits;

        public String getBouncedOrPenalXns() {
            return BouncedOrPenalXns;
        }

        public void setBouncedOrPenalXns(String bouncedOrPenalXns) {
            BouncedOrPenalXns = bouncedOrPenalXns;
        }

        public RegularCredits getRegularCredits() {
            return regularCredits;
        }

        public void setRegularCredits(RegularCredits regularCredits) {
            this.regularCredits = regularCredits;
        }

        public Top10FundsTransferred getTop10FundsTransferred() {
            return top10FundsTransferred;
        }

        public void setTop10FundsTransferred(Top10FundsTransferred top10FundsTransferred) {
            this.top10FundsTransferred = top10FundsTransferred;
        }

        public String getEMILOANXns() {
            return EMILOANXns;
        }

        public void setEMILOANXns(String EMILOANXns) {
            this.EMILOANXns = EMILOANXns;
        }

        public Top10PaymentsReceived getTop10PaymentsReceived() {
            return top10PaymentsReceived;
        }

        public void setTop10PaymentsReceived(Top10PaymentsReceived top10PaymentsReceived) {
            this.top10PaymentsReceived = top10PaymentsReceived;
        }

        public Top10FundsReceived getTop10FundsReceived() {
            return top10FundsReceived;
        }

        public void setTop10FundsReceived(Top10FundsReceived top10FundsReceived) {
            this.top10FundsReceived = top10FundsReceived;
        }
// Getters and Setters


        public List<EODBalances> geteODBalances() {
            return eODBalances;
        }

        public void seteODBalances(List<EODBalances> eODBalances) {
            this.eODBalances = eODBalances;
        }

        public MonthlyDetails getMonthlyDetails() {
            return monthlyDetails;
        }

        public void setMonthlyDetails(MonthlyDetails monthlyDetails) {
            this.monthlyDetails = monthlyDetails;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public String getAccountID() {
            return accountID;
        }

        public void setAccountID(String accountID) {
            this.accountID = accountID;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getLoanTrackDetails() {
            return loanTrackDetails;
        }

        public void setLoanTrackDetails(String loanTrackDetails) {
            this.loanTrackDetails = loanTrackDetails;
        }

        public SummaryInfo getSummaryInfo() {
            return summaryInfo;
        }

        public void setSummaryInfo(SummaryInfo summaryInfo) {
            this.summaryInfo = summaryInfo;
        }

        public static class SummaryInfo {
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private String accNo;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private String accountID;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private String accType;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private String fullMonthCount;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private String instName;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private Total total;
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private Average average;

            // Getters and Setters


            public String getAccNo() {
                return accNo;
            }

            public void setAccNo(String accNo) {
                this.accNo = accNo;
            }

            public String getAccountID() {
                return accountID;
            }

            public void setAccountID(String accountID) {
                this.accountID = accountID;
            }

            public String getAccType() {
                return accType;
            }

            public void setAccType(String accType) {
                this.accType = accType;
            }

            public String getFullMonthCount() {
                return fullMonthCount;
            }

            public void setFullMonthCount(String fullMonthCount) {
                this.fullMonthCount = fullMonthCount;
            }

            public String getInstName() {
                return instName;
            }

            public void setInstName(String instName) {
                this.instName = instName;
            }

            public Total getTotal() {
                return total;
            }

            public void setTotal(Total total) {
                this.total = total;
            }

            public Average getAverage() {
                return average;
            }

            public void setAverage(Average average) {
                this.average = average;
            }

            public class Total {

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String avgUtilization;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal10;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal15;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal17;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal2;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal20;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal25;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal30;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal4;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal5;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String balAvg;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String balLast;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String balMax;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String balMin;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String balTopNAvg;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String cashDeposits;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String cashWithdrawals;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String chqDeposits;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String chqIssues;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String credits;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String creditsSC;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String creditsSelf;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String debits;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String debitsSC;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String debitsSelf;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String dpLimit;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String emiOrLoans;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String intPayDelay;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String inwBounceNonTechnical;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String inwBounceTechnical;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String inwBounces;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String inwChqBounceNonTechnical;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String inwEMIBounces;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String loanDisbursals;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String outwBounces;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String overdrawnAmount;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String overdrawnDays;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String overdrawnDaysPeak;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String overdrawnInstances;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String salaries;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String snLimit;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalBankCharge;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalCashDeposit;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalCashWithdrawal;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalChqDeposit;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalChqIssue;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalCredit;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalCreditCardPayment;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalCreditSC;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalCreditSelf;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalDebit;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalDebitSC;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalDebitSelf;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalEmiIssue;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalEmiOrLoan;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalInterestCharged;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalInterestIncome;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalInvExpense;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalInvIncome;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalLoanDisbursal;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalOtherExpense;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalOtherIncome;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalPaidSalary;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalPension;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalSalary;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalUtilityExpense;

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

            public class Average {

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String avgUtilization;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal10;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal15;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal17;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal2;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal20;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal25;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal30;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal4;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String bal5;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String balAvg;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String balLast;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String balMax;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String balMin;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String balTopNAvg;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String cashDeposits;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String cashWithdrawals;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String chqDeposits;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String chqIssues;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String credits;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String creditsSC;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String creditsSelf;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String debits;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String debitsSC;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String debitsSelf;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String dpLimit;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String emiOrLoans;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String intPayDelay;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String inwBounceNonTechnical;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String inwBounceTechnical;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String inwBounces;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String inwChqBounceNonTechnical;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String inwEMIBounces;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String loanDisbursals;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String outwBounces;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String overdrawnAmount;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String overdrawnDays;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String overdrawnDaysPeak;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String overdrawnInstances;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String salaries;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String snLimit;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalBankCharge;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalCashDeposit;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalCashWithdrawal;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalChqDeposit;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalChqIssue;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalCredit;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalCreditCardPayment;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalCreditSC;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalCreditSelf;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalDebit;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalDebitSC;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalDebitSelf;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalEmiIssue;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalEmiOrLoan;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalInterestCharged;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalInterestIncome;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalInvExpense;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalInvIncome;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalLoanDisbursal;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalOtherExpense;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalOtherIncome;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalPaidSalary;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalPension;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalSalary;

                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalUtilityExpense;

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

        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class MonthlyDetails {

            private List<MonthlyDetail> monthlyDetail;

            public List<MonthlyDetail> getMonthlyDetail() {
                return monthlyDetail;
            }

            public void setMonthlyDetail(List<MonthlyDetail> monthlyDetail) {
                this.monthlyDetail = monthlyDetail;
            }

            @JsonInclude(JsonInclude.Include.NON_NULL)
            static class MonthlyDetail {
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
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
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
            /* private List<EODBalance> eodBalances;

            public List<EODBalance> getEodBalances() {
                return eodBalances;
            }

            public void setEodBalances(List<EODBalance> eodBalances) {
                this.eodBalances = eodBalances;
            }*/

          /*  @JsonInclude(JsonInclude.Include.NON_NULL)
            public static class EODBalance {
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
            }*/
        }

        public static class Top10FundsReceived {
            private List<Item> Item;

            public List<Item> getItem() {
                return Item;
            }

            public void setItem(List<Item> item) {
                Item = item;
            }

            public static class Item {
                private String amount;
                private String month;
                private String category;

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public String getMonth() {
                    return month;
                }

                public void setMonth(String month) {
                    this.month = month;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }
            }
        }

        public static class Top10PaymentsReceived {
            private List<PaymentItem> Item;

            public List<PaymentItem> getItem() {
                return Item;
            }

            public void setItem(List<PaymentItem> item) {
                Item = item;
            }

            public static class PaymentItem {
                private String amount;
                private String count;
                private String party;

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }

                public String getParty() {
                    return party;
                }

                public void setParty(String party) {
                    this.party = party;
                }
            }
        }

        public static class Top10FundsTransferred {
            private List<TransferredItem> Item;

            public List<TransferredItem> getItem() {
                return Item;
            }

            public void setItem(List<TransferredItem> item) {
                Item = item;
            }

            public static class TransferredItem {
                private String amount;
                private String month;
                private String category;

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public String getMonth() {
                    return month;
                }

                public void setMonth(String month) {
                    this.month = month;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }
            }
        }

        public static class RegularCredits {
            private List<CreditTransaction> RXn;

            public List<CreditTransaction> getRXn() {
                return RXn;
            }

            public void setRXn(List<CreditTransaction> RXn) {
                this.RXn = RXn;
            }

            public static class CreditTransaction {
                private String amount;
                private String balance;
                private String date;
                private String narration;
                private String chqNo;
                private String category;
                private String group;

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public String getBalance() {
                    return balance;
                }

                public void setBalance(String balance) {
                    this.balance = balance;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getNarration() {
                    return narration;
                }

                public void setNarration(String narration) {
                    this.narration = narration;
                }

                public String getChqNo() {
                    return chqNo;
                }

                public void setChqNo(String chqNo) {
                    this.chqNo = chqNo;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public String getGroup() {
                    return group;
                }

                public void setGroup(String group) {
                    this.group = group;
                }
            }
        }


    }

    public static class CombinedMonthlyDetails {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String monthName;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String startDate;

        // Getters and Setters

        public String getMonthName() {
            return monthName;
        }

        public void setMonthName(String monthName) {
            this.monthName = monthName;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }
    }

    public static class AdditionalABBSummaryDetails {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private SummaryABBDetail1 summaryABBDetail1;

        // Getters and Setters


        public SummaryABBDetail1 getSummaryABBDetail1() {
            return summaryABBDetail1;
        }

        public void setSummaryABBDetail1(SummaryABBDetail1 summaryABBDetail1) {
            this.summaryABBDetail1 = summaryABBDetail1;
        }

        public static class SummaryABBDetail1 {
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private AdditionalSummaryInfo additionalSummaryInfo;

            // Getters and Setters


            public AdditionalSummaryInfo getAdditionalSummaryInfo() {
                return additionalSummaryInfo;
            }

            public void setAdditionalSummaryInfo(AdditionalSummaryInfo additionalSummaryInfo) {
                this.additionalSummaryInfo = additionalSummaryInfo;
            }

            public static class AdditionalSummaryInfo {
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String averageBankBalance;
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String inwardRTN;
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String outwardRTN;
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalAvgOfTopBalances;

                // Getters and Setters


                public String getAverageBankBalance() {
                    return averageBankBalance;
                }

                public void setAverageBankBalance(String averageBankBalance) {
                    this.averageBankBalance = averageBankBalance;
                }

                public String getInwardRTN() {
                    return inwardRTN;
                }

                public void setInwardRTN(String inwardRTN) {
                    this.inwardRTN = inwardRTN;
                }

                public String getOutwardRTN() {
                    return outwardRTN;
                }

                public void setOutwardRTN(String outwardRTN) {
                    this.outwardRTN = outwardRTN;
                }

                public String getTotalAvgOfTopBalances() {
                    return totalAvgOfTopBalances;
                }

                public void setTotalAvgOfTopBalances(String totalAvgOfTopBalances) {
                    this.totalAvgOfTopBalances = totalAvgOfTopBalances;
                }
            }
        }
    }

    public static class AdditionalMonthlyFinOneDetails {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private MonthlyFinOneDetail1 monthlyFinOneDetail1;

        // Getters and Setters


        public MonthlyFinOneDetail1 getMonthlyFinOneDetail1() {
            return monthlyFinOneDetail1;
        }

        public void setMonthlyFinOneDetail1(MonthlyFinOneDetail1 monthlyFinOneDetail1) {
            this.monthlyFinOneDetail1 = monthlyFinOneDetail1;
        }

        public static class MonthlyFinOneDetail1 {
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private List<Detail> detail;

            // Getters and Setters


            public List<Detail> getDetail() {
                return detail;
            }

            public void setDetail(List<Detail> detail) {
                this.detail = detail;
            }

            public static class Detail {
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String avgOf5Dates;
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String inwChqToDebit;
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String monthName;
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String outwChqToCredit;
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String totalBounces;

                // Getters and Setters


                public String getAvgOf5Dates() {
                    return avgOf5Dates;
                }

                public void setAvgOf5Dates(String avgOf5Dates) {
                    this.avgOf5Dates = avgOf5Dates;
                }

                public String getInwChqToDebit() {
                    return inwChqToDebit;
                }

                public void setInwChqToDebit(String inwChqToDebit) {
                    this.inwChqToDebit = inwChqToDebit;
                }

                public String getMonthName() {
                    return monthName;
                }

                public void setMonthName(String monthName) {
                    this.monthName = monthName;
                }

                public String getOutwChqToCredit() {
                    return outwChqToCredit;
                }

                public void setOutwChqToCredit(String outwChqToCredit) {
                    this.outwChqToCredit = outwChqToCredit;
                }

                public String getTotalBounces() {
                    return totalBounces;
                }

                public void setTotalBounces(String totalBounces) {
                    this.totalBounces = totalBounces;
                }
            }
        }
    }

    public static class AdditionalOverallDetails {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private OverallDetail1 overallDetail1;

        // Getters and Setters

        public OverallDetail1 getOverallDetail1() {
            return overallDetail1;
        }

        public void setOverallDetail1(OverallDetail1 overallDetail1) {
            this.overallDetail1 = overallDetail1;
        }

        public static class OverallDetail1 {
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private AdditionalOverallDetail additionalOverallDetail;

            // Getters and Setters

            public AdditionalOverallDetail getAdditionalOverallDetail() {
                return additionalOverallDetail;
            }

            public void setAdditionalOverallDetail(AdditionalOverallDetail additionalOverallDetail) {
                this.additionalOverallDetail = additionalOverallDetail;
            }

            public static class AdditionalOverallDetail {
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String last12MonthsAverageBalance;
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String last12MonthsCreditAmount;
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String last12MonthsCreditCount;
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String last12MonthsDebitAmount;
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String last12MonthsDebitCount;
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private String negativeBalancesPresent;

                // Add fields for last3Months, last6Months, last9Months, etc.
               /* private String last12MonthsAverageBalance;
                private String last12MonthsCreditAmount;
                private String last12MonthsCreditCount;
                private String last12MonthsDebitAmount;
                private String last12MonthsDebitCount;*/
                private String last12MonthsInwChqReturn;
                private String last3MonthsAverageBalance;
                private String last3MonthsCreditAmount;
                private String last3MonthsCreditCount;
                private String last3MonthsDebitAmount;
                private String last3MonthsDebitCount;
                private String last3MonthsInwChqReturn;
                private String last6MonthsAverageBalance;
                private String last6MonthsCreditAmount;
                private String last6MonthsCreditCount;
                private String last6MonthsDebitAmount;
                private String last6MonthsDebitCount;
                private String last6MonthsInwChqReturn;
                private String last9MonthsAverageBalance;
                private String last9MonthsCreditAmount;
                private String last9MonthsCreditCount;
                private String last9MonthsDebitAmount;
                private String last9MonthsDebitCount;
                private String last9MonthsInwChqReturn;
//                private String negativeBalancesPresent;
                // Getters and Setters


                public String getLast12MonthsInwChqReturn() {
                    return last12MonthsInwChqReturn;
                }

                public void setLast12MonthsInwChqReturn(String last12MonthsInwChqReturn) {
                    this.last12MonthsInwChqReturn = last12MonthsInwChqReturn;
                }

                public String getLast3MonthsAverageBalance() {
                    return last3MonthsAverageBalance;
                }

                public void setLast3MonthsAverageBalance(String last3MonthsAverageBalance) {
                    this.last3MonthsAverageBalance = last3MonthsAverageBalance;
                }

                public String getLast3MonthsCreditAmount() {
                    return last3MonthsCreditAmount;
                }

                public void setLast3MonthsCreditAmount(String last3MonthsCreditAmount) {
                    this.last3MonthsCreditAmount = last3MonthsCreditAmount;
                }

                public String getLast3MonthsCreditCount() {
                    return last3MonthsCreditCount;
                }

                public void setLast3MonthsCreditCount(String last3MonthsCreditCount) {
                    this.last3MonthsCreditCount = last3MonthsCreditCount;
                }

                public String getLast3MonthsDebitAmount() {
                    return last3MonthsDebitAmount;
                }

                public void setLast3MonthsDebitAmount(String last3MonthsDebitAmount) {
                    this.last3MonthsDebitAmount = last3MonthsDebitAmount;
                }

                public String getLast3MonthsDebitCount() {
                    return last3MonthsDebitCount;
                }

                public void setLast3MonthsDebitCount(String last3MonthsDebitCount) {
                    this.last3MonthsDebitCount = last3MonthsDebitCount;
                }

                public String getLast3MonthsInwChqReturn() {
                    return last3MonthsInwChqReturn;
                }

                public void setLast3MonthsInwChqReturn(String last3MonthsInwChqReturn) {
                    this.last3MonthsInwChqReturn = last3MonthsInwChqReturn;
                }

                public String getLast6MonthsAverageBalance() {
                    return last6MonthsAverageBalance;
                }

                public void setLast6MonthsAverageBalance(String last6MonthsAverageBalance) {
                    this.last6MonthsAverageBalance = last6MonthsAverageBalance;
                }

                public String getLast6MonthsCreditAmount() {
                    return last6MonthsCreditAmount;
                }

                public void setLast6MonthsCreditAmount(String last6MonthsCreditAmount) {
                    this.last6MonthsCreditAmount = last6MonthsCreditAmount;
                }

                public String getLast6MonthsCreditCount() {
                    return last6MonthsCreditCount;
                }

                public void setLast6MonthsCreditCount(String last6MonthsCreditCount) {
                    this.last6MonthsCreditCount = last6MonthsCreditCount;
                }

                public String getLast6MonthsDebitAmount() {
                    return last6MonthsDebitAmount;
                }

                public void setLast6MonthsDebitAmount(String last6MonthsDebitAmount) {
                    this.last6MonthsDebitAmount = last6MonthsDebitAmount;
                }

                public String getLast6MonthsDebitCount() {
                    return last6MonthsDebitCount;
                }

                public void setLast6MonthsDebitCount(String last6MonthsDebitCount) {
                    this.last6MonthsDebitCount = last6MonthsDebitCount;
                }

                public String getLast6MonthsInwChqReturn() {
                    return last6MonthsInwChqReturn;
                }

                public void setLast6MonthsInwChqReturn(String last6MonthsInwChqReturn) {
                    this.last6MonthsInwChqReturn = last6MonthsInwChqReturn;
                }

                public String getLast9MonthsAverageBalance() {
                    return last9MonthsAverageBalance;
                }

                public void setLast9MonthsAverageBalance(String last9MonthsAverageBalance) {
                    this.last9MonthsAverageBalance = last9MonthsAverageBalance;
                }

                public String getLast9MonthsCreditAmount() {
                    return last9MonthsCreditAmount;
                }

                public void setLast9MonthsCreditAmount(String last9MonthsCreditAmount) {
                    this.last9MonthsCreditAmount = last9MonthsCreditAmount;
                }

                public String getLast9MonthsCreditCount() {
                    return last9MonthsCreditCount;
                }

                public void setLast9MonthsCreditCount(String last9MonthsCreditCount) {
                    this.last9MonthsCreditCount = last9MonthsCreditCount;
                }

                public String getLast9MonthsDebitAmount() {
                    return last9MonthsDebitAmount;
                }

                public void setLast9MonthsDebitAmount(String last9MonthsDebitAmount) {
                    this.last9MonthsDebitAmount = last9MonthsDebitAmount;
                }

                public String getLast9MonthsDebitCount() {
                    return last9MonthsDebitCount;
                }

                public void setLast9MonthsDebitCount(String last9MonthsDebitCount) {
                    this.last9MonthsDebitCount = last9MonthsDebitCount;
                }

                public String getLast9MonthsInwChqReturn() {
                    return last9MonthsInwChqReturn;
                }

                public void setLast9MonthsInwChqReturn(String last9MonthsInwChqReturn) {
                    this.last9MonthsInwChqReturn = last9MonthsInwChqReturn;
                }

                public String getLast12MonthsAverageBalance() {
                    return last12MonthsAverageBalance;
                }

                public void setLast12MonthsAverageBalance(String last12MonthsAverageBalance) {
                    this.last12MonthsAverageBalance = last12MonthsAverageBalance;
                }

                public String getLast12MonthsCreditAmount() {
                    return last12MonthsCreditAmount;
                }

                public void setLast12MonthsCreditAmount(String last12MonthsCreditAmount) {
                    this.last12MonthsCreditAmount = last12MonthsCreditAmount;
                }

                public String getLast12MonthsCreditCount() {
                    return last12MonthsCreditCount;
                }

                public void setLast12MonthsCreditCount(String last12MonthsCreditCount) {
                    this.last12MonthsCreditCount = last12MonthsCreditCount;
                }

                public String getLast12MonthsDebitAmount() {
                    return last12MonthsDebitAmount;
                }

                public void setLast12MonthsDebitAmount(String last12MonthsDebitAmount) {
                    this.last12MonthsDebitAmount = last12MonthsDebitAmount;
                }

                public String getLast12MonthsDebitCount() {
                    return last12MonthsDebitCount;
                }

                public void setLast12MonthsDebitCount(String last12MonthsDebitCount) {
                    this.last12MonthsDebitCount = last12MonthsDebitCount;
                }

                public String getNegativeBalancesPresent() {
                    return negativeBalancesPresent;
                }

                public void setNegativeBalancesPresent(String negativeBalancesPresent) {
                    this.negativeBalancesPresent = negativeBalancesPresent;
                }
            }
        }
    }

    public static class AdditionalPartialMonthDetails {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String partialMonthDetail1;

        // Getters and Setters

        public String getPartialMonthDetail1() {
            return partialMonthDetail1;
        }

        public void setPartialMonthDetail1(String partialMonthDetail1) {
            this.partialMonthDetail1 = partialMonthDetail1;
        }
    }

    public static class AdditionalMonthlyDetails {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private MonthlyDetail1 monthlyDetail1;

        // Getters and Setters


        public MonthlyDetail1 getMonthlyDetail1() {
            return monthlyDetail1;
        }

        public void setMonthlyDetail1(MonthlyDetail1 monthlyDetail1) {
            this.monthlyDetail1 = monthlyDetail1;
        }

        public static class MonthlyDetail1 {
            @JsonInclude(JsonInclude.Include.NON_NULL)
            private List<Detail> detail;

            // Getters and Setters

            public List<Detail> getDetail() {
                return detail;
            }

            public void setDetail(List<Detail> detail) {
                this.detail = detail;
            }

            @JsonInclude(JsonInclude.Include.NON_NULL)
            public static class Detail {/*
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
                    this.totalUtilityExpense = totalUtilityExpense;*/

                private String avgDaysAccOverdrawn;
                private String businessCredits;
                private String cashDepositsToTotalCredit;
                private String cashWithdrawalsToTotalDebit;
                private String credits;
                private String debits;
                private String interestServiceDelay;
                private String inwBouncesNonTechToDebits;
                private String inwBouncesTechToDebits;
                private String maxAmtOverdrawn;
                private String outwBouncesToCredits;
                private String totalBusinessCredit;
                private String totalCredit;
                private String totalDebit;
                private String monthName;

                public String getAvgDaysAccOverdrawn() {
                    return avgDaysAccOverdrawn;
                }

                public void setAvgDaysAccOverdrawn(String avgDaysAccOverdrawn) {
                    this.avgDaysAccOverdrawn = avgDaysAccOverdrawn;
                }

                public String getBusinessCredits() {
                    return businessCredits;
                }

                public void setBusinessCredits(String businessCredits) {
                    this.businessCredits = businessCredits;
                }

                public String getCashDepositsToTotalCredit() {
                    return cashDepositsToTotalCredit;
                }

                public void setCashDepositsToTotalCredit(String cashDepositsToTotalCredit) {
                    this.cashDepositsToTotalCredit = cashDepositsToTotalCredit;
                }

                public String getCashWithdrawalsToTotalDebit() {
                    return cashWithdrawalsToTotalDebit;
                }

                public void setCashWithdrawalsToTotalDebit(String cashWithdrawalsToTotalDebit) {
                    this.cashWithdrawalsToTotalDebit = cashWithdrawalsToTotalDebit;
                }

                public String getCredits() {
                    return credits;
                }

                public void setCredits(String credits) {
                    this.credits = credits;
                }

                public String getDebits() {
                    return debits;
                }

                public void setDebits(String debits) {
                    this.debits = debits;
                }

                public String getInterestServiceDelay() {
                    return interestServiceDelay;
                }

                public void setInterestServiceDelay(String interestServiceDelay) {
                    this.interestServiceDelay = interestServiceDelay;
                }

                public String getInwBouncesNonTechToDebits() {
                    return inwBouncesNonTechToDebits;
                }

                public void setInwBouncesNonTechToDebits(String inwBouncesNonTechToDebits) {
                    this.inwBouncesNonTechToDebits = inwBouncesNonTechToDebits;
                }

                public String getInwBouncesTechToDebits() {
                    return inwBouncesTechToDebits;
                }

                public void setInwBouncesTechToDebits(String inwBouncesTechToDebits) {
                    this.inwBouncesTechToDebits = inwBouncesTechToDebits;
                }

                public String getMaxAmtOverdrawn() {
                    return maxAmtOverdrawn;
                }

                public void setMaxAmtOverdrawn(String maxAmtOverdrawn) {
                    this.maxAmtOverdrawn = maxAmtOverdrawn;
                }

                public String getOutwBouncesToCredits() {
                    return outwBouncesToCredits;
                }

                public void setOutwBouncesToCredits(String outwBouncesToCredits) {
                    this.outwBouncesToCredits = outwBouncesToCredits;
                }

                public String getTotalBusinessCredit() {
                    return totalBusinessCredit;
                }

                public void setTotalBusinessCredit(String totalBusinessCredit) {
                    this.totalBusinessCredit = totalBusinessCredit;
                }

                public String getTotalCredit() {
                    return totalCredit;
                }

                public void setTotalCredit(String totalCredit) {
                    this.totalCredit = totalCredit;
                }

                public String getTotalDebit() {
                    return totalDebit;
                }

                public void setTotalDebit(String totalDebit) {
                    this.totalDebit = totalDebit;
                }

                public String getMonthName() {
                    return monthName;
                }

                public void setMonthName(String monthName) {
                    this.monthName = monthName;
                }

            }

        }
    }

    public static class Xns {
        public String accountNo;
        public String accountID;
        public String accountType;
        public String ifscCode;
        public String location;
        public String micrCode;
        public List<Xn> Xn;

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public String getAccountID() {
            return accountID;
        }

        public void setAccountID(String accountID) {
            this.accountID = accountID;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getIfscCode() {
            return ifscCode;
        }

        public void setIfscCode(String ifscCode) {
            this.ifscCode = ifscCode;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getMicrCode() {
            return micrCode;
        }

        public void setMicrCode(String micrCode) {
            this.micrCode = micrCode;
        }

        public List<Xns.Xn> getXn() {
            return Xn;
        }

        public void setXn(List<Xns.Xn> xn) {
            Xn = xn;
        }

        public static class Xn {
            public String amount;
            public String balance;
            public String category;
            public String date;
            public String chqNo;
            public String narration;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getChqNo() {
                return chqNo;
            }

            public void setChqNo(String chqNo) {
                this.chqNo = chqNo;
            }

            public String getNarration() {
                return narration;
            }

            public void setNarration(String narration) {
                this.narration = narration;
            }
        }
    }
}

