package elte.edu.samples;

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

import elte.sportStore.model.RequestData;
import com.querydsl.collections.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;

import com.querydsl.core.DefaultQueryMetadata;
import com.querydsl.core.Tuple;
import com.querydsl.core.alias.Alias;
import com.querydsl.core.types.Expression;
import elte.sportStore.model.QRequestData;
import java.util.Optional;

public abstract class AbstractQueryTest {

//    protected final Cat c1 = new Cat("Kitty");
//
//    protected final Cat c2 = new Cat("Bob");
//
//    protected final Cat c3 = new Cat("Alex");
//
//    protected final Cat c4 = new Cat("Francis");
//
//    protected final QCat cat = new QCat("cat");
//
//    protected final QCat kitten = new QCat("kitten");
//
//    protected final QCat offspr = new QCat("offspr");
//
//    protected final QCat otherCat = new QCat("otherCat");
//
//    protected final QCat mate = new QCat("mate");
//
//    protected List<Cat> cats = Arrays.asList(c1, c2, c3, c4);
//
    protected List<Integer> ints = new ArrayList<Integer>();

    protected List<Integer> myInts = new ArrayList<Integer>();

    protected TestQuery<?> last;
    
    //=========================================
    protected final RequestData taz    = new RequestData("111", "xavier");
    protected final RequestData bugs   = new RequestData("222", "bugs");
    protected final RequestData daffy  = new RequestData("333", "daffy");
    protected final RequestData elmer  = new RequestData("444", "elmer");
    
    protected final QRequestData rq = new QRequestData("reqData1");
    protected final QRequestData rq2 = new QRequestData("reqData2");
    protected final QRequestData reqData3 = new QRequestData("reqData3");
    protected final QRequestData reqData4 = new QRequestData("reqData4");
    

    @Before
    public void setUp() {
        myInts.addAll(Arrays.asList(1, 2, 3, 4));
        Alias.resetAlias();
    }
//
//    protected List<Cat> cats(int size) {
//        List<Cat> cats = new ArrayList<Cat>(size);
//        for (int i = 0; i < size / 2; i++) {
//            cats.add(new Cat("Kate" + (i + 1)));
//            cats.add(new Cat("Bob" + (i + 1)));
//        }
//        return cats;
//    }
//
    protected TestQuery<?> query() {
        last = new TestQuery<Void>();
        return last;
    }

    static class TestQuery<T> extends AbstractCollQuery<T, TestQuery<T>> {

        List<Object> res = new ArrayList<Object>();

        public TestQuery() {
            super(new DefaultQueryMetadata(), DefaultQueryEngine.getDefault());
        }

        @Override
        public List<T> fetch() {
            List<T> rv = super.fetch();
            for (T o : rv) {
                res.add(o);
            }
            return rv;
        }

        @Override
        public <U> TestQuery<U> select(Expression<U> expr) {
            queryMixin.setProjection(expr);
            @SuppressWarnings("unchecked") // This is the new projection's type
            TestQuery<U> newType = (TestQuery<U>) queryMixin.getSelf();
            return newType;
        }

        @Override
        public TestQuery<Tuple> select(Expression<?>... exprs) {
            queryMixin.setProjection(exprs);
            @SuppressWarnings("unchecked") // This is the new projection's type
            TestQuery<Tuple> newType = (TestQuery<Tuple>) queryMixin.getSelf();
            return newType;
        }
    }
}
