package edu.elte.query;

import com.querydsl.core.types.JavaTemplates;
import com.querydsl.core.types.Operator;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.PathType;

/**
 * {@code CollQueryTemplates} extends {@link JavaTemplates} to add module specific operation
 * templates.
 *
 * @author Xavier
 */
public class JMSTemplates extends JavaTemplates {

    @SuppressWarnings("FieldNameHidesFieldInSuperclass") //intentional
    public static final JMSTemplates DEFAULT = new JMSTemplates();

    protected JMSTemplates() {
        
        // boolean
        add(Ops.AND, "{0} AND  {1}", Precedence.AND);
        add(Ops.NOT, "NOT {0}", Precedence.NOT_HIGH);
        add(Ops.OR, "{0} OR {1}", Precedence.OR);
        
        add(Ops.EQ, "{0} = '{1s}'");
        add(Ops.IS_NULL, "{0s} IS NULL");
        add(Ops.IS_NOT_NULL, "{0s} IS NOT NULL");
//        add(Ops.IS_NULL, "{0} == null");
//        add(Ops.IS_NOT_NULL, "{0} != null");

        // collection
        add(Ops.IN, " {0} IN ({1s})");
        add(Ops.NOT_IN, "{0} NOT IN ({1s})");

        //like
        add(Ops.LIKE, "{0} LIKE {1s}", Precedence.COMPARISON);
        add(Ops.LIKE_IC, "{0l} LIKE {1l}", Precedence.COMPARISON);
        add(Ops.LIKE_ESCAPE, "{0} LIKE {1} escape '{2s}'", Precedence.COMPARISON);
        add(Ops.LIKE_ESCAPE_IC, "{0l} LIKE {1l} escape '{2s}'", Precedence.COMPARISON);
        add(Ops.STARTS_WITH, "{0} LIKE {1s}%");
        add(Ops.ENDS_WITH, "{0} LIKE %{1s}");
        add(Ops.BETWEEN, "({0}  BETWEEN  {1s} AND {2s})", Precedence.COMPARISON);
        add(Ops.GOE, "{0} >= {1s}", Precedence.COMPARISON);
        add(Ops.GT, "{0} > {1s}", Precedence.COMPARISON);
        add(Ops.LOE, "{0} <= {1s}", Precedence.COMPARISON);
        add(Ops.LT, "{0} < {1s}", Precedence.COMPARISON);
         
        
        // map
        add(Ops.MAP_IS_EMPTY, "{0}.isEmpty()", Precedence.DOT);
        add(Ops.MAP_SIZE, "{0}.size()", Precedence.DOT);
        add(Ops.CONTAINS_KEY, "{0}.containsKey({1})");
        add(Ops.CONTAINS_VALUE, "{0}.containsValue({1})");

        // Comparable
        

        // String
        add(Ops.CHAR_AT, "{0}.charAt({1})");
        add(Ops.LOWER, "{0}.toLowerCase()", Precedence.DOT);
        add(Ops.SUBSTR_1ARG, "{0}.substring({1})");
        add(Ops.SUBSTR_2ARGS, "{0}.substring({1},{2})");
        add(Ops.TRIM, "{0}.trim()", Precedence.DOT);
        add(Ops.UPPER, "{0}.toUpperCase()", Precedence.DOT);
        add(Ops.MATCHES, "{0}.matches({1})");
        add(Ops.MATCHES_IC, "{0l}.matches({1l})");
        add(Ops.STRING_LENGTH, "{0}.length()", Precedence.DOT);
        add(Ops.STRING_IS_EMPTY, "{0}.isEmpty()", Precedence.DOT);
        add(Ops.STRING_CONTAINS, "{0}.contains({1})");
        add(Ops.STRING_CONTAINS_IC, "{0l}.contains({1l})");
//        add(Ops.STARTS_WITH, "{0}.startsWith({1})");
        add(Ops.STARTS_WITH_IC, "{0l}.startsWith({1l})");
        add(Ops.INDEX_OF, "{0}.indexOf({1})");
        add(Ops.INDEX_OF_2ARGS, "{0}.indexOf({1},{2})");
        add(Ops.EQ_IGNORE_CASE, "{0}.equalsIgnoreCase({1})");
//        add(Ops.ENDS_WITH, "{0}.endsWith({1})");
        add(Ops.ENDS_WITH_IC, "{0l}.endsWith({1l})");
        add(Ops.StringOps.LOCATE, "({1}.indexOf({0})+1)");
        add(Ops.StringOps.LOCATE2, "({1}.indexOf({0},{2s}-1)+1)");

      

        // case
        add(Ops.CASE, "({0})");
        add(Ops.CASE_WHEN,  "({0}) ? ({1}) : ({2})");
        add(Ops.CASE_ELSE,  "{0}");

        // case eq
        add(Ops.CASE_EQ, "({0})");
        add(Ops.CASE_EQ_WHEN,  "({0} == {1}) ? ({2}) : ({3})");
        add(Ops.CASE_EQ_ELSE,  "{0}");

        
//        // path types
        add(PathType.PROPERTY, "{1s}");
        add(PathType.VARIABLE, "{0s}");
        add(PathType.DELEGATE, "{0}");
        add(Ops.ORDINAL, "ordinal({0})");
        
        
         // numeric
        add(Ops.NEGATE, "-{0}", Precedence.NEGATE);
        add(Ops.ADD, "{0s} + {1s}", Precedence.ARITH_LOW);
        add(Ops.DIV, "{0s} / {1s}", Precedence.ARITH_HIGH);
        add(Ops.MOD, "{0s} % {1s}", Precedence.ARITH_HIGH);
        add(Ops.MULT, "{0s} * {1s}", Precedence.ARITH_HIGH);
        add(Ops.SUB, "{0s} - {1s}", Precedence.ARITH_LOW);
        
        
//        add(PathType.ARRAYVALUE, "{0}'{1}'");
//        add(PathType.COLLECTION_ANY, "any({0})");
//        add(PathType.LISTVALUE_CONSTANT, "{0}.get({1s})"); // serialized constant
//        add(PathType.ARRAYVALUE_CONSTANT, "{0}{1s}");    // serialized constant
        
//       for (PathType type : new PathType[] {
//                PathType.LISTVALUE,
//                PathType.MAPVALUE,
//                PathType.MAPVALUE_CONSTANT }) {
//            add(type, "{0}.grr({1})");
//        }
        
        

        // Math
        for (Operator op : Ops.MathOps.values()) {
            add(op, "Math." + getTemplate(op));
        }
    }
    
//     @Override
//        public Object convert(final List<?> args) {
//            final Object arg = args.get(index);
//            return arg instanceof Constant<?> ? arg.toString().replace("[", "").replace("]", "") : arg;
//        }

    
    
}
