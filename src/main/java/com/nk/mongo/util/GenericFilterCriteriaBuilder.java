package com.nk.mongo.util;

import com.nk.mongo.model.MongoFilterCondition;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenericFilterCriteriaBuilder {

    /**
     * 2
     * This class is used to build all the queries passed as parameters.
     * 3
     * filterAndConditions (filter list for the AND operator)
     * 4
     * filterOrConditions (filter list for the OR operator)
     * 5
     */
    private final List<MongoFilterCondition> mongoFilterAndConditions;
    private final List<MongoFilterCondition> mongoFilterOrConditions;
    private static final Map<String, Function<MongoFilterCondition, Criteria>>  FILTER_CRITERIA = new HashMap<>();

    // Create map of filter
    static {
        FILTER_CRITERIA.put("EQUAL", condition -> Criteria.where(condition.getField()).is(condition.getValue()));
        FILTER_CRITERIA.put("NOT_EQUAL", condition -> Criteria.where(condition.getField()).ne(condition.getValue()));
        FILTER_CRITERIA.put("GREATER_THAN", condition -> Criteria.where(condition.getField()).gt(condition.getValue()));
        FILTER_CRITERIA.put("GREATER_THAN_OR_EQUAL_TO", condition -> Criteria.where(condition.getField()).gte(condition.getValue()));
        FILTER_CRITERIA.put("LESS_THAN", condition -> Criteria.where(condition.getField()).lt(condition.getValue()));
        FILTER_CRITERIA.put("LESSTHAN_OR_EQUAL_TO", condition -> Criteria.where(condition.getField()).lte(condition.getValue()));
        FILTER_CRITERIA.put("CONTAINS", condition -> Criteria.where(condition.getField()).regex((String) condition.getValue()));
        FILTER_CRITERIA.put("JOIN", condition -> Criteria.where(condition.getField()).is(new ObjectId((String) condition.getValue())));
        FILTER_CRITERIA.put("IN", condition -> Criteria.where(condition.getField()).in(condition.getValue()));
        FILTER_CRITERIA.put("INA", condition -> Criteria.where(condition.getField()).in(condition.getValue()));
        FILTER_CRITERIA.put("IS", condition -> Criteria.where(condition.getField()).is(Integer.parseInt((String)condition.getValue())));
        FILTER_CRITERIA.put("IB", condition -> Criteria.where(condition.getField()).is(Boolean.valueOf((String)condition.getValue())));
    }

    public GenericFilterCriteriaBuilder() {
        mongoFilterAndConditions = new ArrayList<>();
        mongoFilterOrConditions = new ArrayList<>();
    }

    public Query addCondition(List<MongoFilterCondition> andConditions, List<MongoFilterCondition> orConditions) {

        if (andConditions != null && !andConditions.isEmpty()) {
            mongoFilterAndConditions.addAll(andConditions);
        }
        if (orConditions != null && !orConditions.isEmpty()) {
            mongoFilterOrConditions.addAll(orConditions);
        }

        List<Criteria> criteriaAndClause = new ArrayList<>();
        List<Criteria> criteriaOrClause = new ArrayList<>();
        Criteria criteria = new Criteria();
        // build criteria
        mongoFilterAndConditions.stream().map(condition -> criteriaAndClause.add(buildCriteria(condition))).collect(Collectors.toList());
        mongoFilterOrConditions.stream().map(condition -> criteriaOrClause.add(buildCriteria(condition))).collect(Collectors.toList());

        if (!criteriaAndClause.isEmpty() && !criteriaOrClause.isEmpty()) {
            return new Query(criteria.andOperator(criteriaAndClause.toArray(new Criteria[0])).orOperator(criteriaOrClause.toArray(new Criteria[0])));
        } else if (!criteriaAndClause.isEmpty()) {
            return new Query(criteria.andOperator(criteriaAndClause.toArray(new Criteria[0])));
        } else if (!criteriaOrClause.isEmpty()) {
            return new Query(criteria.orOperator(criteriaOrClause.toArray(new Criteria[0])));
        } else {
            return new Query();
        }
    }

    /**
     * Build the predicate according to the request
     * <p>
     *
     * @param condition The condition of the filter requested by the query
     *
     * @return {{@link Criteria}}
     */

    private Criteria buildCriteria(MongoFilterCondition condition) {
        Function<MongoFilterCondition, Criteria> function = FILTER_CRITERIA.get(condition.getOperator().name());

        if (function == null) {
            throw new IllegalArgumentException("Invalid function param type: ");
        }
        return function.apply(condition);
    }
}
