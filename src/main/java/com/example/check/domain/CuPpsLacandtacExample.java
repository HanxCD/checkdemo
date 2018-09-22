package com.example.check.domain;

import java.util.ArrayList;
import java.util.List;

public class CuPpsLacandtacExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CuPpsLacandtacExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andL1IsNull() {
            addCriterion("l1 is null");
            return (Criteria) this;
        }

        public Criteria andL1IsNotNull() {
            addCriterion("l1 is not null");
            return (Criteria) this;
        }

        public Criteria andL1EqualTo(String value) {
            addCriterion("l1 =", value, "l1");
            return (Criteria) this;
        }

        public Criteria andL1NotEqualTo(String value) {
            addCriterion("l1 <>", value, "l1");
            return (Criteria) this;
        }

        public Criteria andL1GreaterThan(String value) {
            addCriterion("l1 >", value, "l1");
            return (Criteria) this;
        }

        public Criteria andL1GreaterThanOrEqualTo(String value) {
            addCriterion("l1 >=", value, "l1");
            return (Criteria) this;
        }

        public Criteria andL1LessThan(String value) {
            addCriterion("l1 <", value, "l1");
            return (Criteria) this;
        }

        public Criteria andL1LessThanOrEqualTo(String value) {
            addCriterion("l1 <=", value, "l1");
            return (Criteria) this;
        }

        public Criteria andL1Like(String value) {
            addCriterion("l1 like", value, "l1");
            return (Criteria) this;
        }

        public Criteria andL1NotLike(String value) {
            addCriterion("l1 not like", value, "l1");
            return (Criteria) this;
        }

        public Criteria andL1In(List<String> values) {
            addCriterion("l1 in", values, "l1");
            return (Criteria) this;
        }

        public Criteria andL1NotIn(List<String> values) {
            addCriterion("l1 not in", values, "l1");
            return (Criteria) this;
        }

        public Criteria andL1Between(String value1, String value2) {
            addCriterion("l1 between", value1, value2, "l1");
            return (Criteria) this;
        }

        public Criteria andL1NotBetween(String value1, String value2) {
            addCriterion("l1 not between", value1, value2, "l1");
            return (Criteria) this;
        }

        public Criteria andL2IsNull() {
            addCriterion("l2 is null");
            return (Criteria) this;
        }

        public Criteria andL2IsNotNull() {
            addCriterion("l2 is not null");
            return (Criteria) this;
        }

        public Criteria andL2EqualTo(String value) {
            addCriterion("l2 =", value, "l2");
            return (Criteria) this;
        }

        public Criteria andL2NotEqualTo(String value) {
            addCriterion("l2 <>", value, "l2");
            return (Criteria) this;
        }

        public Criteria andL2GreaterThan(String value) {
            addCriterion("l2 >", value, "l2");
            return (Criteria) this;
        }

        public Criteria andL2GreaterThanOrEqualTo(String value) {
            addCriterion("l2 >=", value, "l2");
            return (Criteria) this;
        }

        public Criteria andL2LessThan(String value) {
            addCriterion("l2 <", value, "l2");
            return (Criteria) this;
        }

        public Criteria andL2LessThanOrEqualTo(String value) {
            addCriterion("l2 <=", value, "l2");
            return (Criteria) this;
        }

        public Criteria andL2Like(String value) {
            addCriterion("l2 like", value, "l2");
            return (Criteria) this;
        }

        public Criteria andL2NotLike(String value) {
            addCriterion("l2 not like", value, "l2");
            return (Criteria) this;
        }

        public Criteria andL2In(List<String> values) {
            addCriterion("l2 in", values, "l2");
            return (Criteria) this;
        }

        public Criteria andL2NotIn(List<String> values) {
            addCriterion("l2 not in", values, "l2");
            return (Criteria) this;
        }

        public Criteria andL2Between(String value1, String value2) {
            addCriterion("l2 between", value1, value2, "l2");
            return (Criteria) this;
        }

        public Criteria andL2NotBetween(String value1, String value2) {
            addCriterion("l2 not between", value1, value2, "l2");
            return (Criteria) this;
        }

        public Criteria andL3IsNull() {
            addCriterion("l3 is null");
            return (Criteria) this;
        }

        public Criteria andL3IsNotNull() {
            addCriterion("l3 is not null");
            return (Criteria) this;
        }

        public Criteria andL3EqualTo(String value) {
            addCriterion("l3 =", value, "l3");
            return (Criteria) this;
        }

        public Criteria andL3NotEqualTo(String value) {
            addCriterion("l3 <>", value, "l3");
            return (Criteria) this;
        }

        public Criteria andL3GreaterThan(String value) {
            addCriterion("l3 >", value, "l3");
            return (Criteria) this;
        }

        public Criteria andL3GreaterThanOrEqualTo(String value) {
            addCriterion("l3 >=", value, "l3");
            return (Criteria) this;
        }

        public Criteria andL3LessThan(String value) {
            addCriterion("l3 <", value, "l3");
            return (Criteria) this;
        }

        public Criteria andL3LessThanOrEqualTo(String value) {
            addCriterion("l3 <=", value, "l3");
            return (Criteria) this;
        }

        public Criteria andL3Like(String value) {
            addCriterion("l3 like", value, "l3");
            return (Criteria) this;
        }

        public Criteria andL3NotLike(String value) {
            addCriterion("l3 not like", value, "l3");
            return (Criteria) this;
        }

        public Criteria andL3In(List<String> values) {
            addCriterion("l3 in", values, "l3");
            return (Criteria) this;
        }

        public Criteria andL3NotIn(List<String> values) {
            addCriterion("l3 not in", values, "l3");
            return (Criteria) this;
        }

        public Criteria andL3Between(String value1, String value2) {
            addCriterion("l3 between", value1, value2, "l3");
            return (Criteria) this;
        }

        public Criteria andL3NotBetween(String value1, String value2) {
            addCriterion("l3 not between", value1, value2, "l3");
            return (Criteria) this;
        }

        public Criteria andL4IsNull() {
            addCriterion("l4 is null");
            return (Criteria) this;
        }

        public Criteria andL4IsNotNull() {
            addCriterion("l4 is not null");
            return (Criteria) this;
        }

        public Criteria andL4EqualTo(String value) {
            addCriterion("l4 =", value, "l4");
            return (Criteria) this;
        }

        public Criteria andL4NotEqualTo(String value) {
            addCriterion("l4 <>", value, "l4");
            return (Criteria) this;
        }

        public Criteria andL4GreaterThan(String value) {
            addCriterion("l4 >", value, "l4");
            return (Criteria) this;
        }

        public Criteria andL4GreaterThanOrEqualTo(String value) {
            addCriterion("l4 >=", value, "l4");
            return (Criteria) this;
        }

        public Criteria andL4LessThan(String value) {
            addCriterion("l4 <", value, "l4");
            return (Criteria) this;
        }

        public Criteria andL4LessThanOrEqualTo(String value) {
            addCriterion("l4 <=", value, "l4");
            return (Criteria) this;
        }

        public Criteria andL4Like(String value) {
            addCriterion("l4 like", value, "l4");
            return (Criteria) this;
        }

        public Criteria andL4NotLike(String value) {
            addCriterion("l4 not like", value, "l4");
            return (Criteria) this;
        }

        public Criteria andL4In(List<String> values) {
            addCriterion("l4 in", values, "l4");
            return (Criteria) this;
        }

        public Criteria andL4NotIn(List<String> values) {
            addCriterion("l4 not in", values, "l4");
            return (Criteria) this;
        }

        public Criteria andL4Between(String value1, String value2) {
            addCriterion("l4 between", value1, value2, "l4");
            return (Criteria) this;
        }

        public Criteria andL4NotBetween(String value1, String value2) {
            addCriterion("l4 not between", value1, value2, "l4");
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