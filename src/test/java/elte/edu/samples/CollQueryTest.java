/*
 * Copyright 2015, The Querydsl Team (http://www.querydsl.com/team)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package elte.edu.samples;

import elte.sportStore.model.RequestData;
import com.querydsl.collections.*;
import static com.querydsl.collections.CollQueryFactory.from;
import com.querydsl.core.Query;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.querydsl.core.Tuple;
import static com.querydsl.core.alias.Alias.var;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
//import com.mysema.query.sql.SQLTemplates;

public class CollQueryTest extends AbstractQueryTest {

    @Test
    public void sample(){
        List<RequestData> characters =Arrays.asList(taz,bugs,daffy,elmer);
        AbstractCollQuery q=query().from(rq, characters).where(rq.address.eq("tazmania"));
        
    }
    
    
    
    
    
    /**
     * Testing basic queries: where, and  
     */

    @Test
    public void catTest2() {
        
        taz.setCountry("Australia");
        taz.setAddress("tazmania");
        taz.setComments("Acme products");
        taz.setPrio("High");
        
        bugs.setCountry("Hungary");
        bugs.setAddress("forest");
        bugs.setComments("only carrots");
        bugs.setPrio("High");
                
        
        daffy.setCountry("USA");
        daffy.setAddress("north forest");
        daffy.setComments("bank note machines");
        daffy.setPrio("High");
        
        elmer.setCountry("Hungary");
        elmer.setAddress("city");
        elmer.setComments("hunting material");
        elmer.setPrio("Low");
        
        
        List<RequestData> characters =Arrays.asList(taz,bugs,daffy,elmer);
        
        
        assertEquals(Arrays.asList(taz),
                query().from(rq, characters).where(rq.address.eq("tazmania")).fetch());
        assertEquals(Arrays.asList(bugs),
                query().from(rq, characters).where(rq.comments.eq("only carrots").and(rq.address.eq("forest"))).fetch());
        
        assertEquals(Arrays.asList(bugs,daffy,elmer),
                query().from(rq, characters).where(rq.country.in("Hungary","Ecuador","USA")).fetch());
        
        
        assertEquals(Arrays.asList("xavier","bugs","daffy"),
                query().from(rq, characters).where(rq.prio.matches("Hi.*")).select(rq.name).fetch());
        
        assertEquals(Arrays.asList("xavier","bugs","daffy"),
        query().from(rq, characters).where(rq.name.ne(elmer.getName())).select(rq.name).fetch());
        
      
        
        
//        assertEquals(Arrays.asList({"xavier","tazmania"},{"bugs","forest"},{"daffy","north forest"}),
//        query().from(rq, characters).where(rq.name.ne(elmer.getName())).select(rq.name,rq.address).fetch());
        
        
        
    }
    
    
    

    
       @Test
    public void various4() {
        assertEquals(Arrays.asList(1, 2, "aaaa", 3),
                from(var(), 1, 2, 5, "aaaa", 3).where(var().ne(5)).select(var()).fetch());
    }
    
    
       @Test
    public void various6() {
        assertEquals(Arrays.asList(1, 2, "aaaa", 3,"eeeeeee"),
                from(var(), 1, 2, 5, "aaaa", 3,"bcse","eeeeeee").where(var().ne(5).and(var().ne("bcse"))).select(var()).fetch());
    }
    
    @Test
    public void various5() {
        NumberPath<Integer> num = Expressions.numberPath(Integer.class, "num");
        assertEquals(Arrays.asList(1, 2, 3),
                from(num, 1, 2, 3, 4).where(num.loe(3)).select(num).fetch());
    }


//    @Test
//    public void after_and_before() throws ParseException {
////        query().from(cat, Arrays.asList(c1, c2))
////            .where(
////                cat.birthdate.lt(new Date()),
////                cat.birthdate.loe(new Date()),
////                cat.birthdate.gt(new Date()),
////                cat.birthdate.goe(new Date()))
////            .select(cat).fetch();
//
//              
//        String string = "January 2, 2010";
//        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
//        Date date = format.parse(string);
//        
////        Calendar start = GregorianCalendar.getInstance();
////        Calendar end = GregorianCalendar.getInstance();
////        start.add(Calendar.YEAR, 5);
////        Date startDate =  start.getTime();
////        end.roll(Calendar.YEAR, 10);
////        Date endDate = end.getTime();
//        
//         
//         
//         
////         System.out.println(startDate);
////         System.out.println(endDate);
////        c1.setBirthdate(startDate );
////        c2.setBirthdate(new Date());
//        
//        c1.setBirthdate(date);
//        query().from(cat, Arrays.asList(c1))
//            .where(
////                cat.birthdate.gt(startDate)
////                cat.birthdate.lt(endDate),
//                  cat.birthdate.gt(Calendar.getInstance().getTime())
//            )
//            .select(cat).fetch();
//        
//    }
//

//    @Test
//    public void cast() {
//        NumberExpression<?> num = cat.id;
//        Expression<?>[] expr = new Expression[] {num.byteValue(), num.doubleValue(),
//                num.floatValue(), num.intValue(), num.longValue(),
//                num.shortValue(), num.stringValue()};
//
//        for (Expression<?> e : expr) {
//            query().from(cat, Arrays.asList(c1, c2)).select(e).fetch();
//        }
//
//    }
//
//    @Test
//    public void primitives() {
//        // select cats with kittens
//        query().from(cat, cats).where(cat.kittens.size().ne(0)).select(cat.name).fetch();
//        assertTrue(last.res.size() == 4);
//
//        // select cats without kittens
//        query().from(cat, cats).where(cat.kittens.size().eq(0)).select(cat.name).fetch();
//        assertTrue(last.res.size() == 0);
//    }
//    
    
    @Test public void Various1(){
        StringPath str=Expressions.stringPath("str");
        List<String > listStr= from(str,"a","ab","cd","de").where(str.startsWith("a")).fetch();
        for (  String s : listStr ) {
        System.out.println(s);
        assertTrue(s.equals("a") || s.equals("ab"));
  }
}
    
    @Test
    public void various() {
        StringPath a = Expressions.stringPath("a");
        StringPath b = Expressions.stringPath("b");
        List<Tuple> listResult = from(a, "aa", "bb", "cc","cc","dd","ee","ff")
                .from(b, Arrays.asList("a","b","c"))
                .where(a.startsWith(b)).select(a, b).fetch();
                
        System.out.println(listResult); 
        for (Tuple strs : listResult) {
            System.out.println(strs.get(a)+"  "+strs.get(b) + strs.get(b));
            assertEquals(strs.get(a), strs.get(b) + strs.get(b));
        }
        
//        List<Cat> catsOut ;
//        
//        query().from(cat, cats).select(cat.mate).fetch();
//
//        query().from(cat, cats).select(cat.kittens).fetch();
//
//        catsOut = query().from(cat, cats).where(cat.kittens.isNotEmpty()).select(cat).fetch();
//        System.out.println(catsOut);
//
//        query().from(cat, cats).where(cat.kittens.isEmpty()).select(cat).fetch();
//
//        List<String> catsOut1=query().from(cat, cats).where(cat.name.matches("Bo.*")).select(cat.name).fetch();
//        System.out.println(catsOut1);
    }
    
//    @Test
//    public void simpleCases() {
//        // select all cat names
//        query().from(cat, cats).select(cat.name).fetch();
//        assertTrue(last.res.size() == 4);
//
//        // select all kittens
//        query().from(cat, cats).select(cat.kittens).fetch();
//        assertTrue(last.res.size() == 4);
//
//        // select cats with kittens
//        query().from(cat, cats).where(cat.kittens.size().gt(0)).select(cat.name).fetch();
//        assertTrue(last.res.size() == 4);
//
//        // select cats named Kitty
//        query().from(cat, cats).where(cat.name.eq("Kitty")).select(cat.name).fetch();
//        assertTrue(last.res.size() == 1);
//
//        // select cats named Kitt%
//        query().from(cat, cats).where(cat.name.matches("Kitt.*")).select(cat.name).fetch();
//        assertTrue(last.res.size() == 1);
//
//        query().from(cat, cats).select(cat.bodyWeight.add(cat.weight)).fetch();
//    }


//
//    @Test
//    public void bigDecimals() {
//        NumberPath<BigDecimal> a = Expressions.numberPath(BigDecimal.class, "cost");
//        List<BigDecimal> nums = from(a, new BigDecimal("2.1"), new BigDecimal("20.21"),
//                new BigDecimal("44.4")).where(a.lt(new BigDecimal("35.1"))).select(a).fetch();
//        assertEquals(Arrays.asList(new BigDecimal("2.1"), new BigDecimal("20.21")), nums);
//    }
//
//    @Test(expected = UnsupportedOperationException.class)
//    public void groupBy() {
//        query().from(cat, cats).groupBy(cat.name);
//    }
//
//    @Test(expected = UnsupportedOperationException.class)
//    public void having() {
//        query().from(cat, cats).having(cat.name.isNull());
//    }
    
//    @Test
//    public void catTest1() {
//        assertEquals(Arrays.asList(c1,c2),
//                query().from(cat, Arrays.asList(c1,c2)).where(cat.name.isNotEmpty()).fetch());
//        
//        c1.setColor(Color.BLACK);
//        c2.setColor(Color.BLACK);
//        c3.setColor(Color.BLACK);
//        
//        c1.setEyecolor(Color.TABBY);
//        c2.setEyecolor(Color.TABBY);
//        c3.setEyecolor(Color.TABBY);
//        
//        c1.setAlive(true);
//        c2.setAlive(true);
//        c3.setAlive(true);
//        
//        assertEquals(Arrays.asList(c1,c2,c3),
//                query().from(cat, Arrays.asList(c1,c2,c3)).where(cat.color.eq(Color.BLACK).and(cat.alive.eq(Boolean.TRUE)).and(cat.eyecolor.eq(Color.TABBY))).fetch());
//
//        assertEquals(Arrays.asList(c1,c2,c3),
//                query().from(cat, Arrays.asList(c1,c2,c3)).where(cat.color.eq(Color.BLACK).and(cat.alive.eq(Boolean.TRUE)).and(cat.eyecolor.eq(Color.TABBY))).fetch());
//    
//    
//        assertEquals(Arrays.asList(c1,c2,c3),
//                query().from(cat, Arrays.asList(c1,c2,c3)).where(cat.color.eq(Color.BLACK), cat.alive.eq(Boolean.TRUE).and(cat.eyecolor.eq(Color.TABBY))).fetch());
//    
//    
//    }    
    

}
