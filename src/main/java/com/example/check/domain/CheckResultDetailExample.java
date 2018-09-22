package com.example.check.domain;

import java.util.ArrayList;
import java.util.List;

public class CheckResultDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CheckResultDetailExample() {
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

        public Criteria andCheckresultidIsNull() {
            addCriterion("checkresultid is null");
            return (Criteria) this;
        }

        public Criteria andCheckresultidIsNotNull() {
            addCriterion("checkresultid is not null");
            return (Criteria) this;
        }

        public Criteria andCheckresultidEqualTo(Integer value) {
            addCriterion("checkresultid =", value, "checkresultid");
            return (Criteria) this;
        }

        public Criteria andCheckresultidNotEqualTo(Integer value) {
            addCriterion("checkresultid <>", value, "checkresultid");
            return (Criteria) this;
        }

        public Criteria andCheckresultidGreaterThan(Integer value) {
            addCriterion("checkresultid >", value, "checkresultid");
            return (Criteria) this;
        }

        public Criteria andCheckresultidGreaterThanOrEqualTo(Integer value) {
            addCriterion("checkresultid >=", value, "checkresultid");
            return (Criteria) this;
        }

        public Criteria andCheckresultidLessThan(Integer value) {
            addCriterion("checkresultid <", value, "checkresultid");
            return (Criteria) this;
        }

        public Criteria andCheckresultidLessThanOrEqualTo(Integer value) {
            addCriterion("checkresultid <=", value, "checkresultid");
            return (Criteria) this;
        }

        public Criteria andCheckresultidIn(List<Integer> values) {
            addCriterion("checkresultid in", values, "checkresultid");
            return (Criteria) this;
        }

        public Criteria andCheckresultidNotIn(List<Integer> values) {
            addCriterion("checkresultid not in", values, "checkresultid");
            return (Criteria) this;
        }

        public Criteria andCheckresultidBetween(Integer value1, Integer value2) {
            addCriterion("checkresultid between", value1, value2, "checkresultid");
            return (Criteria) this;
        }

        public Criteria andCheckresultidNotBetween(Integer value1, Integer value2) {
            addCriterion("checkresultid not between", value1, value2, "checkresultid");
            return (Criteria) this;
        }

        public Criteria andStdlibidIsNull() {
            addCriterion("stdLibID is null");
            return (Criteria) this;
        }

        public Criteria andStdlibidIsNotNull() {
            addCriterion("stdLibID is not null");
            return (Criteria) this;
        }

        public Criteria andStdlibidEqualTo(Integer value) {
            addCriterion("stdLibID =", value, "stdlibid");
            return (Criteria) this;
        }

        public Criteria andStdlibidNotEqualTo(Integer value) {
            addCriterion("stdLibID <>", value, "stdlibid");
            return (Criteria) this;
        }

        public Criteria andStdlibidGreaterThan(Integer value) {
            addCriterion("stdLibID >", value, "stdlibid");
            return (Criteria) this;
        }

        public Criteria andStdlibidGreaterThanOrEqualTo(Integer value) {
            addCriterion("stdLibID >=", value, "stdlibid");
            return (Criteria) this;
        }

        public Criteria andStdlibidLessThan(Integer value) {
            addCriterion("stdLibID <", value, "stdlibid");
            return (Criteria) this;
        }

        public Criteria andStdlibidLessThanOrEqualTo(Integer value) {
            addCriterion("stdLibID <=", value, "stdlibid");
            return (Criteria) this;
        }

        public Criteria andStdlibidIn(List<Integer> values) {
            addCriterion("stdLibID in", values, "stdlibid");
            return (Criteria) this;
        }

        public Criteria andStdlibidNotIn(List<Integer> values) {
            addCriterion("stdLibID not in", values, "stdlibid");
            return (Criteria) this;
        }

        public Criteria andStdlibidBetween(Integer value1, Integer value2) {
            addCriterion("stdLibID between", value1, value2, "stdlibid");
            return (Criteria) this;
        }

        public Criteria andStdlibidNotBetween(Integer value1, Integer value2) {
            addCriterion("stdLibID not between", value1, value2, "stdlibid");
            return (Criteria) this;
        }

        public Criteria andCulibidIsNull() {
            addCriterion("cuLibID is null");
            return (Criteria) this;
        }

        public Criteria andCulibidIsNotNull() {
            addCriterion("cuLibID is not null");
            return (Criteria) this;
        }

        public Criteria andCulibidEqualTo(Integer value) {
            addCriterion("cuLibID =", value, "culibid");
            return (Criteria) this;
        }

        public Criteria andCulibidNotEqualTo(Integer value) {
            addCriterion("cuLibID <>", value, "culibid");
            return (Criteria) this;
        }

        public Criteria andCulibidGreaterThan(Integer value) {
            addCriterion("cuLibID >", value, "culibid");
            return (Criteria) this;
        }

        public Criteria andCulibidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cuLibID >=", value, "culibid");
            return (Criteria) this;
        }

        public Criteria andCulibidLessThan(Integer value) {
            addCriterion("cuLibID <", value, "culibid");
            return (Criteria) this;
        }

        public Criteria andCulibidLessThanOrEqualTo(Integer value) {
            addCriterion("cuLibID <=", value, "culibid");
            return (Criteria) this;
        }

        public Criteria andCulibidIn(List<Integer> values) {
            addCriterion("cuLibID in", values, "culibid");
            return (Criteria) this;
        }

        public Criteria andCulibidNotIn(List<Integer> values) {
            addCriterion("cuLibID not in", values, "culibid");
            return (Criteria) this;
        }

        public Criteria andCulibidBetween(Integer value1, Integer value2) {
            addCriterion("cuLibID between", value1, value2, "culibid");
            return (Criteria) this;
        }

        public Criteria andCulibidNotBetween(Integer value1, Integer value2) {
            addCriterion("cuLibID not between", value1, value2, "culibid");
            return (Criteria) this;
        }

        public Criteria andIscorrectIsNull() {
            addCriterion("isCorrect is null");
            return (Criteria) this;
        }

        public Criteria andIscorrectIsNotNull() {
            addCriterion("isCorrect is not null");
            return (Criteria) this;
        }

        public Criteria andIscorrectEqualTo(Boolean value) {
            addCriterion("isCorrect =", value, "iscorrect");
            return (Criteria) this;
        }

        public Criteria andIscorrectNotEqualTo(Boolean value) {
            addCriterion("isCorrect <>", value, "iscorrect");
            return (Criteria) this;
        }

        public Criteria andIscorrectGreaterThan(Boolean value) {
            addCriterion("isCorrect >", value, "iscorrect");
            return (Criteria) this;
        }

        public Criteria andIscorrectGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isCorrect >=", value, "iscorrect");
            return (Criteria) this;
        }

        public Criteria andIscorrectLessThan(Boolean value) {
            addCriterion("isCorrect <", value, "iscorrect");
            return (Criteria) this;
        }

        public Criteria andIscorrectLessThanOrEqualTo(Boolean value) {
            addCriterion("isCorrect <=", value, "iscorrect");
            return (Criteria) this;
        }

        public Criteria andIswrongIsNull() {
            addCriterion("isWrong is null");
            return (Criteria) this;
        }

        public Criteria andIswrongIsNotNull() {
            addCriterion("isWrong is not null");
            return (Criteria) this;
        }

        public Criteria andIswrongEqualTo(Boolean value) {
            addCriterion("isWrong =", value, "iswrong");
            return (Criteria) this;
        }

        public Criteria andIswrongNotEqualTo(Boolean value) {
            addCriterion("isWrong <>", value, "iswrong");
            return (Criteria) this;
        }

        public Criteria andIswrongGreaterThan(Boolean value) {
            addCriterion("isWrong >", value, "iswrong");
            return (Criteria) this;
        }

        public Criteria andIswrongGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isWrong >=", value, "iswrong");
            return (Criteria) this;
        }

        public Criteria andIswrongLessThan(Boolean value) {
            addCriterion("isWrong <", value, "iswrong");
            return (Criteria) this;
        }

        public Criteria andIswrongLessThanOrEqualTo(Boolean value) {
            addCriterion("isWrong <=", value, "iswrong");
            return (Criteria) this;
        }

        public Criteria andIslossIsNull() {
            addCriterion("isLoss is null");
            return (Criteria) this;
        }

        public Criteria andIslossIsNotNull() {
            addCriterion("isLoss is not null");
            return (Criteria) this;
        }

        public Criteria andIslossEqualTo(Boolean value) {
            addCriterion("isLoss =", value, "isloss");
            return (Criteria) this;
        }

        public Criteria andIslossNotEqualTo(Boolean value) {
            addCriterion("isLoss <>", value, "isloss");
            return (Criteria) this;
        }

        public Criteria andIslossGreaterThan(Boolean value) {
            addCriterion("isLoss >", value, "isloss");
            return (Criteria) this;
        }

        public Criteria andIslossGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isLoss >=", value, "isloss");
            return (Criteria) this;
        }

        public Criteria andIslossLessThan(Boolean value) {
            addCriterion("isLoss <", value, "isloss");
            return (Criteria) this;
        }

        public Criteria andIslossLessThanOrEqualTo(Boolean value) {
            addCriterion("isLoss <=", value, "isloss");
            return (Criteria) this;
        }

        public Criteria andIsredundantIsNull() {
            addCriterion("isRedundant is null");
            return (Criteria) this;
        }

        public Criteria andIsredundantIsNotNull() {
            addCriterion("isRedundant is not null");
            return (Criteria) this;
        }

        public Criteria andIsredundantEqualTo(Boolean value) {
            addCriterion("isRedundant =", value, "isredundant");
            return (Criteria) this;
        }

        public Criteria andIsredundantNotEqualTo(Boolean value) {
            addCriterion("isRedundant <>", value, "isredundant");
            return (Criteria) this;
        }

        public Criteria andIsredundantGreaterThan(Boolean value) {
            addCriterion("isRedundant >", value, "isredundant");
            return (Criteria) this;
        }

        public Criteria andIsredundantGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isRedundant >=", value, "isredundant");
            return (Criteria) this;
        }

        public Criteria andIsredundantLessThan(Boolean value) {
            addCriterion("isRedundant <", value, "isredundant");
            return (Criteria) this;
        }

        public Criteria andIsredundantLessThanOrEqualTo(Boolean value) {
            addCriterion("isRedundant <=", value, "isredundant");
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