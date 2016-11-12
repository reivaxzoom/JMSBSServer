/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elte.jmsSelExpr;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import elte.sportStore.model.QRequestData;
import edu.elte.query.JMSSelectorSerializer;
import edu.elte.query.JMSTemplates;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//import static QRequestData.*;

/**
 *
 * @author Xavier
 */
class AbstractExpressionTest {
    
     public static final String alias = "rd";
    protected static final QRequestData rd = new QRequestData(alias);
    protected static final QRequestData rd1 = new QRequestData("rd1");
    
//    category.eq("aa").and(budget.between(1,100))
     protected BooleanExpression sp = rd.category.eq("sport");
     protected BooleanExpression sh = rd.category.eq("shoes");
     protected BooleanExpression ts = rd.category.eq("test");
     
     List<String> countries = Arrays.asList("Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia", "Slovenia", "Spain", "Sweden", "United Kingdom");
     List str1=countries.subList(0, 5).stream().map(x -> "'"+x+"'" ).collect(Collectors.toList());
    
     
      protected String serialize(Expression exp) {
        JMSSelectorSerializer serializer = new JMSSelectorSerializer(JMSTemplates.DEFAULT);
        serializer.handle(exp);
        return serializer.toString();
    }
     
}
