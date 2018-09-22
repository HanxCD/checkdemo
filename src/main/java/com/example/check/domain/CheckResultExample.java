package com.example.check.domain;

import java.util.ArrayList;
import java.util.List;

public class CheckResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CheckResultExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProvinceidIsNull() {
            addCriterion("provinceID is null");
            return (Criteria) this;
        }

        public Criteria andProvinceidIsNotNull() {
            addCriterion("provinceID is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceidEqualTo(String value) {
            addCriterion("provinceID =", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidNotEqualTo(String value) {
            addCriterion("provinceID <>", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidGreaterThan(String value) {
            addCriterion("provinceID >", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidGreaterThanOrEqualTo(String value) {
            addCriterion("provinceID >=", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidLessThan(String value) {
            addCriterion("provinceID <", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidLessThanOrEqualTo(String value) {
            addCriterion("provinceID <=", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidLike(String value) {
            addCriterion("provinceID like", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidNotLike(String value) {
            addCriterion("provinceID not like", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidIn(List<String> values) {
            addCriterion("provinceID in", values, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidNotIn(List<String> values) {
            addCriterion("provinceID not in", values, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidBetween(String value1, String value2) {
            addCriterion("provinceID between", value1, value2, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidNotBetween(String value1, String value2) {
            addCriterion("provinceID not between", value1, value2, "provinceid");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }
        
        public Criteria andDeviceNameIsNull() {
            addCriterion("deviceName is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNotNull() {
            addCriterion("deviceName is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameEqualTo(String value) {
            addCriterion("deviceName =", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotEqualTo(String value) {
            addCriterion("deviceName <>", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThan(String value) {
            addCriterion("deviceName >", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThanOrEqualTo(String value) {
            addCriterion("deviceName >=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThan(String value) {
            addCriterion("deviceName <", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThanOrEqualTo(String value) {
            addCriterion("deviceName <=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLike(String value) {
            addCriterion("deviceName like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotLike(String value) {
            addCriterion("deviceName not like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIn(List<String> values) {
            addCriterion("deviceName in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotIn(List<String> values) {
            addCriterion("deviceName not in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameBetween(String value1, String value2) {
            addCriterion("deviceName between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotBetween(String value1, String value2) {
            addCriterion("deviceName not between", value1, value2, "deviceName");
            return (Criteria) this;
        }
        
        public Criteria andVendorIsNull() {
            addCriterion("vendor is null");
            return (Criteria) this;
        }

        public Criteria andVendorIsNotNull() {
            addCriterion("vendor is not null");
            return (Criteria) this;
        }

        public Criteria andVendorEqualTo(String value) {
            addCriterion("vendor =", value, "vendor");
            return (Criteria) this;
        }

        public Criteria andVendorNotEqualTo(String value) {
            addCriterion("vendor <>", value, "vendor");
            return (Criteria) this;
        }

        public Criteria andVendorGreaterThan(String value) {
            addCriterion("vendor >", value, "vendor");
            return (Criteria) this;
        }

        public Criteria andVendorGreaterThanOrEqualTo(String value) {
            addCriterion("vendor >=", value, "vendor");
            return (Criteria) this;
        }

        public Criteria andVendorLessThan(String value) {
            addCriterion("vendor <", value, "vendor");
            return (Criteria) this;
        }

        public Criteria andVendorLessThanOrEqualTo(String value) {
            addCriterion("vendor <=", value, "vendor");
            return (Criteria) this;
        }

        public Criteria andVendorLike(String value) {
            addCriterion("vendor like", value, "vendor");
            return (Criteria) this;
        }

        public Criteria andVendorNotLike(String value) {
            addCriterion("vendor not like", value, "vendor");
            return (Criteria) this;
        }

        public Criteria andVendorIn(List<String> values) {
            addCriterion("vendor in", values, "vendor");
            return (Criteria) this;
        }

        public Criteria andVendorNotIn(List<String> values) {
            addCriterion("vendor not in", values, "vendor");
            return (Criteria) this;
        }

        public Criteria andVendorBetween(String value1, String value2) {
            addCriterion("vendor between", value1, value2, "vendor");
            return (Criteria) this;
        }

        public Criteria andVendorNotBetween(String value1, String value2) {
            addCriterion("vendor not between", value1, value2, "vendor");
            return (Criteria) this;
        }

        public Criteria andTotalcountsIsNull() {
            addCriterion("totalCounts is null");
            return (Criteria) this;
        }

        public Criteria andTotalcountsIsNotNull() {
            addCriterion("totalCounts is not null");
            return (Criteria) this;
        }

        public Criteria andTotalcountsEqualTo(Integer value) {
            addCriterion("totalCounts =", value, "totalcounts");
            return (Criteria) this;
        }

        public Criteria andTotalcountsNotEqualTo(Integer value) {
            addCriterion("totalCounts <>", value, "totalcounts");
            return (Criteria) this;
        }

        public Criteria andTotalcountsGreaterThan(Integer value) {
            addCriterion("totalCounts >", value, "totalcounts");
            return (Criteria) this;
        }

        public Criteria andTotalcountsGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalCounts >=", value, "totalcounts");
            return (Criteria) this;
        }

        public Criteria andTotalcountsLessThan(Integer value) {
            addCriterion("totalCounts <", value, "totalcounts");
            return (Criteria) this;
        }

        public Criteria andTotalcountsLessThanOrEqualTo(Integer value) {
            addCriterion("totalCounts <=", value, "totalcounts");
            return (Criteria) this;
        }

        public Criteria andTotalcountsIn(List<Integer> values) {
            addCriterion("totalCounts in", values, "totalcounts");
            return (Criteria) this;
        }

        public Criteria andTotalcountsNotIn(List<Integer> values) {
            addCriterion("totalCounts not in", values, "totalcounts");
            return (Criteria) this;
        }

        public Criteria andTotalcountsBetween(Integer value1, Integer value2) {
            addCriterion("totalCounts between", value1, value2, "totalcounts");
            return (Criteria) this;
        }

        public Criteria andTotalcountsNotBetween(Integer value1, Integer value2) {
            addCriterion("totalCounts not between", value1, value2, "totalcounts");
            return (Criteria) this;
        }

        public Criteria andCorrectnumIsNull() {
            addCriterion("correctNum is null");
            return (Criteria) this;
        }

        public Criteria andCorrectnumIsNotNull() {
            addCriterion("correctNum is not null");
            return (Criteria) this;
        }

        public Criteria andCorrectnumEqualTo(Integer value) {
            addCriterion("correctNum =", value, "correctnum");
            return (Criteria) this;
        }

        public Criteria andCorrectnumNotEqualTo(Integer value) {
            addCriterion("correctNum <>", value, "correctnum");
            return (Criteria) this;
        }

        public Criteria andCorrectnumGreaterThan(Integer value) {
            addCriterion("correctNum >", value, "correctnum");
            return (Criteria) this;
        }

        public Criteria andCorrectnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("correctNum >=", value, "correctnum");
            return (Criteria) this;
        }

        public Criteria andCorrectnumLessThan(Integer value) {
            addCriterion("correctNum <", value, "correctnum");
            return (Criteria) this;
        }

        public Criteria andCorrectnumLessThanOrEqualTo(Integer value) {
            addCriterion("correctNum <=", value, "correctnum");
            return (Criteria) this;
        }

        public Criteria andCorrectnumIn(List<Integer> values) {
            addCriterion("correctNum in", values, "correctnum");
            return (Criteria) this;
        }

        public Criteria andCorrectnumNotIn(List<Integer> values) {
            addCriterion("correctNum not in", values, "correctnum");
            return (Criteria) this;
        }

        public Criteria andCorrectnumBetween(Integer value1, Integer value2) {
            addCriterion("correctNum between", value1, value2, "correctnum");
            return (Criteria) this;
        }

        public Criteria andCorrectnumNotBetween(Integer value1, Integer value2) {
            addCriterion("correctNum not between", value1, value2, "correctnum");
            return (Criteria) this;
        }

        public Criteria andWrongnumIsNull() {
            addCriterion("wrongNum is null");
            return (Criteria) this;
        }

        public Criteria andWrongnumIsNotNull() {
            addCriterion("wrongNum is not null");
            return (Criteria) this;
        }

        public Criteria andWrongnumEqualTo(Integer value) {
            addCriterion("wrongNum =", value, "wrongnum");
            return (Criteria) this;
        }

        public Criteria andWrongnumNotEqualTo(Integer value) {
            addCriterion("wrongNum <>", value, "wrongnum");
            return (Criteria) this;
        }

        public Criteria andWrongnumGreaterThan(Integer value) {
            addCriterion("wrongNum >", value, "wrongnum");
            return (Criteria) this;
        }

        public Criteria andWrongnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("wrongNum >=", value, "wrongnum");
            return (Criteria) this;
        }

        public Criteria andWrongnumLessThan(Integer value) {
            addCriterion("wrongNum <", value, "wrongnum");
            return (Criteria) this;
        }

        public Criteria andWrongnumLessThanOrEqualTo(Integer value) {
            addCriterion("wrongNum <=", value, "wrongnum");
            return (Criteria) this;
        }

        public Criteria andWrongnumIn(List<Integer> values) {
            addCriterion("wrongNum in", values, "wrongnum");
            return (Criteria) this;
        }

        public Criteria andWrongnumNotIn(List<Integer> values) {
            addCriterion("wrongNum not in", values, "wrongnum");
            return (Criteria) this;
        }

        public Criteria andWrongnumBetween(Integer value1, Integer value2) {
            addCriterion("wrongNum between", value1, value2, "wrongnum");
            return (Criteria) this;
        }

        public Criteria andWrongnumNotBetween(Integer value1, Integer value2) {
            addCriterion("wrongNum not between", value1, value2, "wrongnum");
            return (Criteria) this;
        }

        public Criteria andLossnumIsNull() {
            addCriterion("lossNum is null");
            return (Criteria) this;
        }

        public Criteria andLossnumIsNotNull() {
            addCriterion("lossNum is not null");
            return (Criteria) this;
        }

        public Criteria andLossnumEqualTo(Integer value) {
            addCriterion("lossNum =", value, "lossnum");
            return (Criteria) this;
        }

        public Criteria andLossnumNotEqualTo(Integer value) {
            addCriterion("lossNum <>", value, "lossnum");
            return (Criteria) this;
        }

        public Criteria andLossnumGreaterThan(Integer value) {
            addCriterion("lossNum >", value, "lossnum");
            return (Criteria) this;
        }

        public Criteria andLossnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("lossNum >=", value, "lossnum");
            return (Criteria) this;
        }

        public Criteria andLossnumLessThan(Integer value) {
            addCriterion("lossNum <", value, "lossnum");
            return (Criteria) this;
        }

        public Criteria andLossnumLessThanOrEqualTo(Integer value) {
            addCriterion("lossNum <=", value, "lossnum");
            return (Criteria) this;
        }

        public Criteria andLossnumIn(List<Integer> values) {
            addCriterion("lossNum in", values, "lossnum");
            return (Criteria) this;
        }

        public Criteria andLossnumNotIn(List<Integer> values) {
            addCriterion("lossNum not in", values, "lossnum");
            return (Criteria) this;
        }

        public Criteria andLossnumBetween(Integer value1, Integer value2) {
            addCriterion("lossNum between", value1, value2, "lossnum");
            return (Criteria) this;
        }

        public Criteria andLossnumNotBetween(Integer value1, Integer value2) {
            addCriterion("lossNum not between", value1, value2, "lossnum");
            return (Criteria) this;
        }

        public Criteria andRedundantnumIsNull() {
            addCriterion("redundantNum is null");
            return (Criteria) this;
        }

        public Criteria andRedundantnumIsNotNull() {
            addCriterion("redundantNum is not null");
            return (Criteria) this;
        }

        public Criteria andRedundantnumEqualTo(Integer value) {
            addCriterion("redundantNum =", value, "redundantnum");
            return (Criteria) this;
        }

        public Criteria andRedundantnumNotEqualTo(Integer value) {
            addCriterion("redundantNum <>", value, "redundantnum");
            return (Criteria) this;
        }

        public Criteria andRedundantnumGreaterThan(Integer value) {
            addCriterion("redundantNum >", value, "redundantnum");
            return (Criteria) this;
        }

        public Criteria andRedundantnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("redundantNum >=", value, "redundantnum");
            return (Criteria) this;
        }

        public Criteria andRedundantnumLessThan(Integer value) {
            addCriterion("redundantNum <", value, "redundantnum");
            return (Criteria) this;
        }

        public Criteria andRedundantnumLessThanOrEqualTo(Integer value) {
            addCriterion("redundantNum <=", value, "redundantnum");
            return (Criteria) this;
        }

        public Criteria andRedundantnumIn(List<Integer> values) {
            addCriterion("redundantNum in", values, "redundantnum");
            return (Criteria) this;
        }

        public Criteria andRedundantnumNotIn(List<Integer> values) {
            addCriterion("redundantNum not in", values, "redundantnum");
            return (Criteria) this;
        }

        public Criteria andRedundantnumBetween(Integer value1, Integer value2) {
            addCriterion("redundantNum between", value1, value2, "redundantnum");
            return (Criteria) this;
        }

        public Criteria andRedundantnumNotBetween(Integer value1, Integer value2) {
            addCriterion("redundantNum not between", value1, value2, "redundantnum");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}