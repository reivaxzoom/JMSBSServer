package edu.elte.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import elte.sportStore.model.QRequestData;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringExpression;

/**
 *
 * @author Xavier
 */
public class QueryGenerator {
        public static final String alias="rd";
        private static final QRequestData rd = new QRequestData(alias);
    
        
    public static void main(String[] args) {
        QueryGenerator qg= new QueryGenerator();
        Expression addidas=rd.category.eq("sport").or(rd.category.eq("shoes")).or(rd.category.eq("test"));
        System.out.println(qg.genQuery(addidas));
        Expression bestBuy=rd.category.eq("sport").or(rd.category.eq("shoes")).or(rd.category.eq("test"));
        System.out.println(qg.genQuery(bestBuy));
        Expression supermarket=rd.category.eq("sport").or(rd.category.eq("shoes")).or(rd.category.eq("test"));
        System.out.println(qg.genQuery(supermarket));
        Expression wallmart=rd.category.eq("sport").or(rd.category.eq("shoes")).or(rd.category.eq("test"));
        System.out.println(qg.genQuery(wallmart));
        
        
         BooleanExpression sp = rd.category.eq("sport");
         BooleanExpression sh = rd.category.eq("shoes");
         BooleanExpression ts = rd.category.eq("test");
         BooleanBuilder addidas1 = new BooleanBuilder();
        Expression wallmart1= addidas1.or(sp.or(sh).or(ts));
        System.out.println(qg.genQuery(wallmart1));
        
        Expression wallmart2= Expressions.anyOf(sp,sh,ts);
        System.out.println(qg.genQuery(wallmart2));

        
        List<String> countries = Arrays.asList("Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia", "Slovenia", "Spain", "Sweden", "United Kingdom");
        List str1=countries.subList(0, 5).stream().map(x -> "'"+x+"'" ).collect(Collectors.toList());
        //in
        Expression in=rd.country.in(str1);
        System.out.println(qg.genQuery(in));
        Expression nin=rd.country.notIn(str1);
        System.out.println(qg.genQuery(nin));
        //like
        Expression like=rd.country.like("Belgium");
        System.out.println(qg.genQuery(like));
        Expression nlike=rd.country.notLike("Croatia");
        System.out.println(qg.genQuery(nlike));
        //between
        Expression bt=rd.budget.between(100,200);
        System.out.println(qg.genQuery(bt));
        Expression nbt=rd.budget.notBetween(500,600);
        System.out.println(qg.genQuery(nbt));
        // is null
        Expression nul=rd.comments.isNull();
        System.out.println(qg.genQuery(nul));
        Expression nnul=rd.comments.isNotNull();
        System.out.println(qg.genQuery(nnul));
        //comparators lt gt
        Expression gt=rd.budget.gt(100);
        System.out.println(qg.genQuery(gt));
        Expression lt=rd.budget.lt(50);
        System.out.println(qg.genQuery(lt));
        //aritmetic operations
        Expression add=rd.budget.add(5);
        System.out.println(qg.genQuery(add));
        Expression sus=rd.budget.subtract(2);
        System.out.println(qg.genQuery(sus));
        Expression times=rd.budget.multiply(5);
        System.out.println(qg.genQuery(times));
        Expression div=rd.budget.divide(2);
        System.out.println(qg.genQuery(div));
        
//        mix sample releaseYear * 2 > 2000 - 20
         NumberExpression<Integer> n1 = Expressions.numberPath(Integer.class, "100");
         NumberExpression<Integer> n2 = Expressions.numberPath(Integer.class, "20");
         
         
        Expression mix=rd.budget.multiply(2).gt(n2.subtract(n1));
//        Expression mix=n2.subtract(n1);
        System.out.println(qg.genQuery(mix));
        System.out.println(mix.toString());
        
        StringExpression a = Expressions.stringPath("a");
        StringExpression b = Expressions.stringPath("b");
        Expression<?> expr = a.append(b);
        System.out.println(expr.toString());
        System.out.println(qg.genQuery(expr));
        
        //start end with
        Expression stw=rd.category.startsWith("sp");
        System.out.println(qg.genQuery(stw));
//        System.out.println(stw);
//        Expression en d=rd.category.endsWith("rt");
//        System.out.println(qg.genQuery(end));
        
        
//        assertEquals("(a + b).toLowerCase()", str);
        
        
    }
    
    
    
      protected static String serialize(Expression exp) {
        JMSSelectorSerializer serializer = new JMSSelectorSerializer(JMSTemplates.DEFAULT);
        serializer.handle(exp);
        return serializer.toString();
    }
    
    
    
    
     private String genQuery(Expression exp){
//         CollQuerySerializer serializer = new CollQuerySerializer(CollQueryTemplates.DEFAULT);
        JMSSelectorSerializer serializer = new JMSSelectorSerializer(JMSTemplates.DEFAULT);
         serializer.handle(exp);
        return serializer.toString() ;
//         return  exp.accept(ToStringVisitor.DEFAULT, JMSTemplates.DEFAULT).toString().replace(alias+".", "");
//         return  String.valueOf(exp.accept(ToStringVisitor.DEFAULT, JMSTemplates.DEFAULT));
     }
     
     
     
     
//     private final BooleanExpression a = new BooleanPath("a");
//    private final BooleanExpression b = new BooleanPath("b");
//    private final BooleanExpression c = new BooleanPath("c");
//     private NumberExpression<Integer> num;
//    private DateExpression<Date> date;

//    private void s1(){
//          num = Expressions.numberPath(Integer.class, "num");
//        str = Expressions.stringPath("str");
//        date = Expressions.datePath(Date.class, "date");
//        QueryMixin<?> query = new QueryMixin<Void>();
//        System.out.println(query.from(num, str));
//        
//        
////            QueryMixin<?> query = new QueryMixin<Void>();
//            query.from(rd);
//            query.orderBy(rd.category.asc());
//            query.setProjection(rd.id);
// 
//            serialize(query.getMetadata(), JMSTemplates.DEFAULT);
//            
//            System.out.println(templates.getClass().getSimpleName());
//            System.out.println();
//     }
    
}
