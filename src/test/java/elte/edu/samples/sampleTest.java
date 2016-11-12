///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package elte.edu.samples;
//
////import com.querydsl.apt.Configuration;
//import static com.querydsl.collections.CollQueryFactory.from;
//import com.querydsl.collections.CollQuerySerializer;
//import com.querydsl.collections.CollQueryTemplates;
//import com.querydsl.sql.Configuration;
//import com.querydsl.sql.MySQLTemplates;
//import com.querydsl.sql.SQLTemplates;
//import com.querydsl.core.BooleanBuilder;
//import static com.querydsl.core.alias.Alias.$;
//import static com.querydsl.core.alias.Alias.alias;
//import static com.querydsl.core.alias.Alias.var;
//import com.querydsl.core.support.QueryMixin;
//import com.querydsl.core.support.ReplaceVisitor;
//import com.querydsl.core.types.Expression;
//import com.querydsl.core.types.ExpressionUtils;
//import com.querydsl.core.types.JavaTemplates;
//import com.querydsl.core.types.Operator;
//import com.querydsl.core.types.Ops;
//import com.querydsl.core.types.Path;
//import com.querydsl.core.types.Predicate;
//import com.querydsl.core.types.PredicateOperation;
//import com.querydsl.core.types.PredicateTemplate;
//import com.querydsl.core.types.Templates;
//import com.querydsl.core.types.TemplatesTestUtils;
//import com.querydsl.core.types.ToStringVisitor;
//import com.querydsl.core.types.dsl.CaseBuilder;
//import com.querydsl.core.types.dsl.CaseBuilderTest;
//import com.querydsl.core.types.dsl.DateExpression;
//import com.querydsl.core.types.dsl.EnumExpression;
//import com.querydsl.core.types.dsl.Expressions;
//import com.querydsl.core.types.dsl.MathExpressions;
//import com.querydsl.core.types.dsl.NumberExpression;
//import com.querydsl.core.types.dsl.StringExpression;
//import com.querydsl.core.types.dsl.StringExpressions;
////import com.querydsl.sql.Configuration;
////import com.querydsl.sql.MySQLTemplates;
////import com.querydsl.sql.SQLQuery;
////import com.querydsl.sql.SQLTemplates;
//import edu.elte.client.QRequestData;
//import java.util.Arrays;
//import java.util.Date;
//import javax.annotation.Nullable;
//import static org.junit.Assert.assertTrue;
//import org.junit.Before;
//import com.querydsl.core.types.dsl.BooleanExpression ;
//import com.querydsl.core.types.dsl.BooleanPath;
//import edu.elte.query.JMSTemplates;
////import com.mysema.query.sql.SQLQuery;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//
////import com.mysema.query.types.path.BooleanPath;
//
//
///**
// *
// * @author Xavier
// */
//public class sampleTest extends AbstractQueryTest {
//  
//     private Templates templates = JavaTemplates.DEFAULT;
//    
//    BooleanExpression a = new BooleanPath("a");
//    BooleanExpression b = new BooleanPath("b");
//    BooleanExpression c = new BooleanPath("c");
//
//     private NumberExpression<Integer> num;
//
//    private StringExpression str;
//
//    private DateExpression<Date> date;
//    
//    
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//    @Before
//    public void setUp() {
//        num = Expressions.numberPath(Integer.class, "num");
//        str = Expressions.stringPath("str");
//        date = Expressions.datePath(Date.class, "date");
//        QueryMixin<?> query = new QueryMixin<Void>();
//        query.from(num, str);
//        // TODO sub
//    }
//    
//    
//    
//     @Test
//    public void addidasClause() {
//        QRequestData rd = new QRequestData("");
//        
//        Expression exp=rd.category.eq("sport").or(rd.category.eq("shoes")).or(rd.category.eq("test"));
//        String str =  String.valueOf(exp.accept(ToStringVisitor.DEFAULT, JMSTemplates.DEFAULT)).replace(".", "");
////        bb1.or(rd.category.eq("sport")).or(rd.category.eq("shoes")).or(rd.category.eq("test"));
//        assertEquals("category = 'sport' OR category = 'shoes' OR category = 'test'",str);
//    }
//    
////    public void BooleanBuilder1() {
////        QRequestData rd = new QRequestData("rd");
////        JMSTemplates tmpl= new JMSTemplates();
////        
////        BooleanBuilder bb1 = new BooleanBuilder();
////        bb1.or(rd.category.eq("sport")).or(rd.category.eq("shoes")).or(rd.category.eq("test"));
////        assertEquals("rd.category = 'sport' OR rd.category = 'shoes' OR rd.category = 'test'", bb1.accept(ToStringVisitor.DEFAULT, tmpl));
////    }
//    
//   
//    
//    
//   
//    
//    
//    
//    
//    
//    @Test
//    public void BooleanBuilder2() {
//        QRequestData rd = new QRequestData("");
//        
//        BooleanBuilder bb1 = new BooleanBuilder();
//        bb1.or(rd.category.eq("sport")).or(rd.category.eq("shoes")).or(rd.category.eq("test"));
//        Expression<?> expr = rd.category.eq("sport").or(rd.category.eq("shoes")).or(rd.category.eq("test"));
////        String str = new CollQuerySerializer(CollQueryTemplates.DEFAULT).handle(expr).toString();
//        CollQuerySerializer serializer = new CollQuerySerializer(CollQueryTemplates.DEFAULT);
//        
//        
//        String str1=serializer.handle(expr).toString().replace(".", " ");
//        expr.accept(ToStringVisitor.DEFAULT, JMSTemplates.DEFAULT).replace("", str1);
////        JPQLSerializer serializer = new JPQLSerializer(HQLTemplates.DEFAULT, null);
////        String str = serializer.handle(expr).toString().replace("\n", " ");
////        assertTrue(expected + "\n!=\n" + str, str.matches(expected));
//        
////        NativeSQLSerializer serializer = new NativeSQLSerializer(conf, true);
////         DefaultQueryMetadata md = new DefaultQueryMetadata();
////        assertEquals("rd.category = 'sport' OR rd.category = 'shoes' OR rd.category = 'test'", bb1.accept(ToStringVisitor.DEFAULT, tmpl));
//        assertEquals("rd.category = 'sport' OR rd.category = 'shoes' OR rd.category = 'test'", expr);
//    }
//    
//    
//    
//    
//    
//    
////    @Test
////    public void concat() {
////        StringPath a = Expressions.stringPath("a");
////        StringPath b = Expressions.stringPath("b");
////        Expression<?> expr = a.append(b);
////        String str = new CollQuerySerializer(CollQueryTemplates.DEFAULT).handle(expr).toString();
////        assertEquals("(a + b).toLowerCase()", str);
////    }
//    
//    
//    
//      @Test
//    public void templ(){
//       
//      JavaTemplates jt = JavaTemplates.DEFAULT;
//        
//    SQLTemplates tm = MySQLTemplates.builder().printSchema().build();
//    Configuration configuration = new Configuration(tm);
//    
//    QRequestData rd = new QRequestData("rd");
//    
//    
//    assertEquals(Arrays.asList(1, 2, "aaaa", 3),
//                from(var(), 1, 2, 5, "aaaa", 3).where(var().ne(5)).select(var()).fetch());
//    
//    
//    System.out.println(tm.getSelect());
//    System.out.println(tm.getWhere());
//        System.out.println(configuration.getTemplates().getSelectDistinct());
//
//    }
////    ==================================================================================
//
//    @Test
//    public void anyOf() {
//        assertEquals(a.or(b).or(c), Expressions.anyOf(a, b, c));
//    }
//
//    @Test
//    public void allOf() {
//        assertEquals(a.and(b).and(c), Expressions.allOf(a, b, c));
//    }
//
//    @Test
//    public void allOf_with_nulls() {
//        assertEquals("a && b", Expressions.allOf(a, b, null).toString());
//        assertEquals("a", Expressions.allOf(a, null).toString());
//        assertEquals("a", Expressions.allOf(null, a).toString());
//    }
//
//    @Test
//    public void anyOf_with_nulls() {
//        assertEquals("a || b", Expressions.anyOf(a, b, null).toString());
//        assertEquals("a", Expressions.anyOf(a, null).toString());
//        assertEquals("a", Expressions.anyOf(null, a).toString());
//    }
//
//    @Test
//    public void andAnyOf() {
//        assertEquals(a.and(b.or(c)), a.andAnyOf(b, c));
//    }
//
//    @Test
//    public void orAllOf() {
//        assertEquals(a.or(b.and(c)), a.orAllOf(b, c));
//    }
//
//    @Test
//    public void not() {
//        assertEquals(a, a.not().not());
//    }
//
//    @Test
//    public void isTrue() {
//        assertEquals(a.eq(true), a.isTrue());
//    }
//
//    @Test
//    public void isFalse() {
//        assertEquals(a.eq(false), a.isFalse());
//    }
//       
//     @Test
//    public void true_() {
//        assertEquals("true", Expressions.TRUE.toString());
//    }
//
//    @Test
//    public void false_() {
//        assertEquals("false", Expressions.FALSE.toString());
//    }
//    
//    
//      @Test
//    public void booleanTyped() {
//        CaseBuilderTest.Customer c = alias(CaseBuilderTest.Customer.class, "customer");
//        BooleanExpression cases = new CaseBuilder()
//            .when($(c.getAnnualSpending()).gt(10000)).then(true)
//            .otherwise(false);
//
//        assertEquals(
//                "case " +
//                "when customer.annualSpending > 10000 then true " +
//                "else false " +
//                "end", cases.toString());
//    }
//
//    @Test
//    public void booleanTyped_predicate() {
//        CaseBuilderTest.Customer c = alias(CaseBuilderTest.Customer.class, "customer");
//        BooleanExpression cases = new CaseBuilder()
//                .when($(c.getAnnualSpending()).gt(20000)).then(false)
//                .when($(c.getAnnualSpending()).gt(10000)).then(true)
//                .otherwise(false);
//
//        assertEquals(
//                "case " +
//                        "when customer.annualSpending > 20000 then false " +
//                        "when customer.annualSpending > 10000 then true " +
//                        "else false " +
//                        "end", cases.toString());
//    }
//
//    @Test
//    public void enumTyped() {
//        CaseBuilderTest.Customer c = alias(CaseBuilderTest.Customer.class, "customer");
//        EnumExpression<CaseBuilderTest.Gender> cases = new CaseBuilder()
//            .when($(c.getAnnualSpending()).gt(10000)).then(CaseBuilderTest.Gender.MALE)
//            .otherwise(CaseBuilderTest.Gender.FEMALE);
//
//        assertEquals(
//                "case " +
//                "when customer.annualSpending > 10000 then MALE " +
//                "else FEMALE " +
//                "end", cases.toString());
//    }
//
//    @Test
//    public void numberTyped() {
//        CaseBuilderTest.Customer c = alias(CaseBuilderTest.Customer.class, "customer");
//        NumberExpression<Integer> cases = new CaseBuilder()
//            .when($(c.getAnnualSpending()).gt(10000)).then(1)
//            .when($(c.getAnnualSpending()).gt(5000)).then(2)
//            .when($(c.getAnnualSpending()).gt(2000)).then(3)
//            .otherwise(4);
//
//        assertEquals(
//                "case " +
//                "when customer.annualSpending > 10000 then 1 " +
//                "when customer.annualSpending > 5000 then 2 " +
//                "when customer.annualSpending > 2000 then 3 " +
//                "else 4 " +
//                "end", cases.toString());
//    }
//    
//     @Test
//    public void not1() {
//        PredicateTemplate template = ExpressionUtils.predicateTemplate("XXX");
//        assertEquals("!XXX", template.not().toString());
//    }
//    
//    
//     @Test
//    public void not2() {
//        Path<?> o1 = ExpressionUtils.path(Object.class, "o1");
//        Path<?> o2 = ExpressionUtils.path(Object.class, "o2");
//        PredicateOperation template = ExpressionUtils.predicate(Ops.EQ, o1, o2);
//        assertEquals("!(o1 = o2)", template.not().toString());
//    }
//    
//    
//    @Test
//    public void complex() {
//        BooleanPath a = Expressions.booleanPath("a");
//        BooleanPath b = Expressions.booleanPath("d");
//        BooleanPath c = Expressions.booleanPath("c");
//        BooleanPath d = Expressions.booleanPath("d");
//        Predicate complex = a.or(b).and(c.or(d));
//        assertEquals("(a || d) && (c || d)", complex.accept(ToStringVisitor.DEFAULT, templates));
//
//
//    }
//    
//     @Test
//    public void asComparable_returns_a_corresponding_ComparableExpression_for_a_given_Constant() {
//        assertEquals("1 = 1", Expressions.asComparable(1L).eq(1L).toString());
//    }
//    
//    @Test
//    public void asBoolean_returns_a_corresponding_BooleanExpression_for_a_given_Expression() {
//        assertEquals("true = true", Expressions.asBoolean(Expressions.constant(true)).isTrue().toString());
//    }
//    
//     @Test
//    public void dateOperation() {
//        assertEquals("current_date()",
//                Expressions.dateOperation(Date.class, Ops.DateTimeOps.CURRENT_DATE).toString());
//    }
//    
//    
//    /////////TODO IMPLEMENT IN REQUESTdATA HAY SI
//    
//    
//    
//    
//    public void operation1(){
//        Expression<String> str = Expressions.stringPath(ExpressionUtils.path(Object.class, "customer"), "name");
//        Expression<String> str2 = Expressions.stringPath("str");
//        Expression<String> concat = Expressions.stringOperation(Ops.ALIAS, str, str2);
//        
//        assertEquals("customer.name + str", concat.toString());
//    }
//    
//    
//    
//    
//    @Test
//    public void operation() {
//        Expression<String> str = Expressions.stringPath(ExpressionUtils.path(Object.class, "customer"), "name");
//        Expression<String> str2 = Expressions.stringPath("str");
//        Expression<String> concat = Expressions.stringOperation(Ops.CONCAT, str, str2);
//        assertEquals("customer.name + str", concat.toString());
//        assertEquals("customer_.name + str_", concat.accept(visitor, null).toString());
//    }
//    
//    
//     private static final ReplaceVisitor<Void> visitor = new ReplaceVisitor<Void>() {
//        public Expression<?> visit(Path<?> expr, @Nullable Void context) {
//            if (expr.getMetadata().isRoot()) {
//                return ExpressionUtils.path(expr.getType(), expr.getMetadata().getName() + "_");
//            } else {
//                return super.visit(expr, context);
//            }
//        }
//    };
//    
//    
//    
//    @Test
//    public void test() {
//        //Field<T>               abs()
//        num.abs();
//        //Field<T>               add(Field<?> value)
//        num.add(num);
//        //Field<T>               add(Number value)
//        num.add(1);
//        //Field<T>               as(String alias)
//        num.as("other");
//        //SortField<T>           asc()
//        num.asc();
//        //Field<Integer>         ascii()
//
//        //Field<BigDecimal>      avg()
//        num.avg();
//        //WindowPartitionByStep<BigDecimal>      avgOver()
//        //Condition              between(Field<T> minValue, Field<T> maxValue)
//        num.between(num, num);
//        //Condition              between(T minValue, T maxValue)
//        num.between(1,10);
//        // bitAnd
//
//        //<Z> Field<Z>           cast(Class<? extends Z> type)
//        num.castToNum(Long.class);
//        //<Z> Field<Z>           ast(DataType<Z> type)
//
//        //<Z> Field<Z>           cast(Field<Z> field)
//
//        //Field<T>               ceil()
//        num.ceil();
//        //Field<Integer>         charLength()
//        str.length();
//        //Field<T>               coalesce(Field<T> option, Field<?>... options)
//        str.coalesce(str);
//        //Field<T>               coalesce(T option, T... options)
//
//        //Field<String>          concat(Field<?>... fields)
//        str.concat(str);
//        //Field<String>          concat(String... values)
//        str.concat("str");
//        //Condition              contains(Field<T> value)
//        str.contains(str);
//        //Condition              contains(T value)
//        str.contains("a");
//        //Field<Integer>         count()
//        num.count();
//        //Field<Integer>         countDistinct()
//        num.countDistinct();
//        // currentDate
//        Expressions.currentDate();
//        // currentTime
//        Expressions.currentTime();
//        // currentTimestamp
//        Expressions.currentTimestamp();
//        //SortField<T>           desc()
//        num.desc();
//        //Field<T>               div(Field<? extends Number> value)
//        num.divide(num);
//        //Field<T>               div(Number value)
//        num.divide(2);
//        //Condition              endsWith(Field<T> value)
//        str.endsWith(str);
//        //Condition              endsWith(T value)
//        str.endsWith("str");
//        //Condition              equal(Field<T> field)
//        num.eq(1);
//        //Condition              equal(Select<?> query)
//        //num.eq(sub.unique(num));
//        //Condition              equal(T value)
//        num.eq(num);
//        //Condition              equalAll(Field<T[]> array)
//
//        //Condition              equalAll(Select<?> query)
//        //num.eqAll(sub.select(num));
//        //Condition              equalAll(T... array)
//
//        //Condition              equalAny(Field<T[]> array) -> in
//
//        //Condition              equalAny(Select<?> query) -> in
//        //num.eqAny(sub.select(num));
//        //Condition              equalAny(T... array) -> in
//
//        //Condition              equalIgnoreCase(Field<String> value)
//        str.equalsIgnoreCase(str);
//        //Condition              equalIgnoreCase(String value)
//        str.equalsIgnoreCase("abc");
//        //Field<BigDecimal>      exp()
//        MathExpressions.exp(num);
//        //Field<Integer>         extract(DatePart datePart)
//        date.month(); // ...
//        //WindowIgnoreNullsStep<T>       firstValue()
//
//        //Field<T>               floor()
//        num.floor();
//        //String                 getName()
//
//        //Class<? extends T>     getType()
//        num.getType();
//        //Condition              greaterOrEqual(Field<T> field)
//        num.goe(num);
//        //Condition              greaterOrEqual(Select<?> query)
//        //num.goe(sub.unique(num));
//        //Condition              greaterOrEqual(T value)
//        num.goe(1);
//        //Condition              greaterOrEqualAll(Field<T[]> array)
//
//        //Condition              greaterOrEqualAll(Select<?> query)
//        //num.goeAll(sub.select(num));
//        //Condition              greaterOrEqualAll(T... array)
//
//        //Condition              greaterOrEqualAny(Field<T[]> array)
//
//        //Condition              greaterOrEqualAny(Select<?> query)
//        //num.goeAny(sub.select(num));
//        //Condition              greaterOrEqualAny(T... array)
//
//        //Condition              greaterThan(Field<T> field)
//        num.gt(num);
//        //Condition              greaterThan(Select<?> query)
//        //num.gt(sub.select(num));
//        //Condition              greaterThan(T value)
//        num.gt(1);
//        //Condition              greaterThanAll(Field<T[]> array)
//
//        //Condition              greaterThanAll(Select<?> query)
//        //num.gtAll(sub.select(num));
//        //Condition              greaterThanAll(T... array)
//
//        //Condition              greaterThanAny(Field<T[]> array)
//
//        //Condition              greaterThanAny(Select<?> query)
//        //num.gtAny(sub.select(num));
//        //Condition              greaterThanAny(T... array)
//
//        //Field<T>               greatest(Field<?>... others)
//        MathExpressions.max(num, num);
//        //Field<T>               greatest(T... others)
//
//        //Condition              in(Collection<T> values)
//        num.in(Arrays.asList(1,2,3));
//        //Condition              in(Field<?>... values)
//
//        //Condition              in(Select<?> query)
//        //num.in(sub.select(num));
//        //Condition              in(T... values)
//        num.in(1,2,3);
//        //Condition              isFalse()
//
//        //Condition              isNotNull()
//        num.isNotNull();
//        //Condition              isNull()
//        num.isNull();
//        //boolean                isNullLiteral()
//
//        //Condition              isTrue()
//
//        MathExpressions.min(num, num);
//        //Field<T>               least(T... others)
//
//        //Field<Integer>         length()
//        str.length();
//        //Condition              lessOrEqual(Field<T> field)
//        num.loe(num);
//        //Condition              lessOrEqual(Select<?> query)
//        //num.loe(sub.unique(num));
//        //Condition              lessOrEqual(T value)
//        num.loe(1);
//        //Condition              lessOrEqualAll(Field<T[]> array)
//
//        //Condition              lessOrEqualAll(Select<?> query)
//        //num.loeAll(sub.select(num));
//        //Condition              lessOrEqualAll(T... array)
//
//        //Condition              lessOrEqualAny(Field<T[]> array)
//
//        //Condition              lessOrEqualAny(Select<?> query)
//        //num.loeAny(sub.select(num));
//        //Condition              lessOrEqualAny(T... array)
//
//        //Condition              lessThan(Field<T> field)
//        num.lt(num);
//        //Condition              lessThan(Select<?> query)
//        //num.lt(sub.select(num));
//        //Condition              lessThan(T value)
//        num.lt(1);
//        //Condition              lessThanAll(Field<T[]> array)
//
//        //Condition              lessThanAll(Select<?> query)
//        //num.ltAll(sub.select(num));
//        //Condition              lessThanAll(T... array)
//
//        //Condition              lessThanAny(Field<T[]> array)
//
//        //Condition              lessThanAny(Select<?> query)
//        //num.ltAny(sub.select(num));
//        //Condition              lessThanAny(T... array)
//
//        //Condition              like(Field<String> value)
//        str.like(str);
//        //Condition              like(Field<String> value, char escape)
//        str.like(str, '!');
//        //Condition              like(String value)
//        str.like("a%");
//        //Condition              like(String value, char escape)
//        str.like("a%", '!');
//        //Field<String>          lower()
//        str.lower();
//        //Field<String>          lpad(Field<? extends Number> length)
//        StringExpressions.lpad(str, num);
//        //Field<String>          lpad(Field<? extends Number> length, Field<String> character)
//        StringExpressions.lpad(str, num, '!');
//        //Field<String>          lpad(int length)
//        StringExpressions.lpad(str, 10);
//        //Field<String>          lpad(int length, char character)
//        StringExpressions.lpad(str, 10, '!');
//        //Field<String>          ltrim()
//        StringExpressions.ltrim(str);
//        //Field<T>               max()
//        num.max();
//        //WindowPartitionByStep<T>       maxOver()
//
//        //Field<BigDecimal>      median()
//
//        //Field<T>               min()
//        num.min();
//        //WindowPartitionByStep<T>       minOver()
//
//        //Field<T>               mod(Field<? extends Number> value)
//        num.mod(num);
//        //Field<T>               mod(Number value)
//        num.mod(10);
//        //Field<T>               mul(Field<? extends Number> value)
//        num.multiply(num);
//        //Field<T>               mul(Number value)
//        num.multiply(2);
//        //Field<T>               neg()
//        num.negate();
//        //Condition              notBetween(Field<T> minValue, Field<T> maxValue)
//        num.notBetween(num, num);
//        //Condition              notBetween(T minValue, T maxValue)
//        num.notBetween(1,10);
//        //Condition              notEqual(Field<T> field)
//        num.ne(num);
//        //Condition              notEqual(Select<?> query)
//        //num.ne(sub.select(num));
//        //Condition              notEqual(T value)
//        num.ne(1);
//        //Condition              notEqualAll(Field<T[]> array)
//
//        //Condition              notEqualAll(Select<?> query)
//        //num.neAll(sub.select(num));
//        //Condition              notEqualAll(T... array)
//
//        //Condition              notEqualAny(Field<T[]> array) -> notIn
//
//        //Condition              notEqualAny(Select<?> query) -> notIn
//        //num.neAny(sub.select(num));
//        //Condition              notEqualAny(T... array) -> notIn
//
//        //Condition              notEqualIgnoreCase(Field<String> value)
//        str.notEqualsIgnoreCase(str);
//        //Condition              notEqualIgnoreCase(String value)
//        str.notEqualsIgnoreCase("abc");
//        //Condition              notIn(Collection<T> values)
//        num.notIn(Arrays.asList(1,2,3));
//        //Condition              notIn(Field<?>... values)
//
//        //Condition              notIn(Select<?> query)
//        //num.notIn(sub.select(num));
//        //Condition              notIn(T... values)
//        num.notIn(1, 2, 3);
//        //Condition              notLike(Field<String> value)
//        str.notLike(str);
//        //Condition              notLike(Field<String> value, char escape)
//        str.notLike(str, '!');
//        //Condition              notLike(String value)
//        str.notLike("a%");
//        //Condition              notLike(String value, char escape)
//        str.notLike("a%",'!');
//        //Field<T>               nullif(Field<T> other)
//
//        //Field<T>               nullif(T other)
//
//        //Field<T>               nvl(Field<T> defaultValue)
//        str.coalesce(str);
//        //Field<T>               nvl(T defaultValue)
//        str.coalesce("abc");
//        //<Z> Field<Z>           nvl2(Field<Z> valueIfNotNull, Field<Z> valueIfNull)
//
//        //<Z> Field<Z>           nvl2(Z valueIfNotNull, Z valueIfNull)
//
//        //Field<Integer>         octetLength()
//
//        //Field<Integer>         position(Field<String> search)
//        str.locate(str);
//        //Field<Integer>         position(String search)
//        str.locate("a");
//        //Field<String>          repeat(Field<? extends Number> count)
//        //Field<String>          repeat(Number count)
//
//        //Field<String>          replace(Field<String> search)
//
//        //Field<String>          replace(Field<String> search, Field<String> replace)
//
//        //Field<String>          replace(String search)
//
//        //Field<String>          replace(String search, String replace)
//
//        //Field<T>               round()
//        num.round();
//        //Field<T>               round(int decimals)
//
//        //Field<String>          rpad(Field<? extends Number> length)
//        StringExpressions.rpad(str, num);
//        //Field<String>          rpad(Field<? extends Number> length, Field<String> character)
//        StringExpressions.rpad(str, num, '!');
//        //Field<String>          rpad(int length)
//        StringExpressions.rpad(str, 10);
//        //Field<String>          rpad(int length, char character)
//        StringExpressions.rpad(str, 10, '!');
//        //Field<String>          rtrim()
//        StringExpressions.rtrim(str);
//        // shl (bitwise shift left)
//
//        // shr (bitwise shift right)
//
//        //<Z> SortField<Z>       sort(Map<T,Z> sortMap)
//
//        //SortField<Integer>     sortAsc(Collection<T> sortList)
//
//        //SortField<Integer>     sortAsc(T... sortList)
//
//        //SortField<Integer>     sortDesc(Collection<T> sortList)
//
//        //SortField<Integer>     sortDesc(T... sortList)
//
//        //Condition              startsWith(Field<T> value)
//        str.startsWith(str);
//        //Condition              startsWith(T value)
//        str.startsWith("a");
//        //Field<BigDecimal>      stddevPop()
//
//        //Field<T>               sub(Field<?> value)
//        num.subtract(num);
//        //Field<T>               sub(Number value)
//        num.subtract(1);
//        //Field<String>          substring(Field<? extends Number> startingPosition)
//        str.substring(num);
//        //Field<String>          substring(Field<? extends Number> startingPosition, Field<? extends Number> length)
//        str.substring(num, num);
//        //Field<String>          substring(int startingPosition)
//        str.substring(1);
//        //Field<String>          substring(int startingPosition, int length)
//        str.substring(1, 3);
//        //Field<BigDecimal>      sum()
//        num.sum();
//        //WindowPartitionByStep<BigDecimal>      sumOver()
//
//        //Field<String>          trim()
//        str.trim();
//        //Field<String>          upper()
//        str.upper();
//        //Field<BigDecimal>      varPop()
//
//    }
//    
//    
//    @Test
//    public void precedence() {
//        // postfix    expr++ expr--
//        // unary    ++expr --expr +expr -expr ~ !
//        // multiplicative    * / %
//        // additive    + -
//        // shift    << >> >>>
//        // relational    < > <= >= instanceof
//        // equality    == !=
//        // bitwise AND    &
//        // bitwise exclusive OR    ^
//        // bitwise inclusive OR    |
//        // logical AND    &&
//        // logical OR    ||
//        // ternary    ? :
//        // assignment    = += -= *= /= %= &= ^= |= <<= >>= >>>=
//
//        int p1 = getPrecedence(Ops.NOT);
//        int p2 = getPrecedence(Ops.MULT, Ops.DIV, Ops.MOD);
//        int p3 = getPrecedence(Ops.ADD, Ops.SUB);
//        int p4 = getPrecedence(Ops.LT, Ops.GT, Ops.GOE, Ops.LOE, Ops.BETWEEN, Ops.INSTANCE_OF);
//        int p5 = getPrecedence(Ops.EQ, Ops.NE);
//        int p6 = getPrecedence(Ops.AND);
//        int p7 = getPrecedence(Ops.OR);
//
//        assertTrue(p1 < p2);
//        assertTrue(p2 < p3);
//        assertTrue(p3 < p4);
//        assertTrue(p4 < p5);
//        assertTrue(p5 < p6);
//        assertTrue(p6 < p7);
//    }
//
//    @Test
//    public void generic_precedence() {
//        TemplatesTestUtils.testPrecedence(JavaTemplates.DEFAULT);
//    }
//
//    protected int getPrecedence(Operator... ops) {
//        int precedence = templates.getPrecedence(ops[0]);
//        for (int i = 1; i < ops.length; i++) {
//            assertEquals(ops[i].name(), precedence, templates.getPrecedence(ops[i]));
//        }
//        return precedence;
//    }
//    
//    
//}
