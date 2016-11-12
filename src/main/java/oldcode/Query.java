package oldcode;

import com.querydsl.collections.CollQueryFactory;
import static com.querydsl.collections.CollQueryFactory.from;
import com.querydsl.core.Tuple;
import static com.querydsl.core.alias.Alias.alias;
import static com.querydsl.core.alias.Alias.var;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import edu.elte.client.QRequestData;
import edu.elte.client.RequestData;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

public class Query extends Thread{
	
private BlockingQueue<RequestData> queue;
QRequestData rq = new QRequestData("reqData1");
//protected abstract AbstractCollQuery<?,?> query();
    
    public Query(BlockingQueue<RequestData> queue) {
        this.queue = queue;
    }
    
    @Override
    public void run() {
//            simpleFrom();
//            simpleWhereAnd();
//            simpleRegex();
//            simpleTuple();
            simpleCounting();
        
        
//                   
//                    
//                     Map<String, Long> result =
//                queue.stream().collect(
//                        Collectors.groupingBy(
//                                Function.identity(), Collectors.counting()
//                        )
//                );
                    
        NumberPath<Integer> num = Expressions.numberPath(Integer.class, "num");
                List<Integer> qts=queue.stream().map(RequestData::getItemNumber).collect(Collectors.toList());
                from(num,qts).where(num.loe(3)).select(num).fetch().stream().forEach(System.out::println);
                
                
//                Tuple tuple = new ArrayTuple
//                from(queue).
                
//            List<Tuple> result = from(queue).list(employee.firstName, employee.lastName);
//for (Tuple row : result) {
//     System.out.println("firstName " + row.get(employee.firstName));
//     System.out.println("lastName " + row.get(employee.lastName)); 
//}
    
    
//    }     
            
            
    }
    
    
    public void Query() {
        RequestData rq1 = alias(RequestData.class);
//        CollQueryFactory.from($(rq1), Arrays.asList(new RequestData()))
//                .where($(rq1.getId()).isNull())
//                .select($(rq1)).fetch();
//        assertTrue(CollQueryFactory.from($(rq1), Arrays.asList(new RequestData()))
//                .where($(rq1.getId()).isNotNull())
//                .list($(rq1)).isEmpty());
    }
    
    
    public void entityQueries2() {
         NumberPath<Integer> intVar1 = Expressions.numberPath(Integer.class, "var1");
          List<Integer> list1 = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        CollQueryFactory.from(intVar1, list1).fetch();
        
//          CollQueryFactory.from(this, queue);
        
        RequestData rd = new RequestData("mate");
        QRequestData qrq = QRequestData.requestData;

//        List<RequestData> cats = query().from(rd)
//                .innerJoin(rd).on(rd.getCountry().eq(true))
//                .where(cat.dtype.eq("C"), rd.dtype.eq("C"))
//                .select(qrq).fetch();
//        assertTrue(cats.isEmpty());
    }
    

    private void simpleCounting() {
        //            List<ClientRequest> listItems = queue.stream().map(e -> (RequestData)e.)
        Map<String, Long> counting = queue.stream().collect(
                Collectors.groupingBy(RequestData::getCountry, Collectors.counting()));
    }
    
    public void various4() {
                from(var(), 1, 2, 5, "aaaa", 3).where(var().ne(5)).select(var()).fetch();
    }
    
    
    public void various6() {
                from(var(), 1, 2, 5, "aaaa", 3,"bcse","eeeeeee").where(var().ne(5).and(var().ne("bcse"))).select(var()).fetch();
    }
    
    public void various5() {
        NumberPath<Integer> num = Expressions.numberPath(Integer.class, "num");
                from(num, 1, 2, 3, 4).where(num.loe(3)).select(num).fetch();
    }
    
    

    private void simpleTuple() {
        List<Tuple> tuple=from(rq, queue).where(rq.country.ne("Hungary")).select(rq.id, rq.category).fetch();
        tuple.stream().forEach(System.out::println);
    }

    private void simpleRegex() {
        List<String> res= from(rq, queue).where(rq.prio.matches("Hi.*")).select(rq.name).fetch();
        res.stream().forEach(System.out::println);
    }

    private void simpleWhereAnd() {
        List<RequestData> res= from(rq, queue).where(rq.country.eq("Hungary").and(rq.category.eq("sport")) ).fetch();
        res.stream().forEach(System.out::println);
    }

    private void simpleFrom() {
        List<RequestData> res= from(rq, queue).fetch();
        res.stream().forEach(System.out::println);
    }
}